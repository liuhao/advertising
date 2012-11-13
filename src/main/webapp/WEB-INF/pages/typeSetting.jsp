<%@ page import="com.sparkmedia.van.advertising.entity.AdSiteType" %>
<%@ page import="java.util.List" %>
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

<jsp:useBean id="Page" type="com.sparkmedia.van.advertising.utils.Page" scope="session"/>

<%--
page.totalRecordes = ${Page.totalRecords}
page.curPage = ${Page.curPage}
page.pageCount = ${Page.pageCount}
<table>
<tr>
    <td>${Page.results[0].id}</td>
    <td>${Page.results[0].adContents[1].name}</td>
</tr>
</table>
--%>

<table>
    <tr>
        <td>id</td>
        <td>adContents</td>
    </tr>
    <%
        List<AdSiteType> adSiteTypeList = Page.getResults();
        for (AdSiteType adSiteType : adSiteTypeList) {
            out.println("<tr><td>" + adSiteType.getId() + "</td>");
            out.println("<td>" + adSiteType.getAdContents().size() + "</td></tr>");
        }

    %>
</table>
</body>
</html>