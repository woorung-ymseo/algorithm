import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class programers_1844_게임맵최단거리 {

    class Node {
        int row;
        int col;
        int distance;

        public Node(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {1,0,1,1,1},
                        {1,0,1,0,1},
                        {1,0,1,1,1},
                        {1,1,1,0,1},
                        {0,0,0,0,1}
                }, 11),
                Arguments.of(new int[][]{
                        {1,0,1,1,1},
                        {1,0,1,0,1},
                        {1,0,1,1,1},
                        {1,1,1,0,0},
                        {0,0,0,0,1}
                }, -1)

        );
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void shortestDistance(int[][] maps, int solution) {
        final var answer = solution(maps);

        assertAll(
                () -> assertEquals(solution, answer)
        );
    }

    /**
     * 최종 (배열 사용)
     *
     * 이전에는 0일때 체크를 안해줘서 시간초과가 났었음
     *
     * @param maps
     * @return
     */
    public int solution(int[][] maps) {
        int successDistance = Integer.MAX_VALUE;
        final var maxRowIndex = maps.length - 1;
        final var maxColIndex = maps[0].length - 1;
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{0, 0, 1});

        while (!queue.isEmpty()) {
            final var current = queue.poll();

            if (maps[current[0]][current[1]] != 0) {

                if (current[0] == maxRowIndex && current[1] == maxColIndex) {
                    successDistance = Math.min(successDistance, current[2]);
                } else {
                    if (current[2] < successDistance) {
                        maps[current[0]][current[1]] = 0;

                        // 오른쪽
                        if (maxColIndex > current[1] && maps[current[0]][current[1] + 1] == 1) {
                            queue.add(new int[]{current[0], current[1] + 1, current[2] + 1});
                        }

                        // 왼쪽
                        if (current[1] > 0 && maps[current[0]][current[1] - 1] == 1) {
                            queue.add(new int[]{current[0], current[1] - 1, current[2] + 1});
                        }

                        // 위
                        if (current[0] > 0 && maps[current[0] - 1][current[1]] == 1) {
                            queue.add(new int[]{current[0] - 1, current[1], current[2] + 1});
                        }

                        // 아래
                        if (maxRowIndex > current[0] && maps[current[0] + 1][current[1]] == 1) {
                            queue.add(new int[]{current[0] + 1, current[1], current[2] + 1});
                        }
                    }
                }
            }
        }

        return successDistance == Integer.MAX_VALUE ? -1 : successDistance;
    }

    /**
     * 최종 (Node 사용)
     *
     * 이전에는 0일때 체크를 안해줘서 시간초과가 났었음
     *
     * @param maps
     * @return
     */
    public int solution3(int[][] maps) {
        int successDistance = Integer.MAX_VALUE;
        final var maxRowIndex = maps.length - 1;
        final var maxColIndex = maps[0].length - 1;
        Queue<Node> queue = new LinkedList();
        queue.add(new Node(0, 0, 1));

        while (!queue.isEmpty()) {
            final var current = queue.poll();

            if (maps[current.row][current.col] != 0) {

                if (current.row == maxRowIndex && current.col == maxColIndex) {
                    successDistance = Math.min(successDistance, current.distance);
                } else {
                    if (current.distance < successDistance) {
                        maps[current.row][current.col] = 0;

                        // 오른쪽
                        if (maxColIndex > current.col && maps[current.row][current.col + 1] == 1) {
                            queue.add(new Node(current.row, current.col + 1, current.distance + 1));
                        }

                        // 왼쪽
                        if (current.col > 0 && maps[current.row][current.col - 1] == 1) {
                            queue.add(new Node(current.row, current.col - 1, current.distance + 1));
                        }

                        // 위
                        if (current.row > 0 && maps[current.row - 1][current.col] == 1) {
                            queue.add(new Node(current.row - 1, current.col, current.distance + 1));
                        }

                        // 아래
                        if (maxRowIndex > current.row && maps[current.row + 1][current.col] == 1) {
                            queue.add(new Node(current.row + 1, current.col, current.distance + 1));
                        }
                    }
                }
            }
        }

        return successDistance == Integer.MAX_VALUE ? -1 : successDistance;
    }

    // 시간초과
    public int solution2(int[][] maps) {
        final var maxRowIndex = maps.length - 1;
        final var maxColIndex = maps[0].length - 1;
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{0, 0, 1});

        while (!queue.isEmpty()) {
            final var current = queue.poll();
            final var row = current[0];
            final var col = current[1];
            final var distance = current[2];

            if (row == maxRowIndex && col == maxColIndex) {
                return distance;
            }

            maps[row][col] = 0;

            // 오른쪽
            if (maxColIndex > col && maps[row][col + 1] == 1) {
                queue.add(new int[]{row, col + 1, distance + 1});
            }

            // 왼쪽
            if (col > 0 && maps[row][col - 1] == 1) {
                queue.add(new int[]{row, col - 1, distance + 1});
            }

            // 위
            if (row > 0 && maps[row - 1][col] == 1) {
                queue.add(new int[]{row - 1, col, distance + 1});
            }

            // 아래
            if (maxRowIndex > row && maps[row + 1][col] == 1) {
                queue.add(new int[]{row + 1, col, distance + 1});
            }
        }

        return -1;
    }

    // 시간초과
    public int solution1(int[][] maps) {
        int distance = 0;
        int successDistance = 0;
        final var maxRowIndex = maps.length - 1;
        final var maxColIndex = maps[0].length - 1;
        Queue<Node> queue = new LinkedList();
        queue.add(new Node(0, 0, 1));
        boolean isSuccess = false;

        while (!queue.isEmpty()) {
            final var current = queue.poll();
            distance = current.distance;
            final var row = current.row;
            final var col = current.col;

            if (row == maxRowIndex && col == maxColIndex) {
                isSuccess = true;
                successDistance = distance;
            } else {
                if (!isSuccess || distance < successDistance) {
                    maps[row][col] = 0;

                    // 오른쪽
                    if (maxColIndex > col && maps[row][col + 1] == 1) {
                        queue.add(new Node(row, col + 1, distance + 1));
                    }

                    // 왼쪽
                    if (col > 0 && maps[row][col - 1] == 1) {
                        queue.add(new Node(row, col - 1, distance + 1));
                    }

                    // 위
                    if (row > 0 && maps[row - 1][col] == 1) {
                        queue.add(new Node(row - 1, col, distance + 1));
                    }

                    // 아래
                    if (maxRowIndex > row && maps[row + 1][col] == 1) {
                        queue.add(new Node(row + 1, col, distance + 1));
                    }
                }
            }
        }

        return isSuccess ? successDistance : -1;
    }
}
