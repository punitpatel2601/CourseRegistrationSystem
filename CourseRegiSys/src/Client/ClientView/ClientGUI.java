package Client.ClientView;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import Client.ClientController.ClientCommunication;

public class ClientGUI extends JFrame {
    private ClientCommunication actions;
    private JTextArea jta;

    public ClientGUI(ClientCommunication ccm) {
        actions = ccm;
        jta = new JTextArea();
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);
        prepareGUI();
    }

    private void prepareGUI() {
        setTitle("Course Registration System");
        setSize(500, 500);
        setLayout(new GridLayout(2, 1));

        JScrollPane jsp = new JScrollPane(jta);

        add(addButtons());
        add(jsp);

        setVisible(true);
    }

    public JPanel addButtons() {
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        jp.setSize(450, 150);
        JButton search = new JButton("Search the Catalogue");
        JButton addCourse = new JButton("Add course");
        JButton remove = new JButton("Remove course");
        JButton viewAll = new JButton("View all the courses in Catalogue");
        JButton viewStuCourses = new JButton("View all the courses taken by students");
        JButton quit = new JButton("Quit");

        jp.add(search);
        jp.add(addCourse);
        jp.add(remove);
        jp.add(viewAll);
        jp.add(viewStuCourses);
        jp.add(quit);

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

    private void guiSerOutput(String serverOutput) {
        System.out.println("gui -> " + serverOutput);
        if (serverOutput == null) {
            jta.setText("Error in your input, Server can't process it!!");
            return;
        }
        jta.setText(serverOutput);
    }

    private void quit() {
        int res = JOptionPane.showConfirmDialog(null, "Press OK to quit.", "Quit?", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            actions.closeCon();
            System.exit(0);
        }
        return;
    }

    private String studentCourses() {
        return actions.showStudentCourses();
    }

    private String viewAllCourses() {
        return actions.viewAllCourses();
    }

    private String removeCourse() {
        String cName;
        int cID;
        cName = callInputForName();
        cID = callInputForID();

        System.out.println(cName + "\t" + cID);
        return actions.removeCourse(cName, cID);
    }

    private String addTheCourse() {
        String cName;
        int cID;
        cName = callInputForName();
        cID = callInputForID();

        System.out.println(cName + "\t" + cID);
        return actions.addCourse(cName, cID);
    }

    private String searchCourse() {
        String cName;
        int cID;
        cName = callInputForName();
        cID = callInputForID();

        System.out.println(cName + "\t" + cID);
        return actions.searchCourse(cName, cID);
    }

    private int callInputForID() {
        int id = -1;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Course ID: "));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid ID entered, Please enter only numeric value", "Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    private String callInputForName() {
        String name = "";
        try {
            name = JOptionPane.showInputDialog(null, "Enter the name of the course");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid name entered, Please enter a String", "Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
        String[] mulWords = name.split(" ");
        return mulWords[0];
    }
}