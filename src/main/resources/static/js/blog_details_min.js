// 初始化目录
tocbot.init({
    // 初始目录到那个区域
    tocSelector: '.js-toc',
    // Where to grab the headings to build the table of contents.
    contentSelector: '.js-toc-content',
    // Which headings to grab inside of the contentSelector element.
    headingSelector: 'h1, h2, h3',
    // For headings inside relative or absolute positioned containers within content.
    hasInnerContainers: true,
});


$('#payBtn').popup({
    popup: $('.payButton.popup'),
    on: 'click',
    position: 'bottom center'
});


// 点击目录事件
$('#catalog').popup({
    popup: $('.catalog-click.popup'),
    on: 'click',
    position: 'left center'
});

// 目录微信二维码
$('.click-wechat').popup({
    popup: $('.share-wechat'),
    on: 'click',
    position: 'left center'
});

//生成二维码
var qrcode = new QRCode("qrcode", {
    text: qrcodeurl,
    width: 128,
    height: 128,
    colorDark: "#000000",
    colorLight: "#ffffff",
    correctLevel: QRCode.CorrectLevel.H
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

// 表单验证
$('.ui.form')
    .form({
        fields: {
            nickName: {
                identifier: 'nickName',
                rules: [
                    {
                        type: 'empty',
                        prompt: '评论昵称不能为空'
                    }
                ]
            },
            content: {
                identifier: 'content',
                rules: [
                    {
                        type: 'empty',
                        prompt: '回复内容不能为空'
                    }
                ]
            }
        },
    });

//留言提交事件
$("#comment-btn-sub").click(function () {
    //效验表单
    var hasBoo = $('.ui.form').form('validate form');
    if (hasBoo) {
        postData();
    }
});


//发送请求的方法
function postData() {
    $("#command-container").load("/comments", {
        "parentComment.id": $("[name='parentComment.id']").val(),
        "blogBean.id": $("[name='blogBean.id']").val(),
        "content": $("[name='content']").val()
    }, function (responseTxt, statusTxt, xhr) {
        clearMessage();
    });
}


//清空context内容
function clearMessage() {
    $("[name='content']").val('');
    $("[name='parentComment.id']").val(-1);
    $("[name='content']").attr("placeholder", "请输入评论信息...");
}

//回复点击事件处理
function reply(obj) {
    var commentId = $(obj).data('comment-id');
    var nickName = $(obj).data('comment-nickname');
    console.log(nickName);

    //设置回复内容
    $("[name='content']").attr("placeholder", "@" + nickName).focus();
    $("[name='parentComment.id']").val(commentId);
    $(window).scrollTo($("#comment-form"), 500);
}

increaseViewCount();

//浏览量ajax+cookie
function increaseViewCount() {
    if ($.cookie("viewId") != blogId) {
        $.ajax({
            async: false,
            type: "POST",
            url: "/addView",
            data: {id: blogId},
            dataType: "json",
            success: function (response) {
                $(".views").html(response);
                $.cookie(
                    "viewId",
                    blogId,//需要cookie写入的业务
                    {
                        "path": "/", //cookie的默认属性
                    }
                );
            },
            error: function () {
                alert("获取数据出错!");
            },
        });
    }
}

//点击推荐点击事件
$('#tui_jian').click(function () {
    console.log("点击了")
    //显示无法继续添加事件
    $('#tui_jian').popup();
});



//点击反对点击事件
$('#fan_dui').click(function () {
    console.log("点击了fan_dui")
    //显示无法继续添加事件
    $('#fan_dui').popup();
});