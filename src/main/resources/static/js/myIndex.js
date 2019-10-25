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
var flag = true;
/**
 * 个人信息点击事件
 */
$(".contactusdiyou").on("click", function () {
    if (flag) {
        //鼠标移动到个人信息
        $(".hoverimg").attr("src", getRootPath() + "/static/images/right_icon.png");
        $('.diyoumask').fadeIn();
        $('.contactusdiyou').animate({right: '0'}, 300);
        flag = false;
    } else {
        //鼠标移出个人信息
        $(".hoverimg").attr("src", getRootPath() + "/static/images/left_icon.png");
        $('.contactusdiyou').animate({right: '-230px'}, 300, function () {
        });
        $('.diyoumask').fadeOut();
        flag = true;
    }
});


//获取项目根路径
function getRootPath() {
    var roorUrl = window.location.host;
    return document.location.protocol + '//' + roorUrl;
}

//获取精选文章内容
$.ajax({
    type: "GET",
    url: "/scheduled",
    dataType: "json",
    success: function (response) {
        var selected_author = document.getElementById("selected_author");
        if (selected_author != null) {
            document.getElementById("selected_author").innerText = response.author;
            document.getElementById("selected_source").innerText = response.source;
            document.getElementById("selected_content").innerText = response.content;
        }
    }
});


/**
 * 封装一个居中打开新窗口的方法
 */
function openWindow(url, width, height) {
    width = width || 600;
    height = height || 400;
    var left = (window.screen.width - width) / 2;
    var top = (window.screen.height - height) / 2;
    window.open(url, "_blank", "toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, left=" + left + ", top=" + top + ", width=" + width + ", height=" + height);
}

function githubLogin() {
    $.ajax({
        type: "GET",
        url: "/github/oauth",
        dataType: "json",
        success: function (response) {
            openWindow('https://github.com/login/oauth/authorize?client_id=' + response.clientId + '&redirect_uri=' + response.redirectUrl + '&scope=user&state=' + response.status);
        }
    });
}