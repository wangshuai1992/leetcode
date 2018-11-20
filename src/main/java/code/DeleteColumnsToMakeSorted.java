package code;

/**
 * https://leetcode.com/problems/delete-columns-to-make-sorted/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-19 10:23
 */
public class DeleteColumnsToMakeSorted {

//    public int minDeletionSize(String[] arr) {
//        int result = Integer.MAX_VALUE;
//        List<Set<Integer>> deletions = getDeletionSets(arr.length);
//        for (Set<Integer> del : deletions) {
//            if (isSorted(doDeletion(arr, del))) {
//                result = Math.min(result, del.size());
//            }
//        }
//        return result;
//    }
//
//    private List<Set<Integer>> getDeletionSets(int len) {
//        List<Set<Integer>> result = new ArrayList<>();
//        int ops = 1 << len;
//        for (int i = 0; i <= ops; i++) {
//            Set<Integer> temp = new HashSet<>();
//            String str = String.format("%" + len + "s", Integer.toBinaryString(i)).replace(" ", "0");
//            for (int j = 0; j < str.length(); j++) {
//                if (str.charAt(j) == '1') {
//                    temp.add(j);
//                }
//            }
//            result.add(temp);
//        }
//        return result;
//    }
//
//    private boolean isSorted(String[] arr) {
//        for (String str : arr) {
//            int i = 0;
//            while (i < str.length() - 1) {
//                if (str.charAt(i) > str.charAt(i + 1)) {
//                    return false;
//                }
//                i++;
//            }
//        }
//        return true;
//    }
//
//    private String[] doDeletion(String[] arr, Set<Integer> indices) {
//        String[] res = new String[arr.length];
//        for (int i = 0; i < arr.length; i++) {
//            String str = arr[i];
//            StringBuilder temp = new StringBuilder();
//            for (int j = 0; j < arr[0].length(); j++) {
//                if (indices.contains(j)) {
//                    continue;
//                }
//                temp.append(str.charAt(j));
//            }
//            res[i] = temp.toString();
//        }
//        return res;
//    }

    public static void main(String[] args) {
        String[] arr = {"cba", "daf", "ghi"};
        System.out.println(new DeleteColumnsToMakeSorted().minDeletionSize(arr));
    }

    public int minDeletionSize(String[] arr) {
        int length = arr[0].length();
        int deletions = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j].charAt(i) > arr[j + 1].charAt(i)) {
                    deletions++;
                    break;
                }
            }
        }
        return deletions;
    }

}
