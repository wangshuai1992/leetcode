package code;

import java.util.*;

/**
 * https://leetcode.com/problems/find-duplicate-file-in-system/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-05-29 16:36
 */
public class FindDuplicateFileInSystem {

    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String path : paths) {
            List<File> files = getFilesFromStr(path);
            for(File file : files) {
                String content = file.content;
                if(map.containsKey(content)) {
                    map.get(content).add(file.path);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(file.path);
                    map.put(content, list);
                }
            }
        }

        for(List<String> list : map.values()) {
            if(list.size() > 1) {
                result.add(list);
            }
        }
        return result;
    }

    private List<File> getFilesFromStr(String path) {
        List<File> files = new ArrayList<>();
        String[] strs = path.split(" ");
        String dir = strs[0];
        for(int i=1; i<strs.length; i++) {
            files.add(new File(dir, strs[i]));
        }
        return files;
    }


    private static class File {
        String path;
        String fileName;
        String content;

        public File(String dir, String str) {
            fileName = str.substring(0, str.indexOf('('));
            path = dir + '/' + fileName;
            content = str.substring(str.indexOf('(') + 1, str.length() - 1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            File file = (File) o;
            return Objects.equals(path, file.path) &&
                    Objects.equals(fileName, file.fileName) &&
                    Objects.equals(content, file.content);
        }

        @Override
        public int hashCode() {
            return Objects.hash(path, fileName, content);
        }

        @Override
        public String toString() {
            return path;
        }
    }

}
