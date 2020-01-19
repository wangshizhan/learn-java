1. Wweb.xml内配置路径
---------------------------------------------------------------------
	<context-param>
<param-name>applicationContext</param-name>
<param-value>applicationContext.xml</param-value>
</context-param>
---------------------------------------------------------------------
2.写一个ServletContextListener类的实现类
在初始化方法里面创建ApplicationContext实例并将其加入ServletContext的一个属性中
---------------------------------------------------------------------
//1. 获取 Spring 配置文件的名称.
String configName=sce.getServletContext().getInitParameter("");
//1. 创建 IOC 容器
ApplicationContext ac=new ClassPathXmlApplicationContext(configName);
//2. 把 IOC 容器放在 ServletContext 的一个属性中. 
sce.getServletContext().setAttribute("ApplicationContext", ac);
3.写一个HttpServlet
从域对象获取ApplicationContext实例,并获对应的得bean
4.配置web.xml
1.配置context-param
2.配置listener
3.配置servlet
---------------------------------------------------------------------
<context-param>
<param-name>applicationContext</param-name>
<param-value>applicationContext.xml</param-value>
</context-param>
<listener>
<listener-class>com.st.MyServletContextListener</listener-class>
</listener>
<servlet>
<servlet-name>TestServletName</servlet-name>
<servlet-class>com.st.MyServlet</servlet-class>
</servlet>
<servlet-mapping>
<servlet-name>TestServletName</servlet-name>
<url-pattern>/test/TestServlet</url-pattern>
</servlet-mapping>