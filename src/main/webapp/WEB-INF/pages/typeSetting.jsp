<%--
  Created by IntelliJ IDEA.
  User: D06LH
  Date: 12-10-31
  Time: 下午3:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Advertising Type Setting</title>
    </head>
    <body>
    <%@page contentType="" pageEncoding="" %>

    <jsp:useBean id="Page" type="com.sparkmedia.van.advertising.utils.Page" scope="session" />
    page.totalRecordes = ${Page.totalRecords}
    page.curPage = ${Page.curPage}
    page.pageCount = ${Page.pageCount}
    <table>
    <tr>
        <td>${Page.results[0].id}</td>
        <td>${Page.results[0].adContents[1].name}</td>
    </tr>
</table>
</body>
</html>