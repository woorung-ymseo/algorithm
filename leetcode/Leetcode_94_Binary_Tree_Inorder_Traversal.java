import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Leetcode_94_Binary_Tree_Inorder_Traversal {


    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of(2, 2),
                Arguments.of(3, 3),
                Arguments.of(4, 5),
                Arguments.of(5, 8),
                Arguments.of(6, 13),
                Arguments.of(35, 14930352),
                Arguments.of(44, 1134903170),
                Arguments.of(36, 24157817)
        );
    }
//    @ParameterizedTest
//    @MethodSource("providedTestCase")
//    void climbStairs(int n, int result) {
//        public void search(TreeNode node) {
//            if (node != null) {
//                search(node.left);
//                answer.add(node.val);
//                search(node.right);
//            }
//        }
//
//        long finalAnswer = answer;
//        assertAll(
//                () -> assertEquals(result, finalAnswer)
//        );
//    }
}
