import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class ClientExam {
    public static float finalTime=0;
    public static int counter=0;
    public static void main(String[] args) {
        String serverName = "cti.ubm.ro";
        int port = 9090;

        try (Socket socket = new Socket(serverName, port);
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader bfReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
while(true) {
                System.out.print("Enter somethig: ");
                String name = scanner.nextLine();

                if (name.equals("bye")) {

                    System.out.println("Closing connection with server");
                    printWriter.close();
                    bfReader.close();
                    socket.close();
                    break;
                }

    Date date = new Date();
    long time1 = date.getTime();
                try {

                    Thread.sleep(2000);
                    printWriter.println(name);
                    printWriter.println(name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String response = bfReader.readLine();
    Date date2 = new Date();
    long time2 = date2.getTime();
    counter++;
    finalTime=finalTime=(time2-time1);
                System.out.println(response);
            }
        } catch (UnknownHostException e) {
            System.err.println("Something went wrong ");
        } catch (IOException e) {
            System.err.println("Connection failed ");
        }

        System.out.print("Avg time is: ");
        if(counter>0)
        {System.out.print(finalTime/counter);
        System.out.println(" miliseconds");}
        else
            System.out.println("0");
    }
}