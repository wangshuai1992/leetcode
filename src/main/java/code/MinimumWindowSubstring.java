package code;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-03-27 01:25
 */
public class MinimumWindowSubstring {

    /**
     * 遍历所有长度大于等于t.length的子串，一一比对
     * 子串的长度由小到大，遇到符合条件的直接返回
     *
     * O(N^3) time, O(1) space
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int tLen = t.length();
        // 统计k字符串中各字母出现次数
        int[] mapK = new int['z' - 'A' + 1];
        for (int k = 0; k < t.length(); k++) {
            mapK[t.charAt(k) - 'A']++;
        }
        // 子串长度从t.length开始取
        for (int len = tLen; len <= s.length(); len++) {
            for (int i = 0; i < s.length() && i + len <= s.length(); i++) {
                int[] map = new int['z' - 'A' + 1];
                for (int j = i; j < i + len; j++) {
                    map[s.charAt(j) - 'A']++;
                }
                boolean isLegal = true;
                for (int m = 0; m < map.length; m++) {
                    if (map[m] < mapK[m]) {
                        isLegal = false;
                        break;
                    }
                }
                if (isLegal) {
                    return s.substring(i, i + len);
                }
            }
        }
        return "";
    }

    public String minWindow1(String s, String t) {
        if (s == null || "".equals(s) || t == null || "".equals(t) || s.length() < t.length()) {
            return "";
        }
        //维护两个数组，记录已有字符串指定字符的出现次数，和目标字符串指定字符的出现次数
        //ASCII表总长128
        int[] need = new int[128];
        int[] have = new int[128];

        //将目标字符串指定字符的出现次数记录
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }

        //分别为左指针，右指针，最小长度(初始值为一定不可达到的长度)
        //已有字符串中目标字符串指定字符的出现总频次以及最小覆盖子串在原字符串中的起始位置
        int left = 0;
        int right = 0;
        int min = s.length() + 1;
        int count = 0;
        int start = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            //说明该字符不被目标字符串需要，此时有两种情况
            // 1.循环刚开始，那么直接移动右指针即可，不需要做多余判断
            // 2.循环已经开始一段时间，此处又有两种情况
            //  2.1 上一次条件不满足，已有字符串指定字符出现次数不满足目标字符串指定字符出现次数，那么此时
            //      如果该字符还不被目标字符串需要，就不需要进行多余判断，右指针移动即可
            //  2.2 左指针已经移动完毕，那么此时就相当于循环刚开始，同理直接移动右指针
            if (need[r] == 0) {
                right++;
                continue;
            }
            //当且仅当已有字符串目标字符出现的次数小于目标字符串字符的出现次数时，count才会+1
            //是为了后续能直接判断已有字符串是否已经包含了目标字符串的所有字符，不需要挨个比对字符出现的次数
            if (have[r] < need[r]) {
                count++;
            }
            //已有字符串中目标字符出现的次数+1
            have[r]++;
            //移动右指针
            right++;
            //当且仅当已有字符串已经包含了所有目标字符串的字符，且出现频次一定大于或等于指定频次
            while (count == t.length()) {
                //挡窗口的长度比已有的最短值小时，更改最小值，并记录起始位置
                if (right - left < min) {
                    min = right - left;
                    start = left;
                }
                char l = s.charAt(left);
                //如果左边即将要去掉的字符不被目标字符串需要，那么不需要多余判断，直接可以移动左指针
                if (need[l] == 0) {
                    left++;
                    continue;
                }
                //如果左边即将要去掉的字符被目标字符串需要，且出现的频次正好等于指定频次，那么如果去掉了这个字符，
                //就不满足覆盖子串的条件，此时要破坏循环条件跳出循环，即控制目标字符串指定字符的出现总频次(count）-1
                if (have[l] == need[l]) {
                    count--;
                }
                //已有字符串中目标字符出现的次数-1
                have[l]--;
                //移动左指针
                left++;
            }
        }
        //如果最小长度还为初始值，说明没有符合条件的子串
        if (min == s.length() + 1) {
            return "";
        }
        //返回的为以记录的起始位置为起点，记录的最短长度为距离的指定字符串中截取的子串
        return s.substring(start, start + min);
    }

    /**
     * https://leetcode.cn/problems/minimum-window-substring/solution/zui-xiao-fu-gai-zi-chuan-by-leetcode-solution/
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        int n = chars.length;
        int m = chart.length;

        int[] hash = new int[128];
        for (char ch : chart) {
            hash[ch]--;
        }

        String res = "";
        for (int i = 0, j = 0, cnt = 0; i < n; i++) {
            hash[chars[i]]++;
            if (hash[chars[i]] <= 0) {
                cnt++;
            }
            while (cnt == m && hash[chars[j]] > 0) {
                hash[chars[j++]]--;
            }
            if (cnt == m && ("".equals(res) || res.length() > i - j + 1)) {
                res = s.substring(j, i + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC");
    }

}
