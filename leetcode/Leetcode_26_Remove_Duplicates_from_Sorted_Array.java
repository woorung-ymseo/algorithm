import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class Leetcode_26_Remove_Duplicates_from_Sorted_Array {


    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of(new int[] {1,1,2}, new int[] {1,2}),
                Arguments.of(new int[] {0,0,1,1,1,2,2,3,3,4}, new int[] {0,1,2,3,4})
        );
    }


    @ParameterizedTest
    @MethodSource("providedTestCase")
    public void removeDuplicates(int[] nums, int[] result) {
        for (int i = 1, count = 0; i < nums.length; i++) {
            int before = nums[i - 1];
            int current = nums[i];

            if (current == before) {
                count++;
            } else {
                nums[i - count] = current;
            }
        }

        for (int num : nums) {
            System.out.println(num);
        }
    }
}
