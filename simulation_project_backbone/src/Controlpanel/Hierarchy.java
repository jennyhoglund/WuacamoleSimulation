package Controlpanel;

public class Hierarchy {
    protected int index;
    protected int leaf1, leaf2, leaf3;
    protected int firstReceiver;
    protected int secondReceiver;
    protected int firstSender;
    protected int secondSender;
    protected int parent;
    protected Status status;
    protected Role role;


    public Hierarchy(int index, int leaf1, int leaf2, int leaf3, int parent){
        firstReceiver = -1;
        secondReceiver = -1;
        firstSender = -1;
        secondSender = -1;
        this.index = index;
        this.leaf1 = leaf1;
        this.leaf2 = leaf2;
        this.leaf3 = leaf3;
        this.parent = parent;
        status = Status.idle;
        if (index == 0 || index == 1 || index == 2 || index == 3){
            role = Role.parent;
            if (index == 0){
                firstReceiver = 1;
                secondReceiver = 2;
            }
            else if (index == 1){
                firstSender = 0;
                secondSender = 3;
            }
            else if (index == 2){
                firstSender = 3;
                secondSender = 0;
            }
            else {
                firstReceiver = 2;
                secondReceiver = 1;
            }
        }
        else if (index < 16){
            role = Role.leaf;
        }
    }
    @Override
    public String toString(){
        String s = String.format("Index: %d, leaf 1 index: %d, leaf 2 index: %d, leaf 3 index: %d, "
                +"parent index: %d", index, leaf1,leaf2,leaf3, parent);
        return s;
    }
    public Role getRole(){
        return role;
    }


}
