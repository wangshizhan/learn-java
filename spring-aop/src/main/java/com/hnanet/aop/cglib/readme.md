## lookup-method replaced-method 应用场景
```text
需求：假设一个单例模式的 MyTestBean A需要引用另外一个非单例模式的 Student B ，要求在我们每次引用的时候都能拿到最新的 Student B

我们可以让 MyTestBean A通过实现 ApplicationContextWare 来感知 applicationContext（即可以获得容器上下文），从而能在运行时通过ApplicationContext.getBean(String beanName)的方法来获取最新的 Student B。
但是如果用ApplicationContextAware接口，就让我们与Spring代码耦合了，违背了反转控制原则（IoC，即bean完全由Spring容器管理，我们自己的代码只需要用bean就可以了）

Spring为我们提供了方法注入的方式来实现以上的场景。


工厂方法的实例化手段没有选择策略直接用了发射实现的
实例化策略都是对于构造函数实例化而言

如果beanDefinition.getMethodOverrides()为空也就是用户没有使用 replace 或者 lookup的 配置方法，那么直接使用反射的方式
即如果使用了 lookup 或者 replaced 的配置的话会使用cglib，否则直接使用反射。
```