import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class PhoneBook {

    private FastScanner in = new FastScanner();
    private HashMap<Integer, String> book = new HashMap<Integer, String>();


    public static void main(String[] args) {
        new PhoneBook().processQueries();
    }

    private Query readQuery() {

        String type = in.next();
        int number = in.nextInt();

        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }


    private void writeResponse(String response) {
        System.out.println(response);
    }

    private void processQuery(Query query) {

        if (query.type.equals("add")) {
            book.put(query.number, query.name);

        } else if (query.type.equals("del")) {
            if (book.containsKey(query.number)) {
                book.remove(query.number);
            }

        } else {
            String response = "not found";
            if (book.containsKey(query.number)) {
                response = book.get(query.number);
            }

            writeResponse(response);
        }
    }

    public void processQueries() {
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery());
    }

    static class Contact {
        String name;
        int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
