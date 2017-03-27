package core.java.test.design.singleton;

/**
 * Created by chenyuanjun on 3/27/17.
 */
public class Singletion3 {
    private static Singletion3 instance;
//    private static volatile  Singletion3 instance;
    private Singletion3() {}
    // 简单的懒汉式: 存在线程安全性问题。
    public static Singletion3 getInstance() {
        if(instance == null) {
            instance = new Singletion3();
        }
        return instance;
    }
    // 给创建对象的步骤加锁: 加锁粒度太大。
    public static synchronized Singletion3 getInstance1() {
        if(instance == null) {
            instance = new Singletion3();
        }
        return instance;
    }
    // 双重校验锁: 仍存在隐患。可将instance声明为volatile消除隐患：private static volatile Singletion3 instance;//仍存在序列化的问题
    public static Singletion3 getInstance2() {
        if(instance == null) {
            synchronized (Singletion3.class) {
                if(instance == null) {
                    instance = new Singletion3();
                }
            }
        }
        return instance;
    }
    // 增加这个方法可以防止序列化对单例的破坏
    private Object readResolve() {
        return instance;
    }
}
