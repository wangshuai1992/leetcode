/**
 * https://leetcode.com/problems/number-of-lines-to-write-string/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-03-30 14:46
 */
public class NumberOfLinesToWriteString {

    public int[] numberOfLines(int[] widths, String S) {
        if(S.length() == 0){
            return new int[]{0,0};
        }

        int lines = 1;
        int lastLineWidth = 0;

        for(char c : S.toCharArray()) {
            int width = widths[c - 'a'];
            if(lastLineWidth + width < 100) {
                lastLineWidth += width;
            } else if(lastLineWidth + width == 100) {
                lastLineWidth = 0;
                lines ++;
            } else  {
                lastLineWidth = width;
                lines ++;
            }
        }

        return new int[]{lines, lastLineWidth};
    }

}
