package code;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/sort-array-by-parity/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-08 17:46
 */
public class SortArrayByParity {

    /**
     * use LinkedList
     *
     * @param arr
     * @return
     */
    public int[] sortArrayByParity(int[] arr) {
        LinkedList<Integer> list = new LinkedList<>();
        for(int num : arr) {
            if(num % 2 == 1) {
                list.addLast(num);
            } else {
                list.addFirst(num);
            }
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    /**
     * use Comparator
     * @param arr
     * @return
     */
    public int[] sortArrayByParity1(int[] arr) {

        return Arrays.stream(arr)
                     .boxed()
                     .sorted(Comparator.comparingInt(a -> a%2))
                     .mapToInt(i -> i)
                     .toArray();
    }

    /**
     * use quickSort
     *
     * @param arr
     * @return
     */
    public int[] sortArrayByParity2(int[] arr) {

        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            if((arr[i] & 1) == 1 && (arr[j] & 1) == 0) {
                arr[i] ^= arr[j];
                arr[j] ^= arr[i];
                arr[i] ^= arr[j];
            }

            if((arr[i] & 1) == 0) {
                i++;
            }

            if((arr[j] & 1) == 1) {
                j--;
            }
        }
        return arr;
    }

}
