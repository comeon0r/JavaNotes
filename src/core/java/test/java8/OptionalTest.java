package core.java.test.java8;

import org.junit.Test;

import java.util.Optional;

/**
 * Created by chenyuanjun on 3/27/17.
 */
public class OptionalTest {

    @Test
    public void testOptionalUsage() {
        Optional<String> userName = Optional.ofNullable(null);
        System.out.println(userName.isPresent());                                       // 如果Optional类的实例为非空值的话，isPresent()返回true，否从返回false。
        System.out.println(userName.orElseGet(() -> "[None]"));                         // 为了防止Optional为空值，orElseGet()方法通过回调函数来产生一个默认值。
        System.out.println(userName.orElse("Stranger"));                                // orElse()方法和orElseGet()方法类似，但是orElse接受一个默认值而不是一个回调函数。
        System.out.println(userName.map(s -> "Hi " + s + "!").orElse("Hi Stranger !")); // map()函数对当前Optional的值进行转化，然后返回一个新的Optional实例。


        userName = Optional.of("Tom");
        System.out.println(userName.isPresent());
        System.out.println(userName.orElse("Stranger"));
        System.out.println(userName.map(s -> "Hi " + s + "!").orElse("Hi Stranger !"));
    }

}
