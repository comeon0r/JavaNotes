package core.java.test.design.singleton;

/**
 * Created by chenyuanjun on 3/27/17.
 */
public class Singleton1 {
//    private static Singleton1 instance = new Singleton1();
    private static Singleton1 instance;
    static { instance = new Singleton1(); }
    private Singleton1() {}
    public static Singleton1 getInstance() {
        return instance;
    }
}
