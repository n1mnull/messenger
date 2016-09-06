package messenger.model;

import java.util.Objects;

public class MessageModel {

    private String subject;
    private String message;

    public MessageModel() {
    }

    public MessageModel(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessageModel)) {
            return false;
        }
        MessageModel message1 = (MessageModel) o;
        return Objects.equals(subject, message1.subject) &&
               Objects.equals(message, message1.message);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("subject='").append(subject).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
