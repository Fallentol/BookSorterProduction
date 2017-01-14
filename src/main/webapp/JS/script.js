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

    $("#checkUser").click(function () {
        var fail = false;
        var base = selectForm.baseNameS.value;
        var name = selectForm.user_idS.value;
        var pass = selectForm.userPassS.value;
        var filePath = null;
        if (pass == "")
            fail = "Вы не ввели пароль!";
        if (fail)
            alert(fail);
        $.post("/ProfileDialogServlet", {baseName: base, userName: name, userPass: pass}, function (result) {


        });
    });


    $("#useProfile").click(function () {
        var fail = false;
        var base = selectForm.baseNameS.value;
        var name = selectForm.user_idS.value;
        var pass = selectForm.userPassS.value;
        var filePath = null;
        if (pass == "")
            fail = "Вы не ввели пароль!";
        if (fail)
            alert(fail);
        $.post("/ProfileDialogServlet", {baseName: base, userName: name, userPass: pass}, function (result) {
            $("#profile_idS").text(result);

        });
    });

})(jQuery); // Используем немедленно вызываемую анонимную функцию
