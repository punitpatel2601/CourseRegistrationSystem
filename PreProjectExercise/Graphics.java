import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * creates graphical user interface for user to access the input file and browse
 * the data, also allows user to insert new data or search for any data. Acts as
 * frontend of the program
 * 
 * @author P. Patel
 * @author A. Mohar
 * @author T. Pritchard
 * @version 1.0
 * @since April 6, 2020
 * 
 */
public class Graphics extends JFrame {

    /**
     * handles backend functions of buttons
     */
    private Actions actions;

    /**
     * object from class BinSearchTree
     */
    private PrintWriter printer;

    /**
     * text area where the data is to be printed
     */
    private JTextArea jta;

    /**
     * connects frontend(GUI) to backend starts the GUI
     * 
     * @param wPixels width of window in pixels
     * @param hPixels height of window in pixels
     */
    public Graphics(int wPixels, int hPixels) {
        actions = new Actions(this);
        prepareGUI(wPixels, hPixels);
    }

    /**
     * Creates outer frame of the gui window
     * 
     * @param width  width of the frame
     * @param height height of the frame
     */
    private void prepareGUI(int width, int height) {
        setTitle("ProjectApplication");
        setSize(width, height);
        setLayout(new BorderLayout());

        add("North", createNPanel());
        add("Center", createCPanel());
        add("South", createSPanel());

        setVisible(true);
    }

    /**
     * Creates the panel that is inserted in North part of the frame layout
     * 
     * @return p java panel
     */
    private JPanel createNPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        JLabel jl = new JLabel("An Application to manage Student Records");
        jl.setHorizontalAlignment(0);

        p.add("Center", jl);

        return p;
    }

    /**
     * Creates the panel that is inserted in center part of the frame layout
     * 
     * @return p java panel
     */
    private JPanel createCPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        jta = new JTextArea();
        jta.setEnabled(true);
        JScrollPane sp = new JScrollPane(jta);

        p.add("Center", sp);

        return p;
    }

    /**
     * Creates the panel that is inserted in south part of the frame layout, also
     * calls the actionlistener on the buttons
     * 
     * @return p java panel
     */
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

    /**
     * displays all the information of the tree of the textArea in window
     */
    private void browseGUI() {
        StringWriter buffer = new StringWriter();
        PrintWriter printer = new PrintWriter(buffer);

        actions.browse(printer);

        String content = buffer.toString();
        jta.setText(content);
    }

    /**
     * prompts user for filename and then calls backend to load data from file to
     * BinSearchTree
     */
    private void createTreeGUI() {
        String filename = "";
        String ext = ".txt";
        try {
            filename = JOptionPane.showInputDialog(null, "Please enter filename: ");
            if (filename == null || filename == "" || !filename.toLowerCase().contains(ext.toLowerCase())) {
                JOptionPane.showInputDialog(null, "File has to be in format: nameofFile.txt");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid filename!!");
        }
        actions.createTree(filename);

    }

    /**
     * creates another Graphical user interface which allows user to search for
     * student by id
     */
    private void findGUI() {
        int stuID = 0;
        try {
            while (stuID < 1) {
                stuID = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the Student's ID: "));
            }
            actions.find(stuID);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Must be a numeric number!");
        }
    }

    /**
     * creates a gui which prompts user for id, faculty, major, and year of new
     * student
     */
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
            if ((stID.getText().length() != 0) && (faculty.getText().length() != 0) && (major.getText().length() != 0)
                    && (year.getText().length() != 0)) {
                if (stID.getText().matches("[a-zA-Z_]+")) {
                    JOptionPane.showMessageDialog(null, "ID must be a numeric number");
                    return;
                }

                if (year.getText().matches("[a-zA-Z_]+")) {
                    JOptionPane.showMessageDialog(null, "Year must be a numeric number");
                    return;
                }

                if (!faculty.getText().matches("[a-zA-Z_]+")) {
                    JOptionPane.showMessageDialog(null, "Faculty must be a string of alphabets");
                    return;
                }

                if (!major.getText().matches("[a-zA-Z_]+")) {
                    JOptionPane.showMessageDialog(null, "Major must be a string of alphabets");
                    return;
                }

                actions.insert(stID.getText(), faculty.getText(), major.getText(), year.getText());
                browseGUI();
            } else {
                JOptionPane.showMessageDialog(null, "Please enter all informations", "Incomplete details",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new Graphics(600, 450);
    }
}
