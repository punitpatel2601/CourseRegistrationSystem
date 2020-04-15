package Client.ClientView;

import javax.swing.JOptionPane;

import Client.ClientController.ClientCommunication;

public class View {
    private ClientCommunication action;
    private ClientGUI cg;
    private AdminGUI ag;

    public View(ClientCommunication ccm){
        action = ccm;
       String t = getDetails();
       runView(t);
    }

    public String getDetails(){
        String userType = "";
        while(!userType.equals("ADMIN") || !userType.equals("STUDENT")){
        try {
            userType = JOptionPane.showInputDialog(null, "Are you a Student or Admin?");
            userType = userType.toUpperCase();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid type entered, Please enter a String", "Error!",
                        JOptionPane.ERROR_MESSAGE);
                        continue;
                        
        }
        if(userType.equals("ADMIN"))
        break;

        if(userType.equals("STUDENT"))
        break;
        

    }

        if(userType.equals("ADMIN")){
            return userType;
        }

        if(userType.equals("STUDENT")){
            return userType;
        }

        return null;

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

   // public static void main(String [] args){
     //   View myView = new View();
    //}


}