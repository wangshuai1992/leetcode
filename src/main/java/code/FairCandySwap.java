package code;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/fair-candy-swap/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-08 15:41
 */
public class FairCandySwap {

    /**
     * Brute Force
     *
     * @param a
     * @param b
     * @return
     */
    public int[] fairCandySwap(int[] a, int[] b) {
        int[] result = new int[2];

        int aTotal = Arrays.stream(a).sum();
        int bTotal = Arrays.stream(b).sum();

        int delta = aTotal - bTotal;
        for (int sizeFromA : a) {
            for (int sizeFromB : b) {
                if (delta == (sizeFromA - sizeFromB) * 2) {
                    result[0] = sizeFromA;
                    result[1] = sizeFromB;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * use hash to reduce time complexity
     *
     * @param a
     * @param b
     * @return
     */
    public int[] fairCandySwap1(int[] a, int[] b) {
        int[] result = new int[2];

        int aTotal = Arrays.stream(a).sum();
        int bTotal = Arrays.stream(b).sum();

        int delta = aTotal - bTotal;

        Set<Integer> setB = new HashSet<>();
        for(int i : b) {
            setB.add(i);
        }
        for(int i : a) {
            if(setB.contains(i - delta / 2)) {
                result[0] = i;
                result[1] = i - delta / 2;
            }
        }

        return result;
    }

}
