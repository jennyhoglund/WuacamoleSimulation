package Controlpanel;
import java.util.ArrayList;
import java.util.Random;

public class TestHierarchy {
    private Node[] nodes;
    private Hierarchy[] hierarchyArray;
    private final static int amtOfNodes = 16;
    private ArrayList <String> takenIDs = new ArrayList<>();

    private TestHierarchy(Node[] nodes, Hierarchy[] hierarchyArray){
        this.nodes = nodes;
        this.hierarchyArray = hierarchyArray;
    }
    private String generateID(){
        String temp = "ID_";
        Random random = new Random();
        int num = random.nextInt(1000);
        temp += num;
        if (!takenIDs.contains(temp)){
            takenIDs.add(temp);
            return temp;
        }
        else
        {
            return generateID();
        }

    }

    /**
     * Metod som fixar den statiska ordningen för hierarkin. Skapar 16 hierarki objekt, sätter variablerna
     * i varje objekt till vad som gäller för varje position i hierarkin och lägger in objektet i arrayen.
     */
    protected void setupHierarchy(){
        hierarchyArray = new Hierarchy[16];
        Hierarchy h0 = new Hierarchy(0, 4,8,12,-1);
        hierarchyArray[0] = h0;
        Hierarchy h1 = new Hierarchy(1, 5,9,13,-1);
        hierarchyArray[1] = h1;
        Hierarchy h2 = new Hierarchy(2, 6,10,14,-1);
        hierarchyArray[2] = h2;
        Hierarchy h3 = new Hierarchy(3, 7,11,15,-1);
        hierarchyArray[3] = h3;

        Hierarchy h4 = new Hierarchy(4, -1,-1,-1,0);
        hierarchyArray[4] = h4;
        Hierarchy h5 = new Hierarchy(5, -1,-1,-1,1);
        hierarchyArray[5] = h5;
        Hierarchy h6 = new Hierarchy(6, -1,-1,-1,2);
        hierarchyArray[6] = h6;
        Hierarchy h7 = new Hierarchy(7, -1,-1,-1,3);
        hierarchyArray[7] = h7;

        Hierarchy h8 = new Hierarchy(8, -1,-1,-1,0);
        hierarchyArray[8] = h8;
        Hierarchy h9 = new Hierarchy(9, -1,-1,-1,1);
        hierarchyArray[9] = h9;
        Hierarchy h10 = new Hierarchy(10, -1,-1,-1,2);
        hierarchyArray[10] = h10;
        Hierarchy h11 = new Hierarchy(11, -1,-1,-1,3);
        hierarchyArray[11] = h11;

        Hierarchy h12 = new Hierarchy(12, -1,-1,-1,0);
        hierarchyArray[12] = h12;
        Hierarchy h13 = new Hierarchy(13, -1,-1,-1,1);
        hierarchyArray[13] = h13;
        Hierarchy h14 = new Hierarchy(14, -1,-1,-1,2);
        hierarchyArray[14] = h14;
        Hierarchy h15 = new Hierarchy(15, -1,-1,-1,3);
        hierarchyArray[15] = h15;
    }

    private void test(){
        setupHierarchy();
        for(Hierarchy h: hierarchyArray){
            System.out.println(h.toString());
        }

        for (int i = 0; i < amtOfNodes; i++){
            String id = generateID();
            Node node = new Node(hierarchyArray);
            if (id != "error"){
                node.setId(id);
                nodes[i] = node;
            }
            node.setHierarchy(hierarchyArray[i]);

        }
        for (Node node : nodes){
            System.out.println(node.toString());
            System.out.println(node.getHierarchy().toString());
        }



    }

    public static void main(String[] args){
        Node[] nodes = new Node[amtOfNodes];
        Hierarchy[] hierarchyArr = new Hierarchy[amtOfNodes];
        TestHierarchy testHierarchy = new TestHierarchy(nodes, hierarchyArr);
        testHierarchy.test();

    }
}
