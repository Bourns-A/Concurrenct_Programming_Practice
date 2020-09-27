package designpatterns.producerconsumerpattern;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 实现生产者消费者的思路：
 * 1）wait，notify方法实现生产者消费者模式
 * 2）使用阻塞队列。
 */

public class ProducerConsumerPattern0 {
    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

}

class Producer implements Runnable {
    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.put(); //代表阻塞队列
        }
    }
}

class Consumer implements Runnable {
    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take(); //代表阻塞队列
        }
    }
}

//  wait/notify实现阻塞队列
class EventStorage {
    private int maxSize;
    private LinkedList<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }

    public synchronized void put() {
        //满了就等待，没满就往里放，放完了就通知消费者。
        while (storage.size()==maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.println("Number of products in the storage: "+ storage.size());
        notify();
    }

    public synchronized void take() {
        while (storage.size()==0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(storage.get(0)+ "was got"+storage.poll() + "products remaining");
//            storage.remove(0);
        notify();
    }
}

