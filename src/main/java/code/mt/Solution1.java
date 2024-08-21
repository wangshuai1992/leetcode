package code.mt;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2个线程交替打印1-10
 * https://zhuanlan.zhihu.com/p/370130458
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-14 15:40
 */
//@SuppressWarnings("all")
public class Solution1 {

    /**
     * 由于访问这个变量都有synchronized保护，所以不需要声明成volatile
     */
    private static int count = 1;

    private void print() {
        for (int x = 0; x <= 1; x++) {
            final int y = x;
            new Thread(() -> {
                for (int i = 1; i <= 5; i++) {
                    synchronized (this) {
                        while (count % 2 != y) {
                            try {
                                this.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println(count);
                        count++;
                        this.notify();
                    }
                }
            }).start();
        }
    }

    private void print1() {
        Lock lock = new ReentrantLock();
        Condition isOdd = lock.newCondition();
        Condition isEven = lock.newCondition();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    lock.lock();
                    while (count % 2 != 1) {
                        isOdd.await();
                    }
                    System.out.println(count);
                    count++;
                    isEven.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    lock.lock();
                    while (count % 2 != 0) {
                        isEven.await();
                    }
                    System.out.println(count);
                    count++;
                    isOdd.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new Solution1().print1();
    }

}

