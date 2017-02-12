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

checkInfo.onclick = function () {
    var base = $("#dialogBaseName").val();
    var user = $("#dialogUserName").val();
    var pass = $("#dialogUserPass").val();
    $.post("/profileDialog", {baseName: base, userName: user, userPass: pass, action: "checkInfoAction"}, function (result) {
        $("#selectResult").text(result);
        if (result == 'All parameters are correct') {
            $("#selectResult").css("color", "green");
            $('.not-active').unbind('click', false);
            $(".not-active").css('opacity', '1');
        } else {
            $("#selectResult").css("color", "red");
        }
    });
};

function useProfile() {
    var base = $("#dialogBaseName").val();
    var user = $("#dialogUserName").val();
    var pass = $("#dialogUserPass").val();
    var path = $("#dialogProfPath").val();
    $.post("/profileDialog", {baseName: base, userName: user, userPass: pass, action: "useProfileAction"}, function (result) {

    });
};

