import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.IllegalFormatException;

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
     * 
     */
    private JTextArea jta;

    /**
     * connects frontend(GUI) to backend starts the GUI
     * 
     * @param wPixels
     * @param hPixels
     */
    public Graphics(int wPixels, int hPixels) throws Exception {
        actions = new Actions(this);
        try {
            prepareGUI(wPixels, hPixels);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Creates outer frame
     * 
     * @param width
     * @param height
     */
    private void prepareGUI(int width, int height) throws Exception {
        setTitle("ProjectApplication");
        setSize(width, height);
        setLayout(new BorderLayout());

        add("North", createNPanel());
        add("Center", createCPanel());
        try {
            add("South", createSPanel());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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

        jta = new JTextArea();
        jta.setEnabled(true);
        JScrollPane sp = new JScrollPane(jta);

        p.add("Center", sp);

        return p;
    }

    private JPanel createSPanel() throws Exception, IOException {
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
            try {
                browseGUI();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        createTree.addActionListener((ActionEvent e) -> {
            try {
                createTreeGUI();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        return p;
    }

    /**
     * @throws IOException
     */
    private void browseGUI() throws IOException {
        StringWriter buffer = new StringWriter();
        PrintWriter printer = new PrintWriter(buffer);

        try {
            actions.browse(printer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String content = buffer.toString();
        jta.append(content);
    }

    /**
     * prompts user for filename and then calls backend to load data from file to BinSearchTree
     * @throws Exception
     */
    private void createTreeGUI() throws Exception {
        String filename = "";
        String ext = ".txt";
        try {
            filename = JOptionPane.showInputDialog(null, "Please enter filename: ");
            if (filename == null || filename == "" || !filename.toUpperCase().contains(ext.toUpperCase())) {
                JOptionPane.showInputDialog(null, "File has to be in format: nameofFile.txt");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid filename");
        }
        actions.createTree(filename);

    }

    /**
     * Graphical user interface which allows user to search for student by id
     * @throws NumberFormatException
     */
    private void findGUI() throws NumberFormatException {
        int stuID = 0;
            try {
                while(stuID < 1){
                stuID = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the Student's ID: "));
                }
                actions.find(stuID);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Must be a numeric number!");
            }
    }


    /**
     * prompts user for id, faculty, major, and year of new student
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
            actions.insert(stID.getText(), faculty.getText(), major.getText(), year.getText());
        }
    }

    public static void main(String[] args) throws Exception {
        new Graphics(600, 450);
    }
}
