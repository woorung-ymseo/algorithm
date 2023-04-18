import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Leetcode_2073_Time_Needed_to_Buy_Tickets {

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void buyTicket(int[] arr, int index, int returnIndex) {
        int result = 0;
        int target = arr[index];

        for (int i = 0; i < arr.length; i++) {
            int qty = arr[i];

            result += (i <= index ? (qty <= target ? qty : target) : (qty < target ? qty : target - 1));
        }

        int finalResult = result;

        assertAll(
                () -> assertEquals(finalResult, returnIndex)
        );
    }

    /*
    24 24 5 24 23 23 23 8
     */
    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of(new int[]{2, 3, 2}, 2, 6),
                Arguments.of(new int[]{5, 1, 1, 1}, 0, 8),
                Arguments.of(new int[]{84, 49, 5, 24, 70, 77, 87, 8}, 3, 154)
                                    // 24  24  5  24  23  23  23  8
        );
    }
}
