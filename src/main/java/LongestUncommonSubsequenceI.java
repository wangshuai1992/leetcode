import java.util.HashMap;

/**
 * https://leetcode.com/problems/longest-uncommon-subsequence-i/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-17 14:58
 */
public class LongestUncommonSubsequenceI {

    /*
    Simple analysis of this problem can lead to an easy solution.

    These three cases are possible with string a and b:-

    1.a=b. If both the strings are identical, it is obvious that no subsequence will be uncommon. Hence, return -1.

    2.length(a)=length(b) and a≠b. Example: abc and abd. In this case we can consider any string i.e. abc or abd as a
      required subsequence, as out of these two strings one string will never be a subsequence of other string. Hence,
      return length(a) or length(b).

    3.length(a)≠length(b). Example abcd and abc. In this case we can consider bigger string as a required subsequence
      because bigger string can't be a subsequence of smaller string. Hence, return max(length(a),length(b)).
    */
    /*
    public int findLUSlength(String a, String b) {
        if (a.equals(b))
            return -1;
        return Math.max(a.length(), b.length());
    }
    */

    /**
     * In the brute force approach we will generate all the possible 2^n
     * subsequences of both the strings and store their number of occurences in a hashmap.
     * Longest subsequence whose frequency is equal to 11 will be the required subsequence.
     * And, if it is not found we will return -1−1.
     *
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : new String[]{a, b}) {
            // Consider a string "abc" of length 3. Now to generate all subsequences of this string we can iterate i
            // from 0 to 2^3-1 =1<<3 -1 = 7.
            //Now three left most bits of i will determine the subsequence, i.e. if bit is 1 corresponding character
            // should be considered else not.
            for (int i = 0; i < (1 << s.length()); i++) {
                String t = "";
                for (int j = 0; j < s.length(); j++) {
                    if (((i >> j) & 1) != 0)  //checks whether jth bit of i is 1 or not
                        t += s.charAt(j);
                }
                if (map.containsKey(t))
                    map.put(t, map.get(t) + 1);
                else
                    map.put(t, 1);
            }
        }
        int res = -1;
        for (String s : map.keySet()) {
            if (map.get(s) == 1)
                res = Math.max(res, s.length());
        }

        return res;
    }

}
