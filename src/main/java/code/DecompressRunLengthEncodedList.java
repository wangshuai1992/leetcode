package code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/decompress-run-length-encoded-list/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-04-29 11:15
 */
public class DecompressRunLengthEncodedList {

    public int[] decompressRLElist(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i+=2) {
            for (int j = 0; j < nums[i]; j++) {
                list.add(nums[i + 1]);
            }
        }
//        int[] result = new int[list.size()];
//        for (int i = 0; i < result.length; i++) {
//            result[i] = list.get(i);
//        }
//        return result;
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
