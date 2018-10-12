package code;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/projection-area-of-3d-shapes/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-09 17:44
 */
public class ProjectionAreaOf3DShapes {

    public int projectionArea(int[][] grid) {

        int xyArea = 0;
        Map<Integer, Integer> xzMap = new HashMap<>();
        Map<Integer, Integer> yzMap = new HashMap<>();
        int xzArea = 0;
        int yzArea = 0;

        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[i].length; j++) {
                if(grid[i][j] == 0) {
                    //为0时不存在柱子
                    continue;
                }

                xyArea ++;

                Integer xzHeight = xzMap.get(i);
                if(null == xzHeight) {
                    xzMap.put(i, grid[i][j]);
                } else {
                    xzMap.put(i, Math.max(grid[i][j], xzHeight));
                }

                Integer yzHeight = yzMap.get(j);
                if(null == yzHeight) {
                    yzMap.put(j, grid[i][j]);
                } else {
                    yzMap.put(j, Math.max(grid[i][j], yzHeight));
                }
            }
        }

        for(Map.Entry<Integer, Integer> entry : xzMap.entrySet()) {
            xzArea += entry.getValue();
        }

        for(Map.Entry<Integer, Integer> entry : yzMap.entrySet()) {
            yzArea += entry.getValue();
        }

        return xyArea + yzArea + xzArea;

    }

}
