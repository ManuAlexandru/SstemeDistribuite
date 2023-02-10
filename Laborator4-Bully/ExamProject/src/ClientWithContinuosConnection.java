import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ClientWithContinuosConnection {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9999);
        System.out.println("Connected to server");

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a message to send to server (type 'exit' to quit):");
            String messageToServer = scanner.nextLine();

            if (messageToServer.equals("exit")) {
                dataOutputStream.writeUTF(messageToServer);
                System.out.println("Closing connection with server");
                dataInputStream.close();
                dataOutputStream.close();
                socket.close();
                break;
            }

            dataOutputStream.writeUTF(messageToServer);
            String messageFromServer = dataInputStream.readUTF();
            System.out.println("Message from server: " + messageFromServer);
        }
    }
    }

