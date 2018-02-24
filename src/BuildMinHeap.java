import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildMinHeap {
    private int[] data;
    int n;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildMinHeap().solve();
    }

    private void readData() throws IOException {
        n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
            data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
            out.println(swap.index1 + " " + swap.index2);
        }
    }

    private int LeftChild(int i) {
        return 2*i + 1;
    }
    private int RightChild(int i) {
        return 2*i + 2;
    }

    private void SiftDown(int i) {

        int minIndex = i;

        int r = RightChild(i);

        if (r <= n-1 && data[r] < data[minIndex]) {
            minIndex = r;
        }

        int l = LeftChild(i);

        if (l <= n-1 && data[l] < data[minIndex]) {
            minIndex = l;
        }

        if (i != minIndex) {

            swaps.add(new Swap(i, minIndex));
            int tmp = data[minIndex];
            data[minIndex] = data[i];
            data[i] = tmp;

            SiftDown(minIndex);
        }
    }


    private void generateSwaps() {

        swaps = new ArrayList<Swap>();

        for (int i = (int) ((n-1) / 2) ; i >= 0; i--) {
            SiftDown(i);
        }

    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
