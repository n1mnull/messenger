package messenger.service;

import messenger.configuration.MailgunConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class MailServiceImpl implements MailService {

    private final MailgunConfiguration mailgunConfiguration;
    private final MailgunApiService mailgunApiService;

    @Autowired
    public MailServiceImpl(MailgunConfiguration mailgunConfiguration, MailgunApiService mailgunApiService) {
        this.mailgunConfiguration = mailgunConfiguration;
        this.mailgunApiService = mailgunApiService;
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        String authorization = "api:" + mailgunConfiguration.getMailgunApiKey();
        String basicAuthorization = "Basic " + Base64.getEncoder().encodeToString(authorization.getBytes());
        String from = "noreply@" + mailgunConfiguration.getMailgunDomain();
        mailgunApiService.sendEmail(basicAuthorization, mailgunConfiguration.getMailgunDomain(), from, to, subject, text);
    }
}
