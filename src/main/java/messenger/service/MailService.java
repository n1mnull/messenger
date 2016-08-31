package messenger.service;

public interface MailService {

    void sendEmail(String from, String to, String subject, String text);
}
