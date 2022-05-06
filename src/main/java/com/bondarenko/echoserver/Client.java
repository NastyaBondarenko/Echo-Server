package com.bondarenko.echoserver;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    private static final int PORT = 3000;

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", PORT);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Type 'stop' to interrupt texting with Server");
            try (SocketManager socketManager = new SocketManager(socket)) {
                while (true) {
                    System.out.println("Type message to Server: ");
                    String message = scanner.nextLine();
                    socketManager.sendMessage(message);
                    if (Objects.equals(message, "stop")) {
                        System.out.println("Stop texting with Server");
                        break;
                    }
                    String response = socketManager.readMessage();
                    System.out.println(response);
                }
            }
        }
    }
}

