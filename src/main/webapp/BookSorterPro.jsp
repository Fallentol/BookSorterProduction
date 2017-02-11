<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Sorter Pro</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>
    <!-- Подключаем таблицу стилей -->
    <link href="styles/Style.css" rel="stylesheet" type="text/css">


    <head>
        <title>BookSorterPro</title>
    </head>

<body>
<div class="bodyDiv">
    <div class="nameDiv">
        book sorter
    </div>
    <div class="mainDiv">
        <div class="btn">
            <a href="/fileStore">File Store</a>
        </div>
        <div class="btn">
            <a href="/fileUtility">File Utils</a>
        </div>
        <div class="btn">
            <a href="/bookStore">Books</a>
        </div>
        <div class="btn">
            <a href="/tagStore">Tags</a>
        </div>
    </div>
    <div class="form">

        <div class="submitForm" style="margin: 50px; background-color: #fefcea; padding: 30px;">
            <form action="/fileStore" method="POST">
                <input type="text" placeholder="User Name" name="userName" id="userName" class="authInput"></br>
                <input type="text" placeholder="User Password" name="userPass" id="userPass" class="authInput"></br>
                <input type="text" placeholder="SQL Base" name="baseName" id="baseName" class="authInput"></br>
                <input type="text" placeholder="Folder path (E:\LIBRARY\Техническая литература\)" name="filePath"
                       id="filePath" class="authInput"></br>
                <input type="button" id="sub" value="Submit info">
            </form>
            <div style="font-size: 0.7em;" id="saveResult"></div>
        </div>

        <!--Выбор профиля/добавление новой рабочей папки-->
        <div class="selectorProfile" style="margin: 50px; background-color: #fefcea; padding: 30px;">
            <form name="form" id="formSelect">
                База данных:</br>
                <select name="dialogBaseName" id="dialogBaseName" class="StyleSelectBox">
                    <c:forEach items="${s_baseName}" var="base">
                        <option value="${base}">${base}</option>
                    </c:forEach>
                </select>
                <td></td>
                </br>
                Пользователь:</br>
                <select name="dialogUserName" id="dialogUserName" class="StyleSelectBox">
                    <c:forEach items="${s_userName}" var="user">
                        <option value="${user}">${user}</option>
                    </c:forEach>
                </select>
                <td></td>
                </br>
                <input type="text" placeholder="User Password" name="userPass_S" id="userPass_S" class="authInput"></br>
                <input type="button" onclick="checkInfo();" id="checkUser" value="Check Info"></br></br>
                Рабочая папка:<br/>
                <select name="dialogProfPath" id="dialogProfPath" disabled="disabled" class="StyleSelectBox">
                    <c:forEach items="${s_profPath}" var="path">
                        <option value="${path}">${path}</option>
                    </c:forEach>
                </select>
                <td></td>
                </br>
                <input type="button" value="Use Profile">
            </form>
            <div style="font-size: 0.7em;" id="selectResult"></div>
        </div>
        <!--Выбор профиля/добавление новой рабочей папки-->

    </div>
</div>

<!-- Подключаем библиотеку jQuery -->
<script src="//libs.raltek.ru/libs/jquery/1.8.3/js/jquery-1.8.3.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- Подключаю управляющий скрипт -->
<script src="JS/script.js"></script>

</body>
</head>
</html>
