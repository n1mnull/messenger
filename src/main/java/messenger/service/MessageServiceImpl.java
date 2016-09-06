package messenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import messenger.model.Message;
import messenger.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

    private static final String FROM_EMAIL = "noreply@domain.com";
    private final MailService mailService;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MailService mailService, MessageRepository messageRepository) {
        this.mailService = mailService;
        this.messageRepository = messageRepository;
    }

    @Override
    public void sendEmail(String to, Message message) {
        mailService.sendEmail(FROM_EMAIL, to, message.getSubject(), message.getMessage());
        messageRepository.save(message);
    }
}
