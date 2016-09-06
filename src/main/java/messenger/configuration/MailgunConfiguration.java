package messenger.configuration;

import messenger.service.MailgunApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

@Configuration
public class MailgunConfiguration {

    @Value("mailgun.api.key")
    private String mailgunApiKey;

    @Value("mailgun.domain")
    private String mailgunDomain;

    public String getMailgunApiKey() {
        return mailgunApiKey;
    }

    public String getMailgunDomain() {
        return mailgunDomain;
    }

    @Bean
    public MailgunApiService mailgunApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mailgun.net/")
                .build();
        return retrofit.create(MailgunApiService.class);
    }
}
