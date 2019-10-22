package code;

/**
 * https://leetcode.com/problems/letter-tile-possibilities/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-08-08 11:00
 */
public class LetterTilePossibilities {

    public int numTilePossibilities(String tiles) {
        int[] map = new int[26];
        for (char c : tiles.toCharArray()) {
            map[c - 'A']++;
        }
        return recurse(map);
    }

    private int recurse(int[] map) {
        int sum = 0;
        for (int i = 0; i < map.length; i++) {
            if(map[i] == 0) {
                continue;
            }
            map[i]--;
            sum += (1 + recurse(map));
            map[i]++;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new LetterTilePossibilities().numTilePossibilities("ABB"));
    }

}
