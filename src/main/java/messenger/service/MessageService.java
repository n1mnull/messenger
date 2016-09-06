package messenger.service;

import messenger.domain.Message;
import messenger.model.MessageModel;

public interface MessageService {

    Message sendEmail(String id, String to, MessageModel message);
}
