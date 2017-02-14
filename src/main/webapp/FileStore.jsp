<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Store</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>
</head>


<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>--%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

<script type="text/javascript" src="/JS/chosen_v1.6.2/chosen.jquery.js"></script>
<script type="text/javascript" src="/JS/chosen_v1.6.2/chosen.proto.js"></script>
<link rel="stylesheet" href="/JS/chosen_v1.6.2/chosen.css">


<script type="text/javascript">
    function sendPost() {
        var findText = $(".findText").val();
        $('<form action="/fileStore" method="GET"/>')
            .append($('<input type="hidden" name="findText" value="' + findText + '">'))
            .appendTo($(document.body)) //it has to be added somewhere into the <body>
            .submit();
    }
    function showDialogCard(item) {
        $("#dialog").dialog({
            width: 800,
            overflow:"visible"
        });
        var listIndex = $(item).attr("class");
        $.post("/fileDialog", {listIndex: listIndex}, function (resp) {
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
    }

    function openFile(item) {
        var listIndex = $(item).attr("class");
        $.post("/fileStore", {listIndex: listIndex, action: "openFile"});
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

        $.post("/fileDialog", {
            action: "saveCard",
            name: name,
            author: author,
            year: year,
            format: format,
            path: path,
            type: type,
            description: description,
            language: language
        });

        setTimeout('window.location.reload()', 2000)
        closeDialog();
    }
    function closeDialog() {
        $("#dialog").dialog("close");
    }

    $(document).ready(function () {
        $('#tagSelector').chosen({
            disable_search_threshold: 10,
            no_results_text: "Oops, nothing found!",
            width: "98%"
        });
        $("#dialog").dialog("close");
    });
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
    #tagSelector_chosen{
        padding-left:10px;
    }
    .chosen-choices {

    }
    #dialog {
        overflow: visible;
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
        <table style="table-layout: fixed;">
            <col width="700px" valign="top">
            <col width="70px" valign="top">
            <col width="55px" valign="top">
            <col width="55px" valign="top">
            <tr>
                <td class="headerRow">
                    File Names
                </td>
                <td class="headerRow">
                    Book Id
                </td>
                <td class="headerRow" colspan="2">
                    Action
                </td>
            </tr>
            <c:forEach items="${fileTable}" var="file">
                <tr>
                    <td>${file.getFileName()}</td>

                    <c:if test="${file.getBaseId() == '000000'}">
                        <td>-//-</td>
                        <td><input type="button" value="Create Card" class="item<%=counter%>"
                                   onclick="showDialogCard(this);" style="width:100%; background-color: #ffa718;"></td>
                    </c:if>
                    <c:if test="${file.getBaseId() != '000000'}">
                        <td>${file.getBaseId()}</td>
                        <td><input type="button" value="Edit Card" class="item<%=counter%>"
                                   onclick="showDialogCard(this);" style="width:100%; background-color: #baedba;"></td>
                    </c:if>
                    <td><input type="button" value="Open File" class="item<%=counter%>"
                               onclick="openFile(this);" style="width:100%; background-color: #1cb7ff;"></td>
                    <% counter++; %>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div id="dialog" style="display: none;" title="BOOK CARD">
    <div style="float: right; color: red; font-size:0.6em;" id="dialogWarning"></div>
    <table style="border-radius: 8px; border: none;">
        <tr>
            <td colspan="2" style="border: none;"><input style="width:727px;" id="dialogFileName" class="dialogInput"
                                                         type="text" title="Book's name" placeholder="Book's name"></td>
        </tr>
        <tr>
            <td colspan="2" style="border: none;"><input disabled="true" style="width:727px;" id="dialogFilePath"
                                                         class="dialogInput"
                                                         type="text" title="File Path" placeholder="File Path"></td>
        </tr>
        <tr>
            <td style="border: none;"><input id="dialogFileAuthor" class="dialogInput" type="text" title="Author"
                                             placeholder="Author"></td>
            <td style="border: none;"><input id="dialogFileYear" class="dialogInput" type="text" title="Year"
                                             placeholder="Year"></td>
        </tr>
        <tr>
            <td style="border: none;">
                <select name='language' class="dialogInput" title="Language" id="dialogFileLanguage">
                    <c:forEach items="${bookLanguage}" var="language">
                        <option value="${language}">${language}</option>
                    </c:forEach>
                </select>
            </td>
            <td style="border: none;"><input id="dialogFileFormat" class="dialogInput" type="text" title="Format"
                                             placeholder="Format"></td>
        </tr>
        <tr>
            <td style="border: none;">
                <select name='type' class="dialogInput" title="Type" id="dialogFileType">
                    <c:forEach items="${bookTypes}" var="type">
                        <option value="${type}">${type}</option>
                    </c:forEach>
                </select>
            </td>
            <td style="border: none;"><input id="dialogFileDescription" class="dialogInput" type="text"
                                             title="Description" placeholder="Description"></td>
        </tr>
        <tr>
            <td colspan="2" style="border: none;">
                <select multiple="multiple" name='type' id="tagSelector" class="dialogInput" style="width: 95%;"
                        title="Type" id="dialogFileTags" data-placeholder="Select the tags">
                    <c:forEach items="${tags}" var="tag">
                        <option value="${tag.key}">${tag.value}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <div style="align-content: center; text-align:center; margin:auto; ">
        <input type="button" class="dialogButton" value="Save" onclick="saveCard();">
        <input type="button" class="dialogButton" value="Close" onclick="closeDialog();">
    </div>

</div>

</body>
</html>
