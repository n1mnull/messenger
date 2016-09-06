package messenger.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import messenger.model.Message;
import messenger.repository.MessageRepository;

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
    public void sendMail_shouldSendMailAndSave() throws Exception {
        // given
        Message message = new Message("subject", "text");


        // when
        messageService.sendEmail("user@email.com", message);

        // then
        verify(mailService).sendEmail("noreply@domain.com", "user@email.com", "subject", "text");
        verify(messageRepository).save(message);
    }
}
