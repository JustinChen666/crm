<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%@include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="/static/js/index.js"></script>
    <link rel="stylesheet" href="/static/css/reset.css">
    <link rel="stylesheet" href="/static/css/public.css">
</head>
<body>
<div class="easyui-layout" data-options="fit:true"><!--设置为自适应-->
    <div data-options="region:'north'" style="height:70px;">
        <div class="public-header-warrp">
            <div class="public-header">
                <div class="content">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img  src="http://www.wolfcode.cn/img/wolfcode/logo.png"/>
                    <div class="public-header-admin fr">
                        <p class="admin-name"><font  color ="green">您好！</font> </p>
                        <div class="public-header-fun fr">
                            <a href="/logout.do" class="public-header-loginout">安全退出</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div data-options="region:'south'" style="height:30px;">
        <center><span>&copy;版权所有</span></center>
    </div>
    <div data-options="region:'west'" title="系统菜单" style="width:180px;">
        <ul id="menu"></ul>
    </div>
    <div data-options="region:'center'">
        <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
            <div title="首页" style="padding:20px;display: none;">
                欢迎登陆
            </div>
        </div>
    </div>
</div>
</body>
</html>