# Annotation

## Guice

> 基于纯 Java Annotation 依赖注入框架 ，其性能明显优于采用XML方式的Spring。

```text
    Guice里最常用的两个注解就是@Singleton和@Inject
    Singleton表示构建的对象是单例的，Inject表示被标注的字段将使用Guice自动注入。
```
### Singleton

### @ImplementedBy

### Guice Module 定义装配规则

### @Named

### 注入Set

### 注入Map

## JavaConfig

> Guice 的出现，促使Spring及社区推出并持续完善了JavaConfig子项目，它基于Java代码和Annotation注解来描述bean之间的依赖绑定关系。

### @ComponentScan

### @Import
```text
    @Import注解用于导入配置类
```
### @Conditional
```text
    @Conditional 注解表示在满足某种条件后才初始化一个bean或者启用某些配置。
    它一般用在由@Component、@Service、@Configuration等注解标识的类上面，或者由@Bean标记的方法上。
    如果一个 @Configuration 类标记了@Conditional ，则该类中所有标识了 @Bean 的方法和 @Import 注解导入的相关类将遵从这些条件。
    如：
    @Conditional(JdbcTemplateCondition.class)
    @Service
    public MyService service() {
        ......
    }
    表示只有当 JdbcTemplateCondition 类的条件成立时才会创建MyService这个bean。
    也就是说 MyService 这 bean 的创建条件是 classpath 里面包含 JdbcTemplate，否则这个 bean 的声明就会被忽略掉。
```
````java
/**
 * 自定义条件类：
 * 实现 Condition 接口，并覆盖它的 matches() 方法。
 * 下面的简单条件类表示只有在 Classpath 里存在 JdbcTemplate 类时才生效
 **/
public class JdbcTemplateCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        try {
        conditionContext.getClassLoader().loadClass("org.springframework.jdbc.core.JdbcTemplate");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
````
### @ConfigurationProperties 与 @EnableConfigurationProperties

- 
```text
    需要在 Application 入口类加入注解 @EnableConfigurationProperties，表示对 @ConfigurationProperties的内嵌支持
    复杂 yml 文件需要自定义 PropertySourceFactory： 继承 DefaultPropertySourceFactory 类，重写其 createPropertySource 方法
```