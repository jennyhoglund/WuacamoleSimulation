package Controlpanel;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Node implements Serializable {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String ip;
    private int port;
    protected String id = "";
    private Node[] nodes = new Node[16];
    private Node[] parentNodes = new Node[4];
    private Node myParent; // Bara leafnodes har myParent.
    private Node firstLeaf; //Bara parentnodes har firstleaf, secondleaf och thirdleaf.
    private Node secondLeaf;
    private Node thirdLeaf;
    private int connectedNodes = 0; //Talar om hur många anslutna noder som finns. OBS! säger en för lite, vågar inte ändra på det än.
    private String status; //Idle eller connected.
    private int myIndex; //Vilket index noden befinner sig på i arrayen.
    private Hierarchy[] hierarchyArray;
    private Hierarchy hierarchy; //Används bara i testklassen för hierarchy.
    protected int moleCounter;
    protected int hitCounter;
    protected int dataReceived = -1;

    public Hierarchy getHierarchy() {
        return hierarchyArray[myIndex];
    }

    public Node(String ip, int port) {
        this.ip = ip;
        this.port = port;
        moleCounter = 0;
        hitCounter = 0;
        new Connection().start();
    }

    public Node(Hierarchy[] hierarchyArray) {
        this.hierarchyArray = hierarchyArray;
        moleCounter = 0;
        hitCounter = 0;


    }

    @Override
    public String toString() {
        String s = String.format("index %d id  %s ", myIndex, id);
        return s;
    }

    public void printNodeInfo() {
        int second_R = hierarchyArray[myIndex].secondReceiver;
        int first_R = hierarchyArray[myIndex].firstReceiver;
        int first_S = hierarchyArray[myIndex].firstSender;
        int second_S = hierarchyArray[myIndex].secondSender;
        String s = String.format("My index is %d, my id is %s, ", myIndex, id);
        s += ("my leaves are " + firstLeaf + ", " + secondLeaf + ", " + thirdLeaf + ". My parent is " +
                myParent);
        s += String.format(", my firstReceiver is %d, my secondReceiver is %d, my firstSender is %d, " +
                "my secondSender is %d", first_R, second_R, first_S, second_S);
        System.out.println(s);


    }

    public void setStatus(String str) {
        status = str;
    }

    public void setId(String myid) {
        id = myid;
    }

    public void setArray(Node nodes[]) {
        this.nodes = nodes;
    }


    public void updateArray(Node nodes[], int connectedNodes) {
        this.nodes = nodes;
        this.connectedNodes = connectedNodes;
        myIndex = connectedNodes;
    }

    public void addConnectedNode(Node node, int index) {
        connectedNodes = index;
        nodes[index].setStatus(Status.connected.toString());
        Hierarchy hierarchy = hierarchyArray[myIndex];

        if (index == hierarchy.leaf1) {
            firstLeaf = node;

        } else if (index == hierarchy.leaf2) {
            secondLeaf = node;

        } else if (index == hierarchy.leaf3) {
            thirdLeaf = node;

        } else if (index == hierarchy.firstReceiver) {
            //
        } else if (index == hierarchy.parent) {
            myParent = node;
            System.out.println("1. this shouldn't happen");
        } else if (index == myIndex) { //När den nya noden ska hantera tillägg av sig själv
            //ska den lägga till sina grannar som redan är connected. Den ska hämta index om
            //vilka den ska lägga till från hierarchyArrayen, och om de indexen är lägre än
            //dens egna ska de noderna antas existera, och då ska de läggas till som grannar.
            // Viktigt att ha i åtanke att index kan vara -1 om det inte är aktuellt, så kontrollera
            //ifall index är > -1 men mindre än myIndex.
            //Annars är de idle och ska inte läggas till förrän senare.


            if (hierarchy.parent > -1 && hierarchy.parent < myIndex) {
                myParent = nodes[hierarchy.parent];

            } else if (hierarchy.firstReceiver > -1 && hierarchy.firstReceiver < myIndex) {
                //
            } else if ((hierarchy.leaf1 > -1 && hierarchy.leaf1 < myIndex)
                    || (hierarchy.leaf2 > -1 && hierarchy.leaf2 < myIndex)
                    || (hierarchy.leaf3 > -1 && hierarchy.leaf3 < myIndex)) {
                System.out.println("2. This shouldn't happen.");
            }


        }
    }
    protected void startGame(){
        MoleGenerator moleGenerator = new MoleGenerator(3000);
        CalcTask calcTask = new CalcTask(10000);
        moleGenerator.start();
        calcTask.start();
    }

    /**
     * Tråd som periodiskt randomar fram två tal. Det ena är mullvadstalet, det andra är om den blev träffad.
     */

    private class MoleGenerator extends Thread {
        private int taskPeriod;
        private int moleProbablity = 80;
        private int hitChance = 50;

        public MoleGenerator(int taskPeriod) {
            this.taskPeriod = taskPeriod;
        }

        public void run() {
            while (true) {

                Random random = new Random();
                int temp = random.nextInt(100);
                if (temp >= (100 - moleProbablity)) {
                    System.out.println("A mole has appeared at node " + myIndex);
                    moleCounter++;
                    temp = random.nextInt(100);
                    if (temp >= (100 - hitChance)) {
                              System.out.println("The mole has been hit!");
                        hitCounter++;
                    }else System.out.println("The mole got away");


                }
                      System.out.println();
                try {
                    sleep(taskPeriod);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }


    protected int calcMyStats() {
        int hitPercentage = 0;
        System.out.println();
        System.out.println("calculating");
        String s1 = String.format("Molecounter at node %s is %d, hitcounter is %d", id, moleCounter, hitCounter);
        System.out.println(s1);
        moleCounter *= 10;
        hitCounter *= 1000;
        if (moleCounter > 0)
            hitPercentage = hitCounter / moleCounter;
        String s = String.format("Hit percentage at node %s is %d", id, hitPercentage);
        System.out.println(s);
        System.out.println();
        moleCounter = 0;
        hitCounter = 0;
        return hitPercentage;
    }


    /**
     * Tråd som periodiskt sätter igång processen för att se till så att alla noders data distribueras och
     * att svårigheten beräknas av alla.
     */

    private class CalcTask extends Thread {
        private int taskPeriod;

        public CalcTask(int taskPeriod) {
            this.taskPeriod = taskPeriod;
        }


        public void run() {

            while (true) {
                int stats = calcMyStats();
                if ((myIndex == 0 || myIndex == 3) || (myIndex == 1 || myIndex == 2)) {
                    //Noden är en parent.
                    //Börja med att samla in data från leaves i tur och ordning.
                    if (firstLeaf != null) { //OBS! Kom ihåg att sätta till null om den dc:ar
                        //Be om firstLeafs data, addera den till stats.
                    }

                    if (secondLeaf != null) {
                        //Be om secondLeafs data.
                    }
                    if (thirdLeaf != null) {
                        //Be om thirdLeafs data.
                    }
                    Node temp;


                    if (myIndex == 0 || myIndex == 3) {
                        //Skicka data till första receiver, ta emot data från första receiver, summera.
                        //Skicka data till andra receiver, ta emot data från andra receiver, summera.
                        temp = nodes[hierarchyArray[myIndex].firstReceiver];
                    //    System.out.print(Node.this + "sending data to ");
                        //    System.out.println(temp);
                        sendData(temp, stats);
                    /*    while (dataReceived < 0) { //Vänta här tills noden skickar sin data som svar.
                            System.out.print(Node.this);
                            System.out.println("waiting for data");
                            try {
                                sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        stats += dataReceived;
                        dataReceived = -1;
                        temp = nodes[hierarchyArray[myIndex].secondReceiver];
                        sendData(temp, stats);
                        while (dataReceived < 0) { //Vänta här tills noden skickar sin data som svar.
                        }
                        stats += dataReceived;
                        dataReceived = -1;
                        System.out.print(Node.this);
                        System.out.println(stats);*/


                    } else if (myIndex == 1 || myIndex == 2) {
                        //Ta emot data från första sender, skicka data till första sender.
                        //Ta emot data från andra sender, skicka data till andra sender.
                        temp = nodes[hierarchyArray[myIndex].firstSender];
                      /*  if (dataReceived > 0){
                            System.out.print(Node.this);
                            System.out.println("Data received");
                            try {
                                sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        while(dataReceived < 0){
                            //          System.out.print(Node.this);
                            //       System.out.println("waiting for data");
                            try {
                                sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        dataReceived = -1;*/

                    }
                } else if (myIndex > 3 && myIndex < 16) {
                    //Noden är ett leaf.
                    //Vänta tills förfrågan kommer från parent, skicka data, vänta på att få den totala summan.

                } else {
                    System.out.println("3. This shouldn't happen.");
                }
                int difficulty = calculateNewDifficulty(stats);


                try {
                    sleep(taskPeriod);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

        private int calculateNewDifficulty(int stats) {
            //Gör den slutliga beräkningen.
            return 0;
        }

    }
    //Nod anropar med parametern node, som den ska få datan från.
    //Det som returneras är den frågade nodens nuvarande statestik.

    protected int requestData(Node leaf) {
        return leaf.calcMyStats();
    }
    //Nod anropar med parametern receiver och data. Anropar mottagarens metod för att
    // ta emot data.

    protected void sendData(Node receiver, int data) {
        receiver.receiveData(data);
    }

    //Anropas när en nod skickar data till en annan.
    protected void receiveData(int data) {
        dataReceived = data;
    }


    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getStatus() {
        return status;
    }

    public void send(String message) {
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void disconnect() {
        try {
            oos.flush();
            oos.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crash() {
        nodes = new Node[16];
        myIndex = -1;
        myParent = null;
        firstLeaf = null;
        secondLeaf = null;
        thirdLeaf = null;
        status = Status.idle.toString();
    }

    private class Connection extends Thread {
        @Override
        public void run() {
            try {
                Socket socket = new Socket(ip, port);
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
                new listener().start();
                while (!Thread.interrupted()) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class listener extends Thread {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                //  messageReceived = false;
                try {

                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (status == "connected") {
                  //  keepAliveMessage(firstReceiverParent, true);
                }

                // if (messageReceived == false) { }

            }
        }

    }

    synchronized public void print(String str) {
        System.out.println(str);
    }

    synchronized public void keepAliveMessage(Node node, boolean bool) {
        System.out.println(id + " is alive " + node.id);
        //  messageReceived = bool;
    }


}
