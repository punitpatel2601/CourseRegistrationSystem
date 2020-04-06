import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class Graphics extends JFrame {
    private Actions actions;
    private PrintWriter printer;
    private JTextArea jta;

    public Graphics(int wPixels, int hPixels) {
        actions = new Actions(this);
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

        jta = new JTextArea();
        jta.setEnabled(true);
        JScrollPane sp = new JScrollPane(jta);

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

    private void browseGUI() {
        StringWriter buffer = new StringWriter();
        PrintWriter printer = new PrintWriter(buffer);

        actions.browse(printer);
        String content = buffer.toString();
        jta.append(content);
    }

    private void createTreeGUI() {
        String filename = "";
        try {
            filename = JOptionPane.showInputDialog(null, "Please enter filename: ");
            if (filename == null || filename == "") {
                JOptionPane.showInputDialog(null, "File has to be in format: nameofFile.txt");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid filename");
        }
        actions.createTree(filename);
    }

    private void findGUI() {
        int stuID = 0;
        try {
            stuID = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the Student's ID: "));
            if (stuID < 1) {
                JOptionPane.showMessageDialog(null, "ID cannot be less than one.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid ID.");
        }
        // check for the stuID in tree.
        actions.find(stuID);
    }

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

    public static void main(String[] args) {
        new Graphics(600, 450);
    }
}
