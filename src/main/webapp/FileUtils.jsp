<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Utils</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript">
    function deleteOldIdentity() {
        showWaiter();
        $.post("/fileUtility", {
                action: "deleteOldIdentity"
            },
            function (resp) {
                console.log(resp);
                hideWaiter();
            });
    }
    function showWaiter() {
        $(".waiter").fadeIn();
    }
    function hideWaiter() {
        $(".waiter").fadeOut();
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

    .dialogButton:hover {
        background-color: #6d6d6d;
    }

    .dialogButton:active {
        background-color: #999999;
        color: #a73118;
        font-size: 1.1em;
    }
</style>

<body>

<div class="bodyDiv">
    <div class="mainDiv">
        <h2>File Utils</h2>
    </div>
    <a href="/s">Back to start page</a>
    <fieldset>
        <legend>Utility</legend>
        <input type="button" id="deleteOldIdentity" value="Delete Old File Identity" class="dialogButton"
               onclick="deleteOldIdentity();">
        <input type="button" value="Reset File Base" onclick="" class="dialogButton">
        <div class="waiter" style="display: none;">
            <img src="animation/GCNyjJY.gif" width="150" height="150" alt="WAITER">
        </div>
    </fieldset>

</div>

</body>
</html>

