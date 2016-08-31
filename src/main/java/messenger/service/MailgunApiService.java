package messenger.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface MailgunApiService {

    @FormUrlEncoded
    @POST("/v3/{domain}/messages")
    Call<ResponseBody> sendEmail(
            @Header("Authorization") String authorization,
            @Path("domain") String domain,
            @Field("from") String from,
            @Field("to") String to,
            @Field("subject") String subject,
            @Field("text") String text
    );

}
