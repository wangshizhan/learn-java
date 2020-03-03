package com.hnanet.servlet.loginServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * InitServlet， 仅仅是在 tomcat 启动时初始化一些数据
 * @author apple
 */

public class InitServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // 向 context 中存一个变量 count = 0， 给其他 servlet 使用。
        ServletContext ctx = getServletContext();
        ctx.setAttribute("count", 0);
    }
}
