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
        <legend>Search Properties</legend>
        <div style="float: left;">
            <div style="">
                <input type="text" placeholder="Enter the book name" id="bookName"
                       style="width: 400px; height: 27px; margin: 10px 0px 10px 0px;">
            </div>
            <br/>
            <div style="padding-bottom: 20px;">
                <select name='type' id="tagSelector" multiple="multiple"
                        title="Type" data-placeholder="Select the tags">
                    <c:forEach items="${tags}" var="tag">
                        <option value="${tag.key}">${tag.value}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div style="float: right;">
            <img src="images/SearchButton.png" id="searchBut" width="100" height="100" alt="START SEARCH"
                 title="Start to search">
        </div>

    </fieldset>

    <!--Отчет поиска-->
    <div>
        <table style="width: 100%; margin: 0; margin-top:10px;">
            <tr>
                <td width="20%" class="headerRow">
                    Book name
                </td>
                <td width="20%" class="headerRow">
                    Author
                </td>
                <td width="20%" class="headerRow">
                    Year
                </td>
                <td width="20%;" class="headerRow">

                </td>
            </tr>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.getName()}</td>
                    <td>${book.getAuthor()}</td>
                    <td>${book.getYear()}</td>
                    <td></td>
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

<script type="text/javascript" src="/JS/chosen_v1.6.2/chosen.jquery.js"></script>
<script type="text/javascript" src="/JS/chosen_v1.6.2/chosen.proto.js"></script>
<link rel="stylesheet" href="/JS/chosen_v1.6.2/chosen.css">

</body>

<script>
    $(document).ready(function () {
        $('#tagSelector').chosen({
            disable_search_threshold: 5,
            no_results_text: "Oops, nothing found!",
            width: "400px"
        });
        $("#searchBut").click(function () {
            console.log('Click');
            searchBooks();
            $("#searchBut").animate({
                opacity: 0.2,
                width: '1200px',
                height: '1200px',
                left: "100px",
                top: '0px',
                position: 'absolute'
            }, 1500);
        });
    });

    function searchBooks() {
        var name = $("#bookName").val();
        var tags = $("#tagSelector").val();
        name = name == null || name == '' ? 'none' : name;
        tags = tags == null || tags == '' ? 'none' : tags;
        $.post("/searchPage", {
            action: "searchBook",
            name: name,
            tags: (tags.toString())
        }, function (resp) {
            console.log(resp);
        });

        setTimeout('window.location.reload()', 1000);
    }

</script>

<style>
    #searchBut:hover {
        width: 105px;
        height: 105px;
    }
</style>

</head>

</html>
