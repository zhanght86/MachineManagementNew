package com.springframework.configuration;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.context.annotation.Description;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;


public class WebConfig implements WebApplicationInitializer{

    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceRequestEncoding(true);
        characterEncodingFilter.setForceResponseEncoding(true);
        characterEncodingFilter.setForceEncoding(true);
        
        
         OpenSessionInViewFilter openSessionInViewFilter=new OpenSessionInViewFilter();
//         Environment environment = new StandardEnvironment();
//         openSessionInViewFilter.setEnvironment(environment);
         return new Filter[] {characterEncodingFilter,openSessionInViewFilter};
    }
    
    /**
     * web程序主入口
     */
    @Override
    @Description("")
    public void onStartup(ServletContext servletContext) throws ServletException {
 
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext=new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(SpringMVCConfig.class);
//        annotationConfigWebApplicationContext.register(WebMvcConfig.class);
//        annotationConfigWebApplicationContext.register(AopConfig.class);
        annotationConfigWebApplicationContext.setServletContext(servletContext);
    	servletContext.addListener(new ContextLoaderListener(annotationConfigWebApplicationContext));
        annotationConfigWebApplicationContext.scan("com.*");
        annotationConfigWebApplicationContext.setDisplayName("annotationConfigWebApplicationContext");
        annotationConfigWebApplicationContext.refresh();
        Dynamic servlet=servletContext.addServlet("dispatcher", new DispatcherServlet(annotationConfigWebApplicationContext));
        servlet.addMapping("/");
        servlet.addMapping("*.do");
        servlet.setLoadOnStartup(1);
        
    }
           
}