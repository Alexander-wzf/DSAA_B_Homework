import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Problem5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        String Postfix = infix2postfix(s);
        System.out.printf("%s,%.2f",Postfix,computePostfix(Postfix));
    }

    /**
     * 计算后缀表达式
     * @param Postfix 后缀表达式
     * @return 值
     */
    public static Double computePostfix(String Postfix){
        Stack<Double> values = new Stack<>();

        String[] elements = Postfix.split(" ");
        for (String element : elements) {
            switch (element) {
                case "+" -> values.push(values.pop() + values.pop());
                case "-" -> values.push(-(values.pop() - values.pop()));
                case "*" -> values.push(values.pop() * values.pop());
                case "/" -> values.push(1 / (values.pop() / values.pop()));
                default -> values.push(Double.parseDouble(element));
            }
        }
        return values.pop();
    }

    /**
     * 把中缀表达式转为后缀表达式
     * @param infix 中缀表达式
     * @return 后缀表达式
     */
    public static String infix2postfix(String infix){
        ArrayList<String> postfix = new ArrayList<>();
        Stack<Character> ops = new Stack<>();

        char[] charArray = infix.toCharArray();
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

        return String.join(" ", postfix);
    }

    /**
     * 判断是否为运算符
     *
     * @param c 需要判断的字符
     */
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * 得到运算符的优先级
     * @param op 运算符
     * @return int 越大说明优先级越高
     */
    private static int getPriority(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0; // 括号优先级最低
        };
    }
}
