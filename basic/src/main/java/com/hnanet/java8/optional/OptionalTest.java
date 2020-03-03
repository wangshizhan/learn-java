package com.hnanet.java8.optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Optional;

/**
 *  Optional 类
 *  让我们可以以函数式编程的方式处理null值
 *  抛弃了 Java 8 之前需要嵌套大量 if-else 代码块 或者 卫语句，使代码可读性有了很大的提高
 */
public class OptionalTest {

    /**
     *  java.util.Optional<T> 类是一个封装了 Optional 值的容器对象，Optional 值可以为 null
     *  如果值存在，调用 isPresent() 方法返回 true，调用 get() 方法可以获取值
     *      empty() 创建一个没有值的 Optional 对象
     *      of() 方法使用一个非空的值创建 Optional 对象
     *      ofNullable() ofNullable() 方法接收一个可以为 null 的值
     *
     *  1. 尽量避免在程序中直接调用Optional对象的get()和isPresent()方法；
     *  2. 避免使用Optional类型声明实体类的属性，因为没有实现序列化接口；
     */
    @Test
    public void testOptional(){
        User user = new User("Tom");
        String userNameByDefault = getUserNameByDefault(user);
        String userNameByLambda = getUserNameByDefault(user);
        System.out.println(userNameByDefault);
        System.out.println(userNameByLambda);

        // 使用filter()方法过滤 filter()方法可用于判断Optional对象是否满足给定条件，一般用于条件过滤
        // 如果filter()方法中的Lambda表达式成立，filter()方法会返回当前Optional对象值，否则，返回一个值为空的Optional对象
        Optional<String> optional = Optional.of("lw900925@163.com");

        optional = optional.filter(str -> str.contains("164"));
    }

    // 简化前
    public  String getUserNameByDefault(User user){
        if (user != null) {
            String userName = user.getUserName();
            if (userName != null) {
                return userName.toUpperCase();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    // 简化后
    public  String getUserNameByLambda(User user){

        // return str != null ? str : "Hello World"
        // return strOpt.orElse("Hello World")
        Optional<User> userOpt = Optional.ofNullable(user);
        return userOpt.map(User::getUserName)
                .map(String::toUpperCase)
                .orElse(null);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class User{
        String userName;
    }
}
