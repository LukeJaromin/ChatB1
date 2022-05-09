package pl.jaromin.chat1b.client.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pl.jaromin.chat1b.client.channel.ChannelHistoryDto;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MessageRestClient {

    public static void sendPublicMessage(String message, String username) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/jee/api/message/public");
        httpPost.setEntity(new StringEntity(String.format("{\"message\":\"%s\"}", message), ContentType.APPLICATION_JSON));
        httpPost.setHeader("username", username);
        try {
            HttpEntity entity = client.execute(httpPost).getEntity();
            System.out.println(EntityUtils.toString(entity, StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sendPrivateMessage(String receiver, String message, String username) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/jee/api/message/private");
        httpPost.setEntity(new StringEntity(String.format("{\n" +
                "    \"message\":\"%s\",\n" +
                "    \"receiver\":\"%s\"\n" +
                "}", message, receiver), ContentType.APPLICATION_JSON));
        httpPost.setHeader("username", username);
        try {
            HttpEntity entity = client.execute(httpPost).getEntity();
            System.out.println(EntityUtils.toString(entity, StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sendChannelMessage(String name, String message, String username) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/jee/api/message/channel");
        httpPost.setEntity(new StringEntity(String.format("{\n" +
                "    \"channel\":\"%s\",\n" +
                "    \"message\":\"%s\"\n" +
                "}", name, message), ContentType.APPLICATION_JSON));
        httpPost.setHeader("username", username);
        try {
            HttpEntity entity = client.execute(httpPost).getEntity();
            System.out.println(EntityUtils.toString(entity, StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sendFile(String path, String name, String receiver, String username) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/jee/api/file");
        httpPost.setEntity(new StringEntity(String.format("{\n" +
                "    \"receiver\":\"%s\",\n" +
                "    \"name\":\"%s\",\n" +
                "    \"path\":\"%s\"\n" +
                "}", receiver, name, path), ContentType.APPLICATION_JSON));
        httpPost.setHeader("username", username);
        try {
            HttpEntity entity = client.execute(httpPost).getEntity();
            System.out.println(EntityUtils.toString(entity, StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getHistory(String name, String username) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/jee/api/message/channel/" + name);
        httpGet.setHeader("username", username);
        try {
            HttpEntity entity = client.execute(httpGet).getEntity();
            String message = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            List<ChannelHistoryDto> history = objectMapper.readValue(message, new TypeReference<List<ChannelHistoryDto>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            history.forEach(item -> System.out.println(String.format("From: %S | channel: %s | message: %s", item.getSender(), item.getChannel(), item.getMessage() )));
            System.out.println(EntityUtils.toString(entity, StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
