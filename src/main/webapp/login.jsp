<%--
  Created by IntelliJ IDEA.
  User: D06LH
  Date: 12-11-8
  Time: 下午2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:redirect url="/upload.jsp"/>
--%>

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
    if (request.getSession().getAttribute("Authorization") != null) {
        response.sendRedirect("upload.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <style>
        #wincenter {
            position: absolute;
            top: 30%;
            left: 25%;
        }

        ul li {
            list-style: none;
            clear: both;
        }

        form {
            width: 40em;
            border: 1px solid #666;
            border-radius: 10px;
            box-shadow: .2em .2em .5em #999;
            background-color: #d0e9f6;
            padding: 1em;
            overflow: hidden;
        }

        label {
            display: block;
            float: left;
            width: 10em;
            text-align: right;
            margin-right: .5em;
            margin-bottom: .5em;
            color: #04699d;
        }

        input.textinput {
            width: 20em;
            height: 1.5em;
            border: 1px solid #666;
            margin-bottom: .5em;
        }

        textarea {
            display: block;
            width: 20em;
            height: 5em;
            border: 1px solid #666;
            margin-bottom: 1em;
            line-height: 1.25;
            overflow: auto;
            resize: none;
        }

        input.textinput, textarea {
            font-family: Georgia, "Times New Roman", Times, serif;
            font-size: .875em;
        }

        fieldset {
            margin: 0;
            padding: 0;
            border: none;
        }

        legend {
            display: block;
            width: 10em;
            float: left;
            margin-right: .5em;
            text-align: right;
            color: #04699d;
        }

        #features label, #colors label {
            color: #000;
            display: inline;
            float: none;
            text-align: inherit;
            width: auto;
            font-weight: normal;
            background-color: inherit;
        }

        #colors ul li {
            display: inline;
            margin-bottom: 0;
        }

        #features ul {
            margin-left: 11em;
        }

        #features ul li {
            margin-bottom: 0;
            clear: none;
        }

        input[type="submit"], input[type="reset"] {
            display: block;
            width: 5em;
            height: 2em;
            float: left;
            background: white;
            font-size: inherit;
            border: 1px solid #04699d;
            border-radius: 4px;
            box-shadow: 2px 2px 3px rgba(0, 0, 0, .5);
        }

        input[type="submit"] {
            margin-left: 10.5em;
            margin-right: 1em;
            color: #C00; /* the submit button text is attention-getting red */
        }
    </style>

</head>
<body>
<div id="wincenter">
    <form action="operator_login" method="POST">
        <h2>Hello World!</h2>
        <ul>
            <li>
                <label for="form-username">User name:</label>
                <input type="text" name="username" class="textinput" id="form-username">
            </li>
            <li>
                <label for="form-password">Password:</label>
                <input type="password" name="password" class="textinput" id="form-password">
            </li>
            <li class="buttons">
                <input type="submit" value="Log In">
                <input type="reset">
            </li>
        </ul>
    </form>
</div>
</body>
</html>
