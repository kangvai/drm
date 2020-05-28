$(function () {
    $("#jqGrid").jqGrid({
        url: '/user/trade/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: 'authorId', name: 'authorId', index: 'authorId', width: 50,hidden: true},
            {label: '作品标题', name: 'workTitle', index: 'workTitle', width: 60},
            {label: '作者姓名', name: 'authorName', index: 'authorName', width: 30},
            {label: '上传时间', name: 'createTime', index: 'createTime', width: 30},
            {label: '操作', name: 'isdeleted', index: 'isdeleted', width: 15, formatter: statusFormatter},
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
            return "<button type=\"button\" class=\"btn btn-info\" onclick=\"buy()\" style=\"width: 80%;\">购买</button>";
        } else if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 80%;\">已删除</button>";
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

/**
 * 购买作品
 */
function buy() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    var rowData = $("#jqGrid").jqGrid('getRowData', id);
    $("#authorId").val(rowData.authorId);
    $("#workTitle").val(rowData.workTitle);
    $("#authorName").val(rowData.authorName);
    $('#buyModal').modal('show');
}

//绑定modal上的保存按钮
$('#saveButton').click(function () {
    var url = '/user/trade/buy';
    var params = $("#buyForm").serialize();
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: params,
        success: function (result) {
            if (result.resultCode == 200) {
                $('#buyModal').modal('hide');
                swal("购买成功", {
                    icon: "success",
                });
                reload();
            }
            else {
                $('#buyModal').modal('hide');
                swal(result.message, {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
});



