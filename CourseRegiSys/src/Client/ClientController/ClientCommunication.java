package Client.ClientController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientCommunication {
    private Socket aSocket;
    private BufferedReader socketOut;   //msg to send to server
    private PrintWriter socketIn;   //msg to read from socket
    
    public ClientCommunication(String serverName, int port) {
    	try {
    		aSocket = new Socket(serverName, port);
    		socketOut = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
    		socketIn = new PrintWriter(aSocket.getOutputStream(), true);
    	} catch (IOException e) {
    		e.getStackTrace();
    	}
    }
    
    public void communicate() {
    	
    }
    
    public static void main(String[] args) {
		ClientCommunication clientCom = new ClientCommunication("localhost", 9898);
		clientCom.communicate();
	}
}