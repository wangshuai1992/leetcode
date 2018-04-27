package code;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/distribute-candies/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-08 17:22
 */
public class DistributeCandies {

    public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet<>();
        for (int i : candies) {
            set.add(i);
            if(set.size() == candies.length/2) {
                break;
            }
        }

        return set.size();
    }

}
