$(function () {
    $('#loginButton').click(function () {
        $('#registerButton').attr('disable',true);
        var account = $('#account').val();
        var password = $('#password').val();
        if (validAccountPassword(account,password)) {
            var params = $('#loginForm').serialize();
            $.ajax({
                type: "POST",
                url: "/user/login",
                data: params,
                success: function (result) {
                    if (result.resultCode == 200 ) {
                        window.location.href = '/user/main';
                    } else {
                        swal(result.message, {
                            icon: "error",
                        });
                        window.parent.location.reload();
                    }
                    ;
                },
                error: function () {
                    swal("登录失败", {
                        icon: "error",
                    });
                }
            });
        } else {
            $('loginForm-info').html("信息格式有误，请重新填写");
        }
    });
})

/**
 * 输入信息的合法性验证
 */
function validAccountPassword (account,password) {
    //账号验证
    if(isNull(account) || account.trim().length < 1) {
        $('#loginForm-info').css("display", "block");
        $('#loginForm-info').html("账号不能为空！");
        return false;
    }
    if(!(validEmail(account) || validPhone(account))) {
        $('#loginForm-info').css("display", "block");
        $('#loginForm-info').html("请输入有效的账号！");
        return false;
    }

    //密码验证
    if(isNull(password) || password.trim().length < 1) {
        $('#loginForm-info').css("display", "block");
        $('#loginForm-info').html("密码不能为空！");
        return false;
    }
    if(!validPassword(password)) {
        $('#loginForm-info').css("display", "block");
        $('#loginForm-info').html("请输入有效的密码！");
        return false;
    }

    return true;
}