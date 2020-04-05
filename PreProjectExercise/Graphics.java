import javax.swing.*;
import java.awt.*;

public class Graphics extends JFrame {

    public Graphics(int wPixels, int hPixels) {
        setTitle("ProjectApplication");
        setSize(wPixels, hPixels);
        setLayout(new BorderLayout());
        add("North", new JLabel("An Application to manage Student Records"));
        add("Center", new JTextArea());

        add("South", createFlowLayout());

        setVisible(true);
    }

    private JPanel createFlowLayout() {
        JPanel p = new JPanel();
        FlowLayout fl = new FlowLayout();
        p.setLayout(fl);

        JButton insert = new JButton("Insert");
        JButton find = new JButton("Find");
        JButton browse = new JButton("Browse");
        JButton createTree = new JButton("Create Tree from File");

        p.add(insert);
        p.add(find);
        p.add(browse);
        p.add(createTree);

        return p;
    }

    public static void main(String[] args) {
        Graphics gui = new Graphics(650, 450);
    }
}