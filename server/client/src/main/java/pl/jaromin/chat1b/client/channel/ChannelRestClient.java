package pl.jaromin.chat1b.client.channel;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ChannelRestClient {
    public static void addUser(String name, String user, String username) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/jee/api/channel/"+ name + "/" + user);
        httpPost.setHeader("username", username);
        try {
            HttpEntity entity = client.execute(httpPost).getEntity();
            System.out.println(EntityUtils.toString(entity, StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void newChannel(String name, String username) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/jee/api/channel/" + name);
        httpPost.setHeader("username", username);
        try {
            HttpEntity entity = client.execute(httpPost).getEntity();
            System.out.println(EntityUtils.toString(entity, StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void leaveChannel(String name, String username) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete("http://localhost:8080/jee/api/channel/leave/" + name);
        httpDelete.setHeader("username", username);
        try {
            HttpEntity entity = client.execute(httpDelete).getEntity();
            System.out.println(EntityUtils.toString(entity, StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
