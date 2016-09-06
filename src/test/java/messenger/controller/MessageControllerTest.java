package messenger.controller;

import io.restassured.http.ContentType;
import messenger.Application;
import messenger.model.MessageModel;
import messenger.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class MessageControllerTest {

    @Test
    public void send_shouldReturnStatusCode200() throws Exception {
        MessageModel message = new MessageModel("subject", "message text");

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

        MessageModel message = new MessageModel("subject", "message text");
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
        verify(service).sendEmail("1", "user@email.com", message);
    }

    @Test
    public void send_noEmail_shouldReturnStatusCode400() throws Exception {
        MessageModel message = new MessageModel("subject", "message text");

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
    public void send_onDuplicateKeyId_shouldReturnStatusCode400() throws Exception {
        MessageModel message = new MessageModel("subject", "message text");

        MessageService service = mock(MessageService.class);
        when(service.sendEmail(anyString(), anyString(), any(MessageModel.class))).thenThrow(DuplicateKeyException.class);
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
