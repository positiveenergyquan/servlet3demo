package com.servlet3.demo.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenerByFragment1 implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("初始化监听器ListenerByFragment1");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
