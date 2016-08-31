package messenger.service;

import messenger.configuration.ApplicationConfiguration;
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
    @Mock
    private MailgunApiService apiService;

    @Mock
    private ApplicationConfiguration applicationConfiguration;

    @InjectMocks
    private MailServiceImpl mailService;

    @Before
    public void setUp() throws Exception {
        when(applicationConfiguration.getMailgunApiKey()).thenReturn(API_KEY);
    }

    @Test
    public void sendEmail_shouldSend() throws Exception {
        // given
        String authorization = "api:" + API_KEY;
        String expectedAuthorization = "Basic " + Base64.getEncoder().encodeToString(authorization.getBytes());

        // then
        mailService.sendEmail("noreply@domain.com", "user@email.com", "subject", "text");

        // verify
        Mockito.verify(apiService).sendEmail(expectedAuthorization, "domain.com", "noreply@domain.com", "user@email.com", "subject",
                "text");

//        Call<ResponseBody> call = apiService.sendEmail(
//                "Basic " + Base64.getEncoder().encodeToString("api:key-9578f9ca1c1840c6fa1eee8eae4cc539".getBytes()),
//                "sandbox9e3e8a49584244d8a9ab4dcc9491d82f.mailgun.org",
//                "noreply@sandbox9e3e8a49584244d8a9ab4dcc9491d82f.mailgun.org",
//                "a.shepeliev@gmail.com", "test subject", "Hello Sasha!");
//        Response<ResponseBody> response = call.execute();
//        assertThat(response.code(), is(200));
    }
}