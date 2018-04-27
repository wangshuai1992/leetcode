package code;

/**
 * https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-19 18:04
 */
public class PrimeNumberOfSetBitsInBinaryRepresentation {

    public int countPrimeSetBits(int L, int R) {
        int result = 0;
        for (int i = L; i <= R; i++) {
            if (isSmallPrime(Integer.bitCount(i))) {
                result ++;
            }
        }
        return result;
    }

    /**
     * We only need primes up to 19 because R <= 10^6 < 2^20
     *
     * @param x
     * @return
     */
    private boolean isSmallPrime(int x) {
        return (x == 2 || x == 3 || x == 5 || x == 7 ||
                x == 11 || x == 13 || x == 17 || x == 19);
    }

}
