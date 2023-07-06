> https://leetcode.com/problems/search-insert-position/submissions/987675120/

![img.png](image/35_성능.png)![img.png](image/26_성능.png)

---
~~~java
class Solution {
    public int searchInsert(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] >= target) {
                return i;
            }
        }

        return nums.length;
    }
}
~~~