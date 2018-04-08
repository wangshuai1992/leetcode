/**
 * https://leetcode.com/problems/custom-sort-string/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-08 10:08
 */
public class CustomSortString {

    /*
    public String customSortString(String S, String T) {
        char[] result = new char[T.length()];
        int resultIndex = 0;

        char[] order = S.toCharArray();
        int orderIndex = 0;
        int orderLen = order.length;

        String temp = T;
        while(orderIndex < orderLen) {
            char c = order[orderIndex];
            int pos = temp.indexOf(c);
            if(pos < 0) {
                orderIndex ++;
                continue;
            }

            result[resultIndex] = c;
            resultIndex ++;
            temp = temp.replaceFirst(String.valueOf(c), "");
        }

        char[] tempArr = temp.toCharArray();
        for (char c : tempArr) {
            result[resultIndex] = c;
            resultIndex ++;
        }

        return new String(result);
    }
    */

    public String customSortString(String S, String T) {
        int[] count = new int[26];

        // count each char in T.
        for (char c : T.toCharArray()) {
            ++count[c - 'a'];
        }

        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            // sort chars both in T and S by the order of S.
            while (count[c - 'a']-- > 0) {
                sb.append(c);
            }
        }
        for (char c = 'a'; c <= 'z'; ++c) {
            // group chars in T but not in S.
            while (count[c - 'a']-- > 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "kqep";
        String t = "pekeq";
        System.out.println(new CustomSortString().customSortString(s, t));
    }
}
