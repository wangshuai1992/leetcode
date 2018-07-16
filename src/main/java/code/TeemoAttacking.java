package code;

/**
 * https://leetcode.com/problems/teemo-attacking/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-20 10:56
 */
public class TeemoAttacking {

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int sum = 0;

        //中毒状态结束时间
        int lastTimeTo = 0;
        for(int time : timeSeries) {
            if(time >= lastTimeTo) {
                sum += duration;
            } else if (time > lastTimeTo - duration) {
                sum += time + duration -lastTimeTo;
            }
            lastTimeTo = time + duration;
        }
        return sum;
    }

}
