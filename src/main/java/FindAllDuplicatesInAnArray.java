import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-13 10:32
 */
public class FindAllDuplicatesInAnArray {

    /*
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.putIfAbsent(num, 1) != null) {
                result.add(num);
            }
        }
        return result;
    }
    */

    /**
     * when find a number i, flip the number at position i-1 to negative.
     * if the number at position i-1 is already negative, i is the number that occurs twice.
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0)
                res.add(index+1);
            nums[index] = -nums[index];
        }
        return res;
    }

}
