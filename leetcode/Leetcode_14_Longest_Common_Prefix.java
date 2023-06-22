import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class Leetcode_14_Longest_Common_Prefix {


    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of(new String[] {"abcdsssd", "flower","flow","flight", "fl", "f"}, "fl"),
                Arguments.of(new String[] {"dog","racecar","car"}, "")
        );
    }


    @ParameterizedTest
    @MethodSource("providedTestCase")
    public void longestCommonPrefix(String[] strs, String result) {
        StringBuilder answer = new StringBuilder();
        Arrays.sort(strs);

        String first = strs[0],
                last = strs[strs.length-1];
        int length = first.length();

        for (int i = 0; i < length; i++) {
            if (first.charAt(i) != last.charAt(i)) break;

            answer.append(first.charAt(i));
        }

        System.out.println(answer);
    }
}
