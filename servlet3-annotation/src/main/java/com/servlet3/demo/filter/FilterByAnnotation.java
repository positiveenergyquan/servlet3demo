package com.servlet3.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName="filterByAnnotation",urlPatterns = "/*")
public class FilterByAnnotation implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器filterByAnnotation");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("in过滤器filterByAnnotation");
        chain.doFilter(request,response);
        System.out.println("out过滤器filterByAnnotation");
    }

    @Override
    public void destroy() {
        System.out.println("销毁过滤器filterByAnnotation");
    }
}
