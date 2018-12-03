package code;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/rabbits-in-forest/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-29 15:33
 */
public class RabbitsInForest {

    /**
     * 颜色相同的兔子回答一定相同，回答不同的兔子颜色一定不同
     *
     * 如果某个兔子回答的数字是x，那么说明森林里共有x+1个相同颜色的兔子，我们最多允许x+1个兔子同时回答x个，
     * 一旦超过了x+1个兔子，那么就得再增加x+1个新兔子(关键)
     *
     * 使用一个HashMap来建立某种颜色兔子的总个数和在数组中还允许出现的个数之间的映射
     *
     * 遍历数组中的每个兔子，
     * 如果该兔子回答了x个，若该颜色兔子的总个数x+1不在HashMap中，或者映射为0了，
     * 我们将这x+1个兔子加入结果res中，然后将其映射值设为x，表示在数组中还允许出现x个也回答x的兔子；
     * 否则的话，将映射值自减1即可
     *
     * @param answers
     * @return
     */
    public int numRabbits(int[] answers) {
        int result = 0;

        //key : 某种颜色兔子总数  value : 数组中还允许出现的数量
        Map<Integer, Integer> map = new HashMap<>();

        for (int x : answers) {
            int count = map.getOrDefault(x + 1, 0);
            if (count == 0) {
                result += x + 1;
                map.put(x + 1, x);
            } else {
                map.put(x + 1, count - 1);
            }
        }
        return result;
    }

    /**
     * 当某个兔子回答x的时候，那么数组中最多允许x+1个兔子同时回答x，那么对于数组中所有回答x的兔子的数量n
     *
     * 若 n%(x+1)==0，说明此时只需要 n/(x+1) 组个数为x+1的兔子(每组颜色不同)
     * 若 n%(x+1)!=0，说明此时需要 n/(x+1) + 1 组个数为x+1的兔子
     *
     * 这两种情况可以用Math.ceil(n/(x + 1))来整合，这个值也等于 (n + x) / (x + 1)
     *
     * @param answers
     * @return
     */
    public int numRabbits1(int[] answers) {
        // key : 回答的值 value : 回答这个值的兔子的数量
        Map<Integer, Integer> map = new HashMap<>();

        for (int x : answers) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int result = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int groupCount = (int)Math.ceil(entry.getValue() / (double) (entry.getKey() + 1));
            result += groupCount * (entry.getKey() + 1);
        }
        return result;
    }

    /**
     * 思路：统计出不在数组中的兔子的个数，然后再加上回答问题的兔子数
     *
     * 使用一个长度为1000的数组（题目中限定了最多1000个兔子回答问题）,数组下标代表兔子的回答
     *
     * 对于每个回答x的兔子，将 arr[x] 的值加1，然后对 x+1 取余，那么余数就是总数为 x+1 的兔子中在数组中出现的个数
     * （取余为0，说明这 x+1 个兔子都在数组中出现了）
     *
     * 然后遍历数组，用 x+1 减去这个余数，就是每种颜色不在数组中的兔子的个数
     *
     * @param answers
     * @return
     */
    public int numRabbits2(int[] answers) {
        int absentNum = 0;
        int[] arr = new int[1000];
        for(int x : answers) {
            //记录的是某种颜色兔子在数组中出现的数量
            arr[x] = (arr[x] + 1) % (x + 1);
        }
        for(int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if(num != 0) {
                // i + 1 是某种颜色兔子的总数量
                absentNum += i + 1 - num;
            }
        }
        return absentNum + answers.length;
    }

}
