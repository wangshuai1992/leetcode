package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/course-schedule/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2019-03-28 15:26
 */
public class CourseSchedule {

    /**
     * OO
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course();
        }

        for (int[] condition : prerequisites) {
            courses[condition[0]].addPre(courses[condition[1]]);
        }

        for (Course course : courses) {
            if (course.isCyclic()) {
                return false;
            }
        }
        return true;
    }

    private static class Course {

        private boolean visited;

        private boolean done;

        private List<Course> preList = new ArrayList<>();

        public void addPre(Course course) {
            preList.add(course);
        }

        public boolean isCyclic() {
            if (done) {
                return false;
            }
            if (visited) {
                return true;
            }

            visited = true;
            for (Course course : preList) {
                if (course.isCyclic()) {
                    return true;
                }
            }
            visited = false;
            done = true;
            return false;
        }
    }

    private boolean[] visited;
    private boolean[] done;
    private Map<Integer, List<Integer>> adjMap;

    /**
     * DFS
     *
     * This problem is a topological sort problem
     * Main aim to detect the cycle in DAG.
     * If there is a cycle then it won't be possible to finish all the cources
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        visited = new boolean[numCourses];
        done = new boolean[numCourses];
        adjMap = new HashMap<>();

        //1. Convert the graph edges into the adjacency list
        for(int[] edge : prerequisites) {
            int target = edge[0];
            int pre = edge[1];
            if(!adjMap.containsKey(target)) {
                List<Integer> neighbors = new ArrayList<>();
                neighbors.add(pre);
                adjMap.put(target, neighbors);
            } else {
                adjMap.get(target).add(pre);
            }
        }

        //2. For each of the adjacency list index check whether each of the list element has a cycle.
        for (int i = 0; i < numCourses; i++) {
            if (detectCycle(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean detectCycle(int index) {
        if(done[index]) {
            return false;
        }
        if (visited[index]) {
            return true;
        }
        //Mark visited
        visited[index] = true;
        List<Integer> neighbors = adjMap.get(index);
        if(neighbors != null) {
            for(int neighbor : neighbors) {
                if(detectCycle(neighbor)) {
                    return true;
                }
            }
        }
        visited[index] = false;
        done[index] = true;
        return false;
    }

}
