package messenger.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Value("mailgun.api.key")
    private String mailgunApiKey;

    public String getMailgunApiKey() {
        return mailgunApiKey;
    }
}
