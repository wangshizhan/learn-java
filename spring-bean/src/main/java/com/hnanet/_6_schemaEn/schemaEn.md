## XML schema 的扩展机制
```text
    Spring允许你自己定义XML的的结构并且可以用自己的bean解析器进行解析
    NamespaceHandler 是 Spring 提供的 命名空间处理器。
    NamespaceHandler 会根据 schema 和 节点名 找到某个 BeanDefinitionParser，然后由 BeanDefinitionParser 完成具体的解析工作。
    Spring提供了默认实现类 NamespaceHandlerSupport 和 AbstractSingleBeanDefinitionParser，最简单的方式就是去继承这两个类。
    注解其实也是使用了XML schema的扩展机制
```
### 1. 编写模型类
### 2. 定义xsd文件
```text
    配置这个其实是为了让Spring在解析xml的时候能够感知到我们的自定义元素，我们需要把NamespaceHandler和xsd文件放到位于META-INF目录下的spring.handlers 和 spring.schmas文件中。
    这样就可以在spring配置文件中使用我们自定义的标签了。
    定义targetNamespace（目标命名空间），xmlns的值要与这个相同
    xsd:element定义的就是将来会在xml文件中用到的元素，例如<dubbo:application>中的application
    xsd:attribute定义的就是模型类中的属性，例如<dubbo:application name="xxx">中的name，并且可以指定属性类型，进而起到检测的作用（当我们定义的是int，如果在xml中的值是非int型的，直接会报错）。
     
```
### 3. 编写spring.schemas
```text
    作用：该文件用来指定xsd文件的位置。
    http\://hulk.com/schema/hero.xsd=META-INF/hero.xsd
    KEY 要与 xsd 文件中的 targetNamespace 相同
```

### 4. 编写BeanDefinition解析器
```text
    实现 BeanDefinitionParser 接口
    作用：主要用来解析自定义的xml标签
```
### 5. 编写命名空间处理器
```text
    作用：主要用来注册BeanDefinition解析器
        继承 NamespaceHandlerSupport
        通常为每一个xsd:element都要注册一个BeanDefinitionParser
```

### 6. 编写spring.handlers文件
```text
    配置这个其实是为了让Spring在解析xml的时候能够感知到我们的自定义元素，我们需要把NamespaceHandler和xsd文件放到位于META-INF目录下的spring.handlers 和 spring.schmas文件中。
    这样就可以在spring配置文件中使用我们自定义的标签了。
    作用：主要用于关联命名空间处理器和xsd中的targetNamespace。
    http\://hulk.com/schema=com.hulk.testdubbo.schema.HeroNamespaceHandler
    KEY 是 xsd 文件中的 targetNamespace
```
### 7. 测试 - 编写hero.xml
```text
    xmlns:hero的value是xsd文件中的targetNamespace。
    xmlns:hero可以写成xmlns:xxx，此时<hero:elementname1/>就要写成<xxx:elementname1/>
```
### 8. 编写测试主类
```text
    在实际中，随着注解和javaconfg的盛行，xml的方式渐渐的会淡出舞台，但是spring的启动流程还是会的。来看一下上述代码涉及到的流程。
    
    使用 ResourceLoader 将配置文件 xml 装载为 Resource 对象；
    使用 BeanDefinitionReader 解析配置信息：将每一个 <bean> 解析为一个 BeanDefinition 对象，然后存储到 BeanDefinitionRegistry 中
    实际上是 BeanDefinitionReader 调用 BeanDefinitionParser 进行了解析操作，解析完成后注册到 BeanDefinitionRegistry（代码看上边的HeroBeanDefinitionParser）
```