package core.java.test.design.singleton;

import core.java.test.design.singleton.Singleton1;
import org.junit.Test;

/**
 * Created by chenyuanjun on 3/27/17.
 */
public class SingletonTest {

    @Test
    public void testSingleton1() {
        Singleton1 singleton1 = Singleton1.getInstance();
        Singleton1 singleton2 = Singleton1.getInstance();
        System.out.println(singleton1 == singleton2);
    }

}
