## BeanPostProcessor
```text
BeanPostProcessor：主体是 Bean
```
> spring另一半的扩展能力是由 BeanPostProcessor 提供的
```text
    如果你有一个需求，打点项目中方法每个方法的运行时常，你很容易想到用AOP去做。
    如果不用AOP的话那么你可以使用 BeanPostProcessor 的后置处理方法,将对应的每个Bean都进行动态代理。
```

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
### beanPostProcessor 对比 BeanFactoryPostProcessor
```text
    BeanFactoryPostProcessor 是用来对我们 BeanFactory 中的 BeanDefinition 进行处理，此时Bean还未生成。
    BeanPostProcessor 用来对我们生成的Bean进行处理
```

### InstantiationAwareBeanPostProcessor 
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
#### 实例化前处理
```text
    这个方法的参数是 beanClass 和 beanName，在bean实例化前调用。
    如果这个方法的返回值不为空则 getBean 结束，用户收到的就是这个该方法的返回值。
    这个扩展点主要是留给预处理的，用户可以直接生成一个同类型的bean，替换实际bean，此时实际bean不会被实例化。
```
#### 实例化后处理
> **实例化后处理连同属性后处理是 spring 内部非常重要的扩展点**
```text
    annotation 的 field 注入就是在这两个阶段完成。主要有两个做属性注入的关键实现：
        CommonAnnotationBeanPostProcessor：主要用于@Resource，还继承了 InitDestroyAnnotationBeanPostProcessor 类，该类会在 init-method 被执行前调用 @PostConstruct 方法。
        AutowiredAnnotationBeanPostProcessor：主要用于@Autowired
```

### AutowiredAnnotationBeanPostProcessor
```text
    AutowiredAnnotationBeanPostProcessor 中有个 AutowiredFieldElement 内部类
    这个内部类的作用就是注入目标 bean 的属性值的。这里就包括 @Value 的注入和 @Autowired 注入
```
```java
// 构建一个依赖描述符对象DependencyDescriptor desc = new DependencyDescriptor(field, this.required);// 设置包含此依赖项的具体类desc.setContainingClass(bean.getClass());// 初始化一个注入的 beanName 集合，用于后面注册到容器中// 这里实际上只有一个，如果有多个实例 bean 存在，则需要通过 Qualifier 指定了Set<String> autowiredBeanNames = new LinkedHashSet<>(1);Assert.state(beanFactory != null, "No BeanFactory available");TypeConverter typeConverter = beanFactory.getTypeConverter();try {    // 解析依赖，依赖注入    value = beanFactory.resolveDependency(desc, beanName, autowiredBeanNames, typeConverter);}catch (BeansException ex) {    // 抛出注入失败异常    throw new UnsatisfiedDependencyException(null, beanName, new InjectionPoint(field), ex);}synchronized (this) {    if (!this.cached) {        if (value != null || this.required) {            this.cachedFieldValue = desc;            // 注册依赖的 bean            registerDependentBeans(beanName, autowiredBeanNames);            if (autowiredBeanNames.size() == 1) {                String autowiredBeanName = autowiredBeanNames.iterator().next();                // 判断容器中是否存在此依赖 bean,并且校验 bean 的类型是否匹配                if (beanFactory.containsBean(autowiredBeanName) &&                        beanFactory.isTypeMatch(autowiredBeanName, field.getType())) {                    // 缓存注入值                    this.cachedFieldValue = new ShortcutDependencyDescriptor(                            desc, autowiredBeanName, field.getType());                }            }        }        else {            // 没有找到 依赖bean 实例，且 required 为 false             this.cachedFieldValue = null;        }        this.cached = true;    }}// value 为解析到的属性值，如果不为空，则通过反射设置给目标 Bean，完成属性的注入if (value != null) {    ReflectionUtils.makeAccessible(field);    field.set(bean, value);}复制代码
```

### annotation 的 field 注入
```text
    主要有两个关键实现
    CommonAnnotationBeanPostProcessor，继承了 InitDestroyAnnotationBeanPostProcessor 类，该类会在init-method被执行前调用@PostConstruct方法。该类在实例化后处理阶段做属性注入，主要用于 @Resource
    AutowiredAnnotationBeanPostProcessor，该类在属性后处理阶段做属性注入，主要用于@Autowired
````
