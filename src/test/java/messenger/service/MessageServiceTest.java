package messenger.service;

import messenger.domain.Message;
import messenger.model.MessageModel;
import messenger.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;
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
        MessageModel messageModel = new MessageModel("subject", "text");
        Date now = new Date();

        // when
        messageService.sendEmail("1", "user@email.com", messageModel);

        // then
        verify(mailService).sendEmail("user@email.com", "subject", "text");

        ArgumentCaptor<Message> ac = ArgumentCaptor.forClass(Message.class);
        verify(messageRepository).save(ac.capture());
        Message message = ac.getValue();
        assertThat(message.getTime().getTime(), greaterThan(now.getTime()));
    }
}
