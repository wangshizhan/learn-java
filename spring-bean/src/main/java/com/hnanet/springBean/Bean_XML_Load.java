package com.hnanet.springBean;
import com.hnanet.springBean.bean.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.DefaultDocumentLoader;
import org.springframework.beans.factory.xml.DocumentLoader;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

public class Bean_XML_Load {

    private static final Logger logger = LoggerFactory.getLogger(Bean_XML_Load.class);


    @Test
    public void test_XmlBeanFactory() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("xml-load-spring-config.xml"));
        User user = beanFactory.getBean("userBean", User.class);
        logger.info("测试结果：{}", user.toString());
    }

    @Test
    public void test_ClassPathXmlApplicationContext() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("xml-load-spring-config.xml");
        User user = beanFactory.getBean("userBean", User.class);
        logger.info("测试结果：{}", user.toString());
    }

    @Test
    public void test_DocumentLoader() throws Exception {

        // 设置资源
        EncodedResource encodedResource = new EncodedResource(new ClassPathResource("xml-load-spring-config.xml"));

        // 加载解析
        InputSource inputSource = new InputSource(encodedResource.getResource().getInputStream());
        DocumentLoader documentLoader = new DefaultDocumentLoader();


       /*
        inputSource：加载 Document 的 Resource 源
        entityResolver：解析文件的解析器
        errorHandler：处理加载 Document 对象的过程的错误
        validationMode：验证模式
        namespaceAware：命名空间支持。如果要提供对 XML 名称空间的支持，则为true

        XSD 验证模式
            publicId：null
            systemId：http://www.springframework.org/schema/beans/spring-beans.xsd
        DTD 验证模式
            publicId：-//SPRING//DTD BEAN 2.0//EN
            systemId：http://www.springframework.org/dtd/spring-beans.dtd
        */

        Document doc = documentLoader.loadDocument(inputSource, new ResourceEntityResolver(new PathMatchingResourcePatternResolver()), new DefaultHandler(), 3, false);

        // 输出结果
        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (!(node instanceof Element)) {
                continue;
            }
            Element ele = (Element) node;
            if (!"bean".equals(ele.getNodeName())) {
                continue;
            }
            String id = ele.getAttribute("id");
            String clazz = ele.getAttribute("class");
            String scope = ele.getAttribute("scope");
            logger.info("测试结果 beanName：{} beanClass：{} scope：{}", id, clazz, scope);
        }

    }
}
