document.ready(function () {

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

    $("#cr").click(function () {
        var name = $("#userName").val();
        var pass = $("#userPass").val();
        var base = $("#baseName").val();
        var filePath = $("#filePath").val();
        $.post("/CreateProfileServlet", {userName: name, userPass: pass, baseName: base, filePath: filePath}, function (result) {
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

    $("#useProfile").click(function () {
        var fail = false;
        var pass = selectForm.userPassS.value;
        if (pass == "")
            fail = "Вы не ввели пароль!";
        if (fail)
            alert(fail);

    });

})(jQuery); // Используем немедленно вызываемую анонимную функцию
