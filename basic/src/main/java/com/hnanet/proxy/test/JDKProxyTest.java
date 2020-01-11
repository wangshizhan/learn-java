package com.hnanet.proxy.test;

import com.hnanet.proxy.jdk.ClassLoaderUtils;
import com.hnanet.proxy.jdk.JDKProxy;
import com.hnanet.proxy.service.IUserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JDKProxyTest {

    private final static Logger logger = LoggerFactory.getLogger(JDKProxyTest.class);

    /**
     * JDK代理机制：委托机制，代理类和目标类都实现了同样的接口，InvocationHandler 持有目标类，代理类委托 InvocationHandler 去调用目标类的原始方法
     * 适用于：目标类是接口类
     * 回调方式：反射
     * 效率：效率瓶颈在反射调用稍慢
     */

    @Test
    public void test_proxy_jdk() throws Exception {

        IUserService proxy = (IUserService) JDKProxy.getProxy(ClassLoaderUtils.forName("com.hnanet.proxy.service.IUserService"));
        String userName = proxy.getUserNameById("10001");
        logger.debug(userName);

        String name = "ProxyUserService";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{IUserService.class});

        // 输出类字节码
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(name + ".class");
            logger.debug((new File("")).getAbsolutePath());
            out.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
