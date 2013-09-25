<%@ page import="com.sparkmedia.van.advertising.entity.AdSite" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: D06LH
  Date: 12-10-23
  Time: 上午9:39
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="Header.jsp" />
<div id="list">
    <table>
        <c:forEach var="item" items="${Page.results}">
            <tr>
                <td>${item.id}</td>
                <td>${item.layerId}</td>
                <td>${item.boxId}</td>
                <td>${item.uri}</td>
                <td>${item.stat}</td>
                <td>${item.updateDate}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<section>
    <nav id="category">
        <ul>
            <li>YouTube</li>
            <li>EPG Info</li>
            <li>Radio</li>
        </ul>
    </nav>
    <div id="">
        <table></table>
    </div>

    <p>Note Although there is <em>no way to hover a finger</em> over an element, <a href="">iOS <em>Safari</em> and some
        Android devices</a> may display the :hover state styles after</p>
</section>

<jsp:include page="Footer.jsp" />