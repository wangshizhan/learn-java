package com.hnanet.java8.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lambda {
    /**
     *  Lambda表达式是为了简化内部类的，你可以把它当成是内部类的一种简写方式
     *  只要是有内部类的代码块，都可以转化成Lambda表达式
     */

    @Test
    public void testLambda(){
        // Comparator排序
        List<Integer> list = Arrays.asList(3, 1, 4, 5, 2);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        // 使用Lambda表达式简化
        list.sort((o1, o2) -> o1.compareTo(o2));

        // Runnable代码块
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Man!");
            }
        });

        // 使用Lambda表达式简化
        Thread thread_lambda = new Thread(() -> System.out.println("Hello Man!"));


        // 方法引用 : Lambda表达式的简便写法，::分隔符，分隔符的前半部分表示引用类型，后面半部分表示引用的方法名称
        list.sort(Integer::compareTo);

        list.forEach(System.out::println);
    }
}
