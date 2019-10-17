// 移动端动态显示头部标签
$('.menu.toggle').click(function () {
    $('.m-item').toggleClass('m-mobile-hide');
});


// 返回顶部方法
$('#toTop-div').click(function () {
    $(window).scrollTo(0, 500);
});


// 滚动监听js事件
var waypoint = new Waypoint({
    element: document.getElementById('middle-content'),
    handler: function (direction) {
        if (direction == 'down') {
            $('#toolbar-list').show(100);
        } else {
            $('#toolbar-list').hide(500);
        }
    }
});