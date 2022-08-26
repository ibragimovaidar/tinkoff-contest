import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Task8 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        Node r = new Node();
        for (int i = 0; i < n; i++) {
            char[] chars = sc.nextLine().toCharArray();
            Node c = r;
            Node p;
            for (char character : chars) {
                p = c;
                c = c.getC(character);
                if (c == null) {
                    c = new Node(p.d + 1, character, p);
                    p.addC(c);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            line = sc.nextLine().split(" ");
            char[] pr = line[0].toCharArray();
            char[] po = line[1].toCharArray();
            int print = 0;
            Node p = r;
            for (char character : pr) {
                if (p == null) break;
                p = p.getC(character);
            }
            if (p != null && p.d != 0 && p.a != 0) {
                Set<Node> set = new HashSet<>(Arrays.asList(p.c))
                        .stream().filter(Objects::nonNull).collect(Collectors.toSet());
                while (set.stream().mapToInt(x -> x.a).sum() > 0) {
                    Set<Node> newSet = new HashSet<>();
                    for (Node no : set) {
                        if (no.a == 0) newSet.add(no);
                        else Arrays.stream(no.c).filter(Objects::nonNull).forEach(newSet::add);
                    }
                    set = newSet;
                }
                for (Node no : set) {
                    p = no;
                    boolean flag = false;
                    for (int j = po.length - 1; j >= 0; j--) {
                        if (p.d == pr.length || p.character != po[j]) break;
                        p = p.p;
                        if (j == 0) flag = true;
                    }
                    if (flag) print++;
                }
            }
            System.out.println(print);
        }
    }

    private static class Node {

        private final int d;
        private final Character character;
        private final Node p;
        private final Node[] c;
        private int a;

        public Node() {
            this(0, null, null);
        }

        public Node(int d, Character character, Node p) {
            this.d = d;
            this.character = character;
            this.p = p;
            this.c = new Node[4];
            this.a = 0;
        }

        public Node getC(char character) {
            switch (character) {
                case 'S':
                    return this.c[0];
                case 'T':
                    return this.c[1];
                case 'A':
                    return this.c[2];
                case 'R':
                    return this.c[3];
            }
            return null;
        }

        public void addC(Node c) {
            switch (c.character) {
                case 'S':
                    this.c[0] = c;
                    a += 1;
                    break;
                case 'T':
                    this.c[1] = c;
                    a += 1;
                    break;
                case 'A':
                    this.c[2] = c;
                    a += 1;
                    break;
                case 'R':
                    this.c[3] = c;
                    a += 1;
                    break;
            }
        }
    }
}