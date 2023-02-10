package ContinuesConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class ServerThread extends Thread {

    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;

    static int idthread = 0;
    private int id = -1;
    String clientname = "n0b0dy";

    private int cbPort = 8089;             // callback port (portul de asteptare al clientului)

    private String subj[] = {
            "nimic",

            "cum se diferentiaza algoritmul Berkeley de algoritmul lui Cristian?",

            "ĂŽntr-o reČea P2P (protocol Chord) cu maxim 16 noduri avem " +
                    "active 5 noduri avĂ˘nd cheile 0,2,5,6,11. Unde se va ĂŽnregistra " +
                    "cheia âExamenâ dacÄ hash-ul acesteia este 7.",

            "ce afirma conjectura Brewer?",

            "ExplicaČi conceptul consistent hashing ĂŽn cazul IPFS",

            "DescrieČi conceptul 'routing table' ĂŽn cazul DHT ",

            "care este ideea algoritmului Lamport?",

            "ce afirma teorema CAP?",

            "ce este marshalling?"

    };


    public ServerThread (Socket socket, ArrayList<ServerThread> threads) {
        this.socket = socket;
        this.threadList = threads;
        idthread++;
        id = idthread;
    }

    private void callbackStudent(int nr_subiect, int PORT){
        try {
            System.out.println( "["+this.id+"] send subject: " + nr_subiect + " port: " + PORT);

            Socket cbsocket = new Socket ("localhost", PORT);
            ////BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream())) ;
            PrintWriter output = new PrintWriter(cbsocket.getOutputStream(), true);
            output.println("" + subj[nr_subiect]);
        } catch (Exception e){
            System.out.println("[" + this.id + "] ** EROARE la transmiterea subiectului " + e.getStackTrace());
        }

    }




    @Override
    public void run() {
        Random rand = new Random();

        try {

            //Reading the input from Client
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream ())) ;

            //returning the output to the client: true statement is to flush the buffer otherwise
            //we have to do it manually
            output = new PrintWriter(socket.getOutputStream(), true);

            // inifite loop for server
            while(true) {
                String cmd = input.readLine() ;

                if(cmd==null) continue;
                System.out.println( "["+id+"] received: " + cmd + "\t"+ LocalTime.now() );

                if(this.clientname.equals("n0b0dy")){
                    // presupun ca se inregistreaza
                    output.println("Hello, " + cmd);
                    this.clientname = cmd;
                } else {
                    int l = cmd.length();
                    String cmd3 = cmd.substring(0,3);
                    if(l>4 && cmd3.equalsIgnoreCase("GET")){
                        int rand_i = 1 + rand.nextInt(7);
                        cbPort = Integer.parseInt(cmd.substring(4));
                        output.println("se trimite subiectul " + rand_i + " port " + this.cbPort);
                        callbackStudent( rand_i, cbPort );
                    }

                    //if(cmd.equals("POST")){
                    //    output.println("OK");
                    //}

                    //if user types exit command
                    if (cmd.equalsIgnoreCase("bye") ) {
                        //printToAllClients (outputString);
                        break;
                    }
                    //output.println("server says: "+ cmd);

                }

            }
            LocalTime myTime = LocalTime.now();
            System.out.println("% " + myTime + "\tclose connection [" + this.id + "]\t"+ clientname);

        } catch (Exception e) {
            System.out.println( "["+this.id +"] ** ERROR occured " + e.getStackTrace());
        }

    }

}