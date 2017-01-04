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
<script type="text/javascript">
    function sendPost() {
        var findText = $(".findText").val();
        console.log('findText='+findText);
        $('<form action="/fileStore" method="POST"/>')
            .append($('<input type="hidden" name="findText" value="' + findText + '">'))
            .appendTo($(document.body)) //it has to be added somewhere into the <body>
            .submit();
    }
    function test() {
        var findText = $(".findText").val();
        console.log('findText='+findText);
    }
</script>
</head>

<body>
<div class="bodyDiv">
    <div class="mainDiv">
        <h2>Book Store</h2>
    </div>
    <a href="/s" >Back to start page</a>
    <fieldset>
        <legend>Search</legend>
        <input type="text" class="findText" placeholder="Put out file name" style="width: 200px">
        <input type="button" value="Search" onclick="sendPost();">
    </fieldset>
    <div>
        <%--<table>
            <tr>
                <td width="250px" class="headerRow">
                    File Names
                </td>
            </tr>
            <c:forEach items="${fileTable}" var="file">
                <tr>
                    <td>${file}</td>
                </tr>
            </c:forEach>
        </table>--%>
        <table>
            <tr>
                <td width="150px" class="headerRow">
                    ID
                </td>
                <td width="150px" class="headerRow">
                    Name
                </td>
                <td width="150px;" class="headerRow">
                    Author
                </td>
                <td width="150px;" width="15px" class="headerRow">
                    Language
                </td>
                <td width="150px" class="headerRow">
                    Type
                </td>
                <td width="150px;" class="headerRow">
                    Format
                </td>
                <td width="150px" class="headerRow">
                    Path
                </td>
                <td width="150px" class="headerRow">
                    Description
                </td>
                <td width="150px;" class="headerRow">
                    Year
                </td>
                <td width="150px" class="headerRow">
                    Size
                </td>
            </tr>
            <c:forEach items="${booksSort}" var="bo">
                <tr>
                    <td>${bo.getId()}</td>
                    <td>${bo.getName()}</td>
                    <td>${bo.getAuthor()}</td>
                    <td>${bo.getLanguage()}</td>
                    <td>${bo.getType()}</td>
                    <td>${bo.getFormat()}</td>
                    <td>${bo.getDescription()}</td>
                    <td>${bo.getYear()}</td>
                    <td>${bo.getSize()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
