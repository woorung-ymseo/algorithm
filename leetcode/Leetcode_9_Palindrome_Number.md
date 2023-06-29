> https://leetcode.com/problems/palindrome-number/

![img.png](image/9_성능.png)

---
~~~java
class Solution {
    public boolean isPalindrome(int x) {

        if (x >= 0 && (x % 10 != 0 || x == 0)) {
            int newValue = 0;

            while (x > newValue) {
                newValue = newValue * 10 + x % 10;
                x /= 10;
            }

            return (newValue == x || newValue/10 == x);
        }

        return false;
    }
}
~~~