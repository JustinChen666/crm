<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%@include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/role.js"></script>
</head>
<body>
<table id="role_datagrid"></table>
<div id="role_toolbar">
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" data-cmd="reload">刷新</a>
</div>

<div id="role_buttons">
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" data-cmd="cancel">取消</a>
</div>

<div id="role_dialog">
    <form id="role_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top:20px">
            <tr>
                <td>角色编号:<input class="easyui-textbox" name="sn"/></td>
                <td>角色名称:<input class="easyui-textbox" name="name"/></td>
            </tr>
            <tr>
                <td><table id="allPermissions"></table></td>
                <td><table id="selfPermissions"></table></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>