<%--
  Created by IntelliJ IDEA.
  User: D06LH
  Date: 12-10-31
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> AdSiteType List </title>
    <meta name="Generator" content="EditPlus">
    <meta name="Author" content="Liu Hao">
    <meta name="Keywords" content="List">
    <meta name="Description" content="A page to list AdSiteType recorders">
    <script src="js/jquery-1.4.3.min.js" type="text/javascript"></script>

    <style type="text/css">
        .web_dialog_overlay {
            position: fixed;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0;
            background: #000000;
            opacity: .15;
            -moz-opacity: .15;
            z-index: 101;
            display: none;
        }

        .web_dialog {
            display: none;
            position: fixed;
            width: 380px;
            height: 200px;
            top: 50%;
            left: 50%;
            margin-left: -190px;
            margin-top: -100px;
            background-color: #ffffff;
            border: 2px solid #336699;
            padding: 0;
            z-index: 102;
            font-family: Verdana;
            font-size: 10pt;
        }

        .web_dialog_title {
            border-bottom: solid 2px #336699;
            background-color: #336699;
            padding: 4px;
            color: White;
            font-weight: bold;
        }

        .web_dialog_title a {
            color: White;
            text-decoration: none;
        }

        .align_right {
            text-align: right;
        }
    </style>
</head>
<body>

<h3>JQuery Popup Dialogs</h3>

<input type="button" id="btnShowSimple" value="Simple Dialog"/>
<input type="button" id="btnShowModal" value="Modal Dialog"/>
<br/><br/>

<div id="list">
    <table>
        <c:forEach var="item" items="${Page.results}">
            <tr>
                <td>${item.id}</td>
                <td>
                    <table>
                        <c:forEach var="content" items="${item.adBlocks}">
                            <tr>
                                <td>x:${content.x}</td>
                                <td>y:${content.y}</td>
                            </tr>
                            <tr>
                                <td>w:${content.w}</td>
                                <td>h:${content.h}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div id="overlay" class="web_dialog_overlay"></div>
<div id="dialog" class="web_dialog">
    <table style="width: 100%; border: 0;">
        <tr>
            <td class="web_dialog_title">Add New AdSite Recorder</td>
            <td class="web_dialog_title align_right">
                <a href="#" id="btnClose">Close</a>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2" style="padding-left: 15px;">
                <b>Choose your favorite mobile brand? </b>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2" style="padding-left: 15px;">
                <div id="brands">
                    <input id="brand1" name="brand" type="radio" checked="checked" value="Nokia"/> Nokia
                    <input id="brand2" name="brand" type="radio" value="Sony"/> Sony
                    <input id="brand3" name="brand" type="radio" value="Motorola"/> Motorola
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="padding-left: 15px;">
                <div id="recordData">
                    Type Name:<input id="inputText" name="layerName" type="text" value="YouTube"/>
                    Content List:
                </div>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <input id="btnSubmit" type="button" value="Submit"/>
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#btnShowSimple").click(function (e) {
            ShowDialog(false);
            e.preventDefault();
        });

        $("#btnShowModal").click(function (e) {
            ShowDialog(true);
            e.preventDefault();
        });

        $("#btnClose").click(function (e) {
            HideDialog();
            e.preventDefault();
        });

        $("#btnSubmit").click(function (e) {
            var brand = $("#brands input:radio:checked").val();
            $("#output").html("<b>Your favorite mobile brand: </b>" + brand);
            HideDialog();
            e.preventDefault();
        });

    });

    function ShowDialog(modal) {
        $("#overlay").show();
        $("#dialog").fadeIn(300);

        if (modal) {
            $("#overlay").unbind("click");
        }
        else {
            $("#overlay").click(function (e) {
                HideDialog();
            });
        }
    }

    function HideDialog() {
        $("#overlay").hide();
        $("#dialog").fadeOut(300);
    }
</script>

<form method="post" action="/AddAdSiteType">
    <input type="submit" value="Add New AdSiteType">
</form>

<jsp:useBean id="Page" type="com.sparkmedia.van.advertising.utils.Page" scope="session"/>
page.totalRecordes = ${Page.totalRecords}
page.curPage = ${Page.curPage}
page.pageCount = ${Page.pageCount}
<table border="1">
    <tr>
        <td>${Page.results[0].id}</td>
        <td>${Page.results[0].adBlocks[1].name}</td>
    </tr>
</table>


<%--
<table>
    <tr>
        <td>id</td>
        <td>adBlocks</td>
    </tr>
    <%
        List<AdLayer> adSiteTypeList = Page.getResults();
        for (AdLayer adLayer : adSiteTypeList) {
            out.println("<tr><td>" + adLayer.getId() + "</td>");
            out.println("<td>" + adLayer.getAdBlocks().size() + "</td></tr>");
        }

    %>
</table>
--%>
</body>
</html>