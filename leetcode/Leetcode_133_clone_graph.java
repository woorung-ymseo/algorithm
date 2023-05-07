import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Leetcode_133_clone_graph {

    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private static Stream<Arguments> providedTestCase() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        return Stream.of(
                Arguments.of(node1)
        );
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void maxDistanceProblem(Node node) {
        final var answer = cloneGraph(node);


        System.out.println(1);
//        assertAll(
//                () -> assertEquals(solution, answer)
//        );
    }

    public Node cloneGraph(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        Node clone = new Node(node.val);
        Node currentClone = clone;
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Node> newNodeMap = new HashMap<>();

        newNodeMap.put(node.val, clone);

        while(!queue.isEmpty()) {
            Node current = queue.poll();


            if (!visited.contains(current.val)) {
                currentClone = new Node(current.val);
                int size = current.neighbors.size();

                for (int i = 0; i < size; i++) {
                    Node neighbor = current.neighbors.get(i);
                    Node copyNode = newNodeMap.get(neighbor.val);
                    copyNode = copyNode != null ? copyNode : new Node(neighbor.val);

                    currentClone.neighbors.add(copyNode);
                    queue.add(neighbor);
                }

                newNodeMap.put(current.val, currentClone);
            }
        }

        return clone;
    }
}
