package code;

/**
 * https://leetcode.com/problems/monotonic-array/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-08 15:06
 */
public class MonotonicArray {

    public boolean isMonotonic(int[] arr) {
        if (arr.length <= 2) {
            return true;
        }

        Boolean increasing = null;
        for(int i = 0; i < arr.length - 1; i++) {
            if(null == increasing) {
                if(arr[i] != arr[i + 1]) {
                    increasing = arr[i] < arr[i + 1];
                }
                continue;
            }

            if(increasing && arr[i] > arr[i + 1]) {
                return false;
            }
            if(!increasing && arr[i] < arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean isMonotonic1(int[] arr) {
        boolean increasing = true;
        boolean decreasing = true;

        for(int i = 0; i < arr.length - 1; i++) {
            if(arr[i] < arr[i + 1]) {
                decreasing = false;
            }
            if(arr[i] > arr[i + 1]) {
                increasing = false;
            }
        }
        return increasing || decreasing;
    }

}
