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
     * Creates and Initializes controller GUI for user
     * Presents welcome panel
     * @param ccm
     */
    public View(ClientCommunication ccm){
        super("Welcome to Course Registration System");
        setSize(100,100);
        action = ccm;
      welcome();
    }

    /**
     * The welcome panel
     * Runs the specified view
     */
    public void welcome(){
        JPanel p = new JPanel();
        FlowLayout cl = new FlowLayout();
        p.setLayout(cl);
        JButton admin = new JButton("Admin log in");
        JButton user = new JButton("Student log in");
        admin.setVisible(true);
        user.setVisible(true);

        //setSize(250,250);
        p.add(admin);
        p.add(user);
        
        setContentPane(p);
        setVisible(true);

       

        admin.addActionListener((ActionEvent e) ->{
            String a = getType("admin");
            runView(a);
        });

        user.addActionListener((ActionEvent e) ->{
            String s = getType("student");
            runView(s);
        });
        return;
        
    }

    /**
     * getter function which capitalizes input
     * @param t
     * @return
     */
    public String getType(String t){
        t = t.toUpperCase(); 
        return t;       
    }


    /**
     * Runs the view based on user type
     * @param userType
     */
    public void runView(String userType){
        if(userType.equals("ADMIN")){
           ag = new AdminGUI(this);
        }
 
        if(userType.equals("STUDENT")){
            cg = new ClientGUI(this);
        }
    }

    /**
     * getter function to get ClientCommunication
     * @return
     */
    public ClientCommunication getAction(){
        return action;
    }

   // public static void main(String [] args){
     //   View myView = new View();
    //}


}