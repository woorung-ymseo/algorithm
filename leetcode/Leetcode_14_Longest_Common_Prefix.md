> https://leetcode.com/problems/longest-common-prefix/

![img.png](image/14_성능.png)

---
~~~java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder answer = new StringBuilder();
        Arrays.sort(strs);

        String first = strs[0],
                last = strs[strs.length-1];

        int length = first.length();

        for (int i = 0; i < length; i++) {
            if (first.charAt(i) != last.charAt(i)) break;

            answer.append(first.charAt(i));
        }

        return answer.toString();
    }
}
~~~