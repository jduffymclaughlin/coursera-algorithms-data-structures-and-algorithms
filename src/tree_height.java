import java.util.*;
import java.io.*;


public class tree_height {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class Node {

        ArrayList<Node> children;
        int height;

        public Node() {
            this.children = new ArrayList<Node>();
        }

        public void addChild(Node child) {
            this.children.add(child);
        }

    }

    public class TreeHeight {

        int n;
        int parent[];
        Node[] nodes;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        int computeHeight() {

            nodes = new Node[n];
            int root = 0;

            for (int i = 0; i < n; i++) {
                nodes[i] = new Node();
            }

            for (int i = 0; i < n; i++) {
                if (parent[i] == -1) {
                    root = i;
                    nodes[i].height = 1;

                } else {
                    nodes[parent[i]].addChild(nodes[i]);
                }
            }


            Queue<Node> nodeQ = new LinkedList<Node>();
            nodeQ.add(nodes[root]);
            int maxHeight = 1;


            while (!nodeQ.isEmpty()) {

                Node currentNode = nodeQ.remove();

                if (currentNode.children.size() != 0) {
                    for (int child = 0; child < currentNode.children.size(); child++) {

                        currentNode.children.get(child).height = currentNode.height + 1;

                        if (currentNode.children.get(child).height > maxHeight){
                            maxHeight = currentNode.children.get(child).height;
                        }

                        nodeQ.add(currentNode.children.get(child));
                    }

                }
            }

            return maxHeight;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
