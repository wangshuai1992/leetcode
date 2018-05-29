package code;

/**
 * https://leetcode.com/problems/largest-triangle-area/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-05-29 09:38
 */
public class LargestTriangleArea {

    public double largestTriangleArea(int[][] points) {
        int N = points.length;
        double ans = 0;
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j)
                for (int k = j+1; k < N; ++k)
                    ans = Math.max(ans, area(points[i], points[j], points[k]));
        return ans;
    }

    /**
     * shoelace formula
     *
     * @param P
     * @param Q
     * @param R
     * @return
     */
    public double area(int[] P, int[] Q, int[] R) {
        return 0.5 * Math.abs(P[0]*Q[1] + Q[0]*R[1] + R[0]*P[1]
                -P[1]*Q[0] - Q[1]*R[0] - R[1]*P[0]);
    }

}
