package code;

import java.util.*;

/**
 * https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2020-01-10 14:38
 */
public class GroupThePeopleGivenTheGroupSizeTheyBelongTo {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> ans = new ArrayList<>();
        int id = 0;
        // key: size, value: group
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int size : groupSizes) {
            List<Integer> group = map.get(size);
            if (group == null || group.size() == size) {
                if (group != null) {
                    // group is full
                    ans.add(group);
                }
                group = new ArrayList<>();
                map.put(size, group);
            }
            group.add(id);
            id++;
        }
        ans.addAll(map.values());
        return ans;
    }

}
