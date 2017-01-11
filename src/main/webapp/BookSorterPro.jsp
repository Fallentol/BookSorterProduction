<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<?php
// Подключаем файл для соединения с СУБД MySQL
require_once( '/PHP/database.php' );
// Подключаем файл, в котором будем объявлять пользовательские функции
require_once( '/PHP/functions.php' );
?>

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

        <script>
            $(document).ready(function () {

                $('.not-active').bind('click', false);
                $(".not-active").css('opacity', '0.4');

                $("#sub").click(function () {
                    var name = $("#userName").val();
                    var pass = $("#userPass").val();
                    var base = $("#baseName").val();
                    var filePath = $("#filePath").val();
                    $.post("/AuthorizationServlet", {userName: name, userPass: pass, baseName: base, filePath: filePath}, function (result) {
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
                        <a href="/fileUtility">File Utils</a>
                    </div>
                    <div class="btn">
                        <a href="/bookStore">Books</a><!--class="not-active"-->
                    </div>
                    <div class="btn">
                        <a href="/tagStore">Tags</a><!--class="not-active"-->
                    </div>
                </div>
                <div>
                    <div style="margin: 50px; background-color: #fefcea; padding: 30px;">

                        <form action="/fileStore" method="POST">
                            <input type="text" placeholder="User Name" name="userName" id="userName" class="authInput"></br>
                            <input type="text" placeholder="User Password" name="userPass" id="userPass" class="authInput"></br>
                            <input type="text" placeholder="SQL Base" name="baseName" id="baseName" class="authInput"></br>
                            <input type="text" placeholder="Folder path (E:\LIBRARY\Техническая литература\)" name="filePath" id="filePath" class="authInput"></br>
                            <input type="button" id="sub" value="Submit info">
                            <input type="button" id="cr" value="Create Profile">
                        </form>
                        <div style="font-size: 0.7em;" id="saveResult"></div>

                        <form name="userProfile" id="userProfile" >
                        <!-- Контейнер для поля выбора пользователей -->
                        <div class="row">
                            <!-- Метка поля пользователей -->
                            <label for="userName">Выбор пользователя:</label>
                            <!-- Раскрывающийся список пользователей -->
                            <select id="selectProfile">
                                <option value="0">Выберите из списка</option>
                                <?php
                                $userNames = getUsers();

                                foreach ( $userNames as $userName ) {
                                print '<option value="' . $userName[ 'id' ] . '">' . $userName[ 'userName' ] . '</option>';
                                }
                                ?>
                            </select>
                        </div>
                        <!-- Контейнер для поля выбора Path пользователя выбранного пользователя -->
                        <div class="row">
                            <!-- Метка поля выбора Path пользователя -->
                            <label for="userPath">Рабочая папка:</label>
                            <!-- Раскрывающийся список выбора Path пользователя выбранного пользователя -->
                            <!-- Изначально список пуст и неактивен -->
                            <!-- Данные в нем появятся полсле выбора пользователя -->
                            <select id="userPath" disabled >
                                <option value="0">Выберите из списка</option>
                            </select>
                        </div>
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
