package code;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-increment-to-make-array-unique
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-28 15:20
 */
public class MinimumIncrementToMakeArrayUnique {

    /**
     * Brute Force / O(N) time, O(N) space
     * <p>
     * 将重复的数都取出，再放入后面空的位置上
     *
     * @param arr
     * @return
     */
    public int minIncrementForUnique(int[] arr) {
        int[] count = new int[100000];
        for (int a : arr) {
            count[a] = count[a] + 1;
        }

        int result = 0;
        //taken记录当前已经从数组中拿掉多少个数，由于拿出时已经ans -= val，这些数都可以当做0看待
        int takenNum = 0;

        //遍历count数组，此时的i其实是arr中的值，count[i]代表对应值的个数
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 1) {
                takenNum += count[i] - 1;
                result -= i * (count[i] - 1);
            } else if (count[i] == 0 && takenNum > 0) {
                takenNum--;
                result += i;
            }
        }
        return result;
    }

    /**
     * Maintain Duplicate Info / O(nlogn) time, O(n) space
     *
     * sort and iterate
     *
     * @param arr
     * @return
     */
    public int minIncrementForUnique1(int[] arr) {
        Arrays.sort(arr);
        int ans = 0;
        int taken = 0;

        for (int i = 1; i < arr.length; ++i) {
            if (arr[i - 1] == arr[i]) {
                // a duplicate to take
                taken++;
                ans -= arr[i];
            } else {
                int give = Math.min(taken, arr[i] - arr[i - 1] - 1);
                //use math to calculate sum
                ans += give * (give + 1) / 2 + give * arr[i - 1];
                taken -= give;
            }
        }

        if (arr.length > 0) {
            // taken > 0
            ans += taken * (taken + 1) / 2 + taken * arr[arr.length - 1];
        }

        return ans;
    }

}
