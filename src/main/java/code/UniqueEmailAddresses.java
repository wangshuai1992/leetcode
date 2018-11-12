package code;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/unique-email-addresses/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-07 15:50
 */
public class UniqueEmailAddresses {

    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for(String email : emails) {
            String[] arr = email.split("@");
            arr[0] = arr[0].replaceAll("\\.", "");
            arr[0] = arr[0].split("\\+")[0];
            set.add(arr[0] + arr[1]);
        }
        return set.size();
    }

}
