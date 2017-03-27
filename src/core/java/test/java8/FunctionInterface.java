package core.java.test.java8;

import org.junit.Test;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

/**
 * Created by chenyuanjun on 3/27/17.
 */
public class FunctionInterface {

    @Test
    public void testCurry() {
        // curring（局部套用）
        // here we curry a two parameter adder function into a single parameter incrementer.
        IntBinaryOperator adder = (int x, int y) -> x + y;
        IntUnaryOperator incrementer = x -> adder.applyAsInt(1, x);
        System.out.println(incrementer.applyAsInt(5));
    }

}
