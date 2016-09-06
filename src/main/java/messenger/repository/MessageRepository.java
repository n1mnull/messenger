package messenger.repository;

import messenger.model.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository {

    Message save(Message message);
}
