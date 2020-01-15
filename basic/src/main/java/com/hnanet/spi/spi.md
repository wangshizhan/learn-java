## SPI 是什么
```text
    SPI全称Service Provider Interface，是Java提供的一套用来被第三方实现或者扩展的API，它可以用来启用框架扩展和替换组件。
    基于接口的编程＋策略模式＋配置文件
    适用于：调用者根据实际使用需要，启用、扩展、或者替换框架的实现策略。
    常见例子：
        1、数据库驱动加载接口实现类的加载
            JDBC 加载不同类型数据库的驱动
        2、日志门面接口实现类加载
            SLF4J 加载不同提供商的日志实现类
        3、Spring 中大量使用了 SPI
            对 servlet3.0 规范对 ServletContainerInitializer 的实现、自动类型转换 Type Conversion SPI(Converter SPI、Formatter SPI) 等
        4、Dubbo 中也大量使用 SPI
            它对Java提供的原生SPI做了封装，允许用户扩展实现Filter接口
    使用介绍
        要使用Java SPI，需要遵循如下约定：
        1、当服务提供者提供了接口的一种具体实现后，在jar包的 META-INF/services 目录下创建一个以“接口全限定名”为命名的文件，内容为实现类的全限定名；
        2、接口实现类所在的jar包放在主程序的 classpath 中；
        3、主程序通过 java.util.ServiceLoder 动态装载实现模块，它通过扫描 META-INF/services 目录下的配置文件找到实现类的全限定名，把类加载到JVM；
        4、SPI的实现类必须携带一个不带参数的构造方法；
    优势
        使用Java SPI机制的优势是实现解耦，使得第三方服务模块的装配控制的逻辑与调用者的业务代码分离，而不是耦合在一起。
        应用程序可以根据实际业务情况启用框架扩展或替换框架组件。
    缺点：
        虽然ServiceLoader也算是使用的延迟加载，但是基本只能通过遍历全部获取，也就是接口的实现类全部加载并实例化一遍。如果你并不想用某些实现类，它也被加载并实例化了，这就造成了浪费。
        获取某个实现类的方式不够灵活，只能通过Iterator形式获取，不能根据某个参数来获取对应的实现类。
        多个并发多线程使用ServiceLoader类的实例是不安全的。
```
![SPI机制](../../../../resources/images/SPI机制.png)

