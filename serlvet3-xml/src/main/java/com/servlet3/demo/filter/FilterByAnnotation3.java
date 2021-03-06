package com.servlet3.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName="filterByAnnotation3",urlPatterns = "/*")
public class FilterByAnnotation3 implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器filterByAnnotation3");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("in过滤器filterByAnnotation3");
        chain.doFilter(request,response);
        System.out.println("out过滤器filterByAnnotation3");
    }

    @Override
    public void destroy() {
        System.out.println("销毁过滤器filterByAnnotation3");
    }
}
