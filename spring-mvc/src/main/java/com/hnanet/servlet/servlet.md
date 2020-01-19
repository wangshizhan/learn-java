## CGI（通用网关接口）
```text
    CGI 即使可以让服务器能够调用外部程序，并将HTTP请求信息传递给外部程序处理，对于每一个请求，会启动一个新的进程。
    CGI 技术的缺点
        客户端数量增加时，响应时间更多
        每一个其请求，需要启动一个新的进程，消耗大量的系统资源
        使用平台依赖语言，如：C、C++、Perl
```
![CGI](../../../../resources/images/servlet/CGI.png)

## 为什么有 Servlet
```text
    Servlet 是 J2EE 规范之一，在遵守该规范的前提下，我们可将 Web 应用部署在 Servlet 容器下。
    可使开发者聚焦业务逻辑，而不用去关心 HTTP 协议方面的事情。
    如果我们基于 Servlet 规范实现 Web 应用的话，HTTP 协议的处理过程就不需要我们参与了。这些工作交给 Servlet 容器就行了，我们只需要关心业务逻辑怎么实现即可。
    Servlet 优势
        更好的性能：每个请求创建的是线程，而不是进程
        可移植性：使用Java跨平台语言
        更强大：Servlet有JVM管理，不需要担心内存泄露、溢出等
```

## Servlet 基本目录
```text
    在 tomcat/webapps 目录下创建下述目录结构
    1. 所有 HTML，静态文件直接保存在应用程序目录下
    2. 所有的 Servlet 类保存在 WEB-INF/classe 目录或子目录下
    3. web.xml（部署描述符）文件保存在 WEB-INF 目录下
```
![Servlet基本目录](../../../../resources/images/servlet/Servlet-index.png)

## Servlet 继承关系
```text
servlet(interface)->init(),service(),destroy();
^
genericServlet(abstract class)->协议无关的 servlet，是一个比较原始的实现，通常我们不会直接继承该类。
^
httpServlet(abstract class)->实现了http协议
```
![servlet](../../../../resources/images/servlet/servlet.png

## Servlet & ServletConfig
```java
public interface Servlet {

    /**
     * 
     * init 方法会在容器启动时由容器调用，也可能会在 Servlet 第一次被使用时调用，调用时机取决 load-on-start 的配置。只会被调用一次
     * 容器调用 init 方法时，会向其传入一个 ServletConfig 参数
     * xml 文件的配置信息最终会被放入 ServletConfig 实现类对象中。DispatcherServlet 通过 ServletConfig 接口中的方法，就能获取到 contextConfigLocation 对应的值。
     * 参数包含了 Serlvet 的配置信息。通常情况下，我们在 web.xml 文件中定义 Serlvet 时，会通过 init-param 标签来进行参数配置。
     */
    public void init(ServletConfig config) throws ServletException;

    public ServletConfig getServletConfig();

    /**
     * service 方法用于处理请求，业务逻辑在 service 中编写
     * 一般情况下我们不会直接实现 Servlet 接口，通常是通过继承 HttpServlet 抽象类编写业务逻辑
     */
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException;
   
    /**
     * 用于获取 Servlet 相关的信息：版权等
     */
    public String getServletInfo();
    
    /**
     * 和init一样，只会被调用一次，一般在服务器关闭时用于释放一些资源。
     */
    public void destroy();
}
public interface ServletConfig {
    
    /**
     * 获取Servlet的名字，即在 web.xml 中定义的 servlet-name
     */
    public String getServletName();

    /**
     * 用于获取 Servlet 上下文。ServletContext 代表当前的 Web 应用本身
     * 可通过标签向 ServletContext 中配置信息
     */
    public ServletContext getServletContext();

    /**
     * 用于获取 init-param 中配置的参数
     */
    public String getInitParameter(String name);

    /**
     * 用于获取所有init-param配置名字的集合
     */
    public Enumeration<String> getInitParameterNames();
}
```
## 演进
### 演进1：GenericServlet抽象类
```text
    实现 Servlet 接口的时候必须将所有的方法实现，即使方法中没有任何代码。在 GenericServlet 抽象类的帮助下，只需要重写 service 方法即可。
```
### 演进2：HttpServlet抽象类
```text
    这个类是和 HTTP 协议相关。
    实现了 service 方法，在请求进来时，Web 容器首先调用 HttpServlet 的 service 方法，并根据请求的类型调用 doGet 或 doPost 方法。
    我们若需要基于 Servlet 写 Web 应用，应继承该类，并覆盖doGet()和goPost()方法。
    doGet 和 doPost 等方法并不是处理的入口方法，所以这些方法需要由 service 调用才行。
    Spring MVC 中的 DispatcherServlet 就是 HttpServlet 的子类
```
### 演进3：JSP的加入
```text
    Servlet 的最大缺点就是在 Servlet 类中编写大量繁杂的 HTML 代码，使得 Java 代码与 HTML 代码糅杂在一起，所以 JSP 应运而生。 
    JSP 本质也是 Servelt，然而其不需要编译，JSP页面是一个以.jsp扩展名的文本文件。
    简单的JSP页面在第一次请求后被翻译为（JSP名）_jsp 的 Servelt，翻译之后的 Servelt 可以看到：_jspInit()、_jspDestory()、_jspService() 这样的方法其实都是和 Servlet 相对应的。 
```
    
### 演进4：Spring横空出世
```text
    Spring提供了强大的控制反转（IOC）和依赖注入（DI）功能，达到项目组件的解耦。
```

### 演进5：Spring Web模块 - Spring MVC
```text
    当要使用 Servlet 完成的复杂的功能时，需要编写多个 Servlet 类，并且在 web.xml 进行注册，这对于完成复杂的 Web 应用，代码编写会变得很复杂，开发成本也会很高。
    所以 Spring 提供了强大的 Web 开发框架 Spring MVC。Spring MVC 是 Spring产品的一部分，享有 Spring 松耦合等所有优点。  
    Spring MVC 是一个 模型-视图-控制器 的Web框架，建立前端控制器 Servlet（DispatcherServlet），它负责发送每个请求到合适的处理程序，使用视图来返回响应结果。
    Spring web MVC 框架提供了 MVC(模型 - 视图 - 控制器)架构 和用于开发灵活和松散耦合的 Web 应用程序的组件。
    
    MVC 模式导致应用程序的不同方面(输入逻辑，业务逻辑和UI逻辑)分离，同时提供这些元素之间的松散耦合
        模型（Model）：封装了应用程序的数据，通常由 POJO 类组成
        视图（View）：负责渲染模型数据，一般来说它生成客户端浏览器可以解释 HTML 输出
        控制器(Controller)：负责处理用户请求并构建适当的模型，并将其传递给视图进行渲染

    DispatcherServlet组件类
        Spring MVC 框架是围绕 DispatcherServlet 设计的，它处理所有的请求和响应。

    DispatcherServlet处理HTTP请求的工作流程：
        1. 接收HTTP请求后，DispatcherServlet 会根据请求的url，查询 HandlerMapping 以调用相应的 Controller。
        2. Controller 接受请求并根据请求的类型 Get/Post 调用相应的服务方法，服务方法进行相应的业务处理，并设置模型数据，最后将视图名称返回给 DispatcherServlet
        3. DispatcherServlet 根据返回的视图名称从 ViewResolver 获取对应的视图
        4. DispatcherServlet 将模型数据传递到最终的视图，并将视图返回给浏览器。
```