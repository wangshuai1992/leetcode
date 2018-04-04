/**
 * https://leetcode.com/problems/number-complement/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-03 15:53
 */
public class NumberComplement {

    /*
    public int findComplement(int num) {
        int temp = num;
        List<Integer> list = new ArrayList<>();
        while(temp != 0) {
            //temp&1 求出末位； 异或1求反
            list.add(temp & 1 ^ 1);
            temp >>= 1;
        }

        int result = 0;
        for(int i=0; i<list.size(); i++) {
            result += list.get(i) * (1 << i);
        }
        return result;
    }
    */

    public int findComplement(int num) {
        //求掩码
        int mask = 1;
        while (mask < num) {
            mask = (mask << 1) | 1;
        }
        return ~num & mask;
    }

    public static void main(String[] args) {
        NumberComplement numberComplement = new NumberComplement();
        System.out.println(numberComplement.findComplement(4));
        System.out.println(~4);
    }

}
