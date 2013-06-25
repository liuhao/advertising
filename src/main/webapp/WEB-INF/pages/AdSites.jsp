<%@ page import="com.sparkmedia.van.advertising.entity.AdSite" %>
<%--
  Created by IntelliJ IDEA.
  User: D06LH
  Date: 12-10-23
  Time: 上午9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<jsp:useBean id="Page" type="com.sparkmedia.van.advertising.utils.Page" scope="session"/>
page.totalRecordes = ${Page.totalRecords}
page.curPage = ${Page.curPage}
page.pageCount = ${Page.pageCount}
<table border="1">
    <tr>
        <td>${Page.results[0].id}</td>
        <td>${Page.results[0].adContents[1].name}</td>
    </tr>
</table>

<table>
    <tr>
        <td>id</td>
        <td>adContents</td>
    </tr>
    <%
        List<AdSite> adSiteList = Page.getResults();
        for (AdSite adSite : adSiteList) {
            out.println("<tr><td>" + adSite.getId() + "</td>");
            out.println("<td>" + adSite.getAdContents().size() + "</td></tr>");
        }

    %>
</table>

</body>
</html>