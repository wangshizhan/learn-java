package com.hnanet.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/cookieDemo1")
public class CookieDemo1  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Tomcat 8 以后，中文就不需要 URL 编码解码了。
        Cookie cookie = new Cookie("co", URLEncoder.encode("co值","utf-8"));
        Cookie cookie2 = new Cookie("co2",URLEncoder.encode("co2值","utf-8"));
        Cookie cookie3 = new Cookie("co3","co3值");
        // 同一个服务器，设置虚拟目录，使得多个项目之间的 Cookie 共享
        cookie.setPath("/");
        // 不同服务器间，设置一级域名相同，使得 Cookie 共享
        cookie.setDomain("baidu.com");
        // 持久化保存到硬盘内 300s。默认负数，保存在浏览器内存
        cookie.setMaxAge(300);
        resp.addCookie(cookie);
        resp.addCookie(cookie2);
        resp.addCookie(cookie3);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
