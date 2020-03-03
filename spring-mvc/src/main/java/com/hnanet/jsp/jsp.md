## JSP
* Java Server Pages： java服务器端页面，本质上就是一个Servlet
* 可以理解为：一个特殊的页面，其中既可以指定定义html标签，又可以定义java代码
* 用于简化书写

#### JSP 脚本
1. <%  代码 %>：定义的java代码，在service方法中。service方法中可以定义什么，该脚本中就可以定义什么。
2. <%! 代码 %>：定义的java代码，在jsp转换后的java类的成员位置。
3. <%= 代码 %>：定义的java代码，会输出到页面上。输出语句中可以定义什么，该脚本中就可以定义什么。

#### 指令
* 作用：用于配置JSP页面，导入资源文件
* 格式：
    <%@ 指令名称 属性名1=属性值1 属性名2=属性值2 ... %>
* 三种分类：
    1. page		： 配置JSP页面的
        * contentType：等同于response.setContentType()
            1. 设置响应体的mime类型以及字符集
            2. 设置当前jsp页面的编码（只能是高级的IDE才能生效，如果使用低级工具，则需要设置 pageEncoding 属性设置当前页面的字符集）
        * import：导包
        * errorPage：当前页面发生异常后，会自动跳转到指定的错误页面
        * isErrorPage：标识当前也是是否是错误页面。
            * true：是，可以使用内置对象exception
            * false：否。默认值。不可以使用内置对象exception
    2. include	： 页面包含的。导入页面的资源文件
        * <%@include file="top.jsp"%>
    3. taglib	： 导入资源
        * <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            * prefix：前缀，自定义的
#### 注释
1. html注释
    <!-- -->: 只能注释html代码片段，注释会被发送到页面
2. jsp注释：推荐使用
    <%-- --%>： 可以注释所有,注释不会被发送到页面

#### JSP的9个内置对象
* 在jsp页面中不需要获取和创建，可以直接使用的对象

| 变量名 | 真实类型 | 作用  |
|---|---|---|
| pageContext |  PageContext | 域对象，当前页面共享数据，还可以获取其他八个内置对象 |
| request | HttpServletRequest | 域对象，一次请求访问的多个资源(转发) |
| session | HttpSession | 域对象，一次会话的多个请求间 |
| application | ServletContext | 域对象，所有用户间共享数据 |
| response | HttpServletResponse | 响应对象，response.getWriter() 数据输出永远在 out.write() 之前 |
| page | Object | 当前页面(Servlet)的对象  this |
| out | JspWriter | 输出对象，数据输出到页面上 |
| config | ServletConfig | Servlet的配置对象 |
| exception | Throwable | 异常对象 |

#### TL 表达式
1. 概念：Expression Language 表达式语言
2. 作用：替换和简化jsp页面中java代码的编写
3. 语法：${表达式}
4. 注意：
    * jsp默认支持el表达式的。如果要忽略el表达式
        ```text
        1. 设置jsp中page指令中：isELIgnored="true" 忽略当前jsp页面中所有的el表达式
        2. \${表达式} ：忽略当前这个el表达式
        ```
5. 使用：
    1. 运算：
        * 运算符：
            1. 算数运算符： + - * /(div) %(mod)
            2. 比较运算符： > < >= <= == !=
            3. 逻辑运算符： &&(and) ||(or) !(not)
            4. 空运算符： empty
                * 功能：用于判断字符串、集合、数组对象是否为null或者长度是否为0
                * ${empty list}:判断字符串、集合、数组对象是否为null或者长度为0
                * ${not empty str}:表示判断字符串、集合、数组对象是否不为null 并且 长度>0
    2. 获取值
        1. el表达式只能从域对象中获取值
        2. 语法：
            1. ${域名称.键名}：从指定域中获取指定键的值
                * 域名称：
                    1. pageScope		--> pageContext
                    2. requestScope 	--> request
                    3. sessionScope 	--> session
                    4. applicationScope --> application（ServletContext）
                * 举例：在request域中存储了name=张三
                * 获取：${requestScope.name}

            2. ${键名}：表示依次从最小的域中查找是否有该键对应的值，直到找到为止。

            3. 获取对象、List集合、Map集合的值
                1. 对象：${域名称.键名.属性名}
                    * 本质上会去调用对象的getter方法
                2. List集合
                    ``` text
                    ${域名称.键名[索引]}
                    ```
                3. Map集合
                    ``` text
                    ${域名称.键名.key名称}
                    ${域名称.键名["key名称"]}
                    ```
    3. 11个隐式对象

        | 隐式对象 | 作用 |
        |:---|:---|
        | pageContext | 对应于JSP页面中的pageContext对象，可以获取jsp其他八个内置对象 |
        | pageScope | 代表page域中用于保存属性的Map对象 |
        | requestScope | 代表request域中用于保存属性的Map对象 |
        | sessionScope | 代表session域中用于保存属性的Map对象 |
        | applicationScope | 代表application域中用于保存属性的Map对象 |
        | param | 表示一个保存了所有请求参数的Map对象 |
        | paramValues | 表示一个保存了所有请求参数的Map对象，它对于某个请求参数， 返回的是一个string类型数组 |
        | header | 表示一个保存了所有http请求头字段的Map对象 |
        | headerValues | 表示一个保存了所有http请求头字段的Map对象，返回string类型数组 |
        | cookie | 表示一个保存了所有cookie的Map对象 |
        | initParam | 表示一个保存了所有web应用初始化参数的map对象 |

#### JSTL
1. 概念：JavaServer Pages Tag Library  JSP标准标签库
    * 是由Apache组织提供的开源的免费的jsp标签 <标签>
2. 作用：用于简化和替换jsp页面上的java代码
3. 使用步骤
    1. 导入 jstl 相关 jar 包
    2. 引入标签库：taglib 指令：  <%@ taglib %>
    3. 使用标签
4. 常用的JSTL标签
    1. if:相当于java代码的if语句
        1. 属性
            * test 必须属性，接受boolean表达式
                * 如果表达式为true，则显示if标签体内容，如果为false，则不显示标签体内容
                * 一般情况下，test属性值会结合el表达式一起使用
         2. 注意
             * c:if 标签没有else情况，想要else情况，则可以在定义一个 c:if 标签
    2. choose:相当于java代码的switch语句
        1. 使用choose标签声明         			相当于switch声明
        2. 使用when标签做判断         			相当于case
        3. 使用otherwise标签做其他情况的声明    	相当于default

    3. foreach:相当于java代码的for语句