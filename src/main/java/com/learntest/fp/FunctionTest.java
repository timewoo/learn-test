package com.learntest.fp;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

/**
 * @author yanglin
 * @date 2021/11/5 15:32
 */
public class FunctionTest {

    public static void main(String[] args) {
//        DoubleUnaryOperator log = Math::log;
//        DoubleUnaryOperator sqrt = Math::sqrt;
//        DoubleUnaryOperator compose = sqrt.compose(log);
//        System.out.println(compose.applyAsDouble(3.14));
//        DoubleUnaryOperator then = sqrt.andThen(log);
//        System.out.println(then.applyAsDouble(3.14));
        Function<Double,Function<Double,Double>> weight = mass->gravity->mass*gravity;
        Function<Double, Double> weightOnEarth = weight.apply(9.81);
        System.out.println(weightOnEarth.apply(60.0));
        Function<Double, Double> apply = weight.apply(3.75);
        System.out.println(apply.apply(60.0));

    }

}
