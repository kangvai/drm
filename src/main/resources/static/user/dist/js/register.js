$(function () {
    $('#registerButton').click(function () {
        $('#registerButton').attr('disable',true);
        var username = $('#username').val();
        var account = $('#account').val();
        var publicKey = $('#publicKey').val();
        var password = $('#password').val();
        var passwordAgain = $('#passwordAgain').val();
        if (validInfoFormat(username,account,publicKey,password,passwordAgain)) {
            var params = $('#registerForm').serialize();
            $.ajax({
                type: "POST",
                url: "/user/register",
                data: params,
                success: function (result) {
                    if (result.resultCode == 200 ) {
                        swal(result.message, {
                            icon: "success",
                        });
                        window.location.href = '/user/login';
                    } else {
                        swal(result.message, {
                            icon: "error",
                        });
                        window.parent.location.reload();
                    }
                    ;
                },
                error: function () {
                    swal("修改失败", {
                        icon: "error",
                    });
                }
            });
        } else {
            $('registerForm-info').html("信息格式有误，请重新填写");
        }
    });
})
/**
 * 输入信息的合法性验证
 */
function validInfoFormat (username,account,publicKey,password,passwordAgain) {
    //用户名验证
    if(isNull(username) || username.trim().length < 1) {
        $('#registerForm-info').css("display", "block");
        $('#registerForm-info').html("用户名不能为空！");
        return false;
    }
    if (!validUserName(username)) {
        $('#registerForm-info').css("display", "block");
        $('#registerForm-info').html("请输入有效的用户名！");
        return false;
    }

    //账号验证
    if(isNull(account) || account.trim().length < 1) {
        $('#registerForm-info').css("display", "block");
        $('#registerForm-info').html("账号不能为空！");
        return false;
    }
    if(!(validEmail(account) || validPhone(account))) {
        $('#registerForm-info').css("display", "block");
        $('#registerForm-info').html("请输入有效的账号！");
        return false;
    }

    //公钥验证
    if(isNull(publicKey) || publicKey.trim().length < 1) {
        $('#registerForm-info').css("display", "block");
        $('#registerForm-info').html("公钥不能为空！");
        return false;
    }

    //密码验证
    if(isNull(password) || password.trim().length < 1) {
        $('#registerForm-info').css("display", "block");
        $('#registerForm-info').html("密码不能为空！");
        return false;
    }
    if(isNull(passwordAgain) || passwordAgain.trim().length < 1) {
        $('#registerForm-info').css("display", "block");
        $('#registerForm-info').html("确认密码不能为空！");
        return false;
    }
    if(!validPassword(password)) {
        $('#registerForm-info').css("display", "block");
        $('#registerForm-info').html("请输入有效的密码！");
        return false;
    }
    if(!(password == passwordAgain)) {
        $('#registerForm-info').css("display", "block");
        $('#registerForm-info').html("两次密码不一致，请重新输入！");
        return false;
    }
    return true;
}