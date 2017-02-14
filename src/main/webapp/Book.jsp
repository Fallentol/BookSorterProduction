<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Store</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>
</head>

<link href="styles/Style.css" rel="stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript">
    function sendPost() {
        var findText = $(".findText").val();
        $('<form action="/fileStore" method="POST"/>')
            .append($('<input type="hidden" name="findText" value="' + findText + '">'))
            .appendTo($(document.body)) //it has to be added somewhere into the <body>
            .submit();
    }
</script>
</head>

<body>
<div class="bodyDiv">
    <header>
        <div class="nameDiv">
            book store
        </div>

        <!--Меню-->
        <ul class="css-menu-2">
            <li><a href="/s">Home</a></li>
            <li><a href="/fileStore">File Store</a></li>
            <li><a href="/fileUtility">File Utils</a></li>
            <li><a href="/bookStore" class="selected">Books</a></li>
            <li><a href="/tagStore">Tags</a></li>
        </ul>
        <!--/Меню-->
    </header>
    <fieldset>
        <legend>Search</legend>
        <input type="text" class="findText" placeholder="Put out file name" style="width: 200px">
        <input type="button" value="Search" onclick="sendPost();">
    </fieldset>
    <div>
        <table style="table-layout: fixed; width: 98%;">
            <tr>
                <td width="3%" class="headerRow">
                    ID
                </td>
                <td width="29%" class="headerRow">
                    Name
                </td>
                <td width="29%" class="headerRow">
                    Author
                </td>
                <td width="3%" class="headerRow">
                    Year
                </td>
                <td width="4%" class="headerRow">
                    Type
                </td>
                <td width="4%" class="headerRow">
                    Format
                </td>
                <td width="4%" width="15px" class="headerRow">
                    Lang
                </td>
                <td class="headerRow">
                    Description
                </td>
            </tr>
            <c:forEach items="${booksSort}" var="bo">
                <tr>
                    <td>${bo.getId()}</td>
                    <td>${bo.getName()}</td>
                    <td>${bo.getAuthor()}</td>
                    <td>${bo.getYear()}</td>
                    <td>${bo.getType()}</td>
                    <td>${bo.getFormat()}</td>
                    <td>${bo.getLanguage()}</td>
                    <td>${bo.getDescription()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
