import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String Arrival1 = scanner.nextLine();
        String Departure1 = scanner.nextLine();
        String Arrival2 = scanner.nextLine();
        String Departure2 = scanner.nextLine();

        LocalDate Arrival1Date = LocalDate.parse(Arrival1);
        LocalDate Departure1Date = LocalDate.parse(Departure1);
        LocalDate Arrival2Date = LocalDate.parse(Arrival2);
        LocalDate Departure2Date = LocalDate.parse(Departure2);

        LocalDate Start = Arrival1Date.isAfter(Arrival2Date) ? Arrival1Date : Arrival2Date;
        LocalDate End = Departure1Date.isBefore(Departure2Date) ? Departure1Date : Departure2Date;


        if (Start.isAfter(End)) {
            System.out.println(0);
        }
        else {
            long overlapDays = ChronoUnit.DAYS.between(Start, End) + 1;
            System.out.println(overlapDays);
        }
    }
}
