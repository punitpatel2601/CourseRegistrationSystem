package Server.ServerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import Server.ServerModel.*;

/**
 * Creates threads which enables server to be used by multiple clients
 * simultenously
 */
public class ServerThread extends Thread {

    /**
     * Socket where client connects
     */
    private Socket socket;

    /**
     * Input reader from socket
     */
    private BufferedReader socketIn;

    /**
     * Message sender to client
     */
    private PrintWriter socketOut;

    /**
     * boolean to track the status of server (running or not)
     */
    private boolean running;

    /**
     * Model class object instance
     */
    private Model model;

    /**
     * Instance of the server communication
     */
    private ServerCommunication serCom;

    /**
     * Constructs a new serverThread for each different client
     * 
     * @param soc socket
     */
    public ServerThread(Socket soc, ServerCommunication serCom) {
        socket = soc;
        try {
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            System.out.println("Socket reading failed.");
        }
        running = true;
        this.serCom = serCom;
    }

    /**
     * Runs the thread
     */
    public void run() {
        String line = "";

        while (running) {
            try {
                line = socketIn.readLine();
            } catch (Exception e) {
                running = false;
            }

            if (line.isEmpty() || line == null) { // double checks for wrong input in server
                socketOut.println("Invalid request string.");
                break;
            }

            String[] inputs = line.split(" "); // splits input string into different command and argument strings
            int choice = Integer.parseInt(inputs[0]); // commandString
            String name = inputs[1]; // args String passed into int
            int id = Integer.parseInt(inputs[2]); // args String
            int secNum = Integer.parseInt(inputs[3]); // args String passed into int

            switch (choice) {
                case 1:
                    String searchedCourse = model.searchCourse(name, id);
                    socketOut.println(searchedCourse);
                    break;
                case 2:
                    String addedCourse = model.addCourse(name, id, secNum);
                    socketOut.println(addedCourse);
                    break;
                case 3:
                    String remove = model.removeCourse(name, id);
                    socketOut.println(remove);
                    break;
                case 4:
                    String fullCatalogue = model.viewAllCourses();
                    socketOut.println(fullCatalogue);
                    break;
                case 5:
                    String takenCourses = model.coursesTaken();
                    socketOut.println(takenCourses);
                    break;
                case 6:
                    model = new Model(name, id);
                    socketOut.println("Welcome! #\t" + name + " - " + id
                            + "# # #Now you can use the system.. # # Please select from the following choices.");
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
     * Closes the connection of the serverthread with client
     */
    public void closeConnection() {
        try {
            socketIn.close();
            socketOut.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        System.out.println("\nClient Disconnected!!");
        serCom.clientDisconnect();
    }

}