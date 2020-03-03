package com.hnanet.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// 访问 /filterServlet 的时候被转发到 /filter/index.jsp ，过滤取执行了两次
@WebFilter(urlPatterns={"/*"},dispatcherTypes = {DispatcherType.FORWARD,DispatcherType.REQUEST} )
public class FilterDemo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("DefaultFilter 过滤器 in");
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        if(req.getRequestURI().contains("/layui")){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        // 可以判断没有 session 的话，就跳转到登陆页面
        System.out.println("DefaultFilter 过滤器 out");
    }

    @Override
    public void destroy() {

    }
}
