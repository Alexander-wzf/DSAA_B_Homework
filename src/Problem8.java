import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.Scanner;

public class Problem8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int caseNum = input.nextInt();
        String[] result = new String[caseNum];
        for (int i = 0; i < caseNum; i++) {
            Queue<Integer> locks = new Queue<>();
            int locksNum = input.nextInt();

            Queue<Integer> keys = new Queue<>();
            int keysNum = input.nextInt();

            input.nextLine();

            // 读入locks
            for (int j = 0; j < locksNum; j++) {
                locks.enqueue(Integer.parseInt(input.nextLine()));
            }

            // 读入keys
            for (int j = 0; j < keysNum; j++) {
                String[] key = input.nextLine().split(" ");
                for (String s : key) {
                    keys.enqueue(Integer.parseInt(s));
                }
            }

            // 开始处理该case
            result[i] = unlock(locks,keys,locksNum,keysNum);
        }
        // 打印答案
        for (String s : result) {
            System.out.println(s);
        }
    }
    public static String unlock(Queue<Integer> locks, Queue<Integer> keys, int locksNum, int keysNum){
        StringBuilder result = new StringBuilder();
        int remainLocksNum = locksNum;
        // 遍历locks
        for (int i = 0; i < locksNum; i++) {
            int currentLock = locks.peek();
            boolean isUnlock = false;

            // 遍历keys
            for (int j = 0; j < keysNum; j++) {
                int currentKey = keys.dequeue();
                int[] saveKey = new int[currentKey];

                // 把每个key能解锁的类型保存
                for (int k = 0; k < currentKey; k++) {
                    saveKey[k] = keys.dequeue();
                    if (saveKey[k] == currentLock){
                        isUnlock = true;
                    }
                }

                // 若能解锁整个key都出队列，currentLock也出队列；若不能key则返回队尾，currentLock保留在队列中
                if (!isUnlock){
                    keys.enqueue(currentKey);
                    for (int k = 0; k < currentKey; k++) {
                        keys.enqueue(saveKey[k]);
                    }
                } else {
                    remainLocksNum--;
                    keysNum--;
                    locks.dequeue();
                    break;
                }
            }

            // 若遍历整个keys都没能解锁，返回剩余的locks，终止程序.
            if (!isUnlock){
                for (int j = i; j < locksNum; j++) {
                    result.append(locks.dequeue());
                    result.append(" ");
                }
                return result.toString();
            }
        }
        if (locks.isEmpty()){
            return "\"NULL\"";
        }else {
            for (int j = 0; j < remainLocksNum; j++) {
                result.append(locks.dequeue());
                result.append(" ");
            }
            return result.toString();
        }
    }
}
