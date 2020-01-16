package com.hnanet._0_init.loadResource;

import org.junit.Test;
import org.springframework.beans.factory.xml.DefaultDocumentLoader;
import org.springframework.beans.factory.xml.DocumentLoader;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

public class LoadResourceTest {
    @Test
    public void test_DocumentLoader() throws Exception {
        // 设置资源
        EncodedResource encodedResource = new EncodedResource(new ClassPathResource("spring/loadxml/bean-config.xml"));
        // 加载解析
        InputSource inputSource = new InputSource(encodedResource.getResource().getInputStream());
        DocumentLoader documentLoader = new DefaultDocumentLoader();

        Document doc = documentLoader.loadDocument(inputSource, new ResourceEntityResolver(new PathMatchingResourcePatternResolver()), new DefaultHandler(), 3, false);
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
            System.out.format("测试结果 beanName：%s beanClass：%s scope：%s ", id, clazz, scope);
        }

    }
}
