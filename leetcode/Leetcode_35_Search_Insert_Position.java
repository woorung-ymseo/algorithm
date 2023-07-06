import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Leetcode_35_Search_Insert_Position {


    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
//                Arguments.of(new int[]{1,3,5,6}, 5, 2),
//                Arguments.of(new int[]{1,3,5,6}, 2, 1),
                Arguments.of(new int[]{1,3,5,6}, 7, 4)
        );
    }
    @ParameterizedTest
    @MethodSource("providedTestCase")
    void searchInsert(int[] nums, int target, int result) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int point = left + (right - left) / 2;

            if (nums[point] == target) {
                left = point;
                break;
            } else if (nums[point] > target) {
                right = point - 1;
            } else {
                left = point + 1;
            }
        }

        long finalAnswer = left;
        assertAll(
                () -> assertEquals(result, finalAnswer)
        );
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void searchInsert2(int[] nums, int target, int result) {
        int index = nums.length;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] >= target) {
                index = i;
                break;
            }
        }

        long finalAnswer = index;
        assertAll(
                () -> assertEquals(result, finalAnswer)
        );
    }
}
