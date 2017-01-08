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
        $('<form action="/fileStore" method="GET"/>')
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
            $("#dialogFilePath").val(object.filePath);
        });
    }
    function saveCard() {
        var name = $("#dialogFileName").val();
        var author = $("#dialogFileAuthor").val();
        var year = $("#dialogFileYear").val();
        var format = $("#dialogFileFormat").val();
        var path = $("#dialogFilePath").val();
        var description = $("#dialogFileDescription").val();
        var language = $("#dialogFileLanguage").val();
        var type = $("#dialogFileType").val();
        console.log("console log path="+path);
        $.post( "/fileDialog", {
            action: "saveCard",
            name:"name",
            author: "author",
            year: "year",
            format: "format",
            path: "path",
            type: "type",
            description:"description",
            language:"language"},
            function (resp) {
                console.log(resp);
                $("#dialogWarning").text(resp);
        } );
    }
    function closeDialog() {
        $('.dialogDiv').fadeOut();
    }
</script>

<link href="styles/dialogStyle.css" rel="stylesheet" type="text/css">
<link href="styles/Style.css" rel="stylesheet" type="text/css">

<style>
    .dialogButton {
        display: inline-block;
        padding: 5px;
        margin-bottom: 8px;
        font-size: 1.1em;
        cursor: pointer;
        text-align: center;
        text-decoration: none;
        outline: none;
        color: #fff;
        background-color: #525252;
        border: none;
        border-radius: 10px;
        box-shadow: 0 3px #999;
    }
</style>

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
                <td width="80px" class="headerRow">
                    Book Id
                </td>
                <td width="60px" class="headerRow">
                    Action
                </td>
            </tr>
            <c:forEach items="${fileTable}" var="file">
                <tr>
                    <td>${file.getFileName()}</td>
                    <td>${file.getBaseId()}</td>
                    <td><input type="button" value="Create Card" class="item<%=counter%>" onclick="showDialog(this);"></td>
                    <% counter++; %>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="dialogDiv">
    <h2 style="text-shadow: 2px 2px 10px #303030; font-weight: bold; color: #303030;">BOOK CARD (${bookId})</h2>
    <div style="float: right; color: red; font-size:0.6em;" id="dialogWarning"></div>
    <table style="border-radius: 8px; border: none;">
        <tr>
            <td colspan="2" style="border: none;"><input  style="width:727px;" id="dialogFileName" class="dialogInput" type="text" title="Book's name" placeholder="Book's name"></td>
        </tr>
        <tr>
            <td colspan="2" style="border: none;"><input  style="width:727px;" id="dialogFilePath" class="dialogInput" type="text" title="File Path" placeholder="File Path"></td>
        </tr>
        <tr>
            <td style="border: none;"><input id="dialogFileAuthor" class="dialogInput" type="text" title="Author" placeholder="Author"></td>
            <td style="border: none;"><input id="dialogFileYear" class="dialogInput" type="text" title="Year" placeholder="Year"></td>
        </tr>
        <tr>
            <td style="border: none;">
                <select name='language' class="dialogInput" title="Language">
                    <c:forEach items="${bookLanguage}" var="language">
                        <option value="${language}">${language}</option>
                    </c:forEach>
                </select>
            </td>
            <td style="border: none;"><input id="dialogFileFormat" class="dialogInput" type="text" title="Format" placeholder="Format"></td>
        </tr>
        <tr>
            <td style="border: none;">
                <select name='type' class="dialogInput" title="Type">
                    <c:forEach items="${bookTypes}" var="type">
                            <option value="${type}">${type}</option>
                    </c:forEach>
                </select>
            </td>
            <td style="border: none;"><input id="dialogFileDescription" class="dialogInput" type="text" title="Description" placeholder="Description"></td>
        </tr>
    </table>
    <div>
        <input type="button" class="dialogButton" value="Save" onclick="saveCard();">
        <input type="button" class="dialogButton" value="Close" onclick="closeDialog();">
    </div>

</div>

</body>
</html>
