一个遵循Servlet开发的java类。Serlvet是由服务器调用的，运行在服务器端。
以前我们写的程序都是通过JVM来运行的，而现在，我们是通过Tomcat来运行的
根据web的目录规范，Servlet编译后的class文件是存放在WEB-INF\classes文件夹中的
我们编写java程序想要在网上实现 聊天、发帖、这样一些的交互功能，普通的java技术是非常难完成的。

HTTP1.0和HTTP1.1的区别：短连接和常链接的区别[能不能保持连接]

HTTP请求
浏览器向服务器请求某个web资源时，称之为浏览器向服务器发送了一个http请求。
一个完整http请求应该包含三个部分：

请求行【描述客户端的请求方式、请求的资源名称，以及使用的HTTP协议版本号】
多个消息头【描述客户端请求哪台主机，以及客户端的一些环境信息等】
一个空行


HTTP响应
一个HTTP响应代表着服务器向浏览器回送数据
一个完整的HTTP响应应该包含四个部分:

一个状态行【用于描述服务器对请求的处理结果。】
多个消息头【用于描述服务器的基本信息，以及数据的描述，服务器通过这些数据的描述信息，可以通知客户端如何处理等一会儿它回送的数据】
一个空行
实体内容【服务器向客户端回送的数据】



请求头

Accept: text/html,image/*    【浏览器告诉服务器，它支持的数据类型】
Accept-Charset: ISO-8859-1	【浏览器告诉服务器，它支持哪种字符集】
Accept-Encoding: gzip,compress 【浏览器告诉服务器，它支持的压缩格式】
Accept-Language: en-us,zh-cn 【浏览器告诉服务器，它的语言环境】
Host: www.it315.org:80【浏览器告诉服务器，它的想访问哪台主机】
If-Modified-Since: Tue, 11 Jul 2000 18:23:51 GMT【浏览器告诉服务器，缓存数据的时间】
Referer: http://www.it315.org/index.jsp【浏览器告诉服务器，客户机是从那个页面来的---反盗链】
8.User-Agent: Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0)【浏览器告诉服务器，浏览器的内核是什么】
Cookie【浏览器告诉服务器，带来的Cookie是什么】
Connection: close/Keep-Alive  【浏览器告诉服务器，请求完后是断开链接还是保持链接】
Date: Tue, 11 Jul 2000 18:23:51 GMT【浏览器告诉服务器，请求的时间】

响应头

Location: http://www.it315.org/index.jsp 【服务器告诉浏览器要跳转到哪个页面】
Server:apache tomcat【服务器告诉浏览器，服务器的型号是什么】
Content-Encoding: gzip 【服务器告诉浏览器数据压缩的格式】
Content-Length: 80 【服务器告诉浏览器回送数据的长度】
Content-Language: zh-cn 【服务器告诉浏览器，服务器的语言环境】
Content-Type: text/html; charset=GB2312 【服务器告诉浏览器，回送数据的类型】
Last-Modified: Tue, 11 Jul 2000 18:23:51 GMT【服务器告诉浏览器该资源上次更新时间】
Refresh: 1;url=http://www.it315.org【服务器告诉浏览器要定时刷新】
Content-Disposition: attachment; filename=aaa.zip【服务器告诉浏览器以下载方式打开数据】
Transfer-Encoding: chunked  【服务器告诉浏览器数据以分块方式回送】
Set-Cookie:SS=Q0=5Lb_nQ; path=/search【服务器告诉浏览器要保存Cookie】
Expires: -1【服务器告诉浏览器不要设置缓存】
Cache-Control: no-cache  【服务器告诉浏览器不要设置缓存】
Pragma: no-cache   【服务器告诉浏览器不要设置缓存】
Connection: close/Keep-Alive   【服务器告诉浏览器连接方式】
Date: Tue, 11 Jul 2000 18:23:51 GMT【服务器告诉浏览器回送数据的时间】


Servlet带给我们最大的作用就是能够处理浏览器带来HTTP请求，并返回一个响应给浏览器，从而实现浏览器和服务器的交互。

手撸servlet
实现 Serlvet 接口
init【初始化】，destroy【销毁】,service【服务】,ServletConfig【Servlet配置】,getServletInfo【Serlvet信息】
配置xml文件,Tomcat还要知道浏览器怎么访问这个Servlet。


生命周期：
```text
加载Servlet。当Tomcat第一次访问Servlet的时候，Tomcat会负责创建Servlet的实例
初始化。当Servlet被实例化后，Tomcat会调用init()方法初始化这个对象
处理服务。当浏览器访问Servlet的时候，Servlet 会调用service()方法处理请求
销毁。当Tomcat关闭时或者检测到Servlet要从Tomcat删除的时候会自动调用destroy()方法，让该实例释放掉所占的资源。一个Servlet如果长时间不被使用的话，也会被Tomcat自动销毁
卸载。当Servlet调用完destroy()方法后，等待垃圾回收。如果有需要再次使用这个Servlet，会重新调用init()方法进行初始化操作。


简单总结：只要访问Servlet，service()就会被调用。init()只有第一次访问Servlet的时候才会被调用。
destroy()只有在Tomcat关闭的时候才会被调用。
```

继承HttpServlet
HttpServlet类已经实现了Servlet接口的所有方法，编写Servlet时，只需要继承HttpServlet，重写你需要的方法即可，并且它在原有Servlet接口上添加了一些与HTTP协议处理方法，它比Servlet接口的功能更为强大。


为什么Servlet是单例的
浏览器多次对Servlet的请求，一般情况下，服务器只创建一个Servlet对象，也就是说，Servlet对象一旦创建了，就会驻留在内存中，为后续的请求做服务，直到服务器关闭。
每次访问请求对象和响应对象都是新的
对于每次访问请求，Servlet引擎都会创建一个新的HttpServletRequest请求对象和一个新的HttpServletResponse响应对象，然后将这两个对象作为参数传递给它调用的Servlet的service()方法，service方法再根据请求方式分别调用doXXX方法。


线程安全问题
当多个用户访问Servlet的时候，服务器会为每个用户创建一个线程。当多个用户并发访问Servlet共享资源的时候就会出现线程安全问题。
原则：

如果一个变量需要多个用户共享，则应当在访问该变量的时候，加同步机制synchronized (对象){}
如果一个变量不需要共享，则直接在 doGet() 或者 doPost()定义.这样不会存在线程安全问题

load-on-startup
如果在元素中配置了一个元素，那么WEB应用程序在启动时，就会装载并创建Servlet的实例对象、以及调用Servlet实例对象的init()方法。
作用：

为web应用写一个InitServlet，这个servlet配置为启动时装载，为整个web应用创建必要的数据库表和数据
完成一些定时的任务【定时写日志，定时备份数据】



web.xml文件中的配置,web.xml文件配置了一个缺省Servlet
什么叫做缺省Servlet？凡是在web.xml文件中找不到匹配的元素的URL，它们的访问请求都将交给缺省Servlet处理，也就是说，缺省Servlet用于处理所有其他Servlet都不处理的访问请求
既然我说了在web访问任何资源都是在访问Servlet，那么我访问静态资源【本地图片，本地HTML文件】也是在访问这个缺省Servlet【DefaultServlet】


ServletConfig对象有什么用？

通过此对象可以读取web.xml中配置的初始化参数。

现在问题来了，为什么我们要把参数信息放到web.xml文件中呢？我们可以直接在程序中都可以定义参数信息，搞到web.xml文件中又有什么好处呢？
好处就是：能够让你的程序更加灵活【更换需求，更改配置文件web.xml即可，程序代码不用改】


什么是ServletContext对象？
当Tomcat启动的时候，就会创建一个ServletContext对象。它代表着当前web站点
ServletContext有什么用？

ServletContext既然代表着当前web站点，那么所有Servlet都共享着一个ServletContext对象，所以Servlet之间可以通过ServletContext实现通讯。
ServletConfig获取的是配置的是单个Servlet的参数信息，ServletContext可以获取的是配置整个web站点的参数信息
利用ServletContext读取web站点的资源文件
实现Servlet的转发【用ServletContext转发不多，主要用request转发】

读取资源文件
ServletContext读取就可以避免修改代码的情况，因为ServletContext对象是根据当前web站点而生成的
//获取到ServletContext对象
        ServletContext servletContext = this.getServletContext();

        //调用ServletContext方法获取到读取文件的流
        InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/classes/zhongfucheng/web/1.png");


如果我的文件放在web目录下，那么就简单得多了！,直接通过文件名称就能获取
//获取到ServletContext对象
        ServletContext servletContext = this.getServletContext();

        //调用ServletContext方法获取到读取文件的流
        InputStream inputStream = servletContext.getResourceAsStream("2.png");
通过类装载器读取资源文件。

我的文件放在了src目录下【也叫做类目录】
//获取到类装载器
        ClassLoader classLoader = Servlet111.class.getClassLoader();

        //通过类装载器获取到读取文件流
        InputStream inputStream = classLoader.getResourceAsStream("3.png");
如果文件太大，就不能用类装载器的方式去读取，会导致内存溢出
