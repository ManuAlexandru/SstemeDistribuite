package ContinuesConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWithThread {

    public static void main(String[] args) {

        try {
            //Socket socket = new Socket ("192.168.37.153", 56879);
            Socket socket = new Socket("localhost", 56879);
            //reading the input from server
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream())) ;

            //returning the output to the server : true statement is to flush the buffer otherwise
            //we have to do it manually
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            //taking the user input
            Scanner scanner = new Scanner(System.in);
            String userInput;
            String response;
            String clientName = "empty";

            ClientThread clientThread = new ClientThread (socket) ;
            clientThread.start();

            //loop closes when user enters exit command
            do {
                if (clientName.equals ("empty") ) {
                    System.out.print("Enter your name: ");
                    userInput = scanner.nextLine();
                    clientName = userInput;
                    output.println(userInput);
                    if (userInput. equals ("bye")) {
                        break;
                    }
                } else {
                    // dupa inregistrare POST
                    //String message = ( "(" + clientName + ") message: ");
                    //System.out.println(message);
                    userInput = scanner.nextLine();
                    if (userInput.equals ("bye") ) {
                        //clientThread.tstop();
                        break;
                    }
                    output.println (userInput);
                }
            } while (!userInput.equals ("bye")) ;

            System.out.println("gata, am terminat.");
            //Thread.sleep(10);
            output.println (userInput);
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Exception occurred in client main: " + e.getStackTrace());
        }



    }
}
