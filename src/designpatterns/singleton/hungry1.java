package designpatterns.singleton;

/**
 * 饿汉式1--静态常量
 * 写法简单，类装载完成实例化，避免了线程同步问题（JVM保证线程安全）。
 */
public class hungry1 {
    private final static hungry1 INSTANCE = new hungry1();
    private hungry1() {

    }
    public static hungry1 getInstance() {
        return INSTANCE;
    }

}
