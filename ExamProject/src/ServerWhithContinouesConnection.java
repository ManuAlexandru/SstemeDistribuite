import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWhithContinouesConnection {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("Server started at 9999");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String messageFromClient = dataInputStream.readUTF();
                System.out.println("Message from client: " + messageFromClient);

                if (messageFromClient.equals("exit")) {
                    System.out.println("Closing connection with client");
                    dataInputStream.close();
                    dataOutputStream.close();
                    socket.close();
                    break;
                }

                dataOutputStream.writeUTF("Hello from server");
            }
        }
    }
}
