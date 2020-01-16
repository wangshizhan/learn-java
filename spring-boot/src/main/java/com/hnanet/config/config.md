# Annotation

## 一、Guice

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

## 二、JavaConfig

> Guice 的出现，促使Spring及社区推出并持续完善了JavaConfig子项目，它基于Java代码和Annotation注解来描述bean之间的依赖绑定关系。
```text
    JavaConfig 即Java Configuration，即用纯 Java 的方式来配置 Spring IoC 容器，允许开发者用代码来表示配置逻辑，不再需要 XML
    优势：
    1. JavaConfig 为依赖注入提供了一个真正面向对象的机制，这意味着开发者可以在配置代码中充分利用 Java 重用、继承和多态性等特性。
    2. 开发者可以完全控制实例化和依赖注入，即使是最复杂的对象也可以很优雅地处理。
    3. 因为使用 JavaConfig 只需要 Java，可以很容易的 refactor 代码，而无需再 IDE 之外使用特殊的工具或插件。
    4. JavaConfig 其实很简单，主要是通过 @Configuration 和 @Bean 来进行配置。@Configuration 注解的作用是声明当前类是一个配置类， 就相当于一个 XML 文件。 @Bean 注解声明当前方法返回的是一个 bean。
```
### @ComponentScan
```xml
<context:component-scan/>
```
### @Import
> 用于导入配置类
```xml
<import resource="XXX.xml"/>
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

```text
    需要在 Application 入口类加入注解 @EnableConfigurationProperties，表示对 @ConfigurationProperties的内嵌支持
    复杂 yml 文件需要自定义 PropertySourceFactory： 继承 DefaultPropertySourceFactory 类，重写其 createPropertySource 方法
```

### @Bean

```xml
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="url" value="jdbc:mysql://127.0.0.1:3307/giraffe"/>
    <property name="username" value="ymy"/>
    <property name="password" value="666666"/>
</bean>
```


### @Configuration

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:tx="http://www.springframework.org/schema/tx"
      xmlns:util="http://www.springframework.org/schema/util"
      xmlns:p="http://www.springframework.org/schema/p"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
  	   http://www.springframework.org/schema/beans/spring-beans.xsd
      http://www.springframework.org/schema/tx
  	   http://www.springframework.org/schema/tx/spring-tx.xsd
      http://www.springframework.org/schema/util
      http://www.springframework.org/schema/util/spring-util.xsd">	
   
</beans>
```

```xml
<bean id="drMainDataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="url" value="${mysql.datasource.url}"/>
    <property name="username" value="${mysql.datasource.username}"/>
    <property name="password" value="${mysql.datasource.password}"/>
    <property name="maxActive" value="{mysql.datasource.max-active}"/>
</bean>
```

```java
@Configuration
public class DataAccessConfig extends ConfigurationSupport {
  @Bean(name = "dataSource")
  public DataSource mysqlDataSource(@Value("${mysql.datasource.url}") String url,
                                    @Value("${mysql.datasource.username}") String username,
                                    @Value("${mysql.datasource.password}") String password,
                                    @Value("${mysql.datasource.driverClass}") String driver,
                                    @Value("${mysql.datasource.max-active}") int maxActive) {
    DruidAbstractDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName(driver);
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setMaxActive(maxActive);
    return dataSource;
  }
}
```