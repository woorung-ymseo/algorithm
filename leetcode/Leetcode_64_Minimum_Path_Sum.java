import com.jayway.jsonpath.internal.function.numeric.Max;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Leetcode_64_Minimum_Path_Sum {

    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {1,3,1},
                        {1,5,1},
                        {4,2,1}
                }, 7),
                Arguments.of(new int[][]{{1,2,3}, {4,5,6}}, 12)
        );
    }

    public int minPathSum_bfs(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, grid[0][0]});
        int min = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            final var path = queue.poll();
            final var rowCheck = path[0] + 1 < grid.length;
            final var colCheck = path[1] + 1 < grid[0].length;

            if (!(rowCheck || colCheck)) {
                min = Math.min(min, path[2]);
            } else if (min < Integer.MAX_VALUE && min > path[2]) {
                continue;
            } else {
                if (rowCheck) queue.add(new int[] {path[0] + 1, path[1], path[2] + grid[path[0] + 1][path[1]]});
                if (colCheck) queue.add(new int[] {path[0], path[1] + 1, path[2] + grid[path[0]][path[1] + 1]});
            }
        }

        return min;
    }

    public int minPathSum(int[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i > 0 || j > 0) {
                    grid[i][j] += Math.min(
                            j > 0 ? grid[i][j - 1] : Integer.MAX_VALUE,
                            i > 0 ? grid[i - 1][j] : Integer.MAX_VALUE
                    );
                }
            }
        }

        return grid[grid.length - 1][grid[0].length - 1];
    }


    @ParameterizedTest
    @MethodSource("providedTestCase")
    void solution(int[][] grid, int answer) {
        final var result = minPathSum(grid);

        assertAll(
                () -> assertEquals(answer, result)
        );
    }


}
