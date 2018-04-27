package code;

/**
 * https://leetcode.com/problems/optimal-division/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-19 11:27
 */
public class OptimalDivision {

    /**
     * a/(b/c/d/e/f...)
     *
     * @param nums
     * @return
     */
    public String optimalDivision(int[] nums) {
        if(nums.length == 1) {
            return nums[0] + "";
        }
        if(nums.length == 2) {
            return nums[0] + "/" + nums[1];
        }

        StringBuilder builder = new StringBuilder();
        builder.append(nums[0]).append("/(");
        for(int i=1; i<nums.length; i++) {
            builder.append(nums[i]).append('/');
        }
        builder.deleteCharAt(builder.length() - 1).append(')');
        return builder.toString();
    }

}
