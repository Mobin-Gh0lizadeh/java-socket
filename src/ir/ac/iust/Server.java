package ir.ac.iust;
import ir.ac.iust.ClientHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket welcomeSocket = new ServerSocket(1144);
        System.out.println("Server is Running");


        while (true){
            try {
                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFormClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
                new ClientHandler(connectionSocket, inFormClient, outToClient).start();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
