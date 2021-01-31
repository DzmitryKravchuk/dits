package devinc.dits;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class LocalDateExample {
    private static DecimalFormat df1 = new DecimalFormat("#.#");
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        LocalDate yesterday = date.minusDays(1);
        LocalDate tomorrow = yesterday.plusDays(2);
        System.out.println("Today date: "+date);
        System.out.println("Yesterday date: "+yesterday);
        System.out.println("Tommorow date: "+tomorrow);

        int a=2;
        int b=6;
        double c=(double) a/(double) b*100;
        System.out.println("a/b*100 = "+df1.format(c));
    }
}

