import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task5 {

    public static void main(String[] args) throws IOException {
        InputStreamReader in= new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(in);

        String[] s = input.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int q = Integer.parseInt(s[1]);

        int counter = 1;
        Map<String, Integer> lastNameToNumber = new HashMap<>();
        PrefixTreeNode root = new PrefixTreeNode('\n');

        for (int i = 0; i < n; i++) {
            String lastName = input.readLine();
            lastNameToNumber.put(lastName, counter);
            counter++;
            root.insert(lastName);
        }

        for (int i = 0; i < q; i++) {
            String[] line = input.readLine().split(" ");
            int num = Integer.parseInt(line[0]);
            String query = line[1];

            List<String> result = root.findByPrefix(query);
            result.sort(String::compareTo);
            if (result.size() >= num){
                System.out.println(lastNameToNumber.get(result.get(num-1)));
            } else {
                System.out.println(-1);
            }
        }
    }

    private static class PrefixTreeNode {

        private final char value;
        private List<PrefixTreeNode> children;
        private boolean isWord;

        public PrefixTreeNode(char value) {
            this.value = value;
        }

        public void insert(String data) {
            if (data.length() == 0) {
                this.isWord = true;
                return;
            }
            if (children == null) {
                children = new ArrayList<>();
            }
            char c = data.charAt(0);
            PrefixTreeNode child = findNodeByChar(c);
            if (child == null) {
                child = new PrefixTreeNode(c);
                children.add(child);
            }
            child.insert(data.substring(1));
        }

        public List<String> findByPrefix(String prefix) {
            String tempPrefix = prefix;
            PrefixTreeNode node = this;
            while (tempPrefix.length() > 0 ){
                node = node.findNodeByChar(tempPrefix.charAt(0));
                tempPrefix = tempPrefix.substring(1);
                if (node == null){
                    return new ArrayList<>();
                }
            }
            List<String> result = new ArrayList<>();
            if (prefix.length() > 1){
                node.bfs(prefix.substring(0, prefix.length()-1), result);
            } else {
                node.bfs("", result);
            }
            return result;
        }

        private void bfs(String prefix, List<String> result) {
            if (value != '\n') {
                prefix = prefix + value;
            }
            if (this.isWord){
                result.add(prefix);
            }
            if (children != null) {
                for(PrefixTreeNode node: children) {
                    node.bfs(prefix, result);
                }
            }
        }

        private PrefixTreeNode findNodeByChar(char c) {
            if (children != null) {
                for(PrefixTreeNode node: children) {
                    if (node.value == c) {
                        return node;
                    }
                }
            }
            return null;
        }
    }
}
