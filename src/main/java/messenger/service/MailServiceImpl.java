package messenger.service;

import messenger.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Override
    public void sendEmail(String to, int id, Message message) {
        throw new UnsupportedOperationException();
    }
}
