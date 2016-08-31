package messenger.repository;

import messenger.model.Message;

public interface MessageRepository {

    Message save(Message message);
}
