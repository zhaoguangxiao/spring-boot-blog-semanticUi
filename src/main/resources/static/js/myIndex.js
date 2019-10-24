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


$.ajax({
    type: "GET",
    url: "/links",
    dataType: "json",
    success: function (response) {
        var html;
        var linkId = document.getElementById("link-a-div");
        for (var i = 0; i < response.length; i++) {
            html = document.createElement('a');
            html.setAttribute('href', response[i].url);
            html.target = '_blank';
            html.innerText = response[i].friendName;
            linkId.appendChild(html)
            //添加item
            $("#link-a-div a").addClass("item");
        }
    }
});



$(".contactusdiyou").hover(function () {
    //鼠标移动到个人信息
    $(".hoverimg").attr("src", getRootPath() + "/static/images/right_icon.png");
    $('.diyoumask').fadeIn();
    $('.contactusdiyou').animate({right: '0'}, 300);
}, function () {
    //鼠标移出个人信息
    $(".hoverimg").attr("src", getRootPath() + "/static/images/left_icon.png");
    $('.contactusdiyou').animate({right: '-230px'}, 300, function () {
    });
    $('.diyoumask').fadeOut();
});


//获取项目根路径
function getRootPath() {
    var roorUrl = window.location.host;
    return document.location.protocol + '//' + roorUrl;
}
