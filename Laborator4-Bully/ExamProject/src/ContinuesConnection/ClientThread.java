package ContinuesConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientThread extends Thread {
    private Socket socket;
    private BufferedReader input;
    // private PrintWriter output;

    private final AtomicBoolean running = new AtomicBoolean(false);

    public ClientThread(Socket s) throws IOException {
        this.socket = s;
        this. input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        // this.output = new PrintWriter(socket.getOutputStream(),true);
    }

    // for stopping the thread
    public void tstop()
    {
        running.set(false);
    }

    @Override
    public void run() {
        running.set(true);
        try {
            while(running.get()) {
                String response = input.readLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}