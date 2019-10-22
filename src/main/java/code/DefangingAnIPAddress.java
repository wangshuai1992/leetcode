package code;

/**
 * https://leetcode.com/problems/defanging-an-ip-address/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-08-07 17:16
 */
public class DefangingAnIPAddress {

    public String defangIPaddr(String address) {
        return address.replaceAll("\\.", "[.]");
    }

    public String defangIPaddr1(String address) {
        char[] oldArr = address.toCharArray();
        char[] newArr = new char[oldArr.length + 6];
        int newIndex = 0;
        for(char c : oldArr) {
            if(c != '.') {
                newArr[newIndex] = c;
                newIndex ++;
            } else {
                newArr[newIndex++] = '[';
                newArr[newIndex++] = '.';
                newArr[newIndex++] = ']';
            }
        }
        return new String(newArr);
    }

}
