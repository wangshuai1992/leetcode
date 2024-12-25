package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @date 2024-08-19 14:53
 */
public class TestClass {

    public static void main(String[] args) throws Exception {
        Map<Integer, String> map = new HashMap<>();
        map.put(2548206, "MCC");
        map.put(2725064, "I-ECOM 主页");

        String csvFile = "D:\\Desktop\\123.csv";
        StringBuilder builder;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            builder = new StringBuilder();

            List<String> lineList = new ArrayList<>();
            String temp;
            while ((temp = br.readLine()) != null) {
                // 截取aaa.com/pages/viewpage.action?pageId= 后面的数字
                int index = temp.indexOf("aaa.com/pages/viewpage.action?pageId=");
                if (index != -1) {
                    // 到下一个逗号
                    int nextComma = temp.indexOf(",", index);
                    String pageId;
                    if (nextComma != -1) {
                        pageId = temp.substring(index + 50, nextComma);
                    } else {
                        pageId = temp.substring(index + 50);
                    }
                    String name = map.get(Integer.parseInt(pageId));
                    if (name != null) {
                        temp = temp + "," + name;
                    }
                }
                lineList.add(temp);
            }

            for (String data : lineList) {
                builder.append(data).append("\n");
            }

            for (String data : lineList) {
                System.out.println(data);
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, false))) {
            bw.write(builder.toString());
        }

    }

}
