$(function () {
    $("#jqGrid").jqGrid({
        url: '/user/news/list',
        datatype: "json",
        colModel: [
            {label: 'newsId', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '新闻作者', name: 'newsAuthor', index: 'newsAuthor', width: 80},
            {label: '新闻题目', name: 'newsTitle', index: 'newsTitle', width: 140},
            {label: '新闻出版社', name: 'newsPress', index: 'newsPress', width: 60},
            {label: '出版时间', name: 'publishTime', index: 'publishTime', width: 70},
            {label: '新闻内容', name: 'newsContent', index: 'newsContent', width: 10, hidden: true},
            {label: '操作', name: 'isdeleted', index: 'isdeleted', width: 40, formatter: statusFormatter},
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
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" onclick=\"browse()\" style=\"width: 80%;\">查看内容</button>";
        } else if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 80%;\">已删除</button>";
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
 * 查看新闻具体内容
 */
function browse() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    var rowData = $("#jqGrid").jqGrid('getRowData', id);
    console.log(rowData);
    console.log(rowData.newsTitle);
    $("#newsTitle").val(rowData.newsTitle);
    $("#newsAuthor").val(rowData.newsAuthor);
    $("#publishTime").val(rowData.publishTime);
    $("#newsContent").val(rowData.newsContent);
    $('#browseModal').modal('show');
}