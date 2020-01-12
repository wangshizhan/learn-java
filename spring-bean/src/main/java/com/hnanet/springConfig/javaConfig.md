JavaConfig即Java Configuration, 即用纯 Java 的方式来配置 Spring
IoC 容器,允许开发者用代码来表示配置逻辑，不再需要 XML
使用 JavaConfig 的好处，Spring 官方文档中说的很清楚：

JavaConfig 为依赖注入提供了一个真正面向对象的机制，这意味着开发者可以在配置代码中充分利用 Java 重用、继承和多态性等特性。
开发者可以完全控制实例化和依赖注入，即使是最复杂的对象也可以很优雅地处理。
因为使用 JavaConfig 只需要 Java，可以很容易的 refactor 代码，而无需再 IDE 之外使用特殊的工具或插件。
JavaConfig 其实很简单，主要是通过 @Configuration 和 @Bean 来进行配置。@Configuration 注解的作用是声明当前类是一个配置类， 就相当于一个 XML 文件。 @Bean 注解声明当前方法返回的是一个 bean。

JavaConfig 与 XML

@Configuration

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

@Bean
```xml
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="url" value="jdbc:mysql://127.0.0.1:3307/giraffe"/>
    <property name="username" value="ymy"/>
    <property name="password" value="666666"/>
</bean>

```
@ComponentScan

```xml
<context:component-scan/>
```
@Import
```xml
<import resource="XXX.xml"/>
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
