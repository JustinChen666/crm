<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--拷贝过来的4个必要引入需要修改文件的导入位置,按住ctrl按键移动鼠标,如果能点击进去就是成功引入了-->
    <link rel="stylesheet" type="text/css" href="/static/plugins/easyui/themes/gray/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/static/plugins/easyui/themes/color.css">
    <script type="text/javascript" src="/static/plugins/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/plugins/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/employee.js"></script>
</head>
<body>
<table id="emp_datagrid"></table>
<div id="emp_toolbar">
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="add()">新增</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit()">编辑</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="del()">删除</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick="reload()">刷新</a>
</div>

<div id="emp_buttons">
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" onclick="add()">保存</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="edit()">取消</a>
</div>

<div id="emp_dialog">
    <form id="emp_form" method="post">
        <table align="center" style="margin-top:20px">
            <tr>
                <td>用户名:</td>
                <td><input name="username" class="easyui-textbox" required="true" ></td>
            </tr>
            <tr>
                <td>真实姓名:</td>
                <td><input name="realname" class="easyui-textbox" validType="length[1,5]"></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input name="password" class="easyui-passwordbox"></td>
            </tr>
            <tr>
                <td>手机:</td>
                <td><input name="tel" class="easyui-textbox"></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input name="email" class="easyui-textbox" validType="email"></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td><input name="dept" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'id',textField:'name',url:'/department/selectAll.do'"></td>
            </tr>
            <tr>
                <td>入职时间:</td>
                <td><input name="hireDate" class="easyui-datebox"></td>
            </tr>
            <tr>
                <td>管理员:</td>
                <td>
                    <input name="admin" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'value',
                    textField:'text',
                    data:[{
                        text:'是',
                        value:'1'
                    },{
                        text:'否',
                        value:'Ruby'
                    }]">
                </td>
            </tr>

        </table>
    </form>
</div>

</body>
</html>