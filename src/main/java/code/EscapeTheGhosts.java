package code;

/**
 * https://leetcode.com/problems/escape-the-ghosts/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-13 18:02
 */
public class EscapeTheGhosts {

    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int step = getStepBettweenSquare(new int[]{0, 0}, target);
        for(int[] ghost : ghosts) {
            int goStep = getStepBettweenSquare(ghost, target);
            if(goStep <= step) {
                return false;
            }
        }
        return true;
    }

    private int getStepBettweenSquare(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }

}
