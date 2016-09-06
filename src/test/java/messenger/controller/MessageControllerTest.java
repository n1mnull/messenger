package messenger.controller;

import io.restassured.http.ContentType;
import messenger.Application;
import messenger.model.Message;
import messenger.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class MessageControllerTest {

    @Test
    public void send_shouldReturnStatusCode200() throws Exception {
        Message message = new Message("subject", "message text");

        MessageService service = mock(MessageService.class);
        MessageController controller = new MessageController(service);

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
                .statusCode(200);
        // @formatter:on
    }

    @Test
    public void send_shouldSendMessage() throws Exception {
        // given
        MessageService service = mock(MessageService.class);
        MessageController controller = new MessageController(service);

        Message message = new Message("subject", "message text");
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
                .statusCode(200);
        // @formatter:on

        // then
        verify(service).sendEmail("user@email.com", message);
    }

    @Test
    public void send_noEmail_shouldReturnStatusCode400() throws Exception {
        Message message = new Message("subject", "message text");

        MessageService service = mock(MessageService.class);
        MessageController controller = new MessageController(service);

        // @formatter:off
        given()
                .standaloneSetup(controller)
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
                .body(message)
        .when()
                .post("/messages/{id}/send", 1)
        .then()
                .log().ifValidationFails()
                .statusCode(400);
        // @formatter:on
    }

    @Test
    public void send_noSubject_shouldReturnStatusCode400() throws Exception {

    }

    @Test
    public void send_noMessage_shouldReturnStatusCode400() throws Exception {

    }
}
