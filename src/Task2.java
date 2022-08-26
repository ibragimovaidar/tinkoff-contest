import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {

        Map<String, Integer> nameToNum = new HashMap<>();
        int nameToNumCounter = 0;

        Map<String, Integer> codeToNum = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++){
            String[] names = scanner.nextLine().split(" ");
            String code = "";
            int[] codes = new int[3];
            for (int j = 0; j < names.length; j++){
                if (!nameToNum.containsKey(names[j])){
                    nameToNum.put(names[j], nameToNumCounter);
                    nameToNumCounter++;
                }
                codes[j] = nameToNum.get(names[j]);
            }
            Arrays.sort(codes);
            for (int c : codes){
                code += String.valueOf(c);
            }
            if (codeToNum.containsKey(code)){
                codeToNum.put(code, codeToNum.get(code) + 1);
            } else {
                codeToNum.put(code, 1);
            }
        }
        System.out.println(codeToNum.values().stream().max(Integer::compare).orElse(-1));
    }
}
