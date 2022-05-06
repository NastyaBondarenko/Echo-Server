package com.bondarenko.echoserver;

import java.io.*;
import java.net.Socket;

public class SocketManager implements Closeable {
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    private final Socket socket;
    private final BufferedWriter writer;
    private final BufferedReader reader;

    public SocketManager(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new PrintWriter(socket.getOutputStream()));
    }

    public String readMessage() throws IOException {
        char[] chars = new char[DEFAULT_BUFFER_SIZE];
        int readSize = reader.read(chars);
        return new String(chars, 0, readSize);
    }

    public void sendMessage(String message) throws IOException {
        writer.write(message);
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        try (reader; writer) {
        }
    }
}




