import java.util.Scanner;

public class Problem4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ddl = scanner.nextLine();
        int[] DDL = to24Time(ddl);

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line.isEmpty()){
                break;
            }
            int[] time_24 = to24Time(line);
            for (int i = 0; i < 4; i++) {
                if (time_24[i] > DDL[i]){
                    System.out.println("false");
                    break;
                } else if (time_24[i] < DDL[i]) {
                    System.out.println("true");
                    break;
                }
            }
        }
    }

    public static int[] to24Time(String time){
        String[] data = time.split(" ");
        String[] MaD = data[0].split("-");
        int month = Integer.parseInt(MaD[0]);
        int day = Integer.parseInt(MaD[1]);
        String[] HaM = data[1].split(":");
        int hour = Integer.parseInt(HaM[0]);
        int minute = Integer.parseInt(HaM[1]);

        if (data[2].equals("PM") && hour != 12){
            hour = hour + 12;
        } else if (data[2].equals("AM") && hour == 12) {
            hour = hour - 12;
        }
        int[] Time = {month,day,hour,minute};

        return Time;
    }
}
