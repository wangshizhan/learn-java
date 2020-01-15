## BeanPostProcessor
> spring另一半的扩展能力是由 BeanPostProcessor 提供的
```java

/**
 * 两个方法分别在 init-method 调用前后，对 bean 做后处理。
 * 需要特别注意的就是 postProcessAfterInitialization，大部分的 spring 扩展就是由它来完成的。
 * @PostConstruct 和 @PreDestroy 这两个作用于 Servlet 生命周期的注解，实现 Bean 初始化之前和销毁之前的自定义操作。
 * @PostConstruct 注释的方法，会在构造方法之后，init-method 之前进行调用。
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
```

## InstantiationAwareBeanPostProcessor 
> 实例化后处理器 InstantiationAwareBeanPostProcessor 继承了BeanPostProcessor，主要用于在bean实例化前后做处理
```java
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    PropertyValues postProcessPropertyValues(
            PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName)
            throws BeansException;
}
```
### 实例化前处理
```text
    这个方法的参数是 beanClass 和 beanName，在bean实例化前调用。
    如果这个方法的返回值不为空则 getBean 结束，用户收到的就是这个该方法的返回值。
    这个扩展点主要是留给预处理的，用户可以直接生成一个同类型的bean，替换实际bean，此时实际bean不会被实例化。
```
### 实例化后处理
> **实例化后处理连同属性后处理是 spring 内部非常重要的扩展点**
```text
    annotation 的 field 注入就是在这两个阶段完成。主要有两个做属性注入的关键实现：
        CommonAnnotationBeanPostProcessor：主要用于@Resource，还继承了 InitDestroyAnnotationBeanPostProcessor 类，该类会在 init-method 被执行前调用 @PostConstruct 方法。
        AutowiredAnnotationBeanPostProcessor：主要用于@Autowired
```
