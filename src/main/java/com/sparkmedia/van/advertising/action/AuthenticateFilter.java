package com.sparkmedia.van.advertising.action;

import sun.misc.BASE64Decoder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 13-1-4
 * Time: 下午5:01
 * A filter for user authentication.
 */
public class AuthenticateFilter implements Filter {
    private ServletContext context;
    private String filterName;
    protected FilterConfig config;
    private int startTime, endTime;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        String authorization = req.getHeader("Authorization");
        if (authorization == null) {
            askForPassword(response);
        } else {
            // Authorization headers looks like "Basic blahblah",
            // where blahblah is the base64 encoded username and
            // password. We want the part after "Basic ".
            String userInfo = authorization.substring(6).trim();
            BASE64Decoder decoder = new BASE64Decoder();
            String nameAndPassword = new String(decoder.decodeBuffer(userInfo));
            // Decoded part looks like "username:password".
            int index = nameAndPassword.indexOf(":");
            String user = nameAndPassword.substring(0, index);
            String password = nameAndPassword.substring(index+1);
            // High security: username must be reverse of password.
            if (areEqualReversed(user, password)) {
                showStock(request, response);
            } else {
                askForPassword(response);
            }
        }
        chain.doFilter(request,response);
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config; // In case it is needed by subclass.
        context = config.getServletContext();
        filterName = config.getFilterName();
        try {
            startTime =
                    Integer.parseInt(config.getInitParameter("startTime"));
            endTime =
                    Integer.parseInt(config.getInitParameter("endTime"));
        } catch(NumberFormatException nfe) { // Malformed or null
        // Default: access at or after 10 p.m. but before 6 a.m.
        // is considered unusual.
            startTime = 22; // 10:00 p.m.
            endTime = 6; // 6:00 a.m.
        }
    }

    public void destroy() {}
}
