package Server.ServerController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCommunication {
    private ServerSocket serverSocket;  //server socket
    private Socket aSocket;     //socket to which client is binded to
    private BufferedReader socketIn;    //msg to write to client
    private PrintWriter socketOut;  //msg to read from socket
    private ExecutorService pool;   //threadpool
    
    public ServerCommunication(int port) {
    	try {
    		serverSocket = new ServerSocket(port);
    		pool = Executors.newCachedThreadPool();
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void runServer() {
    	try {
    		while(true) {
    			aSocket = serverSocket.accept();
    			System.out.println("Connection accepted by server!");
    			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
    			socketOut = new PrintWriter(aSocket.getOutputStream(), true);
    			
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	closeConnection();
    }
    
    public void closeConnection() {
    	try {
    		socketIn.close();
    		socketOut.close();
    	} catch (IOException e) {
    		e.getStackTrace();
    	}
    }
    
    public static void main(String[] args) {
		ServerCommunication serverCom = new ServerCommunication(9898);
		serverCom.runServer();
	}
}