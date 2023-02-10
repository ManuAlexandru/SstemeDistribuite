package ContinuesConnection;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;

public class ServerWithThread {
    public static void main(String[] args) {

        //using serversocket as argument to automatically close the socket
        //the port number is unique for each server

        //list to add all the clients thread
        ArrayList<ServerThread> threadList = new ArrayList<>();

        try (ServerSocket serversocket = new ServerSocket (56879) ) {
            LocalTime myTime = LocalTime.now();

            System.out.println("% " + myTime + "\topen the port: 56879");

            while(true) {
                Socket connectedSocket = serversocket.accept();
                InetSocketAddress socketAddress = (InetSocketAddress) connectedSocket.getRemoteSocketAddress();

                String clientIpAddress = socketAddress.getAddress().getHostAddress();

                ServerThread serverThread = new ServerThread (connectedSocket, threadList);

                //starting the thread
                threadList.add(serverThread);
                myTime = LocalTime.now();
                System.out.println("% " + myTime + "\tnew connection [" + threadList.size() + "]\t"+ clientIpAddress);
                serverThread.start();

            }
        } catch (Exception e) {
            System.out.println("% Error occured in main:" + e.getStackTrace());
        }
    }
}
