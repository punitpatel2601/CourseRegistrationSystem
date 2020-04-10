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

	public void communicate(String line) {
		String response = "";
		boolean running = true;
		try {
			while (running) {
				socketOut.println(line);
				// response = socketIn.readLine();
				System.out.println(response);
			}
		} catch (Exception e) {
			System.out.println("Sending error" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		ClientCommunication clientCom = new ClientCommunication("localhost", 9898);
		ClientGUI cgi = new ClientGUI();
	}

	public void showStudentCourses() {
		communicate("1");
	}

	public void viewAllCourses() {
		communicate("2");
	}

	public void removeCourse(String name, int id) {
		String line = "3 ";
		line = line + " " + name + " " + id;
		communicate(line);
	}

	public void addCourse(String name, int id) {
		String line = "4 ";
		line = line + " " + name + " " + id;
		communicate(line);
	}

	public void searchCourse(String name, int id) {
		String line = "5 ";
		line = line + " " + name + " " + id;
		communicate(line);
	}
}