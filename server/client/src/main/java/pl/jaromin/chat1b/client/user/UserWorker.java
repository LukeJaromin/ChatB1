package pl.jaromin.chat1b.client.user;

import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Pattern;

@Data
public class UserWorker {

    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z0-9]+");

    public static String getUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Aby się zalogować podaj nazwę użytkownika");
        String name = scanner.nextLine();
        if (usernameExists(name)) {
            System.out.println("Zalogowano poprawnie.");
        } else {
            System.out.println("Użytkownik: " + name + " nie istnieje. Czy chcesz go utworzyć? T/N");
            if (scanner.nextLine().equals("T")) {
                System.out.println(createUser(name, scanner));
            } else {
                System.out.println("Błędna odpowiedź! Żegnamy i zapraszamy ponownie.");
                System.exit(0);
            }
        }
       return name;
    }

    public static String createUser(String name, Scanner scanner) {
        boolean nameIsIncorrect = !NAME_PATTERN.matcher(name).matches();
        while (nameIsIncorrect) {
            if (!NAME_PATTERN.matcher(name).matches()) {
                System.out.println(
                        "Podany username jest niepoprawny. Powinien zawierać wyłącznie litery i cyfry bez spacji.");
            }
            name = scanner.nextLine();
            nameIsIncorrect = !NAME_PATTERN.matcher(name).matches();
        }
        return UserRestClient.createUser(name);
    }



    public static boolean usernameExists(String name) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/jee/api/user/exists/" + name);
        try {
            HttpEntity entity = client.execute(httpGet).getEntity();
            return Boolean.parseBoolean(EntityUtils.toString(entity, StandardCharsets.UTF_8));
        } catch (IOException e) {
            return false;
        }
    }
}
