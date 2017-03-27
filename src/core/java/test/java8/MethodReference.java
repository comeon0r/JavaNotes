package core.java.test.java8;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by chenyuanjun on 3/27/17.
 */
public class MethodReference {

    public static class Car {
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }
        public static void collide(final Car car) {
            System.out.println("Collided " + car.toString());
        }
        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }
        public void repair() {
            System.out.println("Repaired " + this.toString());
        }
    }

    // Reference to a constructor
    @Test
    public void testConstructorReference() {
        final Car car = Car.create(Car::new);
    }

    // Reference to a static method
    @Test
    public void testStaticMethodReference() {
        final Car car = Car.create(Car::new);
        final List<Car> cars = Collections.singletonList(car);
        cars.forEach(Car::collide);
//        cars.forEach(c -> c.collide()); // 写不成这种lambda表达式，因为它是静态方法
    }

    // Reference to an instance method of a particular object
    @Test
    public void testInstanceMethodReference() {
        final Car car = Car.create(Car::new);
        final List<Car> cars = Collections.singletonList(car);
        cars.forEach(Car::repair);
        cars.forEach(c -> c.repair()); // 等价于写成lambda表达式的形式
    }

    // Reference to an instance method of an arbitrary object of a particular type
    @Test
    public void test1() {
        final Car car = Car.create(Car::new);
        final List<Car> cars = Collections.singletonList(car);
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);
        cars.forEach(c -> police.follow(c)); // 等价于写成lambda表达式的形式
    }

}
