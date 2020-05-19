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
        width: 330,
        height: 320,
        buttons: '#emp_buttons',
        closed: true,
        onClose: function () {
            $("#emp_form").form('clear');
        }
    });

})

//新增按钮
function add() {
    //显示密码框
    $("#tr_password").show();

    $("#emp_dialog").dialog('open');
    $("#emp_dialog").dialog('setTitle', '新增员工');
}

//编辑按钮
function edit() {
    var row = $("#emp_datagrid").datagrid('getSelected');
    if(!row){
        //提示
        $.messager.alert('温馨提示','亲,请选中一条数据!');
        return;
    }

    //隐藏密码框
    $("#tr_password").hide();

    if(row.dept){
        row["dept.id"]=row.dept.id;
    }

    //回显数据
    $("#emp_form").form('load',row);

    $("#emp_dialog").dialog('open');
    $("#emp_dialog").dialog('setTitle', '编辑员工');
}

//离职按钮
function changeState() {

}

//保存按钮
function save() {
    $("#emp_form").form('submit', {
        url: '/employee/saveOrUpdate.do',
        success: function (data) {
            data = $.parseJSON(data);
            if (data.success) {
                $.messager.alert('温馨提示', '保存成功!', 'info', function () {
                    //关闭对话框
                    cancel();
                    //重新加载数据表格
                    //reload方法会保持在当前页
                    $("#emp_datagrid").datagrid('reload');
                });
            } else {
                $.messager.alert("温馨提示", data.msg);
            }
        }
    });
}

//取消按钮
function cancel() {
    $("#emp_dialog").dialog("close");
}