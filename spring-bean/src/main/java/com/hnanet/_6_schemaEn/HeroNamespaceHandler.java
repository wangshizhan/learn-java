package com.hnanet._6_schemaEn;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
public class HeroNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("elemental1", new HeroBeanDefinitionParser(Hero.class));
    }
}