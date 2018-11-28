package code;

/**
 * https://leetcode.com/problems/mirror-reflection/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-27 16:06
 */
@SuppressWarnings("SuspiciousNameCombination")
public class MirrorReflection {

    private static final double EPS = 1e-6;

    /**
     * Simulation
     *
     * @param p
     * @param q
     * @return
     */
    public int mirrorReflection(int p, int q) {
        double x = 0;
        double y = 0;
        double rx = p;
        double ry = q;

        // While it hasn't reached a receptor,...
        while (!(
                close(x, p) && (close(y, 0) || close(y, p))
                        || close(x, 0) && close(y, p))) {
            // Want smallest t so that some x + rx, y + ry is 0 or p
            // x + rxt = 0, then t = -x/rx etc.
            double t = 1e9;
            if ((-x / rx) > EPS) {
                t = Math.min(t, -x / rx);
            }
            if ((-y / ry) > EPS) {
                t = Math.min(t, -y / ry);
            }
            if (((p - x) / rx) > EPS) {
                t = Math.min(t, (p - x) / rx);
            }
            if (((p - y) / ry) > EPS) {
                t = Math.min(t, (p - y) / ry);
            }

            x += rx * t;
            y += ry * t;

            if (close(x, p) || close(x, 0)) {
                rx *= -1;
            }
            if (close(y, p) || close(y, 0)) {
                ry *= -1;
            }
        }

        if (close(x, p) && close(y, p)) {
            return 1;
        }
        return close(x, p) ? 0 : 2;
    }

    private boolean close(double x, double y) {
        return Math.abs(x - y) < EPS;
    }

    /**
     * Mathematical
     *
     * Instead of modelling the ray as a bouncing line, model it as a straight line through reflections of the room.
     *
     * gcd = greatest common divisor
     *
     * @param p
     * @param q
     * @return
     */
    public int mirrorReflection1(int p, int q) {
        int g = gcd(p, q);
        p /= g;
        p %= 2;
        q /= g;
        q %= 2;

        if (p == 1 && q == 1) {
            return 1;
        }
        return p == 1 ? 0 : 2;
    }

    private int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }


    /**
     * 把这个射线无限延长，一定会到达某个方块的右上角（补齐方块）。这样，水平方向假设x个方块，垂直方向假设y个方块，y/x = q/p。
     *
     * p和q都是整数，实际水平扩展到p个方块，垂直扩展到q个方块就满足了。
     *
     * x是奇数，y是奇数时，右上角是1；
     * x是奇数，y是偶数时，右上角是0；
     * x是偶数，y是奇数时，右上角是2；
     * x是偶数，y是偶数，右上角是发射点。这一种情况把x，y同时除以2直到某一方不是偶数就行了。
     *
     * @param p
     * @param q
     * @return
     */
    public int mirrorReflection2(int p, int q) {
        while (p % 2 == 0 && q % 2 == 0) {
            p /= 2;
            q /= 2;
        }
        if (p % 2 == 0) {
            return 2;
        } else {
            if (q % 2 == 0) {
                return 0;
            } else {
                return 1;
            }
        }
    }

}
