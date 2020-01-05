import java.util.HashMap;
import java.util.Map;

public class TwoNumbers {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        int[] r = new int[2];
        int[] input = {3, 2, 4};
        int target = 6;
        r = solution1.twoSum(input, target);
        System.out.println(r[0] + " " + r[1]);
    }
}

class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            /* 
            * 方法一：暴力破解
            for (int j = i + 1; j < nums.length; j++) {
                if (target == (nums[i] + nums[j])) {
                    result[0] = i;
                    result[1] = j;
                }
            }
            */
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
            }
            map.put(nums[i], i);
        }

        return result;
    }
}
