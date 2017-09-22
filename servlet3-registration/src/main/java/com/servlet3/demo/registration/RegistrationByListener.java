package com.servlet3.demo.registration;

import javax.servlet.*;
import java.util.EnumSet;

public class RegistrationByListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        ServletRegistration sr = sc.addServlet("servletByRegistration","com.servlet3.demo.controller.ServletByRegistration");
        sr.addMapping("/ServletByRegistration");


        FilterRegistration fr = sc.addFilter("filterByRegistration","com.servlet3.demo.filter.FilterByRegistration");
        fr.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        fr = sc.addFilter("filterByRegistration2","com.servlet3.demo.filter.FilterByRegistration2");
        fr.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        sc.addListener("com.servlet3.demo.Listener.ListenerByRegistration");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
