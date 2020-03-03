## servletContext
* 代表整个web应用，可以和程序的容器(服务器)来通信
### 获取
    1. 通过request对象获取
        request.getServletContext();
    2. 通过HttpServlet获取
        this.getServletContext();
### 功能
1. 获取MIME类型：
    * MIME类型:在互联网通信过程中定义的一种文件数据类型
        * 格式： 大类型/小类型   text/html		image/jpeg

    * 获取：String getMimeType(String file)
2. 域对象：共享数据
    1. setAttribute(String name,Object value)
    2. getAttribute(String name)
    3. removeAttribute(String name)

    * ServletContext 对象范围：所有用户所有请求的数据
3. 获取文件的真实(服务器)路径
    String getRealPath(String path)

### context-param
* 使用场景
```text
    listener、filter 等初始化时会用到 context-param 这些信息。
    你可能想在项目启动之前就打开数据库。可以在 context-param 中设置数据库的连接方式，在自定义的监听类中初始化数据库的连接。
```
> 配置在 <web-app> 标签中,属于上下文参数, 整个 web 应用中都可以使用。存放在 servletContext 对象中(即application对象)

> 获取：getServletConfig().getInitParameter("initParam")
```text
    1. 启动一个web项目的额时候，容器会读它的配置文件 web.xml 中的 listener 和 context-param。
    2. 接着，容器创建一个 ServletContext(上下文)，这个项目所有的部分都讲共享这个上下文。
    3. 容器将 context-param 转化为键值对，并交给ServletContext。
    4. 容器创建 listener 中的类实例，即创建监听。
    5. 监听中的 contextInitialized(ServletContextEvent args) 初始化方法被触发。 
    6. 容器对 filter 初始化（书写顺序）。
    7. 容器根据 load-on-startup 对 Servlet 进行初始化，负数或不指定则在该 Servlet 调用时初始化（Spring MVC 的初始化为此阶段）
```

## servletConfig
### init-param
> 配置在 <servlet> 标签中，属于当前 Servlet 的配置。存放在 servletConfig 对象中

> 获取：getServletContext().getInitParameter("contextParam")

