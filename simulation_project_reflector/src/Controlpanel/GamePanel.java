package Controlpanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private final Point[] pointList = new Point[17];
    private Button[] buttonGroup = new Button[17];
    private MainPanel mainPanel;
    private String currentStruct = "Backbone";
    private int radian = 55;
    public GamePanel(int w, int h, MainPanel mainPanel) {
        setPreferredSize(new Dimension(w,h));
        this.mainPanel = mainPanel;
        //setup points Hierarkisk struktur med root-nod
        pointList[0] = new Point(getPreferredSize().width / 28 * 15, getPreferredSize().height / 5);
        pointList[1] = new Point(getPreferredSize().width / 14 * 3, getPreferredSize().height / 4 * 2);
        pointList[2] = new Point(getPreferredSize().width / 14 * 6, getPreferredSize().height / 4 * 2);
        pointList[3] = new Point(getPreferredSize().width / 14 * 9, getPreferredSize().height / 4 * 2);
        pointList[4] = new Point(getPreferredSize().width / 14 * 12, getPreferredSize().height / 4 * 2);
        pointList[5] = new Point(getPreferredSize().width / 14 * 2, getPreferredSize().height / 4 * 3);
        pointList[6] = new Point(getPreferredSize().width / 14 * 3, getPreferredSize().height / 4 * 3);
        pointList[7] = new Point(getPreferredSize().width / 14 * 4, getPreferredSize().height / 4 * 3);
        pointList[8] = new Point(getPreferredSize().width / 14 * 5, getPreferredSize().height / 4 * 3);
        pointList[9] = new Point(getPreferredSize().width / 14 * 6, getPreferredSize().height / 4 * 3);
        pointList[10] = new Point(getPreferredSize().width / 14 * 7, getPreferredSize().height / 4 * 3);
        pointList[11] = new Point(getPreferredSize().width / 14 * 8, getPreferredSize().height / 4 * 3);
        pointList[12] = new Point(getPreferredSize().width / 14 * 9, getPreferredSize().height / 4 * 3);
        pointList[13] = new Point(getPreferredSize().width / 14 * 10, getPreferredSize().height / 4 * 3);
        pointList[14] = new Point(getPreferredSize().width / 14 * 11, getPreferredSize().height / 4 * 3);
        pointList[15] = new Point(getPreferredSize().width / 14 * 12, getPreferredSize().height / 4 * 3);
        pointList[16] = new Point(getPreferredSize().width / 14 * 13, getPreferredSize().height / 4 * 3);
        setLayout(null);
        //Setup server button
        Button sb = new Button("Server");
        sb.setFont(new Font("Arial", Font.PLAIN, 12));
        add(sb);
        sb.setBounds(pointList[0].getX()-(radian/2),pointList[0].getY()-(radian/2),radian, radian);
        sb.setBackground(Color.green);
        sb.repaint();
        sb.addActionListener(this);
        new Server(4006);
        buttonGroup[0] = sb;


        //Setup node buttons
        for(int i = 1; i<pointList.length; i++){
            Point p = pointList[i];
            String s = "NODE " + (i-1);

            Button b = new Button();
            b.setFont(new Font("Arial", Font.PLAIN, 12));
            add(b);
            b.setBounds(p.getX()-(radian/2),p.getY()-(radian/2),radian, radian);
            b.setBackground(Color.lightGray);
            b.repaint();
            b.addActionListener(this);
            buttonGroup[i] = b;
        }

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Button b = (Button) actionEvent.getSource();

    }

    public void setCurrentStruct(String currentStruct){
        setPointList(currentStruct);
    }
    public void connectNode(){

    }

    public void setPointList(String s){
        currentStruct = s;
        if(currentStruct == "Root-node") {
            pointList[0] = new Point(getPreferredSize().width / 28 * 15, getPreferredSize().height / 5);
            pointList[1] = new Point(getPreferredSize().width / 28 * 15, getPreferredSize().height / 5 * 2);
            pointList[2] = new Point(getPreferredSize().width / 28 * 7, getPreferredSize().height / 5 * 3);
            pointList[3] = new Point(getPreferredSize().width / 28 * 15, getPreferredSize().height / 5 * 3);
            pointList[4] = new Point(getPreferredSize().width / 28 * 23, getPreferredSize().height / 5 * 3);
            pointList[5] = new Point(getPreferredSize().width / 14 * 2, getPreferredSize().height / 5 * 4);
            pointList[6] = new Point(getPreferredSize().width / 14 * 3, getPreferredSize().height / 5 * 4);
            pointList[7] = new Point(getPreferredSize().width / 14 * 4, getPreferredSize().height / 5 * 4);
            pointList[8] = new Point(getPreferredSize().width / 14 * 5, getPreferredSize().height / 5 * 4);
            pointList[9] = new Point(getPreferredSize().width / 14 * 6, getPreferredSize().height / 5 * 4);
            pointList[10] = new Point(getPreferredSize().width / 14 * 7, getPreferredSize().height / 5 * 4);
            pointList[11] = new Point(getPreferredSize().width / 14 * 8, getPreferredSize().height / 5 * 4);
            pointList[12] = new Point(getPreferredSize().width / 14 * 9, getPreferredSize().height / 5 * 4);
            pointList[13] = new Point(getPreferredSize().width / 14 * 10, getPreferredSize().height / 5 * 4);
            pointList[14] = new Point(getPreferredSize().width / 14 * 11, getPreferredSize().height / 5 * 4);
            pointList[15] = new Point(getPreferredSize().width / 14 * 12, getPreferredSize().height / 5 * 4);
            pointList[16] = new Point(getPreferredSize().width / 14 * 13, getPreferredSize().height / 5 * 4);
        }
        else if(currentStruct == "Backbone") {
            pointList[0] = new Point(getPreferredSize().width / 28 * 15, getPreferredSize().height / 5);
            pointList[1] = new Point(getPreferredSize().width / 14 * 3, getPreferredSize().height / 4 * 2);
            pointList[2] = new Point(getPreferredSize().width / 14 * 6, getPreferredSize().height / 4 * 2);
            pointList[3] = new Point(getPreferredSize().width / 14 * 9, getPreferredSize().height / 4 * 2);
            pointList[4] = new Point(getPreferredSize().width / 14 * 12, getPreferredSize().height / 4 * 2);
            pointList[5] = new Point(getPreferredSize().width / 14 * 2, getPreferredSize().height / 4 * 3);
            pointList[6] = new Point(getPreferredSize().width / 14 * 3, getPreferredSize().height / 4 * 3);
            pointList[7] = new Point(getPreferredSize().width / 14 * 4, getPreferredSize().height / 4 * 3);
            pointList[8] = new Point(getPreferredSize().width / 14 * 5, getPreferredSize().height / 4 * 3);
            pointList[9] = new Point(getPreferredSize().width / 14 * 6, getPreferredSize().height / 4 * 3);
            pointList[10] = new Point(getPreferredSize().width / 14 * 7, getPreferredSize().height / 4 * 3);
            pointList[11] = new Point(getPreferredSize().width / 14 * 8, getPreferredSize().height / 4 * 3);
            pointList[12] = new Point(getPreferredSize().width / 14 * 9, getPreferredSize().height / 4 * 3);
            pointList[13] = new Point(getPreferredSize().width / 14 * 10, getPreferredSize().height / 4 * 3);
            pointList[14] = new Point(getPreferredSize().width / 14 * 11, getPreferredSize().height / 4 * 3);
            pointList[15] = new Point(getPreferredSize().width / 14 * 12, getPreferredSize().height / 4 * 3);
            pointList[16] = new Point(getPreferredSize().width / 14 * 13, getPreferredSize().height / 4 * 3);
        }
        for(int i = 1; i<pointList.length; i++){
            Point p = pointList[i];
            Button b = buttonGroup[i];
            b.setBounds(p.getX()-(radian/2),p.getY()-(radian/2),radian, radian);
            b.setBackground(Color.lightGray);
            b.repaint();
        }
        repaint();

    }

    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        /*int radian = 30;
        for(point p : pointList){
            g2d.fillOval(p.getX() - (radian/2), p.getY()- (radian/2), radian, radian);
        }*/
        if(currentStruct == "Root-node"){
            g2d.drawLine(pointList[0].getX(), pointList[0].getY(), pointList[1].getX(), pointList[1].getY());
            g2d.drawLine(pointList[1].getX(), pointList[1].getY(), pointList[2].getX(), pointList[2].getY());
            g2d.drawLine(pointList[1].getX(), pointList[1].getY(), pointList[3].getX(), pointList[3].getY());
            g2d.drawLine(pointList[1].getX(), pointList[1].getY(), pointList[4].getX(), pointList[4].getY());
            g2d.drawLine(pointList[2].getX(), pointList[2].getY(), pointList[6].getX(), pointList[6].getY());
            g2d.drawLine(pointList[2].getX(), pointList[2].getY(), pointList[5].getX(), pointList[5].getY());
            g2d.drawLine(pointList[2].getX(), pointList[2].getY(), pointList[7].getX(), pointList[7].getY());
            g2d.drawLine(pointList[2].getX(), pointList[2].getY(), pointList[8].getX(), pointList[8].getY());
            g2d.drawLine(pointList[3].getX(), pointList[3].getY(), pointList[9].getX(), pointList[9].getY());
            g2d.drawLine(pointList[3].getX(), pointList[3].getY(), pointList[10].getX(), pointList[10].getY());
            g2d.drawLine(pointList[3].getX(), pointList[3].getY(), pointList[11].getX(), pointList[11].getY());
            g2d.drawLine(pointList[3].getX(), pointList[3].getY(), pointList[12].getX(), pointList[12].getY());
            g2d.drawLine(pointList[4].getX(), pointList[4].getY(), pointList[13].getX(), pointList[13].getY());
            g2d.drawLine(pointList[4].getX(), pointList[4].getY(), pointList[14].getX(), pointList[14].getY());
            g2d.drawLine(pointList[4].getX(), pointList[4].getY(), pointList[15].getX(), pointList[15].getY());
            g2d.drawLine(pointList[4].getX(), pointList[4].getY(), pointList[16].getX(), pointList[16].getY());
        }
        else if(currentStruct == "Backbone"){
            g2d.drawLine(pointList[0].getX(), pointList[0].getY(), pointList[1].getX(), pointList[1].getY());
            g2d.drawLine(pointList[0].getX(), pointList[0].getY(), pointList[2].getX(), pointList[2].getY());
            g2d.drawLine(pointList[0].getX(), pointList[0].getY(), pointList[3].getX(), pointList[3].getY());
            g2d.drawLine(pointList[0].getX(), pointList[0].getY(), pointList[4].getX(), pointList[4].getY());
            g2d.drawLine(pointList[1].getX(), pointList[1].getY(), pointList[2].getX(), pointList[2].getY());
            g2d.drawLine(pointList[2].getX(), pointList[2].getY(), pointList[3].getX(), pointList[3].getY());
            g2d.drawLine(pointList[3].getX(), pointList[3].getY(), pointList[4].getX(), pointList[4].getY());
            g2d.drawLine(pointList[1].getX(), pointList[1].getY(), pointList[5].getX(), pointList[5].getY());
            g2d.drawLine(pointList[1].getX(), pointList[1].getY(), pointList[6].getX(), pointList[6].getY());
            g2d.drawLine(pointList[1].getX(), pointList[1].getY(), pointList[7].getX(), pointList[7].getY());
            g2d.drawLine(pointList[2].getX(), pointList[2].getY(), pointList[8].getX(), pointList[8].getY());
            g2d.drawLine(pointList[2].getX(), pointList[2].getY(), pointList[9].getX(), pointList[9].getY());
            g2d.drawLine(pointList[2].getX(), pointList[2].getY(), pointList[10].getX(), pointList[10].getY());
            g2d.drawLine(pointList[3].getX(), pointList[3].getY(), pointList[11].getX(), pointList[11].getY());
            g2d.drawLine(pointList[3].getX(), pointList[3].getY(), pointList[12].getX(), pointList[12].getY());
            g2d.drawLine(pointList[3].getX(), pointList[3].getY(), pointList[13].getX(), pointList[13].getY());
            g2d.drawLine(pointList[4].getX(), pointList[4].getY(), pointList[14].getX(), pointList[14].getY());
            g2d.drawLine(pointList[4].getX(), pointList[4].getY(), pointList[15].getX(), pointList[15].getY());
            g2d.drawLine(pointList[4].getX(), pointList[4].getY(), pointList[16].getX(), pointList[16].getY());
        }
    }
    public void setButtonColor(int index, String status){
        Color color = Color.green;
        if (status == "disconnected"){
            color = Color.red;
        }
        if(index<=4)
        {
            buttonGroup[index+1].setBackground(color);
        }
        else if(index == 5)
        {
            buttonGroup[index+3].setBackground(color);
        }
        else if(index == 6)
        {
            buttonGroup[index+5].setBackground(color);
        }
        else if(index == 7)
        {
            buttonGroup[index+7].setBackground(color);
        }
        else if(index == 8)
        {
            buttonGroup[index-2].setBackground(color);
        }
        else if(index == 9)
        {
            buttonGroup[index].setBackground(color);
        }
        else if(index == 10)
        {
            buttonGroup[index+2].setBackground(color);
        }
        else if(index == 11)
        {
            buttonGroup[index+4].setBackground(color);
        }
        else if(index == 12)
        {
            buttonGroup[index-5].setBackground(color);
        }
        else if(index == 13)
        {
            buttonGroup[index-3].setBackground(color);
        }
        else if(index == 14)
        {
            buttonGroup[index-1].setBackground(color);
        }
        else if(index == 15)
        {
            buttonGroup[index+1].setBackground(color);
        }



    }

    public void paint(Graphics g) {
        super.paint(g);
        drawLines(g);
    }
}
