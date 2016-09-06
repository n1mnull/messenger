package messenger.service;

import messenger.domain.Message;
import messenger.model.MessageModel;
import messenger.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {

    private final MailService mailService;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MailService mailService, MessageRepository messageRepository) {
        this.mailService = mailService;
        this.messageRepository = messageRepository;
    }

    @Override
    public Message sendEmail(String id, String to, MessageModel messageModel) {
        mailService.sendEmail(to, messageModel.getSubject(), messageModel.getMessage());
        Message message = new Message(id, messageModel.getSubject(), messageModel.getMessage(), new Date());
        return messageRepository.save(message);
    }
}
