<%--
  Created by IntelliJ IDEA.
  User: D06LH
  Date: 12-11-8
  Time: 下午2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--c:redirect url="/upload.jsp"/-->
<%
    /**
     * sets the HTTP version 1.1 response header Cache-Control
     * to “no-store, no-cache, must-revalidate”, ensures that the user will never
     * see the browser-cached version of the login page.
     */
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    /**
     * to satisfy HTTP version 1.0, we also set the Pragma and Expires headers.
     */
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);
    // Check if user is already logged in
    if (request.getRemoteUser() != null) {
        response.sendRedirect("logout-confirmation.jsp");
    }
%>
<html>
<body>
<h2>Hello World!</h2>
<FORM ACTION="j_security_check" METHOD="POST">
    <TABLE>
        <TR><TD>User name: <INPUT TYPE="TEXT" NAME="j_username">
            <TR><TD>Password: <INPUT TYPE="PASSWORD" NAME="j_password">
        <TR><TH><INPUT TYPE="SUBMIT" VALUE="Log In">
    </TABLE>
</FORM>
</body>
</html>
