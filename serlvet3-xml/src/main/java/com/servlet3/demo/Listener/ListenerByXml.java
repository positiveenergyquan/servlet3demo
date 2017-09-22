package com.servlet3.demo.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenerByXml implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("初始化监听器ListenerByXml");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
