<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Store</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>

    <link href="styles/Style.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

    <script type="text/javascript">
        function showDialogTag(item) {
            $("#dialog").dialog({
                width: 400
            });
            if (item == null) {
                $("#dialogTagId").val('');
                $("#dialogTagName").val('');
                $("#dialogTagParents").val('');
            } else {
                var listIndex = $(item).attr("class");
                $.post("/tagDialog", {listIndex: listIndex, action: "getTagInfo"}, function (resp) {
                    var object = JSON.parse(resp);
                    $("#dialogTagId").val(object.tagId);
                    $("#dialogTagName").val(object.tagName);
                    $("#dialogTagParents").val(object.tagParent);
                });
            }
        }
        function saveTag() {
            var name = $("#dialogTagName").val();
            var parent = $("#dialogTagParents").val();

            $.post("/tagStore", {
                action: "saveCard",
                name: name,
                parent: parent
            });

            setTimeout('window.location.reload()', 2000)
            closeDialog();
        }
        function closeDialog() {
            $("#dialog").dialog("close");
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

    <header>
        <div class="nameDiv">
            book sorter
        </div>

        <!--Меню-->
        <ul class="css-menu-2">
            <li><a href="/s">Home</a></li>
            <li><a href="/fileStore">File Store</a></li>
            <li><a href="/fileUtility">File Utils</a></li>
            <li><a href="/bookStore">Books</a></li>
            <li><a href="/tagStore" class="selected">Tags</a></li>
        </ul>
        <!--Меню-->
    </header>

    <fieldset>
        <legend>Search</legend>
        <input type="text" class="findText" placeholder="Put out tag name" style="width: 200px">
        <input type="button" value="Search" onclick="sendPost();">
    </fieldset>
    <div>
        <table>
            <tr>
                <td width="30px" class="headerRow">

                </td>
                <td width="30px" class="headerRow">
                    ID
                </td>
                <td width="200px" class="headerRow">
                    Name
                </td>
                <td width="30px;" class="headerRow">
                    Parent
                </td>
            </tr>
            <c:forEach items="${tagsSort}" var="tag">
                <tr>
                    <td>
                        <input class="item${tag.getTagId()}" style="background-color: #6d6d6d; font-weight: normal; font-size: 10px;" type="button" value="Edit" onclick="showDialogTag(this)">
                    </td>
                    <td>${tag.getTagId()}</td>
                    <td>${tag.getTagName()}</td>
                    <td>${tag.getTagParent()}</td>
                </tr>
            </c:forEach>
        </table>
        <input type="button" class="dialogButton" style="margin-left: 20px;" value="Add New Tag" onclick="showDialogTag(null)">
    </div>
</div>


<div id="dialog" title="TAG CARD" >
    <div style="float: right; color: red; font-size:0.6em;" id="dialogWarning"></div>
    <table style="border-radius: 8px; border: none; table-layout: fixed; width: 340px;">
        <tr>
            <td style="border: none; width:10%;"><input disabled="disabled" style="width:95%;" id="dialogTagId" class="dialogInput"
                                                        type="text" title="Id" placeholder="Id"></td>
            <td style="border: none;width:80%;"><input style="width:95%;" id="dialogTagName" class="dialogInput"
                                                       type="text" title="Tag's name" placeholder="Tag's name"></td>
            <td style="border: none;width:10%; text-align: left;"><input style="width:95%;" id="dialogTagParents" class="dialogInput"
                                                       type="text" title="Parent" placeholder="Parent"></td>
        </tr>
    </table>
    <div style="align-content: center; text-align:center; margin:auto; ">
        <input type="button" class="dialogButton" value="Save" onclick="saveTag();">
        <input type="button" class="dialogButton" value="Close" onclick="closeDialog();">
    </div>

</div>

</body>


</html>
