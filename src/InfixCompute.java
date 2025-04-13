import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

public class InfixCompute {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            switch (s) {
                case "(" -> {}
                case "+", "*" -> ops.push(s);
                case ")" -> {
                    String op = ops.pop();
                    if (op.equals("+")) vals.push(vals.pop() + vals.pop());
                    else if (op.equals("*")) vals.push(vals.pop() * vals.pop());
                }
                default -> vals.push(Double.parseDouble(s));
            }
        }
        StdOut.println(vals.pop());
    }
}