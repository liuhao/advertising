package com.sparkmedia.van.advertising.action;

import sun.misc.BASE64Decoder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import sun.misc.BASE64Decoder;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 13-1-4
 * Time: 下午5:01
 * A filter for user authentication.
 */
public class AuthenticateFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // get user's session Authorization attribute
        HttpSession session = req.getSession();
        String authorization = String.valueOf(session.getAttribute("Authorization"));
        if (authorization == null) {
            askForPassword(res);
        }
        chain.doFilter(request, response);
    }

    // If no Authorization header was supplied in the request.
    private void askForPassword(HttpServletResponse response) {
        // Send HTTP 401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate",
                "BASIC realm=\"Spark Advertising\"");
    }

    public void init(FilterConfig config) throws ServletException {}

    public void destroy() {}
}
