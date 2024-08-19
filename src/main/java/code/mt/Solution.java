package code.mt;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 10个线程打印1-10
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-14 15:40
 */
@SuppressWarnings("all")
public class Solution {

    private void print() {
        Runnable[] runnables = new Runnable[11];
        Set<Integer> doneSet = new ConcurrentSkipListSet<>();
        for (int i = 1; i <= 10; i++) {
            // 这里要赋值成为final变量 不然内部类无法使用
            final int n = i;
            // 这里不能使用lambda lambda里面的this指向的是Solution实例而不是Runnable实例
            runnables[n] = new Runnable() {
                @Override
                public void run() {
                    if (n != 1) {
                        Runnable pre = runnables[n - 1];
                        synchronized (pre) {
                            while (!doneSet.contains(n - 1)) {
                                try {
                                    pre.wait();
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                    System.out.println(n);
                    synchronized (this) {
                        doneSet.add(n);
                        this.notify();
                    }
                }
            };
        }
        for (int i = 10; i > 0; i--) {
            new Thread(runnables[i]).start();
        }
    }

    public static void main(String[] args) {
        new Solution().print();
    }

}
