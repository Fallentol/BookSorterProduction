<%--
  Created by IntelliJ IDEA.
  User: New
  Date: 12/17/2016
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>
    <html>
    <head>
        <title>Title</title>
    </head>
    <style>
        body {
            margin: 0px;
            padding: 0px;
            background-color: #0b0b0b;
        }

        .bodyDiv {
            margin: auto;
            margin-top: 0px;
            width: 70%;
            height: 100%;
            background: linear-gradient(to top, #fefcea, mediumseagreen);
        }

        .mainDiv {
            background-color: antiquewhite;
            color: blue;
            font-weight: bold;
        }

        table {
            border-collapse: collapse;
            margin: 10px;
            background-color: #525252;
            color: aliceblue;
            box-shadow: 2px 2px 6px black;
        }

        td {
            padding: 5px;
            border: 1px solid #0b0b0b;
        }
        .headerRow{
            font-weight: bold;
            color: floralwhite;
        }

    </style>
<body>
<div class="bodyDiv">
    <div class="mainDiv">
        <h2>TEST PAGE</h2>
    </div>
    <div>

        <%--${message}--%>

        <table>
            <tr>
                <td width="150px" class="headerRow">
                    Name
                </td>
                <td width="150px;" class="headerRow">
                    Author
                </td>
                <td width="150px;" class="headerRow">
                    Year
                </td>
                <td width="150px;" width="15px" class="headerRow">
                    Language
                </td>
                <td width="150px;" class="headerRow">
                    Format
                </td>
            </tr>
            <c:forEach items="${booksSort}" var="bo">
                <tr>
                    <td>${bo.getName()}</td>
                    <td>${bo.getAuthor()}</td>
                    <td>${bo.getYear()}</td>
                    <td>${bo.getLanguage()}</td>
                    <td>${bo.getFormat()}</td>
                </tr>
            </c:forEach>
        </table>

        <%--<c:set var="myName" value="Alex"/>
        ${myName}

        <c:if test="${10 > 9}">
        <p>True<p>
        </c:if>
        <c:if test="${10 < 9}">
        <p>False<p>
        </c:if>

            <% if (Math.random() < 0.5) { %>
        <B>Удачного</B> Вам дня!
            <% } else { %>
        <B>Не удачного</B> Вам дня!
            <% } %>

        .<%String name = (String) request.getAttribute("message"); %>
            <%= name%>

            <%= request.getAttribute("message") %>.--%>
    </div>
</div>
</body>
</html>

</head>
<body>

</body>
</html>
