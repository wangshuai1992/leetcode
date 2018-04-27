package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/subdomain-visit-count/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-04-03 10:14
 */
public class SubdomainVisitCount {

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();

        for(String s : cpdomains) {
            String[] parts = s.split(" ");
            int times = Integer.parseInt(parts[0]);
            List<String> domains = new ArrayList<>();

            String temp = parts[1];
            domains.add(temp);
            int index;
            while ((index = temp.indexOf('.')) > 0) {
                temp = temp.substring(index + 1);
                domains.add(temp);
            }

            for(String domain : domains) {
                map.merge(domain, times, (old, add) -> old + add);
            }
        }

        return map.entrySet().stream().map(entry -> entry.getValue() + " " + entry.getKey()).collect(Collectors.toList());
    }

}
