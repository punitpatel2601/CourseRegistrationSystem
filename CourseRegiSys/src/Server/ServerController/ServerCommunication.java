package Server.ServerController;

import Server.ServerModel.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

/**
 * Creates the object instances of the class and connects with client to get and
 * pass the informations requested by the user
 * @author A. Mohar, T. Pritchard, P. Patel
 * @version 1.0
 * @since April 13, 2020
 */
public class ServerCommunication {
	/**
	 * server Socket
	 */
	private ServerSocket serverSocket;

	/**
	 * Socket where client connects
	 */
	private Socket aSocket;

	/**
	 * Reader of input from server
	 */
	private BufferedReader socketIn;

	/**
	 * Printer of output to server
	 */
	private PrintWriter socketOut;

	/**
	 * Threadpool to runs multiple clients(coming soon)
	 */
	// private ExecutorService pool;

	/**
	 * Instance of class Model
	 */
	private Model model;

	/**
	 * boolean to check if server is running
	 */
	private boolean running;

	/**
	 * Initializes the server object, also calls other functions which connects to
	 * client
	 * 
	 * @param port port where server is hosted
	 */
	public ServerCommunication(int port) {
		try {
			serverSocket = new ServerSocket(port);
			// pool = Executors.newCachedThreadPool();
			System.out.println("Server is initialized!!	waiting for connection... ");
		} catch (Exception e) {
			e.getMessage();
		}
		model = new Model();
		running = true;
		initializeServer(); // initialize server with a client
		runServer(); // run the server
	}

	/**
	 * Connects to the client, defines the input and output paths
	 */
	public void initializeServer() {
		try {
			aSocket = serverSocket.accept(); // client joining
			System.out.println("Connection accepted by server!");
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(), true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Runs the server, reads the commands from clients and sends the response back
	 */
	public void runServer() {
		String line = "";

		while (running) {
			// System.out.println("\n");
			try {
				line = socketIn.readLine();
			} catch (Exception e) {
				running = false;
				e.getMessage();
			}

			if (line.isEmpty() || line == null) { // double checks for wrong input in server
				System.out.println("Invalid request string.");
				break;
			}

			String[] inputs = line.split(" "); // splits input string into different command and argument strings
			int choice = Integer.parseInt(inputs[0]); // commandString
			String name = inputs[1]; // args String passed into int
			int id = Integer.parseInt(inputs[2]); // args String
			int secNum = Integer.parseInt(inputs[3]); // args String passed into int

			switch (choice) {
				case 0:
					model.initializeStudent(name, id);
				case 1:
					String searchedCourse = model.searchCourse(name, id);
					socketOut.println(searchedCourse);
					// socketOut.println("Option 1");
					break;
				case 2:
					String addedCourse = model.addCourse(name, id, secNum);
					socketOut.println(addedCourse);
					// socketOut.println("option 2");
					break;
				case 3:
					String remove = model.removeCourse(name, id);
					socketOut.println(remove);
					// socketOut.println("option 3");
					break;
				case 4:
					String fullCatalogue = model.viewAllCourses();
					socketOut.println(fullCatalogue);
					// socketOut.println("option 4");
					break;
				case 5:
					String takenCourses = model.coursesTaken();
					socketOut.println(takenCourses);
					// socketOut.println("option 5");
					break;
				default:
					socketOut.println("default");
					closeConnection();
					running = false;
					break;
			}
		}
	}

	/**
	 * Closes the connection with client
	 */
	public void closeConnection() {
		try {
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
		System.out.println("Client Disconnected!!");
	}

	/**
	 * Runs the server side
	 * 
	 * @param args String argument
	 */
	public static void main(String[] args) {
		ServerCommunication serverCom = new ServerCommunication(9898);
		if (serverCom.running == true) {
			serverCom.closeConnection(); // close connection if connection is not closed before in default case
		}
	}
}