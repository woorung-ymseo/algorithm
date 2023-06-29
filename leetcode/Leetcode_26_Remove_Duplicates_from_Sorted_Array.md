> https://leetcode.com/problems/remove-duplicates-from-sorted-array/

![img.png](image/26_성능.png)

---
~~~java
class Solution {
    public int removeDuplicates(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            int before = nums[i - 1];
            int current = nums[i];

            if (current == before) {
                count++;
            } else {
                nums[i - count] = current;
            }
        }

        return nums.length - count;
    }
}
~~~