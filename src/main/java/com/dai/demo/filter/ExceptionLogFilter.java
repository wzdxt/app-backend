package com.dai.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * Created by wzdxt on 15/3/22.
 */
public class ExceptionLogFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(ExceptionLogFilter.class);

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(req, res);
        } catch (Throwable e) {
            log.error("[Exception] Unhandled exception find.");
            throw e;
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
