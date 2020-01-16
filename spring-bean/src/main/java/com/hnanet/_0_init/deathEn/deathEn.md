## DisposableBean
```text
    实现DisposableBean接口 实现destroy方法即可
```

## destroy-method
```text
   在 destroy-method 中定义销毁方法，如果不调用关闭方法，这个方法不会被调用
```
## 调用顺序
通常实现接口的方法优先调用，配置的方法次之。

## JVM 关闭时自动调用关闭的方法
```java
// 注册关闭钩子
((ClassPathXmlApplicationContext) applicationContext).registerShutdownHook();
```