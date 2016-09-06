package messenger.controller;

import com.lordofthejars.nosqlunit.annotation.CustomComparisonStrategy;
import com.lordofthejars.nosqlunit.annotation.IgnorePropertyValue;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.lordofthejars.nosqlunit.mongodb.MongoFlexibleComparisonStrategy;
import io.restassured.http.ContentType;
import messenger.Application;
import messenger.model.MessageModel;
import messenger.repository.MessageRepository;
import messenger.service.MailService;
import messenger.service.MessageService;
import messenger.service.MessageServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbConfigurationBuilder.mongoDb;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@CustomComparisonStrategy(comparisonStrategy = MongoFlexibleComparisonStrategy.class)
@IgnorePropertyValue(properties = {"time"})
public class MessageControllerIntegrationTest {

    @Rule
    public MongoDbRule remoteMongoDbRule = new MongoDbRule(mongoDb().databaseName("test").build());

//    @Autowired
//    private MongoOperations mongoOperations;

    @Autowired
    private MessageRepository messageRepository;

    private MessageService messageService;

    @Before
    public void setUp() throws Exception {
        MailService mockMailService = mock(MailService.class);
        messageService = new MessageServiceImpl(mockMailService, messageRepository);
    }

    @Test
    @UsingDataSet(locations = "/message-initial.json")
    @ShouldMatchDataSet(location = "/message-expected.json")
    public void send() throws Exception {
        // given
        MessageModel message = new MessageModel("subject", "message text");
        MessageController controller = new MessageController(messageService);
//        Date now = new Date();

        // @formatter:off
        given()
                .standaloneSetup(controller)
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
                .body(message)
                .queryParam("email", "user@email.com")
        .when()
                .post("/messages/{id}/send", 1)
        .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("id", is("1"))
                .body("subject", is("subject"))
                .body("message", is("message text"));
        // @formatter:on

        // then
//        Query query = Query.query(Criteria.where("_id").is("1"));
//        Message actualMessage = mongoOperations.findOne(query, Message.class);
//
//        assertThat(actualMessage, notNullValue());
//        assertThat(actualMessage.getId(), is("1"));
//        assertThat(actualMessage.getSubject(), is("subject"));
//        assertThat(actualMessage.getMessage(), is("message text"));
//        assertThat(actualMessage.getTime(), OrderingComparison.greaterThan(now));
    }
}
