import java.util.Arrays;
import java.util.Scanner;

public class Problem6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(minSwapCost(a));
    }

    public static long minSwapCost(int[] a) {
        return mergeSortAndCount(a, 0, a.length - 1);
    }

    private static long mergeSortAndCount(int[] a, int left, int right) {
        long cost = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;
            cost += mergeSortAndCount(a, left, mid);
            cost += mergeSortAndCount(a, mid + 1, right);
            cost += mergeAndCount(a, left, mid, right);
        }
        return cost;
    }

    private static long mergeAndCount(int[] a, int left, int mid, int right) {
        int[] leftArray = Arrays.copyOfRange(a, left, mid + 1);
        int[] rightArray = Arrays.copyOfRange(a, mid + 1, right + 1);
        int i = 0, j = 0, k = left;
        long cost = 0;

        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                a[k++] = leftArray[i++];
            } else {
                a[k++] = rightArray[j];
                cost += (long) rightArray[j] * (leftArray.length - i);
                j++;
            }
        }

        while (i < leftArray.length) {
            a[k++] = leftArray[i++];
        }

        while (j < rightArray.length) {
            a[k++] = rightArray[j++];
        }

        return cost;
    }
}

// 法一：思想错误，只能换相邻的
//    public static int exchange(int[] a, int i, int j){
//        int tem = a[i];
//        a[i] = a[j];
//        a[j] = tem;
//        return a[i];
//    }
//    public static void main(String[] args) {
//
//        int pay = 0;
//
//        Scanner input = new Scanner(System.in);
//        int N = input.nextInt();
//        int[] sequence = new int[N];
//
//        for (int i = 0; i < N; i++) {
//            sequence[i] = input.nextInt();
//        }
//
//        for (int i = 0; i < N; i++) {
//            int min = i;
//            for (int j = i+1; j < N; j++) {
//                if (sequence[j] < sequence[min]) min = j;
//            }
//            if (min > i) pay = pay + exchange(sequence,i,min);
//        }
//
//        System.out.println(pay);
//    }

// 法二：贪心算法，时间复杂度超了。
//public static void main(String[] args) {
//    Scanner scanner = new Scanner(System.in);
//    int n = scanner.nextInt();
//    int[] a = new int[n];
//    for (int i = 0; i < n; i++) {
//        a[i] = scanner.nextInt();
//    }
//    System.out.println(minSwapCostGreedy(a));
//}
//
//    public static long minSwapCostGreedy(int[] a) {
//        long totalCost = 0;
//        boolean swapped;
//        do {
//            swapped = false;
//            for (int i = 0; i < a.length - 1; i++) {
//                if (a[i] > a[i + 1]) {
//                    // 交换相邻元素
//                    int temp = a[i];
//                    a[i] = a[i + 1];
//                    a[i + 1] = temp;
//                    // 计算成本
//                    totalCost += Math.min(a[i], a[i + 1]);
//                    swapped = true;
//                }
//            }
//        } while (swapped); // 如果没有发生交换，说明序列已经有序
//        return totalCost;
//    }

