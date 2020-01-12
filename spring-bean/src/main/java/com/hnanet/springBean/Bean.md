- Spring Bean的生命周期:

> Spring 只帮我们管理单例模式 Bean 的完整生命周期，对于 prototype 的 bean ，Spring 在创建好交给使用者之后则不会再管理后续的生命周期。

注解方式
    可以使用注解 @PostConstruct, @PreDestroy 来在 bean 的创建和销毁阶段进行调用
InitializingBean, DisposableBean 接口
    可以实现 InitializingBean,DisposableBean 这两个接口，也是在初始化以及销毁阶段调用
自定义初始化和销毁方法
    可以自定义方法用于在初始化、销毁阶段调用
实现 *Aware 接口
    *Aware 接口可以用于在初始化 bean 时获得 Spring 中的一些对象，如获取 Spring 上下文等
BeanPostProcessor 增强处理器
    实现 BeanPostProcessor 接口，Spring 中所有 bean 在做初始化时都会调用该接口中的两个方法，可以用于对一些特殊的 bean 进行处理


1. Bean容器找到配置文件中Spring Bean的定义。
2. Bean容器利用Java Reflection API创建一个Bean的实例。
3. 如果涉及到一些属性值 利用set方法设置一些属性值。
4. 如果Bean实现了 BeanNameAware 接口，调用setBeanName()方法，传入Bean的名字。
5. 如果Bean实现了 BeanClassLoaderAware 接口，调用setBeanClassLoader()方法，传入ClassLoader对象的实例。
6. 如果Bean实现了 BeanFactoryAware 接口，调用setBeanClassLoader()方法，传入ClassLoader对象的实例。
7. 与上面的类似，如果实现了其他*Aware接口，就调用相应的方法。
8. 如果有和加载这个Bean的Spring容器相关的 BeanPostProcessor 对象，执行postProcessBeforeInitialization()方法
9. 如果Bean实现了 InitializingBean 接口，执行afterPropertiesSet()方法。
10. 如果Bean在配置文件中的定义包含 init-method 属性，执行指定的方法。
11. 如果有和加载这个Bean的Spring容器相关的 BeanPostProcessor 对象，执行postProcessAfterInitialization()方法
12. 当要销毁Bean的时候，如果Bean实现了 DisposableBean 接口，执行destroy()方法。
13. 当要销毁Bean的时候，如果Bean在配置文件中的定义包含destroy-method属性，执行指定的方法。

--- 
![binaryTree](../../../../resources/images/bean-lifeCycle.jpeg "binaryTree")
![binaryTree](../../../../resources/images/bean-lifeCycle-ch.png "binaryTree")

--- 
