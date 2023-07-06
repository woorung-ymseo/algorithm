import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Leetcode_70_Climbing_Stairs {


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
    @ParameterizedTest
    @MethodSource("providedTestCase")
    void climbStairs(int n, int result) {
        int answer = 1;
        int mok = n / 2;
        int namuzi = n % 2;

        for (int i = 1; i <= mok; i++) {
            int totalCount = (mok - i) * 2 + namuzi + i;
            int[] temp = new int[totalCount + 1];
            int duplicatedSize = Integer.min(totalCount - i, i);
            int otherSize = Integer.max(totalCount - i, i);
            int tempCount = 1;
            List<Integer> list = new ArrayList<>();

            for (int j = 0; j <= duplicatedSize; j++) temp[j] = temp[j] == 0 ? j * -1 : temp[j] * j;
            for (int j = duplicatedSize + 1; j <= otherSize; j++) temp[j] = temp[j] == 0 ? -1 : temp[j] * j;
            for (int j = 2; j < temp.length; j++) {
                if (temp[j] == 0) {
                    final var size = list.size();

                    for (int k = size - 1; k >= 0; k--) {
                        final var mother = list.get(k);

                        if (tempCount % mother == 0) {
                            tempCount /= mother;
                            list.remove(k);
                        }
                    }

                    tempCount *= j;

                    if (tempCount < 0) {
                        System.out.println("aa");
                    }
                } else if (temp[j] < -1) {
                    if (tempCount % temp[j] == 0) {
                        tempCount /= temp[j] * -1;
                    } else {
                        list.add(temp[j] * -1);
                    }
                }
            }

            for (int mother : list) tempCount /= mother;

            answer += tempCount;
        }

        long finalAnswer = answer;
        assertAll(
                () -> assertEquals(result, finalAnswer)
        );
    }
}
