import java.util.HashMap;
import java.util.Map;

/*
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
示例:

给定 nums = [2, 7, 11, 15], target = 9
因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
 */

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
            * 方法一：暴力破解，要进行两重循环
            * 时间复杂度O（N*N）
            for (int j = i + 1; j < nums.length; j++) {
                if (target == (nums[i] + nums[j])) {
                    result[0] = i;
                    result[1] = j;
                }
            }
            */

            /*
            * 方法二：使用HashMap保存已扫描过的值
            * 循环时每当遇到一个新的值，先看一下当前值和之前保存的值（有没有）是否和为target
            * 时间复杂度O（N），空间复杂度O（N）
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
