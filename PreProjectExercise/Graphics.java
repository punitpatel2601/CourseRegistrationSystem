import javax.swing.*;
import java.awt.*;

public class Graphics extends JFrame {

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

        return p;
    }

    public static void main(String[] args) {
        new Graphics(600, 450);
    }
}