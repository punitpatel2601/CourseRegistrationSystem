import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 * controls all backend functionality
 * Initializes database
 * Utilizes required functions from BinSearchTree
 * @author A. Mohar, 
 * @version 1.0
 * @since April 6, 2020
 * 
 */
public class Actions {
    /**
     * the tree
     */
    private BinSearchTree myTree;

    /**
     * the frontend GUI
     */
    private Graphics graphics;

    /** 
     * connects to frontend and initializes BinSearchTree
     * @param g
     */
    public Actions(Graphics g) {
        this.graphics = g;
        this.myTree = new BinSearchTree();
        // myTree.destroy();
    }

    /**
     * inserts new student into tree
     * @param id
     * @param fac
     * @param major
     * @param yr
     */
    public void insert(String id, String fac, String major, String yr) {
        myTree.insert(id, fac, major, yr);
    }

    /**
     * searches for student info based on id input
     * @param id
     */
    public void find(int id) {
        String ID = String.valueOf(id);
        Node n = myTree.find(myTree.root, ID);
        if (n == null) {
            JOptionPane.showMessageDialog(null, "No such student found!", "Error Message", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, n.data.toString(), "Student Found", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * opens specified file and fills the tree with file data
     * @param fileName
     * @throws FileNotFoundException
     */
    public void createTree(String fileName) throws FileNotFoundException{
        try {
            Scanner sc = new Scanner(new FileReader(fileName));
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] columns = new String[4];
                columns = line.split(" ");
                insert(columns[0], columns[1], columns[2], columns[3]);
            }
            sc.close();
        } catch(FileNotFoundException f){
            JOptionPane.showMessageDialog(null, f.getMessage(), "File not found", JOptionPane.ERROR_MESSAGE);
        }
         
    }

    /**
     * prints the whole tree out
     * @param printer
     * @throws IOException
     */
    public void browse(PrintWriter printer) throws IOException {
        try {
            if(!myTree.empty()){
            myTree.print_tree(myTree.root, printer);
            } else{
                JOptionPane.showMessageDialog(null, "First create a tree to initialize database!", "Tree is empty!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}