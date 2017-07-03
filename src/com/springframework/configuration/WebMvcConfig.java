package com.springframework.configuration;


import org.springframework.context.annotation.Configuration;  
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;  
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;  

@Configuration  
public class WebMvcConfig extends WebMvcConfigurerAdapter {  
    
  @Override  
  public void addViewControllers(ViewControllerRegistry registry){  
      registry.addViewController("/MachineManagementNew/").setViewName("/MachineManagementNew/index.do");  
      registry.addRedirectViewController("/MachineManagementNew/", "/MachineManagementNew/index.do");
  }  
} 