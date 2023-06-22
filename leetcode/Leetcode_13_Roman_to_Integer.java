import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.swing.plaf.PanelUI;
import java.util.*;
import java.util.stream.Stream;

public class Leetcode_13_Roman_to_Integer {


    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of("III", 3),
                Arguments.of("LVIII", 58),
                Arguments.of("MCMXCIV", 1994)
        );
    }

    private int getValue(char c) {
        int val = switch (c) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };

        return val;
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void romanToInt(String s, int result) {
        int answer = 0;
        final var length = s.length();

        for (int i = 0; i < length; i++) {
            final var c = s.charAt(i);
            final var nextIndex = i + 1;
            final var value = getValue(c);

            if (nextIndex == length) {
                answer += value;
            } else {
                final var next = s.charAt(i);
                final var nextValue = getValue(next);

                answer = (value < nextValue) ? -value : +value;
            }
        }


        System.out.println(answer);
//        assertAll(
//                () -> assertEquals(solution, answer)
//        );
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void romanToInt2(String s, int result) {
        int answer = 0, before = Integer.MAX_VALUE;
        final var length = s.length();

        for (int i = 0; i < length; i++) {
            final var c = s.charAt(i);
            final var value = getValue(c);

            answer += value;

            if (i > 0 && before < value) {
                answer -= before * 2;
            }

            before = value;
        }


        System.out.println(answer);
//        assertAll(
//                () -> assertEquals(solution, answer)
//        );
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void romanToInt3(String s, int result) {
        int answer = 0;
        int length = s.length();

        for (int i = length - 1; i >= 0; i--) {
            char c = s.charAt(i);

            switch (c) {
                case 'M' -> answer += 1000;
                case 'D' -> answer += 500;
                case 'C' -> answer += (answer >= 500) ? -100 : 100;
                case 'L' -> answer += 50;
                case 'X' -> answer += (answer >= 50) ? -10 : 10;
                case 'V' -> answer += 5;
                case 'I' -> answer += (answer >= 5) ? -1 : 1;
            }
        }


        System.out.println(answer);
//        assertAll(
//                () -> assertEquals(solution, answer)
//        );
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void romanToInt4(String s, int result) {
        int answer = 0;
        int length = s.length();

        for (int i = length - 1; i >= 0; i--) {
            char c = s.charAt(i);
            int val = getValue(c);

            answer += (val * 4 < answer) ? val * -1 : val;
        }


        System.out.println(answer);
//        assertAll(
//                () -> assertEquals(solution, answer)
//        );
    }
}
