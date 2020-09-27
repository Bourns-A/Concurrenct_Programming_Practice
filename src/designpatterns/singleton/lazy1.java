package designpatterns.singleton;

/**
 * 懒加载，双重检查锁。
 * 双重检查在于两次检查实例是否为空。
 * 如果只有单检查，两个线程同时进入代码块的时候可能会两次创建实例。
 * 如果把synchronized放在整个方法上，性能会降低。当多线程访问时无法及时响应。
 * 使用volatile防止重排序。
 */
public class lazy1 {
    private volatile static lazy1 instance;

    private lazy1() {

    }
    public static lazy1 getInstance() {
        if(instance==null) {
            synchronized (lazy1.class) {
                if (instance==null) {
                    instance = new lazy1();
                }
            }
        }
        return instance;
    }
}
