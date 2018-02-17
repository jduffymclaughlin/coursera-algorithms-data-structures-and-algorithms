import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.Arrays;



class CheckBrackets {

    static class Bracket {
        Bracket(char type, int position) {
            this.type = type;
            this.position = position;
        }

        boolean Match(char c) {
            if (this.type == '[' && c == ']')
                return true;
            if (this.type == '{' && c == '}')
                return true;
            if (this.type == '(' && c == ')')
                return true;
            return false;
        }

        char type;
        int position;
    }


    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> bracketStack = new Stack<Bracket>();
        boolean success = true;
        int position;
        Bracket bracket = new Bracket('*', 1);


        for (position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {

                bracket = new Bracket(next, position + 1);
                bracketStack.push(bracket);

            } else if (next == ')' || next == ']' || next == '}') {

                if (bracketStack.isEmpty()) {
                    success = false;
                    position++;
                    break;
                }

                bracket = bracketStack.pop();

                if (! bracket.Match(next)) {
                    success = false;
                    position++;
                    break;
                }
            }
        }

        if (success && bracketStack.isEmpty()) {
            System.out.println("Success");

        } else if (success) {
            
            bracket = bracketStack.pop();
            System.out.println(bracket.position);

        } else {
            System.out.println(position);
        }



    }
}
