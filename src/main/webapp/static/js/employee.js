$(function () {

    //1.抽取变量
    var emp_datagrid = $("#emp_datagrid");
    var emp_dialog = $("#emp_dialog");
    var emp_form = $("#emp_form");

    var methodObj = {
        //新增按钮
        add: function () {
            //显示密码框
            $("#tr_password").show();
            emp_dialog.dialog('open');
            emp_dialog.dialog('setTitle', '新增员工');
        },

        //编辑按钮
        edit: function () {
            var row = emp_datagrid.datagrid('getSelected');
            if (!row) {
                //提示
                $.messager.alert('温馨提示', '亲,请选中一条数据!');
                return;
            }

            //隐藏密码框
            $("#tr_password").hide();
            //回显数据
            if (row.dept) {
                row["dept.id"] = row.dept.id;
            }
            emp_form.form('load', row);

            //通过异步请求来获取员工的角色id集合
            $.get("/role/selectByEmployeeId.do", {id: row.id}, function (data) {
                $("#role_combobox").combobox('setValues', data);
            });

            emp_dialog.dialog('open');
            emp_dialog.dialog('setTitle', '编辑员工');
        },

        //离职按钮
        changeState: function () {
            var row = emp_datagrid.datagrid('getSelected');
            if (!row) {
                //提示
                $.messager.alert('温馨提示', '亲,请选中一条数据!');
            }
            $.messager.confirm('确认', '您确认设置为离职吗?', function (r) {
                if (r) {
                    $.get('/employee/changeState.do', {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', '操作成功!', 'info', function () {
                                emp_datagrid.datagrid('reload');
                            });
                        } else {
                            $.messager.alert("温馨提示", data.msg);
                        }
                    });
                }
            });
        },

        //保存按钮
        save: function () {
            emp_form.form('submit', {
                url: '/employee/saveOrUpdate.do',
                onSubmit: function (param) {
                    //获取角色下拉框中的数据
                    var ids = $("#role_combobox").combobox('getValues');
                    for (var i = 0; i < ids.length; i++) {
                        //添加额外的参数
                        param["roles[" + i + "].id"] = ids[i];
                    }
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', '保存成功!', 'info', function () {
                            //关闭对话框
                            methodObj.cancel();
                            //重新加载数据表格
                            //reload方法会保持在当前页
                            emp_datagrid.datagrid('reload');
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        },

        //取消按钮
        cancel: function () {
            emp_dialog.dialog("close");
        },

        //高级查询
        searchs: function () {
            //获取关键字内容
            var value = $("#keyword").textbox('getValue');
            emp_datagrid.datagrid('load', {keyword: value});
        }
    }

    //给按钮统一绑定事件
    $("a[data-cmd]").click(function () {
        //根据按钮的data-cmd属性值来调用方法
        var methodName = $(this).data('cmd');
        methodObj[methodName]();
    });


    emp_datagrid.datagrid({
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
        ]],
        onClickRow: function (index, row) {
            if (row.state) {
                $("#btn_changeState").linkbutton({
                    text: '设置离职'
                });
            } else {
                $("#btn_changeState").linkbutton({
                    text: '设置复职'
                });
            }
        }
    });

    emp_dialog.dialog({
        width: 330,
        height: 430,
        buttons: '#emp_buttons',
        closed: true,
        onClose: function () {
            emp_form.form('clear');
        }
    });

})
