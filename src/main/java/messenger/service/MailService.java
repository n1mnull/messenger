package messenger.service;

import messenger.model.Message;

public interface MailService {

    void sendEmail(String to, int id, Message message);
}
