package com.servlet3.demo.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ListenerByAnnotation implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("初始化监听器ListenerByAnnotation");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
