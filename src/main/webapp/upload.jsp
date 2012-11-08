<%--
  Created by IntelliJ IDEA.
  User: D06LH
  Date: 12-11-8
  Time: 下午2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/file_upload" method="post" enctype="multipart/form-data">
    <p><label for="form-picture">A:</label><input type="file" name="A" size="28" id="pictureA"/></p>
    <p><label for="form-picture">B:</label><input type="file" name="B" size="28" id="pictureB"/></p>
    <p><label for="form-picture">C:</label><input type="file" name="C" size="28" id="pictureC"/></p>

    <p><input type="submit" value="start upload"/>
        <input type="reset"/></p>
</form>
</body>
</html>