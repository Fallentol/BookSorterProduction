<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 20.02.2017
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page isELIgnored="false" %>

    <link rel="stylesheet" href="libs/font-awesome-4.2.0/css/font-awesome.min.css"/>
    <!-- Подключаем таблицу стилей -->
    <link href="styles/Style.css" rel="stylesheet" type="text/css">

<body>

<div class="bodyDiv">
    <header>
        <div class="nameDiv">
            search result
        </div>

        <!--Меню-->
        <div class="mainMenu">
            <ul class="css-menu-2">
                <li><a href="/s">Home</a></li>
                <li><a href="/fileStore">File Store</a></li>
                <li><a href="/fileUtility">File Utils</a></li>
                <li><a href="/bookStore">Books</a></li>
                <li><a href="/tagStore">Tags</a></li>
                <li><a href="/searchPage" class="selected">Search Page</a></li>
            </ul>

        </div>
        <!--END Меню-->
    </header>

    <fieldset style="margin-bottom: 20px; color: #eee;">
        <legend>Search</legend>
        <input type="text" class="findText" placeholder="What are you looking for?" style="width: 94%">
        <input type="button" value="Search" id="searchSubmit">
    </fieldset>

    <!--Отчет поиска-->
    <div>
        <table style="width: 100%; margin: 0; margin-top:10px;">
            <tr>
                <td width="5%" class="headerRow">

                </td>
                <td width="" class="headerRow">
                    Book name
                </td>
                <td width="20%" class="headerRow">
                    Tag
                </td>
                <td width="20%;" class="headerRow">
                    Parent
                </td>
            </tr>
            <c:forEach items="${tagsSort}" var="tag">
                <tr>
                    <td></td>
                    <td></td>
                    <td>${tag.getTagName()}</td>
                    <td>${tag.getTagParent()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <!--END Отчет поиска-->

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
