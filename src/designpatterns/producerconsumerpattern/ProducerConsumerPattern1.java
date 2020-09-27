package designpatterns.producerconsumerpattern;

/**
 * 使用阻塞队列实现生产者消费者模式
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerPattern1 {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        Consumer1 consumer1 = new Consumer1(queue);
        Producer1 producer1 = new Producer1(queue);
        new Thread(producer1).start();
        new Thread(consumer1).start();
    }
}

class Producer1 implements Runnable {
    BlockingQueue<String> queue;

    public Producer1(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            String product = "Product"+i;
            try {
                queue.put(product);
                System.out.println(product+" Put");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                queue.put("stop");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer1 implements Runnable {
    BlockingQueue<String> queue;

    public Consumer1(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String msg;
        try{
        while(!(msg=queue.take()).equals("stop")) {
            System.out.println(msg);
        }
            System.out.println("done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}