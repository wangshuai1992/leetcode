package code;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/all-paths-from-source-to-target/description/
 *
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.
 * graph[i] is a list of all nodes j for which the edge (i, j) exists.
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-03-13 15:39
 */
public class AllPathsFromSourceToTarget {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        //结果
        List<List<Integer>> result = new ArrayList<>();

        //用于临时存放路径
        List<Integer> path = new ArrayList<>();

        path.add(0);
        dfsSearch(graph, 0, result, path);

        return result;
    }

    private void dfsSearch(int[][] graph, int node, List<List<Integer>> result, List<Integer> path) {
        if (node == graph.length - 1) {
            //搜索到目标节点 将路径加入到结果集(注意新创建list)
            result.add(new ArrayList<Integer>(path));
            return;
        }

        //遍历所有当前节点可达的节点
        for (int nextNode : graph[node]) {
            path.add(nextNode);
            dfsSearch(graph, nextNode, result, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        System.out.println(new AllPathsFromSourceToTarget().allPathsSourceTarget(graph));
    }

}
