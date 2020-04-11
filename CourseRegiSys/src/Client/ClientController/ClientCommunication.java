package Client.ClientController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Client.ClientView.ClientGUI;

public class ClientCommunication {
	private Socket aSocket;
	private PrintWriter socketOut; // msg to send to server
	private BufferedReader socketIn; // msg to read from socket

	public ClientCommunication(String serverName, int port) {
		try {
			aSocket = new Socket(serverName, port);
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public String communicate(String line) {
		String response = "";

		try {
			socketOut.println(line);
			response = socketIn.readLine();
			System.out.println(response);
		} catch (Exception e) {
			System.out.println("Sending error: " + e.getMessage());
		}
		return response;
	}

	public String showStudentCourses() {
		String ret = communicate("1 stuCourses 0");
		System.out.println("this -> " + ret);
		return ret;
	}

	public String viewAllCourses() {
		String ret = communicate("2 allCourses 0");
		System.out.println("this -> " + ret);
		return ret;
	}

	public String removeCourse(String name, int id) {
		String ret = "";
		if (checkError(name, id)) {
			String line = "3";
			line = line + " " + name + " " + id;
			ret = communicate(line);
		} else {
			ret = null;
		}
		return ("Course removed, " + ret);
	}

	public String addCourse(String name, int id) {
		String ret = "";
		if (checkError(name, id) == false) {
			String line = "4";
			line = line + " " + name + " " + id;
			ret = communicate(line);
		} else {
			ret = null;
		}
		return ret;
	}

	public String searchCourse(String name, int id) {
		String ret = "";
		if (checkError(name, id) == false) {
			String line = "5";
			line = line + " " + name + " " + id;
			ret = communicate(line);
		} else {
			ret = null;
		}
		return ret;
	}

	public boolean checkError(String name, int id) {
		if (name.isEmpty() || name == null || id == -1) {
			return true;
		}
		return false;
	}

	public void closeCon() {
		try {
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public static void main(String[] args) {
		ClientCommunication clientCom = new ClientCommunication("localhost", 9898);
		ClientGUI cgi = new ClientGUI(clientCom);
	}
}