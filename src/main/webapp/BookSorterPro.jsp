<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Sorter Pro</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>

    <link rel="stylesheet" href="libs/font-awesome-4.2.0/css/font-awesome.min.css"/>
    <!-- Подключаем таблицу стилей -->
    <link href="styles/Style.css" rel="stylesheet" type="text/css">


    <head>
        <title>BookSorterPro</title>
    </head>

<body>

<div class="bodyDiv">
    <header>
        <div class="nameDiv">
            book sorter
        </div>

        <!--Меню-->
        <div class="mainMenu">
            <ul class="css-menu-2">
                <li><a href="/s" class="selected">Home</a></li>
                <li><a href="/fileStore">File Store</a></li>
                <li><a href="/fileUtility">File Utils</a></li>
                <li><a href="/bookStore">Books</a></li>
                <li><a href="/tagStore">Tags</a></li>
            </ul>
            <div class="search_header"><i class="fa fa-search"></i></div>
        </div>
        <!--END Меню-->
    </header>

    <!--Поиск-->
    <div class="close_search hidden-sm hidden-xs">×</div>
    <div class="container">
        <div class="col-lg-12">
            <div class="row">
                <div class="search_popup hidden-sm hidden-xs">
                    <form action="" method="post" class="search_vspl">
                        <input type="search" name="" placeholder="Поиск..." class="input_vspl" id="input_vspl"/>
                        <button type="submit" class="searchSubmit" id="searchSubmit" name="submit" value="Search"><i
                                class="fa fa-search"></i></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="opacity hidden-sm hidden-xs"></div>
    <!--END Поиск-->

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

            <form name="form" id="formSelect" onsubmit="checkInfo1();return false"
                  style="border: 1px #888899 solid; padding: 5px;">
                <div class="preForm">
                    <div class="selectorName">
                        <p>Data base:</p>
                    </div>
                    <select name="dialogBaseName" id="dialogBaseName" class="StyleSelectBox">
                        <c:forEach items="${s_baseName}" var="base">
                            <option value="${base}">${base}</option>
                        </c:forEach>
                    </select></br>

                    <div class="selectorName">
                        <p>User:</p>
                    </div>
                    <select name="dialogUserName" id="dialogUserName" class="StyleSelectBox">
                        <c:forEach items="${s_userName}" var="user">
                            <option value="${user}">${user}</option>
                        </c:forEach>
                    </select></br>

                    <div class="inputPass">
                        <p>Password:</p>
                    </div>
                    <input type="text" placeholder="User Password" name="dialogUserPass" id="dialogUserPass"
                           class="passInput">
                    <input type="submit" onclick="checkInfo1()" value="Log in"/>
                    <div class="selectResult" id="selectResult"></div>

                    <div class="selectorName">
                        <p>Selected folder:</p>
                    </div>
                    <select name="dialogProfPath" id="dialogProfPath" disabled="disabled" class="StyleSelectBox">
                        <c:forEach items="${s_profPath}" var="path">
                            <option value="${path}">${path}</option>
                        </c:forEach>
                    </select></br>
                    <input type="button" onclick="useProfile1()" disabled="disabled" id="useProfileButton"
                           value="Use Profile">
                </div>
            </form>
            <!--END Выбор профиля/добавление новой рабочей папки-->

            <div class="kvantifikator" style="border: 1px #888899 solid; padding: 5px;">
                <p>Проверяем работу квантификатора</p>
                <div onsubmit="kvantifikuy();return false">
                    <input type="text" id="kvantifikator_val" onkeyup="return proverka(this);"
                           onchange="return proverka(this);" placeholder="Ведите любое число">
                    <input type="submit" onclick="kvantifikuy()" value="Показать в таблице"><br><br>
                    <p>Введеное число стало:</p>
                    <input type="text" id="kvantifikator_num" placeholder="Число стало таким">
                </div>
            </div>

        </div>
    </div>
</div>

<div class="autorizationInfo">
    <ul>
        <li><input id="baseNameInfo" placeholder="Data base Info" disabled="disabled"></li>
        <li><input id="userNameInfo" placeholder="User Info" disabled="disabled"></li>
        <li><input id="profPathInfo" placeholder="Selected folder Info" disabled="disabled"></li>
        <li class="leftLi">
            <button id="clearUserInfoButton" disabled="disabled" onclick="clearUserInfo1()">Log out</button>
        </li>
    </ul>
</div>

<!--Кнопка наверх-->
<div class="up_button"><i class="fa fa-chevron-up" aria-hidden="true"></i></div>
<!--END Кнопка наверх-->

<!-- Подключаю библиотеку jQuery -->
<script src="//libs.raltek.ru/libs/jquery/1.8.3/js/jquery-1.8.3.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- Подключаю управляющий скрипт -->
<script src="JS/script.js"></script>

</body>
</head>
</html>
