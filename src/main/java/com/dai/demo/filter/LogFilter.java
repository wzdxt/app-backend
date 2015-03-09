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
public class LogFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(LogFilter.class);

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        //Get the IP address of client machine.
        String ipAddress = request.getRemoteAddr();

        //Log the IP address and current timestamp.
        log.info("IP {}, Time {}", ipAddress, new Date().toString());

        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {

        //Get init parameter
        String testParam = config.getInitParameter("test-param");

        //Print the init parameter
        log.info("Test Param: {}", testParam);
    }

    public void destroy() {
        //add code to release any resource
        System.out.println("Filter destroyed. ");
    }
}
