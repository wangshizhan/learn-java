package com.hnanet.servlet.httpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

public class CustomHttpServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException
    {
        System.out.println("CustomHttpServlet#init，初始化执行一次...");
        String username = this.getInitParameter("username");
        System.out.println("param-name: "+username);
    }

    //第一种参数在servlet里面可以通过getServletContext().getInitParameter("context/param")得到
    //第二种参数只能在servlet的init()方法中通过this.getInitParameter("param1")取得.
    /**
     * @usage http://localhost:8080/springmvc/customHttpServlet?userName=tom&passWord=pwd&interests=game&interests=work
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // /springmvc
        System.out.println("ContextPath: "+req.getContextPath());
        // /customHttpServlet
        System.out.println("ServletPath: "+ req.getServletPath());
        // /springmvc/customHttpServlet
        System.out.println("RequestURI: "+ req.getRequestURI());
        // http://localhost:8080/springmvc/customHttpServlet
        System.out.println("RequestURL: "+ req.getRequestURL());

        String init_parameter_username = getInitParameter("username");
        String init_parameter_username2 = getServletConfig().getInitParameter("username");
        // forezp
        System.out.println("init_parameter_username : "+init_parameter_username);
        // forezp
        System.out.println("init_parameter_username2 : "+init_parameter_username2);

        String servletContext_parameter_username = getServletContext().getInitParameter("username");
        // admin
        System.out.println("servletContext_parameter_username: "+servletContext_parameter_username);

        System.out.println("CustomHttpServlet#doGet，每次访问执行");

        System.out.println("请求方式" + req.getMethod());
        System.out.println("访问路径" + req.getServletPath());
        System.out.println("协议类型" + req.getProtocol());

        //获取请求数据
        String code = req.getParameter("userName");
        String pwd = req.getParameter("passWord");
        // 用来获取多个复选按钮的值
        String[] interests = req.getParameterValues("interests");
        //处理请求乱码
		//byte[] bs =code.getBytes("iso8859-1");
		//code = new String(bs,"utf-8");


        // 读取消息头
        // 该迭代器是比Iterator更古老的迭代器
        System.out.println("读取消息头 ------");
        Enumeration e = req.getHeaderNames();
        while(e.hasMoreElements()){
            String key = (String) e.nextElement();
            String value = req.getHeader(key);
            System.out.println(key + ":" + value);
        }
        System.out.println("读取结束 ------");
        // 告诉浏览器给它输出的是什么格式的内容
        // resp.setHeader("content-Type","text/html;charset=UTF-8");
        // resp.setContentType("text/html;charset=UTF-8")
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        // 获取输出流，该流指向的目标就是浏览器
        PrintWriter out = resp.getWriter();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm a");
        // 写实体内容
        out.write("<!DOCTYPE HTML>");
        out.write("<html>");
        out.write("<head>");
        out.write("<title>TimeServlet</title>");
        out.write("<meta charset='utf-8'>");
        out.write("</head>");
        out.write("<body>");
        out.write("梦似烟花心似水，同学少年不言情。。。");
        out.write("<p>"+ LocalDateTime.now().format(format)+"</p>");
        out.write("</body>");
        out.write("</html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 只对 Post 有效
        req.setCharacterEncoding("UTF-8");
        System.out.println("CustomHttpServlet#doPost，每次访问执行");
        this.doGet(req, resp);
    }
}
