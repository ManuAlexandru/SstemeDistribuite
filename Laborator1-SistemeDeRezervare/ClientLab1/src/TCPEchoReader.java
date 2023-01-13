import java.io.*;


public class TCPEchoReader extends Thread {
    public boolean active;
    public DataInputStream dataInputStream;

    public TCPEchoReader(DataInputStream input) {
        dataInputStream = input;
        active = true;
    }

    public void run() {
        while (active) {
            try {
                String message = dataInputStream.readUTF();
                System.out.println("Received from server: " + message);
            } catch (IOException e) {
                System.out.println(e);
                active = false;
            }
        }
    }

}