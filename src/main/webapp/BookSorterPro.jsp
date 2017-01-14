<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Book Sorter Pro</title>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ page isELIgnored="false" %>
        <!-- Подключаем библиотеку jQuery -->
        <script src="//libs.raltek.ru/libs/jquery/1.8.3/js/jquery-1.8.3.js"></script>
        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!-- Подключаем таблицу стилей -->
        <link href="styles/Style.css" rel="stylesheet" type="text/css">
        <!-- Подключаем скрипты -->
        <script src="/JS/script.js"></script>

        <head>
            <title>BookSorterPro</title>
        </head>

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
                        <a href="/fileUtility">File Utils</a>
                    </div>
                    <div class="btn">
                        <a href="/bookStore">Books</a><!--class="not-active"-->
                    </div>
                    <div class="btn">
                        <a href="/tagStore">Tags</a><!--class="not-active"-->
                    </div>
                </div>
                <div class="form">

                    <div class="submitForm" style="margin: 50px; background-color: #fefcea; padding: 30px;">
                        <form action="/fileStore" method="POST">
                            <input type="text" placeholder="User Name" name="userName" id="userName" class="authInput"></br>
                            <input type="text" placeholder="User Password" name="userPass" id="userPass" class="authInput"></br>
                            <input type="text" placeholder="SQL Base" name="baseName" id="baseName" class="authInput"></br>
                            <input type="text" placeholder="Folder path (E:\LIBRARY\Техническая литература\)" name="filePath" id="filePath" class="authInput"></br>
                            <input type="button" id="sub" value="Submit info">
                            <input type="button" id="cr" value="Create Profile">
                        </form>
                        <div style="font-size: 0.7em;" id="saveResult"></div>
                    </div>

                    <div class="selectorProfile" style="margin: 50px; background-color: #fefcea; padding: 30px;">
                        <form action="#" method="POST" name="form" id="formSelect">
                            База данных:</br>
                            <select name="baseNameS" id="baseNameS" class="StyleSelectBox">
                                <option value="0">- выберите БД -</option>
                                <option value="1">BookSorterPro</option>
                            </select><td></td></br>
                            Пользователь:</br>
                            <select name="user_idS" id="user_idS" class="StyleSelectBox">
                                <option value="0">- выберите Пользователя -</option>
                                <option value="1">admin</option>
                            </select><td></td></br>
                            <input type="text" placeholder="User Password" name="userPassS" id="userPassS" class="authInput"></br>
                            <input type="button" id="check" onclick="valid(document.getElementById('formSelect'))" value="Check Info"></br></br>
                            Рабочая папка:<br />
                            <select name="profile_idS" id="profile_idS" disabled="disabled" class="StyleSelectBox">
                                <option value="0">- выберите рабочую папку -</option>
                            </select><td></td></br>
                            <input type="button" onclick="valid(document.getElementById('formSelect'))" value="Use Profile">
                        </form>
                        <div style="font-size: 0.7em;" id="selectResult"></div>

                    </div>

                </div>
            </div>
        </body>

    </head>
    <body>

    </body>
</html>
