$(function () {
    $("#emp_datagrid").datagrid({
        fit: true,
        fitColumns: true,
        toolbar: '#emp_toolbar',
        striped: true,
        url: '/employee/list.do',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {title: "用户名", field: "username", width: 100},
            {title: "真实姓名", field: "realname", width: 100},
            {title: "手机", field: "tel", width: 100},
            {title: "邮箱", field: "email", width: 100},
            {
                title: "部门", field: "dept", width: 100, formatter: function (value, row, index) {
                    return value ? value.name : "";
                }
            },
            {title: "入职时间", field: "hireDate", width: 100},
            {
                title: "状态", field: "state", width: 100, formatter: function (value, row, index) {
                    return value ? "在职" : "<font color='red'>离职</font>";
                }
            },
            {
                title: "管理员", field: "admin", width: 100, formatter: function (value, row, index) {
                    return value ? "是" : "否";
                }
            }
        ]]
    });

    $("#emp_dialog").dialog({
        width:330,
        height:320,
        buttons:'#emp_buttons'
    });

})