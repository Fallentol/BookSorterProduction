<%--
  Created by IntelliJ IDEA.
  User: New
  Date: 12/17/2016
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Sorter Pro</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>
    <html>
    <head>
        <title>BookSorterPro</title>
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

        .headerRow {
            font-weight: bold;
            color: floralwhite;
        }

        .btn {
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
        /*.not-active {
            pointer-events: none;
            cursor: default;
        }*/

    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script>
        $(document).ready(function () {

            $('.not-active').bind('click', false);
            $(".not-active").css('opacity', '0.4');


            $("#sub").click(function () {
                var name = $("#userName").val();
                var pass = $("#userPass").val();
                var base = $("#baseName").val();
                $.post("/AuthorizationServlet", {userName: name, userPass: pass, baseName: base}, function (result) {
                    $("#saveResult").text(result);
                    if (result == 'All parameters are correct') {
                        $("#saveResult").css("color", "green");
                        $('.not-active').unbind('click', false);
                        $(".not-active").css('opacity', '1');
                    } else {
                        $("#saveResult").css("color", "red");
                    }
                });
            });
        });

    </script>
<body>
<div class="bodyDiv">
    <div class="mainDiv">
        <h2>BOOK SORTER</h2>
    </div>
    <div class="mainDiv">
        <div class="btn">
            <a href="/fileStore">File Store</a>
        </div>
        <div class="btn">
            <a href="/bookStore" class="not-active" >Books</a>
        </div>
        <div class="btn">
            <a href="/tagStore" class="not-active">Tags</a>
        </div>
    </div>
    <div>

        <%--${message}--%>

        <div style="margin: 50px; background-color: #fefcea; padding: 30px;">
            <form action="/fileStore" method="POST">
                <input type="text" placeholder="User Name" name="userName" id="userName">
                <input type="text" placeholder="User Password" name="userPass" id="userPass">
                <input type="text" placeholder="SQL Base" name="baseName" id="baseName">
                <input type="button" id="sub" value="Remember">
            </form>
            <div style="font-size: 0.7em;" id="saveResult"></div>
        </div>

        <%--<c:set var="myName" value="Alex"/>
        ${myName}

        <c:if test="${10 > 9}">
        <p>True<p>
        </c:if>
        <c:if test="${10 < 9}">
        <p>False<p>
        </c:if>

            <% if (Math.random() < 0.5) { %>
        <B>Удачного</B> Вам дня!
            <% } else { %>
        <B>Не удачного</B> Вам дня!
            <% } %>

        .<%String name = (String) request.getAttribute("message"); %>
            <%= name%>

            <%= request.getAttribute("message") %>.--%>
    </div>
</div>
</body>
</html>

</head>
<body>

</body>
</html>
