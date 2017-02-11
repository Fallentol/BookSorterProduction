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

function checkInfo() {
    var base = $("#dialogBaseName").val();
    var user = $("#dialogUserName").val();
    var pass = $("#userPass_S").val();
    $.post("/ProfileDialog", {baseName: base, userName: user, userPass: pass}, function (checkInfo) {

    });
};

