import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class programers_42577_전화번호목록 {

    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of(new String[]{"119", "97674223", "1195524421"}, false),
                Arguments.of(new String[]{"123","456","789"}, true),
                Arguments.of(new String[]{"12","123","1235","567","88"}, false)
//                Arguments.of(new String[]{"2", "11"}, false)

        );
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void removeOuterParentheses(String[] phone_book, boolean solution) {
        final var answer = solution(phone_book);

        assertAll(
                () -> assertEquals(solution, answer)
        );
    }

    /**
     * 최종
     *
     * 중복이 불가라고 해서 set 사용
     *
     * @param phone_book
     * @return
     */
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>();

        for (String phone : phone_book){
            set.add(phone);
        }

        for (String phone : phone_book) {
            final var length = phone.length();

            for (int i = 0; i < length; i++) {
                if (set.contains(phone.substring(0, i))) return false;
            }
        }

        return true;
    }

    /**
     * 정렬 후 다음 것만 비교
     * (정렬시 문자열이라 앞 숫자가 작은것부터 정렬됨)
     * ex)
     * {"119", "97674223", "1195524421"} -> 119, 1195524421, 97674223
     *
     * @param phone_book
     * @return
     */
    public boolean solution_sort(String[] phone_book) {
        List<String> sorted = Arrays.stream(phone_book)
                .sorted()
                .collect(Collectors.toList());

        final var size = sorted.size();

        for (int currentIndex = 0, i = 1; i < size; i++) {
            String current = sorted.get(currentIndex);
            String next = sorted.get(i);

            if (next.indexOf(current) == 0) return false;

            currentIndex++;
        }

        return true;
    }

    /**
     * 실패 (시간초과)
     * desc 정률후 indexOf로 찾으려 했으나 시간초과
     *
     * @param phone_book
     * @return
     */
    public boolean solution_timeout(String[] phone_book) {
        List<String> sorted = Arrays.stream(phone_book)
                .sorted((o1, o2) -> o2.length() - o1.length())
                .collect(Collectors.toList());

        Map<Character, StringBuilder> map = new HashMap();

        for (String phone : sorted) {
            char first = phone.charAt(0);
            StringBuilder data = map.get(first);

            if (data == null) {
                data = new StringBuilder(phone);
                map.put(first, data);
            } else {
                final var firstData = data.subSequence(0, phone.length());
                if (firstData.equals(phone) || data.indexOf("," + phone) > -1) return false;

                data.append(",");
                data.append(phone);
            }
        }

        return true;
    }
}
