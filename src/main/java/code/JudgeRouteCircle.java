package code;

/**
 * https://leetcode.com/problems/judge-route-circle/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2017-12-29 15:34
 */
public class JudgeRouteCircle {
    public boolean judgeCircle(String moves) {
        char[] steps = moves.toCharArray();

        int x = 0, y = 0;

        for (char step : steps) {
            switch (step) {
                case 'U':
                    y += 1;
                    break;
                case 'D':
                    y -= 1;
                    break;
                case 'L':
                    x -= 1;
                    break;
                case 'R':
                    x += 1;
                    break;
                default:

            }
        }

        return x == 0 && y == 0;
    }
}
