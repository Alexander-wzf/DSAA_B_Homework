import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Problem5 {
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int getPriority(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0; // 括号优先级最低
        };
    }

    public static void main(String[] args) {
        ArrayList<String> postfix = new ArrayList<>();
        Stack<Character> ops = new Stack<>();

        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        char[] charArray = s.toCharArray();
        int N = charArray.length;

        for (int i = 0; i < N; i++) {
            char c = charArray[i];

            // 处理数字
            if(Character.isDigit(c)){
                StringBuilder num = new StringBuilder();
                while (i < N && Character.isDigit(charArray[i])){
                    num.append(charArray[i++]);
                }
                i--;
                postfix.add(num.toString());
            }

            // 处理括号
            if (c == '(') ops.push(c);

            else if (c == ')') {
                char op = ops.pop();
                while (!(op == '(')){
                    postfix.add(String.valueOf(op));
                    op = ops.pop();
                }
            }

            // 处理运算符
            else if (isOperator(c)) {
                while (!ops.isEmpty() && getPriority(ops.peek()) >= getPriority(c)){
                    postfix.add(ops.pop().toString());
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()){
            postfix.add(ops.pop().toString());
        }

        String result = String.join(" ", postfix);

        // 计算表达式
        Stack<Double> vals = new Stack<>();

        String[] elements = result.split(" ");
        for (String element : elements) {
            switch (element) {
                case "+" -> vals.push(vals.pop() + vals.pop());
                case "-" -> vals.push(-(vals.pop() - vals.pop()));
                case "*" -> vals.push(vals.pop() * vals.pop());
                case "/" -> vals.push(1 / (vals.pop() / vals.pop()));
                default -> vals.push(Double.parseDouble(element));
            }
        }
        Double value = vals.pop();

        System.out.printf("%s,%.2f",result,value);
    }
}
