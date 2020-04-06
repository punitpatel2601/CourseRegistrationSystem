import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Actions {
    private BinSearchTree myTree;
    private Graphics graphics;

    public Actions(Graphics g, BinSearchTree t){
        this.graphics = g;
        this.myTree = t;
    }

    public void insert(String id, String fac, String major, String yr){
            myTree.insert(id, fac, major, yr);
    }

    public void find(int id){
        String ID = String.valueOf(id);
        Node n = myTree.find(myTree.root, ID);
        if( n == null){
            JOptionPane.showMessageDialog(null,"No such student found!", "Error Message", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, n.data.toString(), "Student Found", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void createTree(String fileName){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = null;
            while((line = br.readLine()) != null){
            String [] columns = line.split(" ");
            myTree.insert(columns[0], columns[1], columns[2], columns[3]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
    }

    public void browse(){
        myTree.print_tree(myTree.root, out);
    }
}