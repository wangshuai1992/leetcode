package code;

/**
 * https://leetcode.com/problems/beautiful-arrangement/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-05-03 10:27
 */
public class BeautifulArrangement {

    int count = 0;

    /**
     * https://leetcode.com/problems/beautiful-arrangement/solution/
     *
     * @param N
     * @return
     */
    public int countArrangement1(int N) {
        int[] nums = new int[N];
        for (int i = 1; i <= N; i++) {
            nums[i - 1] = i;
        }
        permute(nums, 0);
        return count;
    }

    private void permute(int[] nums, int i) {
        if(i == nums.length) {
            count ++;
            return;
        }

        for(int j=i; j<nums.length; j++) {
            swap(nums, i, j);
            if(nums[j] % (j+1) == 0 || (j+1) % nums[j] == 0) {
                permute(nums, j + 1);
            }
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    /**
     * Backtracking
     *
     * @param N
     * @return
     */
    public int countArrangement2(int N) {
        boolean[] visited = new boolean[N + 1];
        calculate(N, 1, visited);
        return count;
    }

    public void calculate(int N, int pos, boolean[] visited) {
        if (pos > N) {
            count++;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i] && (pos % i == 0 || i % pos == 0)) {
                visited[i] = true;
                calculate(N, pos + 1, visited);
                visited[i] = false;
            }
        }
    }


}
