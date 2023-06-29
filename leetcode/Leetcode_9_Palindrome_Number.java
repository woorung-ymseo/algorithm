import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Leetcode_9_Palindrome_Number {

    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of(121, true),
                Arguments.of(-121, false),
                Arguments.of(10, false)
        );
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void isPalindrome(int x, boolean result) {
        boolean answer = true;

        if (x >= 0) {

            if (x % 10 != 0 || x == 0) {
                String str = String.valueOf(x);
                int front = 0;
                int back = str.length() - 1;

                answer = true;

                while (front < back) {
                    if (str.charAt(front++) != str.charAt(back--)) {
                        answer = false;
                        break;
                    }
                }
            }
        } else {
            answer = false;
        }

        System.out.println(answer);
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void isPalindrome2(int x, boolean result) {
        if (x >= 0 && (x % 10 != 0 || x == 0)) {
            int newValue = 0;

            while (x > newValue) {
                newValue = newValue * 10 + x % 10;
                x /= 10;
            }

            System.out.println(newValue == x || newValue/10 == x);
        } else {
            System.out.println(false);
        }
    }
}
