$(document).ready(function () {

    $('.not-active').bind('click', false);
    $(".not-active").css('opacity', '0.4');

    $("#sub").click(function () {
        var name = $("#userName").val();
        var pass = $("#userPass").val();
        var base = $("#baseName").val();
        var filePath = $("#filePath").val();
        $.post("/AuthorizationServlet", {
            userName: name,
            userPass: pass,
            baseName: base,
            filePath: filePath
        }, function (result) {
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

function checkInfo1() {
    var user = $("#dialogUserName").val();
    var pass = $("#dialogUserPass").val();
    var base = $("#dialogBaseName").val();
    $.post("/AuthorizationServlet", {
            userName: user,
            userPass: pass,
            baseName: base,
        }, function (result) {
            $("#selectResult").text(result);
            if (result == 'All parameters are correct') {
                $("#selectResult").css("color", "green");
                document.getElementById("dialogProfPath").disabled = false;
                document.getElementById("useProfile1").disabled = false;
                document.getElementById("clearUserInfoButton").disabled = false;
                $.post("/profileDialog", {
                    action: checkInfoAction
                }, function (resp) {
                    var object = JSON.parse(resp);

                    counterMK = 0;
                    MK = "Item " + ++counterMK;
                    console.log(MK);

                    $("#dialogProfPath").val(object.MK);

                });
            } else {
                $("#selectResult").css("color", "red");
                document.getElementById("dialogProfPath").disabled = true;
                document.getElementById("useProfile1").disabled = true;
                document.getElementById("clearUserInfoButton").disabled = true;
            }
        }
    );
};

function useProfile1() {
    var base = $("#dialogBaseName").val();
    var user = $("#dialogUserName").val();
    var path = $("#dialogProfPath").val();
    $.post("/profileDialog", {
        baseName: base,
        userName: user,
        userPath: path,
        action: "useProfileAction"
    }, function (resp) {
        var object = JSON.parse(resp);
        $("#baseNameInfo").val(object.baseNameInfoU);
        $("#userNameInfo").val(object.userNameInfoU);
        $("#profPathInfo").val(object.profPathInfoU);

    });
};

function clearUserInfo1() {
    $.post("/userInfoCleaner",
        function (resp) {
            var object = JSON.parse(resp);
            $("#baseNameInfo").val(object.baseNameInfoC);
            $("#userNameInfo").val(object.userNameInfoC);
            $("#profPathInfo").val(object.profPathInfoC);
        }
    )
};