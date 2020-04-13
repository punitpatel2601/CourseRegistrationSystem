package Client.ClientController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Client.ClientView.ClientGUI;

public class ClientCommunication {
	private Socket aSocket; // socket
	private PrintWriter socketOut; // msg to send to server
	private BufferedReader socketIn; // msg to read from socket
	private String ret; // return string to clientGUI

	public ClientCommunication(String serverName, int port) {
		ret = "";
		new ClientGUI(this);

		try {
			aSocket = new Socket(serverName, port);
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public String searchCourse(String name, int id) {
		String line = "1 ";
		line += name + " " + id;
		ret = communicate(line);

		return ret;
	}

	public String addCourse(String name, int id, int sec) {
		String line = "2 ";
		line += name + " " + id + " " + sec;
		ret = communicate(line);

		return ret;
	}

	public String removeCourse(String name, int id) {
		String line = "3 ";
		line += name + " " + id;
		ret = communicate(line);

		return ("Course removed, " + ret);
	}

	public String viewAllCourses() {
		ret = communicate("4 allCourses 0");
		System.out.println("this -> " + ret);

		return ret;
	}

	public String showStudentCourses() {
		ret = communicate("5 stuCourses 0");
		System.out.println("this -> " + ret);

		return ret;
	}

	public void closeCon() {
		communicate("6 closeCon 0");
		try {
			socketIn.close();
			socketOut.close();
			System.out.println("Server connection aborted!!");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public String communicate(String line) {
		String response = "";

		try {
			socketOut.println(line); // sending info string to server
			response = socketIn.readLine(); // recieving info string from server
			System.out.println(response);
		} catch (Exception e) {
			System.out.println("Error in sending/receiving data to/from server\nError name: " + e.getMessage());
		}

		return response;
	}

	public static void main(String[] args) {
		new ClientCommunication("localhost", 9898); // start new client
	}
}