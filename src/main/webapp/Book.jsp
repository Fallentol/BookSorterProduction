<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Store</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>
</head>

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
        .btn{
            display: inline-block;
            background-color: cornflowerblue;
            color: #525252;
            font-weight: bold;
            padding: 15px;
            border: 2px solid #525252;
        }
        .btn:hover {
            background-color: ivory;
            color: green;
        }
        .btn:active {
            border: 1px solid red;
        }
    </style>

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
