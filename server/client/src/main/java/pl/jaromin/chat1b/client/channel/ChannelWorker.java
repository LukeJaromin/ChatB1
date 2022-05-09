package pl.jaromin.chat1b.client.channel;

import java.util.Scanner;

public class ChannelWorker {
    public static void addUserToChannel(String username) {
        System.out.println("Enter channel name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Enter user name");
        String user = scanner.nextLine();
        ChannelRestClient.addUser(name, user, username);
    }

    public static void createChannel(String username) {
        System.out.println("Enter channel name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        ChannelRestClient.newChannel(name,username);
    }

    public static void leaveChannel(String username) {
        System.out.println("Enter channel name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        ChannelRestClient.leaveChannel(name, username);
    }
}
