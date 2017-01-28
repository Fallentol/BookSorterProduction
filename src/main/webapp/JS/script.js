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
        $.post("/CreateProfileServlet", {userName: name, userPass: pass, baseName: base, filePath: filePath}, function (createUser) {
            $("#saveResult").text(result);
            /*if (result == 'All parameters are correct') {
                $("#saveResult").css("color", "green");
                $('.not-active').unbind('click', false);
                $(".not-active").css('opacity', '1');
            } else {
                $("#saveResult").css("color", "red");
            }*/
        });
    });

    $("#checkUser").click(function () {
        var fail = false;
        var base = selectForm.baseNameS.value;
        var name = selectForm.user_idS.value;
        var pass = selectForm.userPassS.value;
        if (pass == "")
            fail = "Вы не ввели пароль!";
        if (fail)
            alert(fail);
        $.post("/ProfileDialogServlet", {baseName: base, userName: name, userPass: pass}, function (checkUser) {

        });
    });

    $("#useProfile").click(function () {
        var base = $("baseNameS").val();
        var name = $("userNameS").val();
        var pass = $("userPassS").val();
        var filePath = $("profPassS").val();
        $.post("/ProfileDialogServlet", {baseName: base, userName: name, userPass: pass, profPath: filePath}, function (useProfile) {

        });
    });

})(jQuery); // Используем немедленно вызываемую анонимную функцию

function selectBase() {
    var baseName = $("#dialogBaseName").val();
    $.post("/ProfileDialogServlet", {baseName: baseName}, function (selectBase) {
    });
}

function selectUser() {
    var userName = $("#dialogUserName").val();
    $.post("/ProfileDialogServlet", {userName: userName}, function (selectUser) {
    });
}

function checkInfo() {
    var base = $("#dialogBaseName").val();
    var user = $("#dialogUserName").val();
    var pass = $("#userPass_S").val();
    $.post("/ProfileDialogServlet", {baseName: base, userName: user, userPass: pass}, function (checkInfo) {

    });
}

