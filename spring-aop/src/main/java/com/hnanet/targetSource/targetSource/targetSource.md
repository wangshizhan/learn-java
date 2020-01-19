```text
Spring Aop 标签解析的最终结果就是生成了一个 AnnotationAwareAspectJAutoProxyCreator 的 BeanDefinition，AnnotationAwareAspectJAutoProxyCreator 主要是
    1. 重写 InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation 方法
        其主要目的在于如果用户使用了自定义的 TargetSource 对象，则直接使用该对象生成目标对象，而不会使用Spring的默认逻辑生成目标对象，并且这里会判断各个切面逻辑是否可以应用到当前bean上，如果可以，则直接应用
        即TargetSource 为使用者在 Aop 中提供了一个自定义生成目标 bean 逻辑的方式，并且会应用相应的切面逻辑。
        在Spring代理目标bean的时候，其并不是直接创建一个目标bean的对象实例的，而是通过一个TargetSource类型的对象将目标bean进行封装，Spring Aop获取目标对象始终是通过TargetSource.getTarget()方法进行的。
    2. 重写 BeanPostProcessor#postProcessAfterInitialization 方法
        其主要作用在于Spring生成某个bean之后，将相关的切面逻辑应用到该bean上
```
## TargetSource
### SingletonTargetSource
```text
    SingletonTargetSource 通过构造方法传入一个目标bean对象，在使用 getTarget()方法时，也只是将该对象直接返回；
    并且 isStatic() 方法返回的是 true，即 Spring 是可以缓存 SingletonTargetSource 的
```
### PrototypeTargetSource
```text
    PrototypeTargetSource 的生成 prototype 类型 bean 的方式主要是委托给 BeanFactory 进行的，因为BeanFactory 自有一套生 成prototype 类型的 bean 的逻辑，
    因而 PrototypeTargetSource 也就具有生成 prototype 类型 bean 的能力，这也就是我们要生成的目标 bean 必须声明为 prototype 类型的原因。
```
### CommonsPool2TargetSource
```text
    CommonsPool2TargetSource 的实现是将其委托给了 ObjectPool 进行，具体的也就是 GenericObjectPool，其实现了 ObjectPool 接口。
    主要使用 LinkedBlockingDeque，也就是可阻塞的双端队列实现对象池的功能。
```
### ThreadLocalTargetSource
```text
    ThreadLocalTargetSource 也就是和线程绑定的 TargetSource，可以理解，其底层实现必然使用的是 ThreadLocal。既然使用了 ThreadLocal，也就是说我们需要注意两个问题：
    1. 目标对象必须声明为 prototype 类型，因为每个线程都会持有一个不一样的对象；
    2. 目标对象必须是无状态的，因为目标对象是和当前线程绑定的，而 Spring 是使用的线程池处理的请求，因而每个线程可能处理不同的请求，因而为了避免造成问题，目标对象必须是无状态的。
```
### AbstractPrototypeBasedTargetSource
```text
    PrototypeTargetSource 、 ThreadLocalTargetSource 继承了 AbstractPrototypeBasedTargetSource
```
### 自定义的 TargetSource
