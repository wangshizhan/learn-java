package com.hnanet.config.guice.basic;

import com.google.inject.*;
import org.junit.Test;


@Singleton
public class GuiCeTest {

    @Inject
    private IHelloPrinter printer;
    public void hello() {
        printer.print();
    }
    public GuiCeTest() {
    }

    @Test
    public static void testMain() {
        /**
         * 使用Guice创建了一个注射器Injector，然后从Injector拿到你想要的对象就可以了，Guice会自动装配依赖树
         */
        Injector injector = Guice.createInjector();
        GuiCeTest sample = injector.getInstance(GuiCeTest.class);
        sample.hello();

        /**
         * 可以指定装配规则
         */
        Injector injector2 = Guice.createInjector(new ComplexModule());
        GuiCeTest sample2 = injector2.getInstance(GuiCeTest.class);
        sample2.hello();
    }
}

/**
 * 当某个接口有多个实现时，我们使用@ImplementedBy注解在接口定义上，指定接口的具体实现类
 */
@ImplementedBy(SimpleHelloPrinter.class)
interface IHelloPrinter {
    void print();
}

@Singleton
class SimpleHelloPrinter implements IHelloPrinter {

    @Override
    public void print() {
        System.out.println("Hello, Simple World");
    }

}

@Singleton
class ComplexHelloPrinter implements IHelloPrinter {

    @Override
    public void print() {
        System.out.println("Hello, Complex World");
    }

}

/**
 * 当某个接口有多个实现时，可以不用使用@ImplementedBy注解，而用 Guice Module 定义装配规则，它相当于Spring的XML文件
 */
class ComplexModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IHelloPrinter.class).to(ComplexHelloPrinter.class);
    }

}