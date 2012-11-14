<%--
  Created by IntelliJ IDEA.
  User: D06LH
  Date: 12-11-13
  Time: 上午11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/add_adsite_type" method="post" enctype="multipart/form-data">
<p>AdSite Type Name : <input type="text" name="typeName" size="28"></p>
<p>AdSite Position:</p>
    Spot Name:<input type="text" name="spotName" size="28"><br>
    Spot Description:<input type="text" name="spotName" size="28"><br>
    Spot Position: <br>
    x:<input type="text" name="x" size="4">
    y:<input type="text" name="y" size="4"><br>
    Spot Size:<br>
    w:<input type="text" name="w" size="4">
    h:<input type="text" name="h" size="4">
</form>
</body>
</html>