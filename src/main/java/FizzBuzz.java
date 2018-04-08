import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/fizz-buzz/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-08 15:25
 */
public class FizzBuzz {

    /*
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>(n);

        for(int i=1; i<=n; i++) {
            String str = "";
            if(i % 3 == 0) {
                str += "Fizz";
            }
            if(i % 5 == 0) {
                str += "Buzz";
            }
            if(i % 3 != 0 && i % 5 != 0) {
                str += i;
            }
            result.add(str);
        }
        return result;
    }
    */

    public List<String> fizzBuzz(int n) {
        List<String> ret = new ArrayList<>(n);
        for (int i = 1, fizz = 0, buzz = 0; i <= n; i++) {
            fizz++;
            buzz++;
            if (fizz == 3 && buzz == 5) {
                ret.add("FizzBuzz");
                fizz = 0;
                buzz = 0;
            } else if (fizz == 3) {
                ret.add("Fizz");
                fizz = 0;
            } else if (buzz == 5) {
                ret.add("Buzz");
                buzz = 0;
            } else {
                ret.add(String.valueOf(i));
            }
        }
        return ret;
    }

}
