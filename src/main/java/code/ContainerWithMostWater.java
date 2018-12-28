package code;

/**
 * https://leetcode.com/problems/container-with-most-water/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-28 15:25
 */
public class ContainerWithMostWater {

    /**
     * Brute Force
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int maxArea = 0;
        for(int i = 0; i < height.length; i++) {
            for(int j = i + 1; j < height.length; j++) {
                int w = j - i;
                int h = Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, w * h);
            }
        }
        return maxArea;
    }

    /**
     * Two Pointer
     *
     * move the short line inside every time
     *
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int maxArea = 0;
        while (left < right) {
            int w = right - left;
            int area;
            if(height[left] > height[right]) {
                area = w * height[right];
                right--;
            } else {
                area = w * height[left];
                left++;
            }
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

}
