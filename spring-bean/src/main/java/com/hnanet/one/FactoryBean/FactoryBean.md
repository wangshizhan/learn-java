## 静态工厂 vs 实例工厂
```text
1. 实例化时，静态工厂中实例化是在static中，而实例工厂中是在工厂类的构造函数中；
2. 调用方法中，静态工厂中函数有static说明，实例工厂中没有；
3. bean配置时，静态工厂只需要bean 一个car类就可以，但是实例工厂需要先bean工厂，在bean 一个具体的类car。
```

## FactoryBean vs BeanFactory
```text
BeanFactory：Spring中的IoC容器，所有Spring Bean 的Factory
FactoryBean：一个Bean，一个不简单的Bean，FactoryBean的特殊之处在于它可以向容器中注册两个Bean，一个是它本身，一个是FactoryBean.getObject()方法返回值所代表的Bean。

Spring 中有两种类型的Bean，一种是普通Bean，另一种是工厂Bean 即 FactoryBean。
Spring 为此提供了一个 org.Springframework.bean.factory.FactoryBean的工厂类接口，用户可以通过实现该接口定制实例化bean的逻辑

当配置一个<bean>的过程非常复杂，创建过程中涉及到很多其他的bean 和复杂的逻辑，用xml配置比较困难，这时可以考虑用FactoryBean。
```


## 开源上的使用
### MyBatis-Spring
```text
    SqlSessionFactoryBean：提供一个SqlSessionFactory
    MapperFactoryBean：为每一个Mapper创建JDK动态代理对象
```

### Dubbo 
```text
    <dubbo:reference ...> com.alibaba.dubbo.config.spring.ReferenceBean
```

```text
    可以通过FactoryBean来对一些开源工具API使用进行一些封装，比如对httpClient创建过程做了一些封装，例如超时时间、连接池大小、http代理等。
    给定一个id=mybean的FactoryBean，getBean("mybean")得到的就是这个FactoryBean创建的对象实例，而getBean("&mybean")得到的确实FactoryBean自身对象。
```
