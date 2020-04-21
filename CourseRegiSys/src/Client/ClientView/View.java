package Client.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import Client.ClientController.ClientCommunication;

/**
 * Controls which view is presented to user
 * 
 * @author Punit Patel
 * @author Armaan Mohar
 * @author Tom Pritchard
 * 
 * @since April 17, 2020
 * @version 1.0 (beta)
 */
public class View extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * server connection
     */
    private ClientCommunication action;

    /**
     * instance of client view
     */
    private ClientGUI cg;

    /**
     * instance of admin view
     */
    private AdminGUI ag;

    /**
     * Creates and Initializes controller GUI for user Presents welcome panel
     * 
     * @param ccm
     */
    public View(ClientCommunication ccm) {
        super("Welcome to Course Registration System");
        setSize(550, 450);
        action = ccm;
        add(welcome());
    }

    /**
     * The welcome panel Runs the specified view
     */
    public JPanel welcome() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5, 3));
        JButton admin = new JButton("Admin");
        JButton user = new JButton("Student");

        p.add(new JLabel(" "));
        p.add(new JLabel(" "));
        p.add(new JLabel(" "));
        p.add(new JLabel(" "));
        p.add(admin);
        p.add(new JLabel(" "));
        p.add(new JLabel(" "));
        p.add(new JLabel(" "));
        p.add(new JLabel(" "));
        p.add(new JLabel(" "));
        p.add(user);
        p.add(new JLabel(" "));
        p.add(new JLabel(" "));
        p.add(new JLabel(" "));
        p.add(new JLabel(" "));

        setVisible(true);

        admin.addActionListener((ActionEvent e) -> {
            String a = getType("admin");
            runView(a);
        });

        user.addActionListener((ActionEvent e) -> {
            String s = getType("student");
            runView(s);
        });
        return p;
    }

    /**
     * getter function which capitalizes input
     * 
     * @param t
     * @return
     */
    public String getType(String t) {
        t = t.toUpperCase();
        return t;
    }

    /**
     * Runs the view based on user type
     * 
     * @param userType
     */
    public void runView(String userType) {
        if (userType.equals("ADMIN")) {
            ag = new AdminGUI(this);
        }

        if (userType.equals("STUDENT")) {
            new ClientGUI(this);
        }
    }

    /**
     * getter function to get ClientCommunication
     * 
     * @return
     */
    public ClientCommunication getAction() {
        return action;
    }

}