package com.practice.threads.sockets;

import com.practice.MyTechHub;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client implements MyTechHub {
    public static void main(String[] args) {
        try {
            String ipAddress = args[0];

            Scanner scanner = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName(ipAddress);
            Socket socket = new Socket(ip, 5056);

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            AtomicBoolean receiveStatus = new AtomicBoolean(true);
            Thread receiverThread = new Thread(() -> {
                while (receiveStatus.get()) {
                    try {
                        String received = dataInputStream.readUTF();
                        System.out.printf("Server >> %s %s",
                                GREEN_COLOR,received);
                        System.out.println();
                    } catch (IOException exception) {
                        receiveStatus.set(false);
                    }
                }
            }, "server-message-receiver");
            receiverThread.start();

            AtomicBoolean responseStatus = new AtomicBoolean(true);
            Thread respondThread = new Thread(() -> {
                while (responseStatus.get()) {
                    try {
                        String toSend = scanner.nextLine();
                        dataOutputStream.writeUTF(toSend);

                        if (toSend.equals("Exit") || toSend.contains("Bye") ) {
                            System.out.println("Closing this connection :\n" + socket);
                            socket.close();
                            System.out.println("Connection closed");
                            responseStatus.set(false);
                        }
                    } catch (IOException e) {
                        System.out.println("failed at socket handling!");
                        responseStatus.set(false);
                    }
                }
            },"respond-thread");
            respondThread.start();

        } catch (Exception e) {
            System.out.println("failed at socket close!");
        }
    }

    @Override
    public String getProblem() {
        return null;
    }

    @Override
    public String getApproach() {
        return null;
    }
}

