package com.hnanet.targetSource;

import org.springframework.aop.TargetSource;

import java.util.concurrent.ThreadLocalRandom;

public class AppleTargetSource implements TargetSource {
    private Apple apple1;
    private Apple apple2;

    public AppleTargetSource() {
        this.apple1 = new Apple(1);
        this.apple2 = new Apple(2);
    }

    /**
     * 本方法主要用于返回目标 bean 的 Class 类型
     * @return
     */
    @Override
    public Class<?> getTargetClass() {
        return Apple.class;
    }

    /**
     * 这个方法用户返回当前 bean 是否为静态的，比如常见的单例 bean 就是静态的，而 prototype 就是动态的，
     * 这里这个方法的主要作用是，对于静态的 bean，spring 是会对其进行缓存的，再多次使用 TargetSource 获取目标 bean 对象的时候，其获取的总是同一个对象，通过这种方式提高效率
     * @return
     */
    @Override
    public boolean isStatic() {
        return false;
    }

    /**
     * 获取目标 bean 对象，这里可以根据业务需要进行自行定制
     * @return
     * @throws Exception
     */
    @Override
    public Object getTarget() throws Exception {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int index = random.nextInt(2);
        return index % 2 == 0 ? apple1 : apple2;
    }

    /**
     * Spring 在完目标 bean 之后会调用这个方法释放目标 bean 对象，对于一些需要池化的对象，这个方法是必须要实现的，这个方法默认不进行任何处理
     * @param target
     * @throws Exception
     */
    @Override
    public void releaseTarget(Object target) throws Exception {}
}
