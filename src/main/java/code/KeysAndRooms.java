package code;

import java.util.*;

/**
 * https://leetcode.com/problems/keys-and-rooms/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-05-28 17:16
 */
public class KeysAndRooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        List<Integer> remain = new LinkedList<>();
        for(int i=1; i<rooms.size(); i++) {
            remain.add(i);
        }
        goDown(rooms, remain, 0);
        return remain.isEmpty();
    }

    private void goDown(List<List<Integer>> rooms, List<Integer> remain, int i) {
        List<Integer> keys = rooms.get(i);
        for(Integer key : keys) {
            if(remain.contains(key)) {
                //未访问过该房间
                remain.remove(key);
                goDown(rooms, remain, key);
            }
            //访问过该房间 不做操作
        }
    }


    /**
     * DFS
     *
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        boolean[] seen = new boolean[rooms.size()];
        seen[0] = true;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);

        while(!stack.isEmpty()) {
            List<Integer> keys = rooms.get(stack.pop());
            for(Integer key : keys ) {
                if(!seen[key]) {
                    seen[key] = true;
                    stack.push(key);
                }
            }
        }

        for(boolean v : seen) {
            if(!v) {
                return false;
            }
        }
        return true;
    }

}
