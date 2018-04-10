public class Tree {

    public class Node {

        int iData;
        double fData;

        Node leftChild;
        Node rightChild;

    }

    Node root;

    public void find(int key){

    }

    public void insert(int id, double dd) {

    }

    public void delete(int id) {

    }


    public static void main(String[] args) {

        Tree tree = new Tree();

        tree.insert(50, 1.5);
        tree.insert(25, 1.7);
        tree.insert(75, 1.9);

        Node found = tree.find(25);

        if (found != null) {
            System.out.println("Found it");
        } else {
            System.out.println("Did not find");
        }

    }

}
