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
<jsp:useBean id="Page" type="com.sparkmedia.van.advertising.utils.Page" scope="request" />
page.totalRecordes = <jsp:getProperty name="Page" property="totalRecords" /> .
</body>
</html>