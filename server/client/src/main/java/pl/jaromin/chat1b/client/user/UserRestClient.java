package pl.jaromin.chat1b.client.user;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class UserRestClient {

    public static String createUser(String name) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/jee/api/user/" + name);
        try {
            HttpEntity entity = client.execute(httpPost).getEntity();
            return EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "Nie udało się utworzyć użytkownika. Spróbuj ponownie później.";
        }
    }
}
