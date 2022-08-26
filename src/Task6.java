import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task6 {

    public static void main(String[] args) throws IOException {

        InputStreamReader in= new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(in);

        int n = Integer.parseInt(input.readLine());

        Map<Integer, Node> valueToNode = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] line = input.readLine().split(" ");
            int v1 = Integer.parseInt(line[0]);
            int v2 = Integer.parseInt(line[1]);

            if (!valueToNode.containsKey(v1)){
                valueToNode.put(v1, new Node(v1));
            }
            if (!valueToNode.containsKey(v2)){
                valueToNode.put(v2, new Node(v2));
            }

            if (v1 == v2){
                valueToNode.get(v1).cycles++;
            } else {
                valueToNode.get(v1).children.add(valueToNode.get(v2));
            }
        }

        int maxCost = 0;

        Collection<Node> values = valueToNode.values();
        for (Node node: values) {
            int cost = bfsMaxCost(node, 0);
            if (cost > maxCost) {
                maxCost = cost;
            }
        }
        System.out.println(maxCost);
    }

    private static class Node {

        final int value;
        final List<Node> children = new ArrayList<>();
        int cycles = 0;

        boolean isPassed = false;
        int cost = 0;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int bfsMaxCost(Node node, int cost){
        if (node.isPassed){
            return cost + node.cost;
        }
        if (!node.children.isEmpty()){
            for (Node child: node.children) {
                return bfsMaxCost(child, cost + 1);
            }
        }
        node.cost = cost + 1 + node.cycles;
        node.isPassed = true;
        return cost + 1 + node.cycles;
    }
}
