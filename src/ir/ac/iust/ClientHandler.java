package ir.ac.iust;

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread{
    public static int[][] multiply(int[][] A, int[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB)
            throw new RuntimeException("Illegal matrix dimensions.");
        int[][] C = new int[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }

    private final Socket connectionSocket;
    private final BufferedReader inFormClient;
    private final ObjectOutputStream outToClient;

    public ClientHandler(Socket connectionSocket, BufferedReader inFormClient, ObjectOutputStream outToClient){
        this.connectionSocket = connectionSocket;
        this.inFormClient = inFormClient;
        this.outToClient = outToClient;
    }

    @Override
    public void run(){
        while (true){
            try {
                String clientSentence;
                String clientresponce;
                clientSentence = inFormClient.readLine();
                String input = clientSentence;
                int length = input.length();

                if (length % 2 ==1){
                    String helpstring = " ";
                    input = input.concat(helpstring);
                    length = length + 1;
                }

                int [][] matrix = new int[2][length/2];
                int counter=0;
                for (int j =0 ; j<2 ; j++){
                    for (int i=0 ; i<length/2 ; i++){
                        if (j==0)
                            counter=i;
                        else counter = i+(length/2);
                        matrix[j][i]= (int) input.charAt(counter);
                    }
                }

                int [][] primary = {{1,2},{-1,2}};
                int [][] muliply = multiply(primary, matrix);
                int[] muliplyOneline = new int[length];

                for (int i=0 ; i<length/2; i++){
                    muliplyOneline[i] = muliply[0][i];
                }

                int k =0;
                for (int i=length/2 ; i<length; i++){
                    muliplyOneline[i] = muliply[1][k];
                    k++;
                }

                String[] stri = new String[length];

                for (int i = 0; i < muliplyOneline.length; i++) {
                    stri[i] = Character.toString((char) muliplyOneline[i]);
                }

                StringBuilder stringBuilder = new StringBuilder();
                for (int i=0; i<length;i++) {
                    stringBuilder.append(stri[i]);
                }
                clientresponce = stringBuilder.toString();
                System.out.println(clientresponce);

                outToClient.writeObject(clientresponce);
            } catch (IOException e){
                break;
            }
        }
    }
}
