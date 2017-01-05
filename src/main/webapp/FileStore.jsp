<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Store</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>
</head>
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
    function showDialog(item) {
        var listIndex = $(item).attr("class");
        $.post("/fileDialog", {listIndex: listIndex}, function (resp) {
            $(".dialogDiv").fadeIn();
            var object = JSON.parse(resp);
            $("#dialogFileName").val(object.fileName);
            $("#dialogFileAuthor").val(object.fileAuthor);
            $("#dialogFileYear").val(object.fileYear);
            $("#dialogFileFormat").val(object.fileFormat);
        });
    }
    function closeDialog() {
        $('.dialogDiv').fadeOut();
    }
</script>
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

    .headerRow {
        font-weight: bold;
        color: floralwhite;
    }

</style>
<link href="styles/dialogStyle.css" rel="stylesheet" type="text/css">
<body>

<div class="bodyDiv">
    <div class="mainDiv">
        <h2>File Store</h2>
    </div>
    <a href="/s">Back to start page</a>
    <fieldset>
        <legend>Search</legend>
        <input type="text" class="findText" placeholder="Put out file name" style="width: 200px">
        <input type="button" value="Search" onclick="sendPost();">
    </fieldset>
    <div>
        <% int counter = 0; %>
        <table>
            <tr>
                <td width="250px" class="headerRow">
                    File Names
                </td>
                <td width="100px" class="headerRow">
                    Action
                </td>
            </tr>
            <c:forEach items="${fileTable}" var="file">
                <tr>
                    <td>${file}</td>
                    <td><input type="button" value="Create Card" class="item<%=counter%>" onclick="showDialog(this);"></td>
                    <% counter++; %>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="dialogDiv">
    <h2 style="text-shadow: 2px 2px 10px #303030; font-weight: bold; color: #303030;">DIALOG PANEL</h2>
    <table>
        <tr>
            <td><input id="dialogFileName" class="dialogInput" type="text" placeholder="Book's name"></td>
            <td><input id="dialogFileAuthor" class="dialogInput" type="text" placeholder="Author"></td>
        </tr>
        <tr>
            <td><input id="dialogFileYear" class="dialogInput" type="text" placeholder="Year"></td>
            <td><input id="dialogFileFormat" class="dialogInput" type="text" placeholder="Format"></td>
        </tr>
    </table>
    <div>
        <input type="button" value="Save">
        <input type="button" value="Close" onclick="closeDialog();">
    </div>

</div>

</body>
</html>
