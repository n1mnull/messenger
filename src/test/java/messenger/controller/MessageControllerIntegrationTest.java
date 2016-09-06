package messenger.controller;

import io.restassured.http.ContentType;
import messenger.Application;
import messenger.domain.Message;
import messenger.model.MessageModel;
import messenger.repository.MessageRepository;
import messenger.service.MailService;
import messenger.service.MessageService;
import messenger.service.MessageServiceImpl;
import org.hamcrest.number.OrderingComparison;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class MessageControllerIntegrationTest {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private MessageRepository messageRepository;

    private MessageService messageService;

    @Before
    public void setUp() throws Exception {
        MailService mockMailService = mock(MailService.class);
        messageService = new MessageServiceImpl(mockMailService, messageRepository);
    }

    @Test
    public void send() throws Exception {
        // given
        MessageModel message = new MessageModel("subject", "message text");
        MessageController controller = new MessageController(messageService);
        Date now = new Date();

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
        Query query = query(where("_id").is("1"));
        Message actualMessage = mongoOperations.findOne(query, Message.class);

        assertThat(actualMessage, notNullValue());
        assertThat(actualMessage.getId(), is("1"));
        assertThat(actualMessage.getSubject(), is("subject"));
        assertThat(actualMessage.getMessage(), is("message text"));
        assertThat(actualMessage.getTime(), OrderingComparison.greaterThan(now));
    }
}
