package com.core_java_practice.multithread.examples.socket_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket ss = new ServerSocket(5056);
        while (true) {
            Socket client = null;
            try {
                client = ss.accept();
                System.out.println("A new connection encountered : " + client);

                DataInputStream dis = new DataInputStream(client.getInputStream());
                DataOutputStream dos = new DataOutputStream(client.getOutputStream());

                System.out.println("Assign request to client-handler thread");
                Thread thread = new ClientHandler(client, dis, dos);
                thread.setName("client-handler-thread-"+client.toString());
                thread.start();
            } catch (Exception e) {
                client.close();
                e.printStackTrace();
            }
        }
    }
}

class ClientHandler extends Thread
{

    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;
    final Socket socket;
    final Scanner scanner;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.socket = s;
        this.dataInputStream = dis;
        this.dataOutputStream = dos;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run()
    {
        String received;
        String respond;
        try{
            dataOutputStream.writeUTF("Welcome to terminal chat communication ! ... Say Something ..!");
        }catch (IOException exception) {
            exception.printStackTrace();
        }

        AtomicBoolean clientDown = new AtomicBoolean(false);
        while (!clientDown.get())
        {
            try {
                received = dataInputStream.readUTF();
                System.out.println("Client >> "+received);

                if(received.equalsIgnoreCase("Exit") || received.equalsIgnoreCase("bye"))
                {
                    System.out.println("Client " + this.socket + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.socket.close();
                    System.out.println("Connection closed");
                    break;
                }

                respond = scanner.nextLine();
                dataOutputStream.writeUTF(respond);

            } catch (IOException e) {
                clientDown.set(true);
                e.printStackTrace();
            }
        }

        try
        {
            this.dataInputStream.close();
            this.dataOutputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

