package Client.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


import Client.ClientController.ClientCommunication;

public class View extends JFrame {
    private ClientCommunication action;
    private ClientGUI cg;
    private AdminGUI ag;

    public View(ClientCommunication ccm){
        super("Welcome to Course Registration System");
        setSize(100,100);
        action = ccm;
      welcome();
    }

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

    public String getType(String t){
        t = t.toUpperCase(); 
        return t;       
    }


    public void runView(String userType){
        if(userType.equals("ADMIN")){
           ag = new AdminGUI(this);
        }
 
        if(userType.equals("STUDENT")){
            cg = new ClientGUI(this);
        }
    }

    public ClientCommunication getAction(){
        return action;
    }

    //public static void main(String [] args){
   //View myView = new View();
  //  }


}