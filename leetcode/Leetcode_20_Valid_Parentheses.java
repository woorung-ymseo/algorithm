import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Stream;

public class Leetcode_20_Valid_Parentheses {


    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of("()", true),
                Arguments.of("()[]{}", true),
                Arguments.of("(]", false),
                Arguments.of("([)]", false),
                Arguments.of("(", false),
                Arguments.of("((", false),
                Arguments.of("){", false)
        );
    }


    @ParameterizedTest
    @MethodSource("providedTestCase")
    public void isValid(String s, boolean result) {
        boolean answer = true;
        final var length = s.length();
        Stack<Character> q = new Stack<>();

        for (int i = 0; i < length; i++) {
            final var c = s.charAt(i);

            switch (c) {
                case '(', '{', '[' -> q.add(c);
                case ')' -> {
                    final var pop = q.pop();
                    if (pop.charValue() != c - 1) {
                        System.out.println(false);
                        return;
                    }
                }
                case '}', ']' -> {
                    final var pop = q.pop();
                    if (pop.charValue() != c - 2) {
                        System.out.println(false);
                        return;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
