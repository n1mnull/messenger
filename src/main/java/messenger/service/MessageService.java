package messenger.service;

import messenger.model.Message;

public interface MessageService {

    void sendEmail(String to, Message message);
}
