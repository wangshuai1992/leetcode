package code;

/**
 * https://leetcode.com/problems/minimum-time-visiting-all-points/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-01-10 15:28
 */
public class MinimumTimeVisitingAllPoints {

    public int minTimeToVisitAllPoints(int[][] points) {
        int sec = 0;
        int[] prePoint = null;
        for (int[] point : points) {
            if(prePoint != null) {
                sec += Math.max(Math.abs(prePoint[0] - point[0]), Math.abs(prePoint[1] - point[1]));
            }
            prePoint = point;
        }
        return sec;
    }

}
