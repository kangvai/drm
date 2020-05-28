$(function () {
    $("#jqGrid").jqGrid({
        url: '/user/block/list',
        datatype: "json",
        colModel: [
            {label: '交易哈希值索引', name: 'blockId', index: 'blockId', width: 50, key: true, hidden: true},
            {label: '交易哈希值', name: 'txhash', index: 'txhash', width: 120},
            {label: '发送方地址', name: 'sender', index: 'sender', width: 120},
            {label: '作品标题', name: 'workTitle', index: 'workTitle', width: 80},
            {label: '作者姓名', name: 'authorName', index: 'authorName', width: 50},
            {label: '交易时间', name: 'createTime', index: 'createTime', width: 60},
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



