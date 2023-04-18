import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class programers_42587_프린터 {

    class Node {
        int index;
        int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void printer(int[] priorities, int location, int returnIndex) {
        List<Node> arrayList = new ArrayList<>();

        for (int i = 0; i < priorities.length; i++) {
            final var node = new Node(i, priorities[i]);

            arrayList.add(node);
        }

        int result = 1;

        for (int i = 0, j = 1; j < arrayList.size(); j++) {
            Node currentNode = arrayList.get(i);
            Node nextNode = arrayList.get(j);

            if (currentNode.value < nextNode.value) {
                List<Node> preIndex = arrayList.subList(i, j);

                arrayList = arrayList.subList(j, arrayList.size());
                arrayList.addAll(preIndex);

                j = 0;
            }

            if (j + 1 == arrayList.size()) {
                final var node = arrayList.get(0);

                if (node.index == location) break;

                arrayList.remove(0);
                result++;
                j = 0;
            }
        }

        int finalResult = result;

        assertAll(
                () -> assertEquals(finalResult, returnIndex)
        );
    }

    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of(new int[]{2, 1, 3, 2}, 2, 1),
                Arguments.of(new int[]{1, 1, 9, 1, 1, 1}, 0, 5),
                Arguments.of(new int[]{3, 1, 9, 4, 4, 1}, 1, 5)
        );
    }
}
