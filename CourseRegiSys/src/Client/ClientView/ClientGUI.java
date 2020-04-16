package Client.ClientView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.ClientController.ClientCommunication;

/**
 * Creates the graphical user interface for the user/Client computer. Passes the
 * input to ClientCommunication class
 * 
 * @author Punit Patel
 * @author Armaan Mohar
 * @author Tom Pritchard
 * 
 * @since April 08, 2020
 * @version 1.0 (beta)
 */
public class ClientGUI extends GUI {

    /**
     * serial id
     */
    private static final long serialVersionUID = 42L;


    /**
     * Creates and Initializes the GUI for the user
     * 
     * @param ccm a pointer to ClientCommunication class
     */
    public ClientGUI(View v) {
        //actions = ccm;
        theView = v;
        studentName = "";
        studentId = -1;
        valid = "";
        tGUI();
    }

    public void tGUI(){
        detailsEntered = false;

        jta = new JTextArea(
                "Welcome!\n This is a course registration system (beta).\n Please start by entering your details..\n\nCRITICAL WARNING: You can not do anything without entering your details.");
        jta.setMargin(new Insets(3, 7, 3, 5));
        jta.setEditable(false);

        prepareGUI();
    }

    /**
     * Prepares GUI for the user to interact with server
     */
    public void prepareGUI() {
        setTitle("Course Registration System");
        setSize(600, 750);
        setLayout(new GridLayout(2, 1));

        JScrollPane jsp = new JScrollPane(jta);

        add(jsp);
        add(addButtons());

        setVisible(true);
    }

    /**
     * Creates the JPanel which has multiple buttons in order to get choice of user
     * 
     * @return jp JPanel created
     */
    public JPanel addButtons() {
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, 1));

        JButton search = new JButton("Search courses in Course Catalogue");
        JButton addCourse = new JButton("Add course to Student's course");
        JButton remove = new JButton("Remove course from Student's courses");
        JButton viewAll = new JButton("View all the courses in Catalogue");
        JButton viewStuCourses = new JButton("View all the courses taken by Student");
        JButton enterDetails = new JButton("Enter details");
        JButton quit = new JButton("Quit the application");

        // setting visibilities of buttons
        search.setVisible(detailsEntered);
        addCourse.setVisible(detailsEntered);
        remove.setVisible(detailsEntered);
        viewAll.setVisible(detailsEntered);
        viewStuCourses.setVisible(detailsEntered);
        enterDetails.setVisible(!detailsEntered);
        quit.setVisible(true);

        jp.add(new JLabel(" "));
        jp.add(enterDetails);

        // creating spaces between buttons using JLabel
        jp.add(search);
        jp.add(new JLabel(" "));
        jp.add(addCourse);
        jp.add(new JLabel(" "));
        jp.add(remove);
        jp.add(new JLabel(" "));
        jp.add(viewAll);
        jp.add(new JLabel(" "));
        jp.add(viewStuCourses);
        jp.add(new JLabel(" "));
        jp.add(quit);

        enterDetails.addActionListener((ActionEvent e) -> {
            //logIn();
            while(getInfo() == null){
            guiSerOutput(getInfo());
            }
            detailsEntered = true;

            // changing visibilty
            search.setVisible(detailsEntered);
            addCourse.setVisible(detailsEntered);
            remove.setVisible(detailsEntered);
            viewAll.setVisible(detailsEntered);
            viewStuCourses.setVisible(detailsEntered);
            enterDetails.setVisible(!detailsEntered);
            quit.setVisible(true);
        });
        search.addActionListener((ActionEvent e) -> {
            guiSerOutput(searchCourse());
        });
        addCourse.addActionListener((ActionEvent e) -> {
            guiSerOutput(addTheCourse());
        });
        remove.addActionListener((ActionEvent e) -> {
            guiSerOutput(removeCourse());
        });
        viewAll.addActionListener((ActionEvent e) -> {
            guiSerOutput(viewAllCourses());
        });
        viewStuCourses.addActionListener((ActionEvent e) -> {
            guiSerOutput(studentCourses());
        });
        quit.addActionListener((ActionEvent e) -> {
            quit();
        });

        return jp;
    }

    /**
     * Creates another GUI and shows the Server output on it
     * 
     * @param serverOutput String containing server output
     */
    public void guiSerOutput(String serverOutput) {
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
    public void quit() {
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
    public String studentCourses() {
        return theView.getAction().showStudentCourses();
    }

    /**
     * Returns allCourses to the buttons, allCourses are received from server
     * through clientCommunication
     * 
     * @return allCourses String
     */
    public String viewAllCourses() {
        return theView.getAction().viewAllCourses();
    }

    /**
     * Invokes remove course in server
     * 
     * @return confirmation String confirming the status of removal
     */
    public String removeCourse() {
        callForInput(false);
        return theView.getAction().removeCourse(cName, cID);
    }

    /**
     * Invokes the server to add the course
     * 
     * @return String confirmation of addition of course
     */
    public String addTheCourse() {
        callForInput(true); // true prompts for input of section number
        return theView.getAction().addCourse(cName, cID, cSec);
    }

    /**
     * Searches for the course
     */
    public String searchCourse() {
        callForInput(false);
        return theView.getAction().searchCourse(cName, cID);
    }

    /**
     * Helps other functions to get input for course name and id
     */
    public void callForInput(boolean act) {
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
    public String getInfo() {
        JFrame log = new JFrame();
            JPanel logIn = new JPanel(new GridLayout(3,1));

            JLabel user = new JLabel();
            JLabel pw = new JLabel("Enter password:");
            JTextField userName = new JTextField(20);
            JTextField passw = new JTextField(20);

            user.setText("Username: ");
            pw.setText("ID: ");

            JButton submit = new JButton("SUBMIT");
            log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            logIn.add(user);
            logIn.add(pw);
            logIn.add(userName);
            logIn.add(passw);
            logIn.add(submit);

            log.add(logIn, BorderLayout.CENTER);
            log.setTitle("Please login here");
            log.setSize(300,100);
            log.setVisible(true);

            submit.addActionListener((ActionEvent e) ->{
                while(studentName == null && studentId == -1){
                studentName = userName.getText();
                studentId = Integer.parseInt(passw.getText());
                valid = validateCredentials(studentName, studentId);
                }
                log.dispose();
            });

            return valid;

        //return theView.getAction().passStudentInfo(studentName, studentId);
    }

    public String validateCredentials(String n, int p){
        return theView.getAction().passStudentInfo(n, p);
    }


    /**
     * Creates input dialog and asks for input from user, sets the input as cSec
     */
    public void callInputForSection() {
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
    public int callInputForID() {
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
    public String callInputForName() {
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