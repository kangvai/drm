$(function () {
    $("#jqGrid").jqGrid({
        url: '/user/record/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'recordId', index: 'recordId', width: 50, key: true, hidden: true},
            {label: '作品名称', name: 'workTitle', index: 'workTitle', width: 80},
            {label: '上传时间', name: 'checkTime', index: 'checkTime', width: 100},
            {label: '作者', name: 'authorName', index: 'authorName', width: 60},
            {label: '反馈内容', name: 'feedback', index: 'feedback', width: 120},
            {label: '状态', name: 'resultStatus', index: 'resultStatus', width: 60, formatter: statusFormatter},
        ],
        height: 700,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",          //数据列表
            page: "data.currPage",      //当前第几页
            total: "data.totalPage",    //总共多少页
            records: "data.totalCount"  //总记录数
        },
        prmNames: {
            page: "page",       //页码（第几页）
            rows: "limit",      //每页多少条数据
            order: "order",     //
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

    function statusFormatter(cellvalue) {
        if (cellvalue == 0) {
            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 80%;\">未通过</button>";
        } else if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 80%;\">已通过</button>";
        }
    }

});

/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

//文件上传功能
$('input[id=myFile]').change(function () {
    var myFile = document.getElementById("myFile").files[0];
    var myFileName = myFile.name;
    $('#fileCover').val(myFileName);
    //拼接上传的文件名字并返回到FileCover
});

$(function () {
    //上传文件
    $('#uploadButton').click(function () {
        var myFile = document.getElementById("myFile").files[0];//获取文件
        var data = new FormData();//构建FormData用于Ajax发送
        data.append('file', myFile);
        $.ajax({
            type: "POST",
            url: "/user/upload",
            data: data,
            processData: false, // 告诉jQuery不要处理数据
            contentType: false, // 告诉jQuery不要设置类型
            dataType: "json",
            success: function (result) {
                if (result.resultCode == 200 && result.data) {
                    swal("检测正常", {
                        icon: "success",
                    });
                    reload();
                }
            },
            error: function () {
                swal("操作异常", {
                    icon: "error",
                });
            },
        })
    })
})



