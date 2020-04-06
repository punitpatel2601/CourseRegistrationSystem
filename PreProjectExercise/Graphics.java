import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class Graphics extends JFrame {
    private Actions actions;
    private PrintWriter printer;

    public Graphics(int wPixels, int hPixels) {
        prepareGUI(wPixels, hPixels);
    }

    private void prepareGUI(int width, int height) {
        setTitle("ProjectApplication");
        setSize(width, height);
        setLayout(new BorderLayout());

        add("North", createNPanel());
        add("Center", createCPanel());
        add("South", createSPanel());
        
        setVisible(true);
    }

    private JPanel createNPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        JLabel jl = new JLabel("An Application to manage Student Records");
        jl.setHorizontalAlignment(0);

        p.add("Center", jl);

        return p;
    }

    private JPanel createCPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        JTextArea ta = new JTextArea();
        ta.setEnabled(false);
        JScrollPane sp = new JScrollPane(ta);

        p.add("Center", sp);

        return p;
    }

    private JPanel createSPanel() {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());

        JButton insert = new JButton("Insert");
        JButton find = new JButton("Find");
        JButton browse = new JButton("Browse");
        JButton createTree = new JButton("Create Tree from File");

        p.add(insert);
        p.add(find);
        p.add(browse);
        p.add(createTree);

        // add actions to these buttons here
        insert.addActionListener((ActionEvent e) -> {
            insertGUI();
        });
        find.addActionListener((ActionEvent e) -> {
            findGUI();
        });
        browse.addActionListener((ActionEvent e) -> {
            browseGUI();
        });
        createTree.addActionListener((ActionEvent e) -> {
            createTreeGUI();
        });
        return p;
    }

    //private JPanel findGUI(){

    //}

    private JPanel browseGUI(){
    	StringWriter buffer = new StringWriter();
    	PrintWriter printer = new PrintWriter(buffer);
    	JPanel i = new JPanel();
    	i.setLayout(new FlowLayout());
    	JTextArea textArea = new JTextArea();
    	JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
    												   JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	actions.browse(printer);
    	String contents = buffer.toString();
    	textArea.setText(contents);

    	this.add(scroll);
    	this.setVisible(true);
    	return i;
	}

    private void createTreeGUI() {
    }

    private void findGUI() {
        int stuID;
        try {
            stuID = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the Student's ID: "));
            if (stuID < 1) {
                JOptionPane.showMessageDialog(null, "ID cannot be less than one.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid ID.");
        }
        // check for the stuID in tree.
    }
    
   // private JPanel createTreeGUI() {
    	
    //}

    private void insertGUI() {
        JPanel jp = new JPanel();
        JTextField stID = new JTextField(8);
        JTextField faculty = new JTextField(10);
        JTextField major = new JTextField(10);
        JTextField year = new JTextField(5);

        jp.setLayout(new FlowLayout());

        jp.add(new JLabel("Enter the student ID: "));
        jp.add(stID);
        jp.add(new JLabel("Enter Faculty: "));
        jp.add(faculty);
        jp.add(new JLabel("Enter student's major: "));
        jp.add(major);
        jp.add(new JLabel("Enter the year: "));
        jp.add(year);

        int res = JOptionPane.showConfirmDialog(null, jp, "Insert a new node", JOptionPane.OK_CANCEL_OPTION);
        // checking the values
        if (res == JOptionPane.OK_OPTION) {
            System.out.println("id: " + stID.getText());
            System.out.println("f: " + faculty.getText());
            System.out.println("mjr: " + major.getText());
            System.out.println("y: " + year.getText());
        }
    }

    public static void main(String[] args) {
        new Graphics(600, 450);
    }
}
