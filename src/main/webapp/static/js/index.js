$(function () {
    $("#menu").tree({
        url: '/static/json/menu.json',
        onClick: function (node) {
            //根据标题判断选项卡是否存在
            if ($("#tabs").tabs("exists", node.text)) {
                $("#tabs").tabs("select", node.text);
            } else {
                //在tabs中添加一个选项卡
                $("#tabs").tabs("add", {
                    title: node.text,
                    selected: true,
                    //href:node.url
                    content: "<iframe src='" + node.url + "' width='100%', height='99%' frameborder='0'>"
                });
            }
        }
    });
})