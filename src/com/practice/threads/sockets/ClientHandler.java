package com.practice.threads.sockets;

import com.practice.MyTechHub;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientHandler extends Thread implements MyTechHub {

    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;
    final Socket socket;
    final Scanner scanner;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.socket = s;
        this.dataInputStream = dis;
        this.dataOutputStream = dos;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        String received;
        String respond;
        try {
            dataOutputStream.writeUTF("Welcome to terminal chat communication !" +
                    "\n... Say Something ..!");
        } catch (IOException exception) {
            System.out.println("failed to write to client " + exception.getMessage());
        }
        AtomicBoolean clientDown = new AtomicBoolean(false);
        while (!clientDown.get()) {
            try {
                received = dataInputStream.readUTF();
                System.out.println("Client >> " + received);
                if (received.equalsIgnoreCase("Exit")
                        || received.equalsIgnoreCase("bye")) {
                    System.out.println("Client: \n " + this.socket + "\n sends exit...");
                    System.out.println("Closing this connection.");
                    this.socket.close();
                    System.out.println("Connection closed");
                    break;
                }
                respond = scanner.nextLine();
                dataOutputStream.writeUTF(respond);
            } catch (IOException e) {
                clientDown.set(true);
                System.out.println("failed at handling client requests:" + e.getMessage());
            }
        }
        try {
            this.dataInputStream.close();
            this.dataOutputStream.close();
        } catch (IOException e) {
            System.out.println("failed at handling client request:" + e.getMessage());
        }
    }

    @Override
    public String getProblem() {
        return "Client Handler";
    }

    @Override
    public String getApproach() {
        return "Client Handler using dedicated thread";
    }
}