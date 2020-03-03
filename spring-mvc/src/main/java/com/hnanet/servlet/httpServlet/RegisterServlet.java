package com.hnanet.servlet.httpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @usage http://localhost:8080/springmvc/customHttpServlet/register
 *
 */
public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String mypassword = req.getParameter("mypassword");
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        String introduce = req.getParameter("introduce");

        String isAccept;
        if(req.getParameterValues("isAccpet")!=null)
        {
            isAccept = req.getParameter("isAccept");
        }
        else
        {
            isAccept = "false";
        }
        // 用来获取多个复选按钮的值
        String[] favorites = req.getParameterValues("favorite");

        PrintWriter out =resp.getWriter();
        out.write("OK");
        out.close();
    }
}
