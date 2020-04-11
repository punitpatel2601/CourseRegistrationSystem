package Server.ServerController;

import Server.ServerModel.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCommunication {
	private ServerSocket serverSocket; // server socket
	private Socket aSocket; // socket to which client is binded to
	private BufferedReader socketIn; // msg to write to client
	private PrintWriter socketOut; // msg to read from socket
	private ExecutorService pool; // threadpool
	private Model model;

	public ServerCommunication(int port) {
		try {
			serverSocket = new ServerSocket(port);
			pool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			e.printStackTrace();
		}
		model = new Model();
	}

	public void initializeServer() {
		try {
			aSocket = serverSocket.accept();
			System.out.println("Connection accepted by server!");
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(), true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void runServer() {
		String line = "";
		boolean running = true;
		while (running) {
			System.out.println("\n");
			try {
				line = socketIn.readLine();
			} catch (Exception e) {
				e.printStackTrace();
				running = false;
			}
			if (line == "" || line == null) {
				break;
			}
			String[] inputs = line.split(" ");
			int choice = Integer.parseInt(inputs[0]);
			 String courseName = inputs[1];
			 int courseId = Integer.parseInt(inputs[2]);
			switch (choice) {
				case 1:
					 String searchedCourse = model.searchCourse(courseName, courseId);
					 socketOut.println(searchedCourse);
					socketOut.println("Option 1");
					break;
				case 2:
					 String addedCourse = model.addCourse(courseName, courseId);
					 socketOut.println(addedCourse);
					socketOut.println("option 2");
					break;
				case 3:
					String remove = model.removeCourse(courseName, courseId);
					socketOut.println(remove);
					socketOut.println("option 3");
					break;
				case 4:
					 String fullCatalogue = model.viewAllCourses();
					 socketOut.println(fullCatalogue);
					socketOut.println("option 4");
					break;
				case 5:
					 String takenCourses = model.coursesTaken();
					 socketOut.println(takenCourses);
					socketOut.println("option 5");
					break;
				default:
					socketOut.println("default");
					closeConnection();
					running = false;
					break;
			}
		}
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
		System.out.println("Server is initialized...waiting for connection \n");
		serverCom.initializeServer();
		serverCom.runServer();
	}
}