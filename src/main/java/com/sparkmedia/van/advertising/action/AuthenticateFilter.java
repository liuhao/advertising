package com.sparkmedia.van.advertising.action;

import sun.misc.BASE64Decoder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String authorization = req.getHeader("Authorization");
        if (authorization == null) {
            askForPassword(res);
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
            String password = nameAndPassword.substring(index + 1);
            // High security: username must be reverse of password.
            if (areEqualReversed(user, password)) {
                showStock(req, res);
            } else {
                askForPassword(res);
            }
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

    // Returns true if s1 is the reverse of s2.
    // Empty strings don't count.
    private boolean areEqualReversed(String s1, String s2) {
        s2 = (new StringBuffer(s2)).reverse().toString();
        return ((s1.length() > 0) && s1.equals(s2));
    }

    // Show a Web page giving the symbol of the next hot stock.
    private void showStock(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String docType =
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                        "Transitional//EN\">\n";
        out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>Hot Stock Tip!</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1>Today's Hot Stock:");
        for (int i = 0; i < 3; i++) {
            out.print(randomLetter());
        }
        out.println("</H1>\n" +
                "</BODY></HTML>");
    }

    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Returns a random number from 0 to n-1 inclusive.
    private int randomInt(int n) {
        return ((int) (Math.random() * n));
    }

    // A random letter from the alphabet.
    private char randomLetter() {
        return (ALPHABET.charAt(randomInt(ALPHABET.length())));
    }

    public void init(FilterConfig config) throws ServletException {}

    public void destroy() {}
}
