## 演进
### 演进1：GenericServlet 抽象类
```text
    实现 Servlet 接口的时候必须将所有的方法实现，即使方法中没有任何代码。在 GenericServlet 抽象类的帮助下，只需要重写 service 方法即可。
```
### 演进2：HttpServlet 抽象类
```text
    这个类是和 HTTP 协议相关。
    实现了 service 方法，在请求进来时，Web 容器首先调用 HttpServlet 的 service 方法，并根据请求的类型调用 doGet 或 doPost 方法。
    我们若需要基于 Servlet 写 Web 应用，应继承该类，并覆盖doGet()和goPost()方法。
    doGet 和 doPost 等方法并不是处理的入口方法，所以这些方法需要由 service 调用才行。
    Spring MVC 中的 DispatcherServlet 就是 HttpServlet 的子类
```
### 演进3：JSP 的加入
```text
    Servlet 的最大缺点就是在 Servlet 类中编写大量繁杂的 HTML 代码，使得 Java 代码与 HTML 代码糅杂在一起，所以 JSP 应运而生。 
    JSP 本质也是 Servelt，然而其不需要编译，JSP页面是一个以.jsp扩展名的文本文件。
    简单的JSP页面在第一次请求后被翻译为（JSP名）_jsp 的 Servelt，翻译之后的 Servelt 可以看到：_jspInit()、_jspDestory()、_jspService() 这样的方法其实都是和 Servlet 相对应的。 
```
    
### 演进4：Spring 横空出世
```text
    Spring提供了强大的控制反转（IOC）和依赖注入（DI）功能，达到项目组件的解耦。
```

### 演进5：Spring Web 模块 - Spring MVC
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
