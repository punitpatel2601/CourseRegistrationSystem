package Client.ClientController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import Client.ClientView.ClientGUI;

/**
 * Creates the clientCommunication class and connects with server to pass the
 * information , Also acts as actionlistener class for buttons in ClientGUI
 * 
 * @author Punit Patel
 * @author Tom Pritchard
 * @author Armaan Mohar
 * 
 * @version 1.0 (beta)
 * @since April 10, 2020
 */
public class ClientCommunication {

	/**
	 * Socket
	 */
	private Socket aSocket;

	/**
	 * Message sender to server
	 */
	private PrintWriter socketOut;

	/**
	 * Message reader from server
	 */
	private BufferedReader socketIn; // message to read from socket

	/**
	 * Constructs the object of this class and connects it with server
	 * 
	 * @param serverName name of the server
	 * @param port       port where the server is hosted
	 */
	public ClientCommunication(String serverName, int port) {
		new ClientGUI(this);

		try {
			aSocket = new Socket(serverName, port);
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	/**
	 * Passes the student information to the server
	 * 
	 * @param studentName name of the student
	 * @param studentId   student id
	 */
	public String passStudentInfo(String studentName, int studentId) {
		String line = "6 ";
		line += studentName + " " + studentId + " 0";

		return communicate(line);
	}

	/**
	 * Searches the course in server database, returns server response to ClientGUI
	 * as string
	 * 
	 * @param name course name
	 * @param id   course id
	 * @return String of server response
	 */
	public String searchCourse(String name, int id) {
		String line = "1 ";
		line += name + " " + id + " 0";

		return communicate(line);
	}

	/**
	 * Adds new course to student in server, returns server response as string
	 * 
	 * @param name course name
	 * @param id   course id
	 * @param sec  course section
	 * @return string of server response
	 */
	public String addCourse(String name, int id, int sec) {
		String line = "2 ";
		line += name + " " + id + " " + sec;

		return communicate(line);
	}

	/**
	 * removes any course from the student, returns server response
	 * 
	 * @param name course name
	 * @param id   course id
	 * @return string of server response
	 */
	public String removeCourse(String name, int id) {
		String line = "3 ";
		line += name + " " + id + " 0";

		return communicate(line);
	}

	/**
	 * shows all the courses by invoking server functions and returning the response
	 * to ClientGUI
	 * 
	 * @return server response string
	 */
	public String viewAllCourses() {
		return communicate("4 allCourses 0 0");
	}

	/**
	 * shows all the student's courses by invoking server functions and returning
	 * the response to ClientGUI
	 * 
	 * @return server response string
	 */
	public String showStudentCourses() {
		return communicate("5 stuCourses 0 0");
	}

	/**
	 * Closes the connection to server and turns off the server
	 */
	public void closeCon() {
		communicate("7 closeCon 0 0");
		try {
			socketIn.close();
			socketOut.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
		System.out.println("Server connection aborted!!");
	}

	/**
	 * helps other functions to send the data to server
	 * 
	 * @param line string containing commands and argument to server
	 * @return server response
	 */
	public String communicate(String line) {
		String response = "";

		try {
			socketOut.println(line); // sending info string to server
			response = socketIn.readLine(); // receiving info string from server
		} catch (Exception e) {
			System.out.println("Error in sending/receiving data to/from server\nError name: " + e.getMessage());
			response = null;
		}

		return response;
	}

	/**
	 * Runs the client side
	 * 
	 * @param args String argument
	 */
	public static void main(String[] args) {
		new ClientCommunication("localhost", 9898); // start new client
	}
}