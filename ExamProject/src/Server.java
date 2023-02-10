import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9998);
        System.out.println("Server started at 9998");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected");

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        String messageFromClient = dataInputStream.readUTF();
        System.out.println("Message from client: " + messageFromClient);

        dataOutputStream.writeUTF("Hello from server");

        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
        serverSocket.close();
    }
}
