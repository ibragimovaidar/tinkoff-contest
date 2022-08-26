import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task4 {

    public static void main(String[] args) throws IOException {

        InputStreamReader in= new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(in);

        int stackDepth = 0;
        List<Map<String,Integer>> stack = new ArrayList<>();
        stack.add(new HashMap<>());
        String s;
        while ((s = input.readLine()) != null){
            // строка вида var = value или var = var
            if (containsEqualsSign(s)){
                String[] split = s.split("=");
                if (isInteger(split[1])){
                    stack.get(stackDepth).put(split[0], Integer.valueOf(split[1]));
                } else {
                    LookupResult res = lookup(split[1], stack, stackDepth);
                    if (res.isOk){
                        stack.get(stackDepth).put(split[0], res.value);
                        System.out.println(res.value);
                    } else {
                        stack.get(stackDepth).put(split[0], 0);
                        System.out.println(0);
                    }
                }
            } else {
                if ("{".equals(s)){
                    stack.add(new HashMap<>());
                    stackDepth++;
                    continue;
                }
                if ("}".equals(s)){
                    stack.remove(stackDepth);
                    stackDepth--;
                    continue;
                }
                throw new RuntimeException("Input error");
            }
        }
    }

    public static boolean isInteger(String s) {
        if(s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1){
                    return false;
                }
                else continue;
            }
            if (Character.digit(s.charAt(i), 10) < 0){
                return false;
            }
        }
        return true;
    }

    public static boolean containsEqualsSign(String s){
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '='){
                return true;
            }
        }
        return false;
    }

    public static LookupResult lookup(String var, List<Map<String,Integer>> stack, int stackDepth) {
        for (int i = stackDepth; i >= 0 ; i--) {
            if (stack.get(i).containsKey(var)){
                return new LookupResult(stack.get(i).get(var), true);
            }
        }
        return new LookupResult(-1, false);
    }

    private static class LookupResult {

        int value;
        boolean isOk;

        public LookupResult(int value, boolean isOk) {
            this.value = value;
            this.isOk = isOk;
        }
    }
}
