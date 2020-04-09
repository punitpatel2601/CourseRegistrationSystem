package Client.ClientController;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientCommunication {
    private Socket aSocket;
    private BufferedReader socketOut;   //msg to send to server
    private PrintWriter socketIn;   //msg to read from socket
    private BufferedReader stdIn;   //commands from client
}