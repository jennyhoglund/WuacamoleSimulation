package Controlpanel;

import javax.swing.*;

public class MainPanel extends JPanel {
    private GamePanel gamePanel;
    private ControllPanel controllPanel;
    public MainPanel(){
        this.gamePanel = new GamePanel(800,700,this);
        this.controllPanel = new ControllPanel(200,700,this, gamePanel);

        add(gamePanel);
        add(controllPanel);
    }
    public void setStucture(String stucture){
        gamePanel.setCurrentStruct(stucture);
    }
}
