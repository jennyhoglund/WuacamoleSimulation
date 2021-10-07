package Controlpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ControllPanel extends JPanel implements ActionListener {
    private MainPanel mainPanel;
    private GamePanel gamePanel;
    private JRadioButton rotNodButton;
    private JRadioButton backboneButton;
    //private Node node;
    private LinkedList<JButton> buttons;
    private int firstIdleIndex = 0;
    private Node[] nodes;
    private Hierarchy[] hierarchyArray;
    private final int amtOfNodes = 16;
    private ArrayList<String> takenIDs = new ArrayList<>();

    public ControllPanel(int w, int h, MainPanel mainPanel, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setupHierarchy();
        nodes = new Node[amtOfNodes];

        for (int i = 0; i < nodes.length; i++) {
            String id = generateID();
            Node node = new Node(hierarchyArray);
            node.setId(id);
            nodes[i] = node;
            node.setStatus("idle");
        }

        for (int i = 0; i < nodes.length; i++) {
            nodes[i].setArray(nodes);
        }

        this.mainPanel = mainPanel;
        buttons = new LinkedList<>();
        setPreferredSize(new Dimension(w, h));
        setBackground(Color.LIGHT_GRAY);

        JButton buttonConnect = new JButton("Connect a node");
        buttons.add(buttonConnect);

        JButton buttonDisconnectChild = new JButton("Disconnect a child node");
        buttons.add(buttonDisconnectChild);

        JButton buttonDisconnectParent = new JButton("Disconnect a parent node");
        buttons.add(buttonDisconnectParent);

        JButton buttonDisconnectHead = new JButton("Disconnect a head node");
        buttons.add(buttonDisconnectHead);

        JButton buttonStart = new JButton("Start game");
        buttons.add(buttonStart);

        JButton buttonExit = new JButton("Exit game");
        buttons.add(buttonExit);

        JButton buttonReset = new JButton("Reset");
        buttons.add(buttonReset);

        for (JButton b : buttons) {
            b.setPreferredSize(new Dimension(getPreferredSize().width - 20, 50));
            b.setFont(new Font("Arial", Font.PLAIN, 10));
            b.addActionListener(this);
        }

        backboneButton = new JRadioButton("Backbone");
        backboneButton.setSelected(true);

        rotNodButton = new JRadioButton("Root-node");


        ButtonGroup group = new ButtonGroup();
        group.add(backboneButton);
        group.add(rotNodButton);
        backboneButton.addActionListener(this);
        rotNodButton.addActionListener(this);

        setAlignmentY(Component.CENTER_ALIGNMENT);
        add(buttonConnect);
        add(buttonDisconnectHead);
        add(buttonDisconnectParent);
        add(buttonDisconnectChild);
        add(buttonStart);
        add(buttonExit);
        add(buttonReset);
        add(backboneButton);
        add(rotNodButton);

    }

    protected void setupHierarchy() {
        hierarchyArray = new Hierarchy[16];
        Hierarchy h0 = new Hierarchy(0, 4, 8, 12, -1);
        hierarchyArray[0] = h0;
        Hierarchy h1 = new Hierarchy(1, 5, 9, 13, -1);
        hierarchyArray[1] = h1;
        Hierarchy h2 = new Hierarchy(2, 6, 10, 14, -1);
        hierarchyArray[2] = h2;
        Hierarchy h3 = new Hierarchy(3, 7, 11, 15, -1);
        hierarchyArray[3] = h3;

        Hierarchy h4 = new Hierarchy(4, -1, -1, -1, 0);
        hierarchyArray[4] = h4;
        Hierarchy h5 = new Hierarchy(5, -1, -1, -1, 1);
        hierarchyArray[5] = h5;
        Hierarchy h6 = new Hierarchy(6, -1, -1, -1, 2);
        hierarchyArray[6] = h6;
        Hierarchy h7 = new Hierarchy(7, -1, -1, -1, 3);
        hierarchyArray[7] = h7;

        Hierarchy h8 = new Hierarchy(8, -1, -1, -1, 0);
        hierarchyArray[8] = h8;
        Hierarchy h9 = new Hierarchy(9, -1, -1, -1, 1);
        hierarchyArray[9] = h9;
        Hierarchy h10 = new Hierarchy(10, -1, -1, -1, 2);
        hierarchyArray[10] = h10;
        Hierarchy h11 = new Hierarchy(11, -1, -1, -1, 3);
        hierarchyArray[11] = h11;

        Hierarchy h12 = new Hierarchy(12, -1, -1, -1, 0);
        hierarchyArray[12] = h12;
        Hierarchy h13 = new Hierarchy(13, -1, -1, -1, 1);
        hierarchyArray[13] = h13;
        Hierarchy h14 = new Hierarchy(14, -1, -1, -1, 2);
        hierarchyArray[14] = h14;
        Hierarchy h15 = new Hierarchy(15, -1, -1, -1, 3);
        hierarchyArray[15] = h15;

    }

    private String generateID() {
        String temp = "ID_";
        Random random = new Random();
        int num = random.nextInt(1000);
        temp += num;
        if (!takenIDs.contains(temp)) {
            takenIDs.add(temp);
            return temp;
        } else {
            return generateID();
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == backboneButton) {
            mainPanel.setStucture("Backbone");
        } else if (actionEvent.getSource() == rotNodButton) {
            mainPanel.setStucture("Root-node");
        } else {
            JButton b = (JButton) actionEvent.getSource();

            if (b.getText() == "Connect a node") {

                if (firstIdleIndex < 16) {
                    nodes[firstIdleIndex].setStatus(Status.connected.toString());
                    Node node = nodes[firstIdleIndex];
                    node.updateArray(nodes, firstIdleIndex);
                    gamePanel.setButtonColor(firstIdleIndex, "connected");

                    for (int i = 0; i < nodes.length; i++) {
                        if (nodes[i].getStatus() == "connected") {
                            nodes[i].addConnectedNode(nodes[firstIdleIndex], firstIdleIndex);
                        }
                    }
                    firstIdleIndex++;
                    for (int i = 0; i < firstIdleIndex; i++) {
                        if (nodes[i].getStatus() == "connected")
                            nodes[i].printNodeInfo();
                    }
                    System.out.println();
                    System.out.println();

                }


            } else if (b.getText() == "Disconnect a child node") {
                System.out.println(b.getText());
            } else if (b.getText() == "Disconnect a parent node") {
                System.out.println(b.getText());
                disconnectParentNode();
            } else if (b.getText() == "Disconnect a head node") {
                System.out.println(b.getText());
            } else if (b.getText() == "Start game") {
                for (int i = 0; i < 16; i++){
                    if (nodes[i].getStatus() == "connected"){
                        nodes[i].startGame();
                    }
                }

            } else if (b.getText() == "Exit game") {
                System.out.println(b.getText());
            } else if (b.getText() == "Reset") {
                System.out.println(b.getText());
            }
        }

    }

    public void disconnectParentNode() {
        if (firstIdleIndex >= 4) {
            Random random = new Random();
            int index = random.nextInt(4);
            nodes[index].crash();


        }
    }
}
