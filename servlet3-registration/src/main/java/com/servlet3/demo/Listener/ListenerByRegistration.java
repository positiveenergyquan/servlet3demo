package com.servlet3.demo.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenerByRegistration implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("初始化监听器ListenerByRegistration");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
