//package com.fsproject.floatlyshare;
//
//import org.beetl.core.resource.ClasspathResourceLoader;
//import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
//import org.beetl.ext.spring.BeetlSpringViewResolver;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.logging.Logger;
//
//@Configuration
//public class beetlConfig {
////    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Bean(name = "beetlConfig")
//    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
//        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
//        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
//        beetlGroupUtilConfiguration.setResourceLoader(classpathResourceLoader);
//        beetlGroupUtilConfiguration.init();
//        return beetlGroupUtilConfiguration;
//    }
//
//    @Bean(name = "beetlViewResolver")
//    public BeetlSpringViewResolver getBeetlSpringViewResolver(
//            @Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
//        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
//        beetlSpringViewResolver.setPrefix("/templates/");
//        beetlSpringViewResolver.setSuffix(".html");
//        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
//        beetlSpringViewResolver.setOrder(0);
//        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
//        return beetlSpringViewResolver;
//    }
//}
