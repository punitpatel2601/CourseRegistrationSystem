package Client.ClientView;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import Client.ClientController.ClientCommunication;


public abstract class GUI extends JFrame {    
        /**
         * serial id
         */
        static final long serialVersionUID = 2L;
    
        /**
         * Links GUI to communication class, acts as action listener to buttons in GUI
         */
        //ClientCommunication actions;
    
        /**
         * Text area where the output is shown to user
         */
        JTextArea jta;
    
        /**
         * Name of course entered by user
         */
        String cName;
    
        /**
         * Id of the course
         */
        int cID;
    
        /**
         * Section of the class
         */
        int cSec;
    
        /**
         * Name of the student accessing system
         */
        String studentName;
    
        /**
         * Student ID
         */
        int studentId;
    
        /**
         * boolean for showing different gui
         */
        boolean detailsEntered;

        View theView;
    
        /**
         * Creates and Initializes the GUI for the user
         * 
         * @param ccm a pointer to ClientCommunication class
         */
        public abstract void tGUI();
    
        /**
         * Prepares GUI for the user to interact with server
         */
        public abstract void prepareGUI();
    
        /**
         * Creates the JPanel which has multiple buttons in order to get choice of user
         * 
         * @return jp JPanel created
         */
        abstract JPanel addButtons();
    
        /**
         * Creates another GUI and shows the Server output on it
         * 
         * @param serverOutput String containing server output
         */
        void guiSerOutput(String serverOutput){
            jta.setText(""); // resetting text area
            if (serverOutput == null) { // checking if null response from server
                jta.setText("Error in your input, Server didn't respond!");
                return;
            }
    
            // showing server output to text area
            String[] outputs = serverOutput.split("#"); // splitting different lines by #
            for (String o : outputs) { // showing output
                jta.append(o);
                jta.append("\n");
            }
        }
    
        /**
         * Quits the applications, invokes other quit methods
         */
        void quit(){
            int res = JOptionPane.showConfirmDialog(null, "Press OK to quit.", "Quit?", JOptionPane.OK_CANCEL_OPTION);
    
            if (res == JOptionPane.OK_OPTION) { // exit only if ok is pressed
                theView.getAction().closeCon();
                System.exit(0);
            } else if (res == JOptionPane.CANCEL_OPTION) { // otherwise back to program
                return;
            }
    
            return;
        }
    
        /**
         * Returns student courses to the buttons, student courses are received from
         * server through clientCommunication
         * 
         * @return studentCourses String
         */
        abstract String studentCourses();
    
        /**
         * Returns allCourses to the buttons, allCourses are received from server
         * through clientCommunication
         * 
         * @return allCourses String
         */
        String viewAllCourses() {
            return theView.getAction().viewAllCourses();
        }
    
        /**
         * Invokes remove course in server
         * 
         * @return confirmation String confirming the status of removal
         */
        abstract String removeCourse();
    
        /**
         * Invokes the server to add the course
         * 
         * @return String confirmation of addition of course
         */
        abstract String addTheCourse();
    
        /**
         * Searches for the course
         */
         String searchCourse(){
            callForInput(false);
            return theView.getAction().searchCourse(cName, cID);
        }
    
        /**
         * Helps other functions to get input for course name and id
         */
        void callForInput(boolean act)
        {
            this.cName = callInputForName();
            if (cName.isEmpty() || (cName.compareTo(" ") == 0) || cName == null) {
                JOptionPane.showMessageDialog(null, "Invalid name entered, Please enter a String", "Error!",
                        JOptionPane.ERROR_MESSAGE);
                return; // don't ask for id input if name not entered correctly
            }
            this.cID = callInputForID();
            this.cName = this.cName.toUpperCase();
            if (act == true) {
                callInputForSection();
            }
        }
    
        /**
         * Prompt the user to enter their name and id to access the system, keeps asking
         * till input is not valid
         */
        abstract String getInfo();
    
        /**
         * Creates input dialog and asks for input from user, sets the input as cSec
         */
        void callInputForSection()
        {
            int sec = 1;
            try {
                sec = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Section Number: "));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid section entered, Please try again", "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
            this.cSec = sec;
        }
    
        /**
         * Creates input dialog and asks for input from user
         * 
         * @return id integer id entered
         */
        int callInputForID(){
            int id = -1;
            try {
                id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Course ID: "));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid ID entered, Please enter only numeric value", "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
    
            return id;
        }
    
        /**
         * Creates input dialog and asks for input from user
         * 
         * @return name first word of string of name
         */
        String callInputForName(){
            String name = "";
            try {
                name = JOptionPane.showInputDialog(null, "Enter the name of the Course");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid name entered, Please enter a String", "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
            String[] mulWords = name.split(" ");
            if (mulWords[0].isEmpty()) {
                return mulWords[1]; // return second words if first words entered was space or empty
            }
    
            return mulWords[0]; // only accepting the first word entered as the name
        }
}
