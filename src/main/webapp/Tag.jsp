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
    function showDialogTag(item) {
        $(".dialogDiv").fadeIn();
        /*var listIndex = $(item).attr("class");
        $.post("/tagDialog", {listIndex: listIndex}, function (resp) {
            $(".dialogDiv").fadeIn();
            var object = JSON.parse(resp);
            $("#dialogFileName").val(object.fileName);
            $("#dialogFileAuthor").val(object.fileAuthor);
            $("#dialogFileYear").val(object.fileYear);
            $("#dialogFileFormat").val(object.fileFormat);
            $("#dialogFilePath").val(object.filePath);
            $("#dialogFileDescription").val(object.fileDescription);
            $("#dialogFileType").val(object.fileType);
            $("#dialogFileLanguage").val(object.fileLanguage);
            if (object.fileId != null) {
                $("#dialogTitle").text("BOOK CARD (" + object.fileId + ")");
            }
        });
         */
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
</head>

<body>
<div class="bodyDiv">
    <div class="mainDiv">
        <h2>Tag Store</h2>
    </div>
    <a href="/s" >Back to start page</a>
    <fieldset>
        <legend>Search</legend>
        <input type="text" class="findText" placeholder="Put out tag name" style="width: 200px">
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
        <input type="button" value="Add New Tag" onclick="showDialogTag(null)" >
    </div>
</div>

</body>

<div class="dialogDiv">
    <h2 id="dialogTitle" style="text-shadow: 2px 2px 10px #303030; font-weight: bold; color: #303030;">TAG CARD</h2>
    <div style="float: right; color: red; font-size:0.6em;" id="dialogWarning"></div>
    <table style="border-radius: 8px; border: none; table-layout: fixed;"  >
        <tr>
            <td style="border: none; width:10%;"><input style="width:95%;" id="dialogTagId" class="dialogInput"
                                                         type="text" title="Id" placeholder="Id"></td>
            <td style="border: none;width:45%;"><input style="width:95%;" id="dialogTagName" class="dialogInput"
                                                         type="text" title="Tag's name" placeholder="Tag's name"></td>
            <td style="border: none;width:45%;"><input style="width:95%;" id="dialogTagParents" class="dialogInput"
                                             type="text" title="Parent" placeholder="Parent"></td>
        </tr>
    </table>
    <div>
        <input type="button" class="dialogButton" value="Save" onclick="saveCard();">
        <input type="button" class="dialogButton" value="Close" onclick="closeDialog();">
    </div>

</div>

</html>
