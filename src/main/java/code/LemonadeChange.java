package code;

/**
 * https://leetcode.com/problems/lemonade-change/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-30 15:25
 */
public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        //0 : 5, 1 : 10
        int[] arr = new int[2];

        for(int bill : bills) {
            if(bill == 5) {
                arr[0]++;
                continue;
            }
            if(bill == 10) {
                if(arr[0] < 1) {
                    return false;
                }
                arr[1]++;
                arr[0]--;
            }
            if(bill == 20) {
                if(arr[1] > 0 && arr[0] > 0) {
                    arr[1]--;
                    arr[0]--;
                    continue;
                }
                if(arr[1] == 0 && arr[0] >= 3) {
                    arr[0] -= 3;
                    continue;
                }
                return false;
            }
        }
        return true;
    }

}
