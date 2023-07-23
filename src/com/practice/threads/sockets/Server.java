package com.practice.threads.sockets;

import com.practice.MyTechHub;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements MyTechHub {

    public void acceptClientRequests(ServerSocket serverSocket) {
        while (true) {
            try {
                Socket client = serverSocket.accept();
                startClientHandler(client);
            } catch (IOException ie) {
                System.out.println("failed to accept client request:"+
                        ie.getMessage());
            }
        }
    }

    private void startClientHandler(Socket client) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        System.out.println("\n verify client-handler code for logic");
        Thread clientHandler = new ClientHandler(client,dataInputStream,dataOutputStream);
        clientHandler.start();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.displayProblemAndApproach();
        server.delay(2000);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5056);
            server.acceptClientRequests(serverSocket);
            server.endCard();
        } catch (Exception e) {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
        System.out.printf("%sMake sure closed the server/client ports"
                , RED_COLOR);
    }

    @Override
    public String getProblem() {
        return "How to implement one to many terminal chat app";
    }

    @Override
    public String getApproach() {
        return "Using java socket programming,\nwe can write a client-server chat application";
    }
}
