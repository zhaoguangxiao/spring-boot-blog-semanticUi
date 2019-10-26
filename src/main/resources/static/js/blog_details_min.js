tocbot['init']({
    'tocSelector': '.js-toc',
    'contentSelector': '.js-toc-content',
    'headingSelector': 'h1,\x20h2,\x20h3',
    'hasInnerContainers': !![]
});
$('#payBtn')['popup']({'popup': $('.payButton.popup'), 'on': 'click', 'position': 'bottom\x20center'});
$('#catalog')['popup']({'popup': $('.catalog-click.popup'), 'on': 'click', 'position': 'left\x20center'});
$('.click-wechat')['popup']({'popup': $('.share-wechat'), 'on': 'click', 'position': 'left\x20center'});
var qrcode = new QRCode('qrcode', {
    'text': qrcodeurl,
    'width': 0x80,
    'height': 0x80,
    'colorDark': '#000000',
    'colorLight': '#ffffff',
    'correctLevel': QRCode['CorrectLevel']['H']
});
$('#toTop-div')['click'](function () {
    var _0x43a5df = {
        'pVsbF': function (_0x5384f8, _0x122261) {
            return _0x5384f8(_0x122261);
        }
    };
    _0x43a5df['pVsbF']($, window)['scrollTo'](0x0, 0x1f4);
});
var waypoint = new Waypoint({
    'element': document['getElementById']('middle-content'), 'handler': function (_0x320b45) {
        var _0x2a7476 = {
            'Ckqhg': function (_0x469ff8, _0x5cd11c) {
                return _0x469ff8 == _0x5cd11c;
            }, 'bDtFE': 'down', 'BqmBe': function (_0x1df816, _0x4701b9) {
                return _0x1df816(_0x4701b9);
            }, 'eyVUT': '#toolbar-list', 'CEXJj': function (_0x4f2ccf, _0x23e0cc) {
                return _0x4f2ccf(_0x23e0cc);
            }
        };
        if (_0x2a7476['Ckqhg'](_0x320b45, _0x2a7476['bDtFE'])) {
            _0x2a7476['BqmBe']($, _0x2a7476['eyVUT'])['show'](0x64);
        } else {
            _0x2a7476['CEXJj']($, _0x2a7476['eyVUT'])['hide'](0x1f4);
        }
    }
});
$('.ui.form')['form']({
    'fields': {
        'nickName': {
            'identifier': 'nickName',
            'rules': [{'type': 'empty', 'prompt': '评论昵称不能为空'}]
        }, 'content': {'identifier': 'content', 'rules': [{'type': 'empty', 'prompt': '回复内容不能为空'}]}
    }
});
$('#comment-btn-sub')['click'](function () {
    var _0x19ea25 = {
        'WMqVz': function (_0x568012, _0x2af04b) {
            return _0x568012(_0x2af04b);
        }, 'NAaLP': '.ui.form', 'hMYzs': 'validate\x20form', 'Tffrz': function (_0x142248) {
            return _0x142248();
        }
    };
    var _0x26e47e = _0x19ea25['WMqVz']($, _0x19ea25['NAaLP'])['form'](_0x19ea25['hMYzs']);
    if (_0x26e47e) {
        _0x19ea25['Tffrz'](postData);
    }
});

function postData() {
    var _0x283a49 = {
        'nGPxR': function (_0xcb4636) {
            return _0xcb4636();
        }, 'qRqtq': function (_0xc5aaea, _0x5b91a3) {
            return _0xc5aaea(_0x5b91a3);
        }, 'ELzps': '#command-container', 'KBNIZ': '/comments', 'HdMeD': function (_0x45fc63, _0x16c1c0) {
            return _0x45fc63(_0x16c1c0);
        }, 'GIJiP': '[name=\x27parentComment.id\x27]', 'redww': function (_0x2573bf, _0x31ff05) {
            return _0x2573bf(_0x31ff05);
        }, 'KnlCA': '[name=\x27blogBean.id\x27]', 'Ypisp': function (_0x3a4cbe, _0x488760) {
            return _0x3a4cbe(_0x488760);
        }, 'TuTzK': '[name=\x27content\x27]'
    };
    _0x283a49['qRqtq']($, _0x283a49['ELzps'])['load'](_0x283a49['KBNIZ'], {
        'parentComment.id': _0x283a49['HdMeD']($, _0x283a49['GIJiP'])['val'](),
        'blogBean.id': _0x283a49['redww']($, _0x283a49['KnlCA'])['val'](),
        'content': _0x283a49['Ypisp']($, _0x283a49['TuTzK'])['val']()
    }, function (_0x29c0ab, _0xbe8458, _0x185af1) {
        _0x283a49['nGPxR'](clearMessage);
    });
}

function clearMessage() {
    var _0x47d14d = {
        'uqJae': function (_0x2b03ce, _0xb22fbb) {
            return _0x2b03ce(_0xb22fbb);
        }, 'giuBZ': '[name=\x27content\x27]', 'Xjmxp': function (_0x731754, _0x30f655) {
            return _0x731754(_0x30f655);
        }, 'STElQ': '[name=\x27parentComment.id\x27]', 'jJieZ': function (_0x46bac3, _0xd2eff0) {
            return _0x46bac3(_0xd2eff0);
        }, 'ruiil': 'placeholder', 'ImZig': '请输入评论信息...'
    };
    _0x47d14d['uqJae']($, _0x47d14d['giuBZ'])['val']('');
    _0x47d14d['Xjmxp']($, _0x47d14d['STElQ'])['val'](-0x1);
    _0x47d14d['jJieZ']($, _0x47d14d['giuBZ'])['attr'](_0x47d14d['ruiil'], _0x47d14d['ImZig']);
}

function reply(_0x4f512a) {
    var _0x321d38 = {
        'iVGcy': '3|1|0|4|5|2',
        'HeFaY': function (_0x1b0cd0, _0x2a80fc) {
            return _0x1b0cd0(_0x2a80fc);
        },
        'spJaG': 'comment-nickname',
        'ncEqy': '#comment-form',
        'bmUZw': function (_0x409e74, _0x533775) {
            return _0x409e74(_0x533775);
        },
        'GosxG': 'comment-id',
        'ojyvd': '[name=\x27content\x27]',
        'uzAtG': 'placeholder',
        'UGoJl': function (_0x18108f, _0x3a66fc) {
            return _0x18108f + _0x3a66fc;
        },
        'SJsRw': function (_0x12ddfd, _0x5420d5) {
            return _0x12ddfd(_0x5420d5);
        },
        'BMfyE': '[name=\x27parentComment.id\x27]'
    };
    var _0x4153c2 = _0x321d38['iVGcy']['split']('|'), _0x1d690b = 0x0;
    while (!![]) {
        switch (_0x4153c2[_0x1d690b++]) {
            case'0':
                console['log'](_0x3f508c);
                continue;
            case'1':
                var _0x3f508c = _0x321d38['HeFaY']($, _0x4f512a)['data'](_0x321d38['spJaG']);
                continue;
            case'2':
                _0x321d38['HeFaY']($, window)['scrollTo'](_0x321d38['HeFaY']($, _0x321d38['ncEqy']), 0x1f4);
                continue;
            case'3':
                var _0x3f4f7e = _0x321d38['bmUZw']($, _0x4f512a)['data'](_0x321d38['GosxG']);
                continue;
            case'4':
                _0x321d38['bmUZw']($, _0x321d38['ojyvd'])['attr'](_0x321d38['uzAtG'], _0x321d38['UGoJl']('@', _0x3f508c))['focus']();
                continue;
            case'5':
                _0x321d38['SJsRw']($, _0x321d38['BMfyE'])['val'](_0x3f4f7e);
                continue;
        }
        break;
    }
}

increaseViewCount();

function increaseViewCount() {
    var _0x547753 = {
        'phXPY': function (_0x2c0ee1, _0x2f87b7) {
            return _0x2c0ee1(_0x2f87b7);
        }, 'pRMne': '.views', 'MQdBm': 'viewId', 'ElsTJ': '获取数据出错!', 'LnJlb': function (_0x5694d3, _0x2622b8) {
            return _0x5694d3 != _0x2622b8;
        }, 'UGZdK': 'POST', 'nFSAq': '/addView', 'FNhau': 'json'
    };
    if (_0x547753['LnJlb']($['cookie'](_0x547753['MQdBm']), blogId)) {
        $['ajax']({
            'async': ![],
            'type': _0x547753['UGZdK'],
            'url': _0x547753['nFSAq'],
            'data': {'id': blogId},
            'dataType': _0x547753['FNhau'],
            'success': function (_0x1d5219) {
                _0x547753['phXPY']($, _0x547753['pRMne'])['html'](_0x1d5219);
                $['cookie'](_0x547753['MQdBm'], blogId, {'path': '/'});
            },
            'error': function () {
                _0x547753['phXPY'](alert, _0x547753['ElsTJ']);
            }
        });
    }
}

$('#like_add')['click'](function () {
    var _0x4cb468 = {
        'emvlb': 'like_add', 'WADFd': 'countLike', 'deLPk': function (_0x6ee263, _0xc0c7e4) {
            return _0x6ee263 != _0xc0c7e4;
        }, 'gQVYx': 'likeId', 'YCDvV': function (_0x232617, _0x44591d, _0x458206, _0x1a33db) {
            return _0x232617(_0x44591d, _0x458206, _0x1a33db);
        }, 'itOwV': 'data-content', 'MTKBe': '你当前还没登录,无法点赞', 'lXVHD': function (_0x17f14a, _0x3379fe) {
            return _0x17f14a(_0x3379fe);
        }, 'ZnNtx': '#like_add'
    };
    var _0x169ecf = document['getElementById'](_0x4cb468['emvlb']);
    var _0x2eb0c2 = document['getElementById'](_0x4cb468['WADFd']);
    if (_0x4cb468['deLPk']($['cookie'](_0x4cb468['gQVYx']), blogId)) {
        if (!hasUser) {
            _0x4cb468['YCDvV'](postBlogAjax, 0x1, _0x169ecf, _0x2eb0c2);
        } else {
            _0x169ecf['setAttribute'](_0x4cb468['itOwV'], _0x4cb468['MTKBe']);
        }
    }
    _0x4cb468['lXVHD']($, _0x4cb468['ZnNtx'])['popup']();
});
$('#opposition_add')['click'](function () {
    var _0xbda76e = {
        'wkmzn': 'opposition_add', 'PXHTe': 'countOpposition', 'QvLuC': function (_0x54e22a, _0x482b63) {
            return _0x54e22a != _0x482b63;
        }, 'ClCZO': 'likeId', 'QbJIw': function (_0xd61974, _0x11af7b, _0x51cfee, _0x39bf70) {
            return _0xd61974(_0x11af7b, _0x51cfee, _0x39bf70);
        }, 'HsWQc': 'data-content', 'ptDzr': '你当前还没登录,无法反对', 'SjiyU': function (_0x162153, _0x5a6f4a) {
            return _0x162153(_0x5a6f4a);
        }, 'YYcND': '#opposition_add'
    };
    var _0x53a506 = document['getElementById'](_0xbda76e['wkmzn']);
    var _0x45c819 = document['getElementById'](_0xbda76e['PXHTe']);
    if (_0xbda76e['QvLuC']($['cookie'](_0xbda76e['ClCZO']), blogId)) {
        if (!hasUser) {
            _0xbda76e['QbJIw'](postBlogAjax, 0x2, _0x53a506, _0x45c819);
        } else {
            _0x53a506['setAttribute'](_0xbda76e['HsWQc'], _0xbda76e['ptDzr']);
        }
    }
    _0xbda76e['SjiyU']($, _0xbda76e['YYcND'])['popup']();
});

function postBlogAjax(_0x412555, _0x39d39d, _0x1aedd1) {
    var _0x17a8a1 = {
        'MVKgg': function (_0x50b4d0, _0x163584) {
            return _0x50b4d0 == _0x163584;
        }, 'lktox': '200', 'RaBsV': function (_0x14f0e8, _0x5763ce) {
            return _0x14f0e8 + _0x5763ce;
        }, 'DbieH': function (_0x20d280, _0x1d5220) {
            return _0x20d280(_0x1d5220);
        }, 'pLfBt': 'data-content', 'mmiew': 'likeId', 'qGPIq': 'POST', 'VrgVR': '/addlike', 'wfpEk': 'json'
    };
    $['ajax']({
        'async': ![],
        'type': _0x17a8a1['qGPIq'],
        'url': _0x17a8a1['VrgVR'],
        'data': {'id': blogId, 'typeId': _0x412555},
        'dataType': _0x17a8a1['wfpEk'],
        'success': function (_0xef938c) {
            if (_0x17a8a1['MVKgg'](_0xef938c['code'], _0x17a8a1['lktox'])) {
                _0x1aedd1['innerText'] = _0x17a8a1['RaBsV'](_0x17a8a1['DbieH'](Number, _0x1aedd1['innerText']), 0x1);
            }
            _0x39d39d['setAttribute'](_0x17a8a1['pLfBt'], _0xef938c['message']);
            $['cookie'](_0x17a8a1['mmiew'], blogId, {'path': '/'});
        }
    });
}