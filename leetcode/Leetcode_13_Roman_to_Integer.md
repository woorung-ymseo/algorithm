> https://leetcode.com/problems/roman-to-integer/


![img.png](image/13_성능.png)
---
~~~java
class Solution {
    public int romanToInt(String s) {
        int answer = 0;
        int length = s.length();

        for (int i = length - 1; i >= 0; i--) {
            char c = s.charAt(i);
            int val = getValue(c);

            answer += (val * 4 < answer) ? val * -1 : val;
        }

        return answer;
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
}
~~~