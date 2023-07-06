> https://leetcode.com/problems/climbing-stairs/submissions/987662959/

![img.png](image/35_성능.png)![img.png](image/26_성능.png)![img.png](image/70_성능.png)

---
~~~java
class Solution {
    public int climbStairs(int n) {
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

        return answer;
    }
}
~~~