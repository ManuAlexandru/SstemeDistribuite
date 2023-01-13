import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 8910;
        Socket socket = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        BufferedReader keyboardReader = null;
// Connect to the server...
        try{
            socket = new Socket(address, port);
// Obtain the streams...
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        }
        catch(IOException e)
        {
            System.out.println("Problems initialising: "+e);
            System.exit(1);
        }
        try {
// Start the listening thread...
            TCPEchoReader reader = new TCPEchoReader(dataInputStream);
            reader.setDaemon(true);
            reader.start();
            String input;
            while(true)
            {
// read data in from the keyboard
                input = keyboardReader.readLine();
// send data to server
                dataOutputStream.writeUTF(input);
            }
        }
        catch(IOException e) { }
        try {
            socket.close();
        }
        catch(IOException e) { }
    }
    }
