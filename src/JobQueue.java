import java.io.*;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.*;
import java.util.StringTokenizer;

public class JobQueue {
    
    private int numWorkers;
    private long[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new long[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = (long) in.nextInt();
        }
    }

    class Worker {

        long name;
        long nextFreeTime;

        public Worker(long name, long nextFreeTime) {
            this.name = name;
            this.nextFreeTime = nextFreeTime;
        }
        public String toString() {;
            return this.name + " " + this.nextFreeTime;
        }
    }

     class WorkerCompare implements Comparator<Worker> {
         public int compare(Worker w1, Worker w2) {
             long timeDiff = w1.nextFreeTime - w2.nextFreeTime;

             if (timeDiff == 0) {
                 return (int)(w1.name - w2.name);
             } else {
                 return (int)(w1.nextFreeTime - w2.nextFreeTime);
             }
         }
     }


    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.

        WorkerCompare comp = new WorkerCompare();
        PriorityQueue<Worker> workerQ = new PriorityQueue<Worker>(numWorkers, comp);
        for (long w = 0; w < numWorkers; w++) {
            workerQ.add(new Worker(w, 0));
        }

        for (int i = 0; i < jobs.length; i++) {

            Worker nextWorker = workerQ.poll();

            System.out.println(nextWorker);

            nextWorker.nextFreeTime += jobs[i];
            workerQ.add(nextWorker);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        //writeResponse();
        out.close();
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
