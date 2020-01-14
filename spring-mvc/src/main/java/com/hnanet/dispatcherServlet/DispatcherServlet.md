## DispatcherServlet

> 底层是通过实现Sevlet或者Servlet类型的扩展【如：GenericServlet】来实现的

![binaryTree](../../../../resources/images/spring-dispatcher.png "binaryTree")




- Spring MVC 的核心

```text
    准确的说就是 MVC 设计模式中的 C 或 Controller。
    前端控制器模式的实现，为 Spring MVC 应用提供统一入口，处理所有的请求。
    完全与 Spring IoC 容器集成，可以使用 Spring 框架的每一个特性，比如依赖注入。
    DispatcherServlet 是连接 Java 与 Spring 的桥梁,处理所有传入的请求。
```

- 在 web.xml 中声明和配置

>与其他声明在 web.xml 中的 Servlet 一样，也是通过一个 URL pattern 将每个请求映射到 DispatcherServlet。

```xml
<web-app> 
    <servlet> 
        <servlet-name>SpringMVC</servlet-name> 
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class> 
        <load-on-startup>1</load-on-startup> 
    </servlet> 
    <servlet-mapping> 
        <servlet-name>SpringMVC</servlet-name> 
        <url-pattern>*</url-pattern> 
    </servlet-mapping> 
</web-app>
```
- 委派

```text
    负责将请求委派给 Spring MVC 中其他的组件处理，比如注有 @Controller 或 @RestController 的 Controller类，Handler Mappers(处理器映射)，View Resolvers(视图解析器) 等等。
    尽管，请求映射是由 @ResquestMapping 注解完成的，但实际上是由 DispatcherServlet 将请求委派给相应的 Controller 来处理的。
```

- 格式转换

```text
    在 RESTFul 的 web 服务中， DispatcherServlet 还负责选择正确的信息转换器，以便将响应结果转换成客户端期望的格式(JSON, XML 或 TEXT)。
    比如，如果客户端期望 JSON 格式，那么会使用 MappingJacksonHttpMessageConverter 或 MappingJackson2HttpMessageConverter (取决于 classpath 中可用的是 Jackson 1 还是 Jackson 2) 来将响应结果转成 JSON 字符串的格式。
    
```
- 继承自 HttpServlet 基类。Servlet 引擎(比如 Tomcat) 创建该类的实例，并且调用它不同的方法。

- load-on-startup

```text
当 DispatcherServlet 被配置为 load-on-startup = 1,意味着该 servlet 会在启动时由容器创建，而不是在请求到达时。这样做会降低第一次请求的响应时间，因为DispatcherServlet 会在启动时做大量工作，包括扫描和查找所有的 Controller 和 RequestMapping。
```

- WEB-INF 文件夹

```text
在 DispatcherServlet 初始化期间，Spring 框架会在 WEB-INF 文件夹中查找名为 [servlet-name]-servlet.xml 的文件，并创建相应的 bean。
比如，如果 servlet 像上面 web.xml 文件中配置的一样，名为 “SpringMVC”，那么会查找 “SpringMVC-Servlet.xml”的文件。如果全局作用域中有相同名字的bean，会被覆盖。
可以用 servlet 初始化参数 contextConfigLocation更改配置文件的位置。
```

- WebApplicationContext
```text
每个 DispatcherServlet 都有它自己的 WebApplicationContext ，并且继承了根 WebApplicationContext 中定义的所有 bean。
这些继承的 bean 在 servlet 指定的作用域中可以被重载，也可以在其指定作用域中定义新的 bean。
```

- last-modification-date
```text
DispatcherServlet也允许返回 Servlet API 定义的 last-modification-date。
为了决定请求最后修改时间，DispatcherServlet会先查找合适的 handler mapping，然后检测处理器是否实现了 LastModified 接口。
如果实现了，就调用接口的 getLastModified(request) 方法，并将该值返回给客户端。
```

## DispatcherServlet 如何处理请求

1. DispatcherServlet 被用来处理所有传入的请求，并将它们路由到不同的 Controller 来进行进一步处理。它决定了由哪个 Controller 处理请求。

2. DispatcherServlet 使用处理器映射来将传入的请求路由到处理器。

```text
    默认情况下，使用 BeanNameUrlHandlerMapping 和 由 @RequestMapping 注解驱动的 DefaultAnnotationHandlerMapping。
```

3. 为了找到正确的方法来处理请求，它会扫描所有声明了 @Controller 注解的类，并且通过 @RequestMapping 注解找到负责处理该请求的方法。@RequestMapping 注解可以通过多种方式处理请求。

```text
    1. 通过路径来映射请求(比如: @RequestMapping(“path”))
    2. 通过 HTTP 方法(比如: @RequestMapping("path", method=RequestMethod.GET))
    3. 通过请求参数(比如: @RequestMapping("path"”, method=RequestMethod.POST, params="param1”))
    4. 通过 HTTP 请求头(比如: @RequestMapping("path", header="content-type=text/*”))
    5. 可以在类级别声明 @RequestMapping 注解来过滤传入的请求
```
4. 在请求处理之后，Controller 会将逻辑视图的名字和 model 返回给 DispatcherServlet。之后利用视图解析器定位到真正的 View 以便渲染结果。我们可以指定使用的视图解析器，默认情况下，DispatcherServlet 使用 InternalResourceViewResolver 来将逻辑视图的名字转换成真正的视图，比如 JSP。

5. 选定视图之后，DispatcherServlet 会将数据模型与视图相结合，并将结果返回给客户端。并不是任何时候都需要视图，比如一个 RESTful 的 web 服务就不需要，它们的处理方法会利用 @ResponseBody 注解直接将请求结果返回给客户端。