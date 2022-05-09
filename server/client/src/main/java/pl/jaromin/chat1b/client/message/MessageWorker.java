package pl.jaromin.chat1b.client.message;

import java.util.Scanner;

public class MessageWorker {

    public static void sendToUser(String username) {
        System.out.println("Enter message receiver");
        Scanner scanner = new Scanner(System.in);
        String receiver = scanner.nextLine();
        System.out.println("Enter message text");
        String message = scanner.nextLine();
        MessageRestClient.sendPrivateMessage(receiver, message, username);
    }

    public static void broadcastToChannel(String username) {
        System.out.println("Enter channel name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Enter message text");
        String message = scanner.nextLine();
        MessageRestClient.sendChannelMessage(name, message, username);
    }

    public static void broadcast(String username) {
        System.out.println("Enter message text");
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        MessageRestClient.sendPublicMessage( message, username);
    }

    public static void sendFile(String username) {
        System.out.println("Enter file path");
        System.out.println("path example: C:/Users/user/Desktop/MyFile.txt");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        System.out.println("Enter file name");
        System.out.println("file name example: MyFile.txt");
        String name = scanner.nextLine();
        System.out.println("Enter receiver name");
        String receiver = scanner.nextLine();
        MessageRestClient.sendFile(path, name, receiver, username);
    }

    public static void getHistoryFromChannel(String username) {
        System.out.println("Enter channel name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        MessageRestClient.getHistory(name, username);
    }
}
