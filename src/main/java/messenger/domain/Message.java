package messenger.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document
@TypeAlias("Message")
public class Message {

    @Id
    private String id;

    private String subject;

    private String message;

    private Date time;

    public Message() {
    }

    public Message(String id, String subject, String message, Date time) {
        this.id = id;
        this.subject = subject;
        this.message = message;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Message)) {
            return false;
        }
        Message message1 = (Message) o;
        return Objects.equals(id, message1.id) &&
               Objects.equals(subject, message1.subject) &&
               Objects.equals(message, message1.message) &&
               Objects.equals(time, message1.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, message, time);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("id='").append(id).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }
}
