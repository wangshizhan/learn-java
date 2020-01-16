![容器如何进行刷新](../../../../resources/images/init/bean-refresh.png)

![容器如何进行刷新](../../../../resources/images/init/bean-refresh-detail.png)


```text
    比较常见的扩展在
        加载 BeanDefinition  BeanFactoryPostProcessor扩展 如PropertyPlaceholderConfigurer: 在真正的实例化Bean之前对BeanDefinition进行修改
        执行 BeanPostProcessor
```


![单例Bean初始化](../../../../resources/images/init/bean-singleton.png)


![单例Bean初始化](../../../../resources/images/init/bean-singletonCh.png)


![Bean创建流程](../../../../resources/images/init/bean.png)



