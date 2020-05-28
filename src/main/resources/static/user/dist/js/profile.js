$(function () {
    //修改用户名
    $('#updateUserNameButton').click(function () {
        var newUserName = $('#newUserName').val();
        var originalUserName = $('#originalUserName').val();
        if (validUserNameForUpdate(originalUserName,newUserName)) {
            //ajax提交数据
            var params = $("#usernameForm").serialize();
            $.ajax({
                type: "POST",
                url: "/user/profile/name",
                data: params,
                success: function (result) {
                    if (result.resultCode == 200 ) {
                        swal(result.message, {
                            icon: "success",
                        });
                        window.parent.location.reload();
                    } else {
                        swal(result.message, {
                            icon: "error",
                        });
                        $("#updateUserNameButton").prop("disabled", false);
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
            $("#updateUserNameButton").prop("disabled", false);
        }
    });

    //修改用户公钥
    $('#updatePublicKeyButton').click(function () {
        $("#updatePublicKeyButton").attr("disabled", true);
        var newPublicKey = $('#newPublicKey').val();
        var originalPublicKey = $('#originalPublicKey').val();
        if (validPublicKeyForUpdate(originalPublicKey,newPublicKey)) {
            var params = $("#publicKeyForm").serialize();
            $.ajax({
                type: "POST",
                url: "/user/profile/public-key",
                data: params,
                success: function (result) {
                    if (result.resultCode == 200 ) {
                        swal(result.message, {
                            icon: "success",
                        });
                        window.parent.location.reload();
                    } else {
                        swal(result.message, {
                            icon: "error",
                        });
                        $("#updateUserNameButton").prop("disabled", false);
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
            $("#updatePublicKeyButton").attr("disabled", false);
        }
    });

    //修改密码
    $('#updatePasswordButton').click(function () {
        $("#updatePasswordButton").attr("disabled", true);
        var originalPassword = $('#originalPassword').val();
        var newPassword = $('#newPassword').val();
        if (validPasswordForUpdate(originalPassword, newPassword)) {
            var params = $("#passwordForm").serialize();
            $.ajax({
                type: "POST",
                url: "/user/profile/password",
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
                        $("#updateUserNameButton").prop("disabled", false);
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
            $("#updatePasswordButton").attr("disabled", false);
        }
    });
})

/**
 * 名称验证
 */
function validUserNameForUpdate(originalUserName,newUserName) {
    if (isNull(newUserName) || newUserName.trim().length < 1) {
        $('#usernameForm-info').css("display", "block");
        $('#usernameForm-info').html("新用户名不能为空！");
        return false;
    }
    if (!validCN_ENString2_18(newUserName)) {
        $('#usernameForm-info').css("display", "block");
        $('#usernameForm-info').html("请输入符合规范的用户名！");
        return false;
    }
    if(originalUserName == newUserName) {
        $('#usernameForm-info').css("display", "block");
        $('#usernameForm-info').html("新用户名不能与原用户名相同！");
        return false;
    }
    return true;
}

/**
 * 公钥验证
 */
function validPublicKeyForUpdate(originalPublicKey,newPublicKey) {
    if (isNull(newPublicKey) || newPublicKey.trim().length < 1) {
        $('#publicKeyForm-info').css("display", "block");
        $('#publicKeyForm-info').html("请输入新的个人公钥！");
        return false;
    }
    if(newPublicKey == originalPublicKey) {
        $('#publicKeyForm-info').css("display", "block");
        $('#publicKeyForm-info').html("新公钥不能与原公钥相同！");
        return false;
    }
    return true;
}

/**
 * 密码验证
 */
function validPasswordForUpdate(originalPassword, newPassword) {
    if (isNull(originalPassword) || originalPassword.trim().length < 1) {
        $('#passwordForm-info').css("display", "block");
        $('#passwordForm-info').html("请输入原密码！");
        return false;
    }
    if (isNull(newPassword) || newPassword.trim().length < 1) {
        $('#passwordForm-info').css("display", "block");
        $('#passwordForm-info').html("新密码不能为空！");
        return false;
    }
    if (!validPassword(newPassword)) {
        $('#passwordForm-info').css("display", "block");
        $('#passwordForm-info').html("请输入符合规范的密码！");
        return false;
    }
    return true;
}
