<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/js/employee.js"></script>
</head>
<body>
<table id="emp_datagrid"></table>
<div id="emp_toolbar">
    <shiro:hasPermission name="employee:saveOrUpdate">
        <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" data-cmd="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasRole name="HR">
        <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" id="btn_changeState"
           data-cmd="changeState">设置离职</a>
    </shiro:hasRole>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" data-cmd="reload">刷新</a>
    <input class="easyui-textbox" id="keyword" prompt="请输入用户名或电话"/>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" data-cmd="searchs"/>
</div>

<div id="emp_buttons">
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" data-cmd="cancel">取消</a>
</div>

<div id="emp_dialog">
    <form id="emp_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top:20px">
            <tr>
                <td>用户名:</td>
                <td><input name="username" class="easyui-textbox" data-options="required:true"></td>
            </tr>
            <tr>
                <td>真实姓名:</td>
                <td><input name="realname" class="easyui-textbox" validType="length[1,5]"></td>
            </tr>
            <tr id="tr_password">
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
                <td><input name="dept.id" class="easyui-combobox"
                           data-options="panelHeight:'auto',valueField:'id',textField:'name',url:'/department/selectAll.do'">
                </td>
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
                        value:'0'
                    }]">
                </td>
            </tr>
            <tr>
                <td>角色:</td>
                <td><input id="role_combobox" class="easyui-combobox"
                           data-options="multiple:'true',panelHeight:'auto',valueField:'id',textField:'name',url:'/role/selectAll.do'"/>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>