import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int income = 0;
        int loss = 0;
        int minDailyIncome = Integer.MAX_VALUE;
        int maxDailyLoss = 0;
        
        int a;
        for (int i = 0; i < n; i++) {
            a = scanner.nextInt();
            if (i % 2 == 0){
                income += a;
                if (a <= minDailyIncome) {
                    minDailyIncome = a;
                }
            } else {
                loss += a;
                if (a >= maxDailyLoss) {
                    maxDailyLoss = a;
                }
            }
        }
        System.out.println(income - loss + 2 * maxDailyLoss - 2 * minDailyIncome);
    }
}
