package code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/partition-labels/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-03-14 10:47
 */
public class PartitionLabels {

    /**
    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();

        while(S.length() != 0) {
            int index = 0;

            for(int i=0; i<S.length(); i++) {
                if(i > index) {
                    break;
                }
                char c = S.charAt(i);
                int tempIndex = S.lastIndexOf(c);
                index = index < tempIndex ? tempIndex : index;
            }
            result.add(index + 1);
            S = S.substring(index + 1);
        }

        return result;
    }
     */

    public List<Integer> partitionLabels(String S) {
        //26个字母
        int[] last = new int[26];

        //记录每个字母最后出现的位置
        for (int i = 0; i < S.length(); ++i) {
            last[S.charAt(i) - 'a'] = i;
        }

        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "ababcbacadefegdehijhklij";
        System.out.println(new PartitionLabels().partitionLabels(str));
    }

}
