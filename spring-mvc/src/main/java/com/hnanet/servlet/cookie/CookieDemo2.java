package com.hnanet.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet( "/cookieDemo2")
public class CookieDemo2  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Cookie cookie :req.getCookies()) {
//            System.out.println(cookie.getName()+" : "+ URLDecoder.decode(cookie.getValue(),"utf-8"));
            System.out.println(cookie.getName()+" : "+ cookie.getValue());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
