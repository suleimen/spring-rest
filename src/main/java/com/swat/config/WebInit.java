package com.swat.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
