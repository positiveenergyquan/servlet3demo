package com.servlet3.demo.filter;

import javax.servlet.*;
import java.io.IOException;

public class FilterByRegistration implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器FilterByRegistration");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("in过滤器FilterByRegistration");
        chain.doFilter(request,response);
        System.out.println("out过滤器FilterByRegistration");
    }

    @Override
    public void destroy() {
        System.out.println("销毁过滤器FilterByRegistration");
    }
}
