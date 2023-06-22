> https://leetcode.com/problems/valid-parentheses/

![img.png](image/20_성능.png)

---
~~~java
class Solution {
    public boolean isValid(String s) {
        final var length = s.length();

        if (length % 2 == 1) return false;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < length; i++) {
            final var c = s.charAt(i);

            switch (c) {
                case '(', '{', '[' -> stack.add(c);
                case ')' -> {
                    if (stack.isEmpty()) return false;

                    final var pop = stack.pop();
                    if (pop.charValue() != c - 1) {
                        return false;
                    }
                }
                case '}', ']' -> {
                    if (stack.isEmpty()) return false;

                    final var pop = stack.pop();
                    if (pop.charValue() != c - 2) {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }
}
~~~