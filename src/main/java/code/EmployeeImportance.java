package code;

import java.util.*;

/**
 * https://leetcode.com/problems/employee-importance/description/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-06-19 15:59
 */
public class EmployeeImportance {

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();

        for(Employee employee : employees) {
            map.put(employee.id, employee);
        }

        return calImportance(map, id);
    }

    /**
     * recursive
     *
     * @param map
     * @param id
     * @return
     */
    private int calImportance1(Map<Integer,Employee> map, int id) {
        int sum = 0;

        Employee employee = map.get(id);
        sum += employee.importance;
        if(!employee.subordinates.isEmpty()) {
            for(int subId : employee.subordinates) {
                sum += calImportance(map, subId);
            }
        }
        return sum;
    }

    /**
     * dfs
     *
     * @param map
     * @param id
     * @return
     */
    private int calImportance(Map<Integer,Employee> map, int id) {
        int sum = 0;

        Employee employee = map.get(id);
        sum += employee.importance;

        Deque<Integer> stack = new LinkedList<>();
        stack.addAll(employee.subordinates);
        while(!stack.isEmpty()) {
            int nodeId = stack.pop();
            Employee e = map.get(nodeId);
            sum += e.importance;
            stack.addAll(e.subordinates);
        }
        return sum;
    }

    private static class Employee {
        /**
         * It's the unique id of each node;
         * unique id of this employee
         */
        public int id;

        /**
         * the importance value of this employee
         */
        public int importance;

        /**
         * the id of direct subordinates
         */
        public List<Integer> subordinates;
    }

}
