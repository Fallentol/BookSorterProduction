<%--
  Created by IntelliJ IDEA.
  User: New
  Date: 12/24/2016
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<c:set var="myName" value="Alex"/>
<div class="bodyDiv">
    <div class="mainDiv">
        <h2>TEST PAGE</h2>
        <h1>${myName}</h1>
    </div>
</div>
</body>
</html>
