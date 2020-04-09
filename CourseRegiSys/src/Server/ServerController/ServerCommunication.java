package Server.ServerController;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class ServerCommunication {
    private ServerSocket serverSocket;  //server socket
    private Socket aSocket;     //socket to which client is binded to
    private BufferedReader socketIn;    //msg to write to client
    private PrintWriter socketOut;  //msg to read from socket
    private ExecutorService pool;   //threadpool
}