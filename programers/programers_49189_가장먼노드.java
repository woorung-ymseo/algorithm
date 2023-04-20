import com.jayway.jsonpath.internal.function.numeric.Max;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class programers_49189_가장먼노드 {

    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of(6, new int[][] {
                        {3,6},
                        {4,3},
                        {3,2},
                        {1,3},
                        {1,2},
                        {2,4},
                        {5,2},
                }, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void maxDistanceProblem(int n, int[][] edge, int solution) {
        final var answer = solution(n, edge);

        assertAll(
                () -> assertEquals(solution, answer)
        );
    }

    public int solution(int n, int[][] edge) {
        final var graph = new ArrayList<HashSet<Integer>>();

        for (int i = 0; i < n + 1; i++) {
            graph.add(new HashSet<>());
        }

        int node1, node2;

        for (int i = 0; i < edge.length; i++) {
            node1 = edge[i][0];
            node2 = edge[i][1];

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        return search(graph, new boolean[n + 1], new int[n + 1]);
    }

    public int search(ArrayList<HashSet<Integer>> graph, boolean[] visited, int[] distance) {
        int maxDistance = 0;
        int cnt = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        visited[1] = true;
        distance[1] = 0;


        while (!queue.isEmpty()) {
            final var current = queue.poll();
            final var node = graph.get(current);

            for (int val : node) {

                // 방문 ㄴㄴ
                if (!visited[val]) {
                    visited[val] = true;
                    distance[val] = distance[current] + 1;

                    queue.add(val);
                }
            }
        }

        for (int i = 1; i < distance.length; i++) {
            final var d = distance[i];

            if (d > maxDistance) {
                maxDistance = d;
                cnt = 1;
            } else if (d == maxDistance) {
                cnt++;
            }
        }

        return cnt;
    }
}
