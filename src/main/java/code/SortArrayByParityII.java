package code;

/**
 * https://leetcode.com/problems/sort-array-by-parity-ii/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-15 14:48
 */
public class SortArrayByParityII {

    public int[] sortArrayByParityII(int[] arr) {
        int[] oddArr = new int[arr.length / 2];
        int[] evenArr = new int[arr.length / 2];

        int oddIndex = 0;
        int evenIndex = 0;
        for (int num : arr) {
            if ((num & 1) == 1) {
                oddArr[oddIndex++] = num;
            } else {
                evenArr[evenIndex++] = num;
            }
        }

        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if((i & 1) == 1) {
                result[i] = oddArr[--oddIndex];
            } else {
                result[i] = evenArr[--evenIndex];
            }
        }
        return result;
    }

    public int[] sortArrayByParityII1(int[] arr) {
        int size = arr.length;

        int even = 0;
        int odd = 1;
        while (even < size - 1 && odd < size) {
            if((arr[even] & 1) == 1 && (arr[odd] & 1) == 0) {
                arr[even] ^= arr[odd];
                arr[odd] ^= arr[even];
                arr[even] ^= arr[odd];
                even += 2;
                odd += 2;
                continue;
            }
            if((arr[even] & 1) == 0) {
                even += 2;
            }
            if((arr[odd] & 1) == 1) {
                odd += 2;
            }
        }
        return arr;
    }

}
