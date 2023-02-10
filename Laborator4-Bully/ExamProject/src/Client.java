import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9998);
        System.out.println("Connected to server");

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        dataOutputStream.writeUTF("Hello from client");

        String messageFromServer = dataInputStream.readUTF();
        System.out.println("Message from server: " + messageFromServer);

        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
    }
}