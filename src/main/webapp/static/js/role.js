$(function () {

    //1.抽取变量
    var role_datagrid = $("#role_datagrid");
    var role_dialog = $("#role_dialog");
    var role_form = $("#role_form");

    //2.用一个对象统一管理方法
    var methodObj = {
        //新增按钮
        add: function () {
            role_dialog.dialog('open');
            role_dialog.dialog('setTitle', '新增角色');
        },

        //编辑按钮
        edit: function () {
            var row = role_datagrid.datagrid('getSelected');
            if (!row) {
                //提示
                $.messager.alert('温馨提示', '亲,请选中一条数据!');
                return;
            }

            //回显数据
            role_form.form('load', row);
            role_dialog.dialog('open');
            role_dialog.dialog('setTitle', '编辑角色');
        },

        //保存按钮
        save: function () {
            role_form.form('submit', {
                url: '/role/saveOrUpdate.do',
                onSubmit: function (param) {
                    //获取已有权限中的数据
                    var rows = selfPermissions.datagrid('getRows');
                    for (var i = 0; i < rows.length; i++) {
                        //添加额外的参数
                        param["permissions[" + i + "].id"] = rows[i].id;
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
                            role_datagrid.datagrid('reload');
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        },

        //取消按钮
        cancel: function () {
            role_dialog.dialog("close");
        }

    }

    //给按钮统一绑定事件
    $("a[data-cmd]").click(function () {
        //根据按钮的data-cmd属性值来调用方法
        var methodName = $(this).data('cmd');
        methodObj[methodName]();
    });


    role_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        toolbar: '#role_toolbar',
        striped: true,
        url: '/role/list.do',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {title: "角色编号", field: "sn", width: 100},
            {title: "角色名称", field: "name", width: 100}
        ]]
    });

    role_dialog.dialog({
        width: 580,
        height: 450,
        buttons: '#role_buttons',
        closed: true,
        onClose: function () {
            role_form.form('clear');
        }
    });

    //所有权限
    var allPermissions = $("#allPermissions");

    //已有权限
    var selfPermissions = $("#selfPermissions");

    allPermissions.datagrid({
        height: 300,
        title: '所有权限',
        fitColumns: true,
        url: '/permission/selectAll.do',
        columns: [[
            {title: '权限名称', field: 'name', width: 100, align: 'center'}
        ]],
        onClickRow: function (index, row) {
            //在已有权限中添加选中的数据
            selfPermissions.datagrid('appendRow', row);
            //把选中的数据从所有权限中移除掉
            allPermissions.datagrid('deleteRow', index);
        }
    });


    selfPermissions.datagrid({
        height: 300,
        title: '已有权限',
        fitColumns: true,
        columns: [[
            {title: '权限名称', field: 'name', width: 100, align: 'center'}
        ]],
        onClickRow: function (index, row) {
            //在所有权限中添加选中的数据
            allPermissions.datagrid('appendRow', row);
            //把选中的数据从已有权限中移除掉
            selfPermissions.datagrid('deleteRow', index);
        }
    });
})
