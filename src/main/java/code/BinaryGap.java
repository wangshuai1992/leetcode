package code;

/**
 * https://leetcode.com/problems/binary-gap/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-10-12 17:41
 */
public class BinaryGap {

    public int binaryGap(int n) {
        String bin = Integer.toBinaryString(n);

        int result = 0;
        int preIndex = 0;

        //正在遍历开头的0
        boolean starting = true;

        for(int i=0; i<bin.length(); i++) {
            char c = bin.charAt(i);
            if('1' == c && starting) {
                //进入正式状态
                starting = false;
                preIndex = i;
                continue;
            }

            if('1' == c) {
                if(preIndex + 1 == i) {
                    //连续中
                    preIndex = i;
                    //有了一个连续块 结果至少为1
                    result = result == 0 ? 1 : result;
                } else {
                    //未连续
                    result = Math.max(result, i - preIndex);
                    preIndex = i;
                }
            }
        }
        return result;
    }

}
