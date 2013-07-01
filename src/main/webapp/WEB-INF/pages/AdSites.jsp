<%@ page import="com.sparkmedia.van.advertising.entity.AdSite" %>
<%--
  Created by IntelliJ IDEA.
  User: D06LH
  Date: 12-10-23
  Time: 上午9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> AdSite List </title>
</head>
<body>

<div id="list">
    <table>
        <c:forEach var="item" items="${Page.results}">
            <tr>
                <td>${item.id}</td>
                <td>
                    <table>
                        <c:forEach var="content" items="${item.adContents}">
                            <tr>
                                <td>x:${content.name}</td>
                                <td>y:${content.uri}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>