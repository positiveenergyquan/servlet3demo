package com.servlet3.demo.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenerByFragment2 implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("初始化监听器ListenerByFragment2");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
