import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Task7 {

    public static void main(String[] args) throws IOException {
        InputStreamReader in= new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(in);


        String s = input.readLine();
        int q = Integer.parseInt(input.readLine());

        for (int i = 0; i < q; i++) {
            String[] line = input.readLine().split(" ");
            int l = Integer.parseInt(line[0]);
            int r = Integer.parseInt(line[1]);

            if (r <= l){
                System.out.println(0);
                continue;
            }

            char[] substr = s.substring(l - 1, r).toCharArray();
            List<CharAndPosition> chars = new ArrayList<>();
            for (int j = 0; j < substr.length; j++) {
                chars.add(new CharAndPosition(substr[j], j+1));
            }
            chars.sort((Comparator.comparingInt(o -> o.c)));

            int answer = chars.get(0).pos - 1;
            for (int j = 1; j < chars.size(); j++) {
                int step;
                if (chars.get(j).pos <  chars.get(j-1).pos){
                    step = (r - l + 1) - chars.get(j-1).pos + 1;
                } else {
                    step = chars.get(j).pos - chars.get(j-1).pos;
                }
                answer += step;
            }
            System.out.println(answer);
        }
    }

    private static class CharAndPosition {
        char c;
        int pos;

        public CharAndPosition(char c, int pos) {
            this.c = c;
            this.pos = pos;
        }
    }
}
