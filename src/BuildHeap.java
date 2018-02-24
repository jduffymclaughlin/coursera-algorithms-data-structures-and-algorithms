import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    int n;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
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

    private int Parent(int i) {
        return (int) Math.floor(i / 2);
    }
    private int LeftChild(int i) {
        return 2*i + 1;
    }
    private int RightChild(int i) {
        return 2*i + 2;
    }


    private void SiftDown(int i) {

        int maxIndex = i;
        int l = LeftChild(i);

        if (l <= n-1 && data[l] > data[maxIndex]) {
            maxIndex = l;
        }

        int r = RightChild(i);


        if (r <= n-1 && data[r] > data[maxIndex]) {
            maxIndex = r;
        }

        if (i != maxIndex) {
            swaps.add(new Swap(i, maxIndex));
            int tmp = data[maxIndex];
            data[maxIndex] = data[i];
            data[i] = tmp;

            System.out.println(Arrays.toString(data));
            SiftDown(maxIndex);
        }
    }


    private void generateSwaps() {

        swaps = new ArrayList<Swap>();

        for (int i = (int) Math.floor(n / 2) - 1; i >= 0; i--) {
            System.out.println(swaps);
            SiftDown(i);
        }

        System.out.println(Arrays.toString(data));
        System.out.println(swaps);

//        for (int i = 0; i < data.length; ++i) {
//            for (int j = i + 1; j < data.length; ++j) {
//                if (data[i] > data[j]) {
//                    swaps.add(new Swap(i, j));
//                    int tmp = data[i];
//                    data[i] = data[j];
//                    data[j] = tmp;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(data));
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
