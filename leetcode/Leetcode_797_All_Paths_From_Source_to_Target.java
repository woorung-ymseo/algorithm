import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Leetcode_797_All_Paths_From_Source_to_Target {

    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of(new int[][]{{1,2}, {3}, {3}, {}}, new int[][]{{0, 1, 3}, {0, 2, 3}}),
                Arguments.of(new int[][]{{4,3,1}, {3,2,4}, {3}, {4}, {}}, new int[][]{{0,4}, {0,3,4}, {0,1,3,4}, {0,1,2,3,4}, {0,1,4}})
        );
    }

    public List<List<Integer>> bfs_allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> answer = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Arrays.asList(0));
        int dest = graph.length -1;

        while (!queue.isEmpty()) {
            final var path = queue.poll();
            final var now = path.get(path.size() - 1); // 마지막 경로 (현재 위치한 노드)

            if (now == dest) {
                answer.add(new ArrayList<>(path));
            } else {

                for (int node : graph[now]) {
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(node);

                    queue.add(newPath);
                }
            }
        }

        return answer;
    }


    public List<List<Integer>> dfs_allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> answer = new ArrayList<>();
        final var path = new ArrayList<Integer>();
        path.add(0);

        search(answer, path, graph, 0, graph.length - 1);

        return answer;
    }

    public void search(List<List<Integer>> answer, List<Integer> paths, int[][] graph, int now, int dest) {

        if (now == dest) {
            answer.add(new ArrayList(paths));

            return;
        }

        for (int n : graph[now]) {
            paths.add(n);
            search(answer, paths, graph, n, dest);
            paths.remove(paths.size() - 1);
        }
    }


    @ParameterizedTest
    @MethodSource("providedTestCase")
    void solution_bfs(int[][] graph, int[][] result) {
        final var lists = bfs_allPathsSourceTarget(graph);

        for (List<Integer> list : lists) {
            System.out.println(list);
        }

//        assertAll(
//                () -> assertEquals(finalResult, returnIndex)
//        );
    }


    @ParameterizedTest
    @MethodSource("providedTestCase")
    void solution_dfs(int[][] graph, int[][] result) {
        final var lists = dfs_allPathsSourceTarget(graph);

        for (List<Integer> list : lists) {
            System.out.println(list);
        }

//        assertAll(
//                () -> assertEquals(finalResult, returnIndex)
//        );
    }
}
