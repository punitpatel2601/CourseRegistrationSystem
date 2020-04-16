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

public class AdminGUI extends GUI { 
        /**
         * serial id
         */
        private static final long serialVersionUID = 1L;

        private int adminId;

        private String adminName;
    
        /**
         * Creates and Initializes the GUI for the user
         * 
         * @param ccm a pointer to ClientCommunication class
         */
        public AdminGUI(View v) {
            //actions = ccm;
            theView = v;
            adminName = "";
            adminId = -1;
            valid = "";
            tGUI();
        }
    
        public void tGUI(){
            detailsEntered = false;
    
            jta = new JTextArea(
                    "Welcome!\n This is the Admin version of course registration system (beta).\n Please start by entering your details..\n\nCRITICAL WARNING: You can not do anything without entering your details.");
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
            JButton addNewCourse = new JButton("Add course to Course Catalogue");
            JButton quit = new JButton("Quit the application");
    
            // setting visibilities of buttons
            search.setVisible(detailsEntered);
            addCourse.setVisible(detailsEntered);
            remove.setVisible(detailsEntered);
            viewAll.setVisible(detailsEntered);
            viewStuCourses.setVisible(detailsEntered);
            enterDetails.setVisible(!detailsEntered);
            addNewCourse.setVisible(detailsEntered);
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
            jp.add(addNewCourse);
            jp.add(new JLabel(" "));
            jp.add(quit);
    
            enterDetails.addActionListener((ActionEvent e) -> {
                String s = "";
                while(s == null){
                 s = getInfo(); 
                guiSerOutput(s);
                }
                detailsEntered = true;
    
                if(s.equals("VALID")){
                // changing visibilty
                search.setVisible(detailsEntered);
                addCourse.setVisible(detailsEntered);
                remove.setVisible(detailsEntered);
                viewAll.setVisible(detailsEntered);
                viewStuCourses.setVisible(detailsEntered);
                enterDetails.setVisible(!detailsEntered);
                addNewCourse.setVisible(detailsEntered);
                quit.setVisible(true);
                } else{
                    JOptionPane.showMessageDialog(null, "Incorrect Credentials!");
                    quit.setVisible(true);
                }
               // quit.setVisible(true);
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
            addNewCourse.addActionListener((ActionEvent e) -> {
                guiSerOutput(addNewCourse());
            });
            quit.addActionListener((ActionEvent e) -> {
                quit();
            });
    
            return jp;
        }

        /**
         * adds a new course to database
         * @return
         */
        public String addNewCourse(){
            callForInput(true);
            return theView.getAction().addNewCourse(this.cName, this.cID, this.cSec); 
        }
        
        /**
         * I am guessing an Admin will find the student in database first then remove from them
         * Returns student courses to the buttons, student courses are received from
         * server through clientCommunication
         * 
         * @return studentCourses String
         */
        public String studentCourses() {
            while (true) {
                try {
                    studentName = JOptionPane.showInputDialog(null, "Please enter Student name to access");
                    studentName = studentName.toUpperCase();
                    studentId = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter Student's ID number"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Invalid details", "Error!", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                break;
            }
    
            if (studentName.contains(" ")) {
                String[] names = studentName.split(" ");
                if (names[0].isEmpty()) {
                    studentName = names[1]; // return second words if first words entered was space or empty
                }
                studentName = names[0];
            }
            
            return theView.getAction().showStudentCourses();   //make a new admin show function or pass name & id
        }
    
        /**
         * I am guessing an Admin will find the student in database first then remove from them
         * Invokes remove course in server
         * 
         * @return confirmation String confirming the status of removal
         */
        public String removeCourse() {
            while (true) {
                try {
                    studentName = JOptionPane.showInputDialog(null, "Please enter Student name to access");
                    studentName = studentName.toUpperCase();
                    studentId = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter Student's ID number"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Invalid details", "Error!", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                break;
            }
    
            if (studentName.contains(" ")) {
                String[] names = studentName.split(" ");
                if (names[0].isEmpty()) {
                    studentName = names[1]; // return second words if first words entered was space or empty
                }
                studentName = names[0];
            }


            callForInput(false);
            return theView.getAction().removeCourse(cName, cID);
        }
    
        /**
         * I am guessing an Admin will find the student in database first then remove from them
         * Invokes the server to add the course
         * 
         * @return String confirmation of addition of course
         */
        public String addTheCourse() {
            while (true) {
                try {
                    studentName = JOptionPane.showInputDialog(null, "Please enter Student name to access");
                    studentName = studentName.toUpperCase();
                    studentId = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter Student's ID number"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Invalid details", "Error!", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                break;
            }
    
            if (studentName.contains(" ")) {
                String[] names = studentName.split(" ");
                if (names[0].isEmpty()) {
                    studentName = names[1]; // return second words if first words entered was space or empty
                }
                studentName = names[0];
            }

            callForInput(true); // true prompts for input of section number
            return theView.getAction().addCourse(cName, cID, cSec);
        }
        
        /**
         * Admin Credentials
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
                while(adminName == null && adminId == -1){
                adminName = userName.getText();
                adminId = Integer.parseInt(passw.getText());
                valid = validateCredentials(adminName, adminId);
                }
                log.dispose();
            });
            return valid;

            //return theView.getAction().passAdminInfo(adminName, adminId);           
    }

    public String validateCredentials(String n, int p){
        return theView.getAction().passAdminInfo(n, p);
    }
}