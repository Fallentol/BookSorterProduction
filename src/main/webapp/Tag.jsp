<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Store</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>
</head>

<link href="Style.css" rel="stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>

<body>
<div class="bodyDiv">
    <div class="mainDiv">
        <h2>Tag Store</h2>
    </div>
    <a href="/s" >Back to start page</a>
    <fieldset>
        <legend>Search</legend>
        <input type="text" class="findText" placeholder="Put out file name" style="width: 200px">
        <input type="button" value="Search" onclick="sendPost();">
    </fieldset>
    <div>
        <table>
            <tr>
                <td width="150px" class="headerRow">
                    ID
                </td>
                <td width="150px" class="headerRow">
                    Name
                </td>
                <td width="150px;" class="headerRow">
                    Parent
                </td>
            </tr>
            <c:forEach items="${tagsSort}" var="bo">
                <tr>
                    <td>${bo.getId()}</td>
                    <td>${bo.getName()}</td>
                    <td>${bo.getParent()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
