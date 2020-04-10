package Client.ClientView;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ClientGUI extends JFrame {
    private ClientActions actions;

    public ClientGUI(int wPixels, int hPixels) {
        actions = new ClientActions(this);
        prepareGUI(wPixels, hPixels);
    }

    private void prepareGUI(int width, int height) {
        setTitle("Course Registration System");
        setSize(400, 300);
        setLayout(new FlowLayout());
        addButtons();

        setVisible(true);
    }

    public void addButtons() {
        JButton search = new JButton("Search the Catalogue");
        JButton addCourse = new JButton("Add course");
        JButton remove = new JButton("Remove course");
        JButton viewAll = new JButton("View all the courses in Catalogue");
        JButton viewStuCourses = new JButton("View all the courses taken by students");
        JButton quit = new JButton("Quit");

        add(search);
        add(addCourse);
        add(remove);
        add(viewAll);
        add(viewStuCourses);
        add(quit);

        search.addActionListener((ActionEvent e) -> {
            searchCourse();
        });
        addCourse.addActionListener((ActionEvent e) -> {
            addTheCourse();
        });
        remove.addActionListener((ActionEvent e) -> {
            removeCourse();
        });
        viewAll.addActionListener((ActionEvent e) -> {
            viewAllCourses();
        });
        viewStuCourses.addActionListener((ActionEvent e) -> {
            studentCourses();
        });
        quit.addActionListener((ActionEvent e) -> {
            quit();
        });
    }

    private void quit() {
        int res = JOptionPane.showConfirmDialog(null, "Press OK to quit.", "Quit?", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
        return;
    }

    private void studentCourses() {
        // actions.showStudentCourses();
    }

    private void viewAllCourses() {
        // actions.viewAllCourses();
    }

    private void removeCourse() {
        String cName;
        int cID;
        cName = callInputForName();
        cID = callInputForID();

        System.out.println(cName + "\t" + cID);
        // actions.removeCourse();
    }

    private void addTheCourse() {
        String cName;
        int cID;
        cName = callInputForName();
        cID = callInputForID();

        System.out.println(cName + "\t" + cID);
        // actions.addCourse();
    }

    private void searchCourse() {
        String cName;
        int cID;
        cName = callInputForName();
        cID = callInputForID();

        System.out.println(cName + "\t" + cID);
        // actions.searchCourse();
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
        return name;
    }

    public static void main(String[] args) {
        new ClientGUI(650, 400);
    }
}