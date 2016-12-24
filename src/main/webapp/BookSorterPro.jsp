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
    <title>Title</title><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    </style>
<body>
<div class="bodyDiv">
    <div class="mainDiv">
        <h2>TEST PAGE</h2>
    </div>
    <div>
        <c:set var="myName" value="Alex"/>
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

            <%= request.getAttribute("message") %>.
    </div>
</div>
</body>
</html>

</head>
<body>

</body>
</html>
