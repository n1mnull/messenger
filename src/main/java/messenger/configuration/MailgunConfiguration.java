package messenger.configuration;

import messenger.service.MailgunApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

@Configuration
public class MailgunConfiguration {

    @Bean
    public MailgunApiService mailgunApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mailgun.net/")
                .build();
        return retrofit.create(MailgunApiService.class);
    }
}
