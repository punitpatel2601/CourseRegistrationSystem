import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Graphics extends JFrame {
    private Actions actions;

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
        insert.addActionListener((ActionEvent e) -> {insertGUI();});
        find.addActionListener((ActionEvent e) -> {findGUI();});
        browse.addActionListener((ActionEvent e) ->{browseGUI();});
        createTree.addActionListener((ActionEvent e) -> {createTreeGUI();});

        return p;
    }

    private JPanel findGUI(){

    }

    private JPanel browseGUI(){
        
    }

    private JPanel insertGUI(){
        JPanel i = new JPanel();
        i.setLayout(new FlowLayout());
        JTextField id = new JTextField();
        JTextField faculty = new JTextField();
        JTextField major = new JTextField();
        JTextField year = new JTextField();
        JButton insertStudent = new JButton("Insert");
        JButton returntoMain = new JButton("Return to Main Window");
        i.add(id);
        i.add(faculty);
        i.add(major);
        i.add(year);
        i.add(insertStudent);
        i.add(returntoMain);
        
        insertStudent.addActionListener((ActionEvent e) -> {actions.insert(id.getText(), faculty.getText(), major.getText(), year.getText());});
        returntoMain.addActionListener((ActionEvent e) -> {return;});
    }
    
    private JPanel createTreeGUI() {
    	
    }

    public static void main(String[] args) {
        new Graphics(600, 450);
    }
}
