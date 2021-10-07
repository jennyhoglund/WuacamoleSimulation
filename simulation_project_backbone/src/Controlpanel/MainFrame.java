package Controlpanel;

import javax.swing.*;

public class MainFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sick wack-a-mole");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MainPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
