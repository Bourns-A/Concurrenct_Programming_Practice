package basicpractice;
/**
 wait/notify使用。
 线程拿到锁就打印。
 打印完了通知另一个线程，自己则休眠。
 **/
public class OddEven2 {
    private static int count = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runner(), "Even").start();
        new Thread(new Runner(), "Odd").start();
    }

    static class Runner implements Runnable {

        @Override
        public void run() {
            while (count<=100){
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName()+":"+count++);
                    lock.notify();
                    if (count <=100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
