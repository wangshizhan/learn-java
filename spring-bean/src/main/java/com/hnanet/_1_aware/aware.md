## ApplicationContextAware
```text
    实现了 ApplicationContextAware 接口的类，可以在该Bean被加载的过程中获取Spring的应用上下文ApplicationContext
    通过 ApplicationContext 可以获取 Spring容器内的很多信息
```

## BeanFactoryAware
> 应用场景：消除 if-else，需要动态的去获取对象实现
```text
    BeanFactory 是整个 Ioc 容器最顶层的接口，它规定了容器的基本行为。
    BeanFactoryAware 接口中只有一个 setBeanFactory 方法。实现了 BeanFactoryAware 接口的类，可以在该 Bean 被加载的过程中获取加载该 Bean的BeanFactory，同时也可以获取这个BeanFactory中加载的其它Bean。
```