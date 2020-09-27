package basicpractice;

//synchronized关键字实现线程交替打印奇偶数
/**
新建两个线程，一个处理奇数，另一个处理偶数。
synchronized作为通信机制。
两个线程不断去竞争synchronized锁，这种无谓的竞争可能会浪费资源。
因为同一个资源会多次进入，不满足条件的话会跳出来。
 **/
public class OddEven1 {
    private static int count;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        if ((count & 1) == 0) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        }
                    }
                }
            }
        }, "Even").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        if ((count & 1) == 1) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        }
                    }
                }
            }
        }, "Odd").start();
    }
}



