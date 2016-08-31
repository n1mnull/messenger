package messenger.service;

import messenger.configuration.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class MailServiceImpl implements MailService {

    private static final String DOMAIN = "domain.com";
    private final ApplicationConfiguration applicationConfiguration;
    private final MailgunApiService mailgunApiService;

    @Autowired
    public MailServiceImpl(ApplicationConfiguration applicationConfiguration, MailgunApiService mailgunApiService) {
        this.applicationConfiguration = applicationConfiguration;
        this.mailgunApiService = mailgunApiService;
    }

    @Override
    public void sendEmail(String from, String to, String subject, String text) {
        String authorization = "api:" + applicationConfiguration.getMailgunApiKey();
        String basicAuthorization = "Basic " + Base64.getEncoder().encodeToString(authorization.getBytes());
        mailgunApiService.sendEmail(basicAuthorization, DOMAIN, from, to, subject, text);
    }
}
