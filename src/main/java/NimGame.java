/**
 * https://leetcode.com/problems/nim-game/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-18 10:49
 */
public class NimGame {

    /*
    public boolean canWinNim(int n) {
        if(n == 0) {
            return false;
        }

        if(n == 1 || n == 2 || n == 3) {
            return true;
        }

        //三种情况下下一个人获胜的概率；存在一种情况使下一个人失败即可
        if(!canWinNim(n - 1)) {
            return true;
        }
        if(!canWinNim(n - 2)) {
            return true;
        }
        if(!canWinNim(n - 3)) {
            return true;
        }
        return false;
    }
    */

    /**
     * You can always win a Nim game if the number of stones n in the pile is not divisible by 4.
     * <p>
     * Reasoning
     * Let us think of the small cases. It is clear that if there are only one, two, or three stones in the pile,
     * and it is your turn, you can win the game by taking all of them. Like the problem description says, if
     * there are exactly four stones in the pile, you will lose. Because no matter how many you take, you will leave
     * some stones behind for your opponent to take and win the game. So in order to win, you have to ensure that you
     * never reach the situation where there are exactly four stones on the pile on your turn.
     * <p>
     * Similarly, if there are five, six, or seven stones you can win by taking just enough to leave four stones for
     * your opponent so that they lose. But if there are eight stones on the pile, you will inevitably lose, because
     * regardless whether you pick one, two or three stones from the pile, your opponent can pick three, two or one
     * stone to ensure that, again, four stones will be left to you on your turn.
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        // the binary presentation of 4 is 100, 8 is 1000..., which means the last two bits is always 0 if
        // the number is 4's multiples. So n & 3 (n & 11) is for checking if the number is 4's multiples,
        // and that is the result, too.
        return (n & 3) != 0;
    }

}
