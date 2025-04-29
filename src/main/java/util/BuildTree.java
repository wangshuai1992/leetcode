package util;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @date 2024-09-04 18:30
 */
public class BuildTree {

    @Data
    static class PageNode implements Serializable {
        String id;
        String title;
        String childPosition;
        transient String parentId;
        transient String parentTitle;
        List<PageNode> children = new ArrayList<>();

        public PageNode(String id, String title, String childPosition, String parentId, String parentTitle) {
            this.id = id;
            this.title = title;
            this.childPosition = childPosition;
            this.parentId = parentId;
            this.parentTitle = parentTitle;
        }
    }

    public static void main(String[] args) throws IOException {
        String csvFile = "C:\\Users\\Administrator\\Desktop\\Result_9.csv";
        StringBuilder builder;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            builder = new StringBuilder();
            String temp;
            while ((temp = br.readLine()) != null) {
                builder.append(temp).append("\n");
            }
        }

        // Parse TSV data
        List<PageNode> nodes = parseTsvData(builder.toString());

        // Build tree structure
        Map<String, PageNode> nodeMap = new HashMap<>();
        for (PageNode node : nodes) {
            nodeMap.put(node.id, node);
        }

        PageNode root = new PageNode("0", "root", null, null, null);
        for (PageNode node : nodes) {
            if (StringUtils.isEmpty(node.parentId)) {
                root.children.add(node);
            } else {
                PageNode parent = nodeMap.get(node.parentId);
                if (parent != null) {
                    parent.children.add(node);
                }
            }
        }

        // children sort
        for (PageNode node : nodes) {
            node.children.sort((o1, o2) -> {
                if (StringUtils.isEmpty(o1.childPosition) || StringUtils.isEmpty(o2.childPosition)) {
                    return 0;
                }
                return Integer.parseInt(o1.childPosition) - Integer.parseInt(o2.childPosition);
            });
        }

        // Convert to JSON
        // Output JSON
        System.out.println(JSON.toJSONString(root));
    }

    private static List<PageNode> parseTsvData(String tsvData) {
        List<PageNode> nodes = new ArrayList<>();
        String[] lines = tsvData.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] fields = lines[i].split(",");
            String id = fields[0];
            String title = fields[1];
            String childPosition = fields.length > 2 ? fields[2] : "";
            String parentId = fields.length > 3 ? fields[3] : "";
            String parentTitle = fields.length > 4 ? fields[4] : "";
            nodes.add(new PageNode(id, title, childPosition, parentId, parentTitle));
        }
        return nodes;
    }
}
