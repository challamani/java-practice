package com.core_java_practice.multithread.socket_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client
{
    public static void main(String[] args) {
        try {
            String ipAddress = args[0];
            System.out.println(args);

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
                        System.out.println("Server >> " + received);
                    } catch (IOException e) {
                        e.printStackTrace();
                        receiveStatus.set(false);
                    }
                }
            });
            receiverThread.start();

            AtomicBoolean responseStatus = new AtomicBoolean(true);

            Thread respondThread = new Thread(() -> {
                while (responseStatus.get()) {
                    try {
                        String toSend = scanner.nextLine();
                        dataOutputStream.writeUTF(toSend);

                        if (toSend.equals("Exit")) {
                            System.out.println("Closing this connection : " + socket);
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Connection closed");
                            responseStatus.set(false);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        responseStatus.set(false);
                    }
                }
            });

            respondThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

