package com.hnanet.servlet.loginServlet;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    // final static Logger log = LoggerFactory.getLogger(LoginServlet.class);
    // tomcat 在创建此 Servlet 前会给它单独创建一个 config 对象，该对象只给此 Servlet 自己使用，其他 Servlet 无法访问。
    // 并且在调用此 Servlet 的 init() 时，将这个 config 对象传入。
    // config 被 tomcat 创建后已经自动读取了 web.xml 中的参数。
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String maxOnlineNum = config.getInitParameter("maxOnlineNum");
        System.out.println(maxOnlineNum);
    }

    @Override
    protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 验证验证码是否正确
        String code = req.getParameter("code");
        if(code==null)return;
        CircleCaptcha captcha = (CircleCaptcha)getServletContext().getAttribute("captcha");
        if(captcha==null)return;
        if(!captcha.verify(code))return;
        getServletContext().removeAttribute("captcha");
        User user = new User();
        Map<String,String[]> map = req.getParameterMap();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // 接收传入的账号密码
        System.out.println("接收账号密码: "+user);
        // 验证账号密码是否正确
        System.out.println("验证账号密码");
        UserDao userDao = new UserDao();
        User userLogin = userDao.getuser(user);
        if(userLogin == null){
            // 验证失败
            req.getRequestDispatcher("/failLoginServlet").forward(req,res);
        }else{
            // 验证成功
            // 判断当前在线人数是否达上限
            String maxOnlineNum = getServletConfig().getInitParameter("maxOnlineNum");

            ServletContext ctx = getServletContext();
            Integer count = (Integer)ctx.getAttribute("count");

            // 进入游戏 or 排队
            if(count >= Integer.parseInt(maxOnlineNum)){
                System.out.println("人数达到上限:" + count +"/" + maxOnlineNum);
                req.getRequestDispatcher("/waitLoginServlet").forward(req,res);
            }else{
                ctx.setAttribute("count", ++count);
                System.out.println("人数:" + count +"/" + maxOnlineNum);
                System.out.println("进入游戏...");
            }
            req.getRequestDispatcher("/successLoginServlet").forward(req,res);
        }


    }
}
