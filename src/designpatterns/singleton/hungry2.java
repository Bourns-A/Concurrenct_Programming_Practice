package designpatterns.singleton;

/**
 * 饿汉式1--静态代码块
 * 把初始化从静态变量移动到了代码块。
 */
public class hungry2 {
    private final static hungry2 INSTANCE;
    static {
        INSTANCE = new hungry2();
    }

    private hungry2() {

    }
    public static hungry2 getInstance() {
        return INSTANCE;
    }

}
