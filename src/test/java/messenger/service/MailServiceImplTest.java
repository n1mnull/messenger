package messenger.service;

import messenger.configuration.MailgunConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Base64;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MailServiceImplTest {

    private static final String API_KEY = "key-okjfokfpdojf";
    private static final String DOMAIN = "domain.com";

    @Mock
    private MailgunApiService apiService;

    @Mock
    private MailgunConfiguration applicationConfiguration;

    @InjectMocks
    private MailServiceImpl mailService;

    @Before
    public void setUp() throws Exception {
        when(applicationConfiguration.getMailgunApiKey()).thenReturn(API_KEY);
        when(applicationConfiguration.getMailgunDomain()).thenReturn(DOMAIN);
    }

    @Test
    public void sendEmail_shouldSend() throws Exception {
        // given
        String authorization = "api:" + API_KEY;
        String expectedAuthorization = "Basic " + Base64.getEncoder().encodeToString(authorization.getBytes());

        // then
        mailService.sendEmail("user@email.com", "subject", "text");

        // verify
        Mockito.verify(apiService).sendEmail(expectedAuthorization, "domain.com", "noreply@domain.com", "user@email.com", "subject",
                "text");
    }
}