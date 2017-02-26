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
                document.getElementById("useProfileButton").disabled = false;
                document.getElementById("clearUserInfoButton").disabled = false;
                $.post("/profileDialog", {
                    action: "checkInfoAction"
                }, function (resp) {
                    var object = JSON.parse(resp);
                    console.log(resp);

                    $("#dialogProfPath").val(object.MK);

                });
            } else {
                $("#selectResult").css("color", "red");
                document.getElementById("dialogProfPath").disabled = true;
                document.getElementById("useProfileButton").disabled = true;
                document.getElementById("clearUserInfoButton").disabled = true;
            }
        }
    );
};

function useProfile1() {
    var base = $("#dialogBaseName").val();
    var user = $("#dialogUserName").val();
    var path = $("#dialogProfPath").val();
    console.log("base = " + base);
    console.log("user = " + user);
    console.log("path = " + path);
    $.post("/useProfile", {
        baseName: base,
        userName: user,
        userPath: path,
        action: "useProfileAction"
    }, function (infoForm) {
        var object = JSON.parse(infoForm);
        console.log("object = " + object);
        $("#baseNameInfo").val(object.baseNameInfoU);
        $("#userNameInfo").val(object.userNameInfoU);
        $("#profPathInfo").val(object.profPathInfoU);
    });
};

function clearUserInfo1() {
    $.post("/userInfoCleaner", {
            action: "clearUserInfoAction"
        }, function (clearInfoForm) {
            var object = JSON.parse(clearInfoForm);
            $("#baseNameInfo").val(object.baseNameInfoC);
            $("#userNameInfo").val(object.userNameInfoC);
            $("#profPathInfo").val(object.profPathInfoC);
        }
    )
};

function proverka(input) {
    input.value = input.value.replace(/[^-+\d.]/g, '');
};


function kvantifikuy() {
    var value = $("#kvantifikator_val").val();
    console.log("value = " + value);
    if (value < 0) {
        var pt = value.replace(/[^0-9]/, '');
        console.log("pt = " + pt);
        kvantifikator_str = "(" + pt + ")";
        console.log("kvantifikator_str = " + kvantifikator_str);
        $("#kvantifikator_num").val(kvantifikator_str);
    } else {
        $("#kvantifikator_num").val(value);
    }
};

//Кнопка поиска открывающаяся при клике
$('.opacity').css({opacity: 0.7});

$(".search_header").click(function () {
    $(".search_popup").show();
    $(".close_search").show();
    $(".opacity").show();
});

$(".close_search").click(function () {
    $(".search_popup").hide();
    $(".close_search").hide();
    $(".opacity").hide();
});

//Кнопка "Наверх".
$(window).scroll(function () {
    if ($(this).scrollTop() > 800) {
        $('.up_button i').fadeIn();
    } else {
        $('.up_button i').fadeOut();
    }
});

$('.up_button i').click(function () {
    $("html, body").animate({scrollTop: 0}, 600);
    return false;
});


/*searchSubmit.onclick = function () {
    var value = $("#input_vspl").val();
    console.log("value = " + value);
    $.post("/searchPage", {
            value: value,
            action: "searchValue"
        }, function (search) {
            var object = JSON.parse(search);
            $("#").val(object.tagName);
        }
    );
    window.open("/searchPage");
};*/
