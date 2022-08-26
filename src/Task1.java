import java.util.Arrays;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x11 = sc.nextInt();
        int x12 = sc.nextInt();
        int y11 = sc.nextInt();
        int y12 = sc.nextInt();

        int x21 = sc.nextInt();
        int x22 = sc.nextInt();
        int y21 = sc.nextInt();
        int y22 = sc.nextInt();

        int minX = Arrays.stream(new int[]{x11, x12, x21, x22}).min().getAsInt();
        int maxX = Arrays.stream(new int[]{x11, x12, x21, x22}).max().getAsInt();
        int minY = Arrays.stream(new int[]{y11,y12,y21,y22}).min().getAsInt();
        int maxY = Arrays.stream(new int[]{y11,y12,y21,y22}).max().getAsInt();

        int answer = Math.max(maxX - minX, maxY - minY);
        System.out.println(answer * answer);
    }
}
