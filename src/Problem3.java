import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        // 2024 2020 2016 2012
        Scanner scanner = new Scanner(System.in);
        int[][] AaD = new int[4][3];

        for (int i = 0; i < 4; i++) {
            AaD[i] = toInt(scanner.nextLine());
        }
        int[] dif = new int[4];
        for (int i = 0; i < 4; i++) {
            dif[i] = toDays(AaD[i]);
        }
        int start = dif[0] > dif[2] ? dif[0] : dif[2];
        int finish = dif[1] > dif[3] ? dif[3] : dif[1];
        System.out.println(start > finish ? 0 : finish - start + 1);
    }

    // 相对于2010 1 1的差
    public static int toDays(int[] date){
        int days = 0;

        for (int i = 2010; i < date[0]; i++) {
            days += isLeap(i) ? 366 : 365;
        }

        for (int i = 1; i < date[1]; i++) {
            days += daysInMonth(i,date[0]);
        }

        days += date[2];
        return days;
    }

    public static boolean isLeap(int year){
        return year%4==0;
    }

    public static int daysInMonth(int month, int year){
        int[] dayinmonth = {31,28,31,30,31,30,31,31,30,31,30,31};
        if (month==2 && isLeap(year)){
            return 29;
        }
        return dayinmonth[month - 1];
    }

    public static int[] toInt(String d){
        String[] ds = d.split("-");
        int[] date = new int[3];
        for (int i = 0; i < 3; i++) {
            date[i] = Integer.parseInt(ds[i]);
        }
        return date;
    }
}