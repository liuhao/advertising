package com.sparkmedia.van.advertising.action;

import sun.misc.BASE64Decoder;

import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-17
 * Time: 下午3:32
 * check if the user has permission to access the main page.
 */
public class OperatorLogin extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        if ( userName == null || password == null ) {
            askForPassword(response);
        } else {
            // High security: username must be reverse of password.
            if (areEqualReversed(userName, password)) {
                // Add session attribute for the user.
                HttpSession session = request.getSession();
                session.setAttribute("Authorization", "yes");
                // redirect to main page.
                response.sendRedirect("/show_ad_sites");
            } else {
                // stay at login page.
                askForPassword(response);
            }
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // If no Authorization header was supplied in the request.
    private void askForPassword(HttpServletResponse response) {
        // Send HTTP 401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate",
                "BASIC realm=\"Spark Advertising\"");
    }

    // Returns true if s1 is the reverse of s2.
    // Empty strings don't count.
    private boolean areEqualReversed(String s1, String s2) {
        s2 = (new StringBuffer(s2)).reverse().toString();
        return ((s1.length() > 0) && s1.equals(s2));
    }
}
