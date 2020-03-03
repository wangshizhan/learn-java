package com.hnanet.servlet.loginServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PlayGameServlet extends HttpServlet {

    /**
     * 1. 默认首次访问时实例化
     * 2. 修改配置后在启动 tomcat 时实例化
     */
    public PlayGameServlet() {
        System.out.println("实例化 PlayGameServlet");
    }

    /**
     * 在实例化 Servlet 后由 tomcat自动调用
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("初始化 PlayGameServlet#init");
    }

    /**
     * 每次访问 该Servlet 时调用
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("调用 PlayGameServlet#service");
    }

    /**
     * 正常关闭tomcat时调用销毁
     */
    @Override
    public void destroy() {
        super.destroy();
        System.out.println("销毁 PlayGameServlet#destroy");
    }
}