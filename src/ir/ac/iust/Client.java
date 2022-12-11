package ir.ac.iust;


import java.io.*;
import java.net.*;
import java.util.*;


public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String sentence;
        String responce;
        System.out.println("Enter the input sentence: \n!!!If the number of characters in the input sentence is odd, space is added to the end!!!\n");

        BufferedReader inFormUser =
                new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("127.0.0.1", 1144);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        ObjectInputStream inFormServer = new ObjectInputStream(clientSocket.getInputStream());
        sentence = inFormUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        responce = (String) inFormServer.readObject();
        System.out.println("Encrypted Sentence:\n" + responce);
        clientSocket.close();

        /*System.out.println("Enter the sentences:\n");
        Scanner input = new Scanner(System.in);
        Socket clientSocket = new Socket("localhost", 1414);

        //ByteArrayOutputStream outputStream = new ByteArrayOutputStream(clientSocket.getOutputStream());
        //ByteArrayInputStream inputStream = new ByteArrayInputStream(clientSocket.getInputStream());

        ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

        String stinput = input.next();

        int length = stinput.length();
        int [] array = new int[length];
        for(int i=0 ; i<length ; i++){
            array[i] = (int) stinput.charAt(i)
        }
        outputStream.write(array);

        outputStream.writeChars(stinput);
        outputStream.flush();
        String response = String.valueOf(inputStream.readChar());
        System.out.println(response);*/
    }
}
