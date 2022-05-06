package com.bondarenko.echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {
    private static final int PORT = 3000;

    public static void main(String[] args) throws IOException {
        int counter = 0;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                counter++;
                System.out.println("Start texting with a client " + counter);
                try (SocketManager socketManager = new SocketManager(socket)) {
                    while (true) {
                        String massage = socketManager.readMessage();
                        if (Objects.equals(massage, "stop")) {
                            break;
                        }
                        String modifiedMessage = "echo:" + massage;
                        socketManager.sendMessage(modifiedMessage);
                    }
                    System.out.println("Stop texting with client " + counter);
                } catch (IOException exception) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
}

