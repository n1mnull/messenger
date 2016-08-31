package messenger;

import messenger.model.Message;
import messenger.repository.MessageRepository;
import messenger.service.MailService;
import messenger.service.MessageServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @Mock
    private MailService mailService;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @Test
    public void sendMail_shouldSendMail() throws Exception {
        // given
        Message message = new Message("subject", "text");


        // when
        messageService.sendEmail("user@email.com", message);

        // then
        verify(mailService).sendEmail("noreply@domain.com", "user@email.com", "subject", "text");
    }

    @Test
    public void saveMail_shouldSaveMailInDB() throws Exception {
        // given
        Message message = new Message("subject", "text");

        // when
        messageService.saveEmail(1, message);

        // then
        verify(messageRepository).save(message);
    }
}
