$('.menu.toggle')['click'](function () {
    var _0x3d3532 = {
        'hgfce': function (_0x3ef053, _0x56494d) {
            return _0x3ef053(_0x56494d);
        }, 'DqTdM': '.m-item', 'zwihw': 'm-mobile-hide'
    };
    _0x3d3532['hgfce']($, _0x3d3532['DqTdM'])['toggleClass'](_0x3d3532['zwihw']);
});
$('#toTop-div')['click'](function () {
    var _0x364c6f = {
        'bxPvE': function (_0x315c6c, _0x384769) {
            return _0x315c6c(_0x384769);
        }
    };
    _0x364c6f['bxPvE']($, window)['scrollTo'](0x0, 0x1f4);
});
var waypoint = new Waypoint({
    'element': document['getElementById']('middle-content'), 'handler': function (_0x5e3047) {
        var _0x2c6c75 = {
            'EIWrz': function (_0x3d5b41, _0x49daf1) {
                return _0x3d5b41 == _0x49daf1;
            }, 'wccbN': 'down', 'ifePf': function (_0x27f480, _0x58db31) {
                return _0x27f480(_0x58db31);
            }, 'zwSWC': '#toolbar-list'
        };
        if (_0x2c6c75['EIWrz'](_0x5e3047, _0x2c6c75['wccbN'])) {
            _0x2c6c75['ifePf']($, _0x2c6c75['zwSWC'])['show'](0x64);
        } else {
            _0x2c6c75['ifePf']($, _0x2c6c75['zwSWC'])['hide'](0x1f4);
        }
    }
});
$['ajax']({
    'type': 'GET', 'url': '/links', 'dataType': 'json', 'success': function (_0x449ea5) {
        var _0x5554ca = {
            'fSoNe': 'link-a-div', 'SrEUe': function (_0x15948a, _0xfdc5b9) {
                return _0x15948a < _0xfdc5b9;
            }, 'PvsAv': '5|3|2|0|1|4', 'RtueY': '_blank', 'UZTls': 'href', 'osIYV': function (_0x13133f, _0x2ce354) {
                return _0x13133f(_0x2ce354);
            }, 'uFBCj': '#link-a-div\x20a', 'BGGdj': 'item'
        };
        var _0x1e1237;
        var _0x3c0005 = document['getElementById'](_0x5554ca['fSoNe']);
        for (var _0x44124b = 0x0; _0x5554ca['SrEUe'](_0x44124b, _0x449ea5['length']); _0x44124b++) {
            var _0x15a34c = _0x5554ca['PvsAv']['split']('|'), _0x2f89e7 = 0x0;
            while (!![]) {
                switch (_0x15a34c[_0x2f89e7++]) {
                    case'0':
                        _0x1e1237['innerText'] = _0x449ea5[_0x44124b]['friendName'];
                        continue;
                    case'1':
                        _0x3c0005['appendChild'](_0x1e1237);
                        continue;
                    case'2':
                        _0x1e1237['target'] = _0x5554ca['RtueY'];
                        continue;
                    case'3':
                        _0x1e1237['setAttribute'](_0x5554ca['UZTls'], _0x449ea5[_0x44124b]['url']);
                        continue;
                    case'4':
                        _0x5554ca['osIYV']($, _0x5554ca['uFBCj'])['addClass'](_0x5554ca['BGGdj']);
                        continue;
                    case'5':
                        _0x1e1237 = document['createElement']('a');
                        continue;
                }
                break;
            }
        }
    }
});
var flag = !![];
$('.contactusdiyou')['on']('click', function () {
    var _0x54fbc6 = {
        'HyvLs': function (_0x1f2148, _0x20c538) {
            return _0x1f2148(_0x20c538);
        },
        'oGwcG': '.hoverimg',
        'WvNsi': 'src',
        'FEkry': function (_0x5d98a2, _0x4be4ec) {
            return _0x5d98a2 + _0x4be4ec;
        },
        'FeUXw': function (_0xd683b9) {
            return _0xd683b9();
        },
        'JvEni': '/static/images/right_icon.png',
        'KcrAc': function (_0x5dd55d, _0xa91603) {
            return _0x5dd55d(_0xa91603);
        },
        'TeqAM': '.diyoumask',
        'jihjQ': function (_0x34aa34, _0x3de97c) {
            return _0x34aa34(_0x3de97c);
        },
        'QBMxO': '.contactusdiyou',
        'LViZL': '/static/images/left_icon.png',
        'foqAu': function (_0x370cc7, _0x262a8c) {
            return _0x370cc7(_0x262a8c);
        },
        'rxXUX': '-230px',
        'zAkZW': function (_0x58fa32, _0x4c305a) {
            return _0x58fa32(_0x4c305a);
        }
    };
    if (flag) {
        _0x54fbc6['HyvLs']($, _0x54fbc6['oGwcG'])['attr'](_0x54fbc6['WvNsi'], _0x54fbc6['FEkry'](_0x54fbc6['FeUXw'](getRootPath), _0x54fbc6['JvEni']));
        _0x54fbc6['KcrAc']($, _0x54fbc6['TeqAM'])['fadeIn']();
        _0x54fbc6['jihjQ']($, _0x54fbc6['QBMxO'])['animate']({'right': '0'}, 0x12c);
        flag = ![];
    } else {
        _0x54fbc6['jihjQ']($, _0x54fbc6['oGwcG'])['attr'](_0x54fbc6['WvNsi'], _0x54fbc6['FEkry'](_0x54fbc6['FeUXw'](getRootPath), _0x54fbc6['LViZL']));
        _0x54fbc6['foqAu']($, _0x54fbc6['QBMxO'])['animate']({'right': _0x54fbc6['rxXUX']}, 0x12c, function () {
        });
        _0x54fbc6['zAkZW']($, _0x54fbc6['TeqAM'])['fadeOut']();
        flag = !![];
    }
});

function getRootPath() {
    var _0x2246d2 = {
        'INBhx': function (_0x454f93, _0x52467b) {
            return _0x454f93 + _0x52467b;
        }
    };
    var _0x35d740 = window['location']['host'];
    return _0x2246d2['INBhx'](_0x2246d2['INBhx'](document['location']['protocol'], '//'), _0x35d740);
}

$['ajax']({
    'type': 'GET', 'url': '/scheduled', 'dataType': 'json', 'success': function (_0x37f7e3) {
        var _0x8cfc9e = {
            'sKNsu': 'selected_author', 'AagbC': function (_0x1b084a, _0x416615) {
                return _0x1b084a != _0x416615;
            }, 'sDLkQ': 'selected_source', 'QhKPC': 'selected_content'
        };
        var _0x5ad27b = document['getElementById'](_0x8cfc9e['sKNsu']);
        if (_0x8cfc9e['AagbC'](_0x5ad27b, null)) {
            document['getElementById'](_0x8cfc9e['sKNsu'])['innerText'] = _0x37f7e3['author'];
            document['getElementById'](_0x8cfc9e['sDLkQ'])['innerText'] = _0x37f7e3['source'];
            document['getElementById'](_0x8cfc9e['QhKPC'])['innerText'] = _0x37f7e3['content'];
        }
    }
});

function openWindow(_0x43d69f, _0x5312d0, _0x2f0622) {
    var _0x1ce508 = {
        'udEda': '2|1|0|4|3',
        'WVQEb': function (_0x13af45, _0x27c24d) {
            return _0x13af45 / _0x27c24d;
        },
        'tJtOn': function (_0x50bc8d, _0xa092e1) {
            return _0x50bc8d - _0xa092e1;
        },
        'jhfpL': function (_0x1d5435, _0x50bfa5) {
            return _0x1d5435 || _0x50bfa5;
        },
        'bosgc': function (_0x731e44, _0x250bf5) {
            return _0x731e44 || _0x250bf5;
        },
        'BcKwM': '_blank',
        'tnnuJ': function (_0x3076ba, _0xa83df8) {
            return _0x3076ba + _0xa83df8;
        },
        'iOcVC': function (_0x244729, _0x199e8a) {
            return _0x244729 + _0x199e8a;
        },
        'zjHCy': function (_0x5a3801, _0x287dcb) {
            return _0x5a3801 + _0x287dcb;
        },
        'RGRwF': function (_0x1d2144, _0x4719ba) {
            return _0x1d2144 + _0x4719ba;
        },
        'zEXCe': 'toolbar=yes,\x20location=yes,\x20directories=no,\x20status=no,\x20menubar=yes,\x20scrollbars=yes,\x20resizable=no,\x20copyhistory=yes,\x20left=',
        'qKfrY': ',\x20top=',
        'oNref': ',\x20width=',
        'xpjfj': ',\x20height=',
        'kDDEZ': function (_0x65d77d, _0x3789ef) {
            return _0x65d77d - _0x3789ef;
        }
    };
    var _0x5561f1 = _0x1ce508['udEda']['split']('|'), _0x1c363a = 0x0;
    while (!![]) {
        switch (_0x5561f1[_0x1c363a++]) {
            case'0':
                var _0x5d5d46 = _0x1ce508['WVQEb'](_0x1ce508['tJtOn'](window['screen']['width'], _0x5312d0), 0x2);
                continue;
            case'1':
                _0x2f0622 = _0x1ce508['jhfpL'](_0x2f0622, 0x190);
                continue;
            case'2':
                _0x5312d0 = _0x1ce508['bosgc'](_0x5312d0, 0x258);
                continue;
            case'3':
                window['open'](_0x43d69f, _0x1ce508['BcKwM'], _0x1ce508['tnnuJ'](_0x1ce508['iOcVC'](_0x1ce508['zjHCy'](_0x1ce508['zjHCy'](_0x1ce508['zjHCy'](_0x1ce508['RGRwF'](_0x1ce508['RGRwF'](_0x1ce508['zEXCe'], _0x5d5d46), _0x1ce508['qKfrY']), _0x124772), _0x1ce508['oNref']), _0x5312d0), _0x1ce508['xpjfj']), _0x2f0622));
                continue;
            case'4':
                var _0x124772 = _0x1ce508['WVQEb'](_0x1ce508['kDDEZ'](window['screen']['height'], _0x2f0622), 0x2);
                continue;
        }
        break;
    }
}

function githubLogin() {
    var _0x1be31c = {
        'gSOGi': function (_0x1d8e3c, _0x2332ae) {
            return _0x1d8e3c(_0x2332ae);
        },
        'aXsfc': function (_0x573712, _0x59cf18) {
            return _0x573712 + _0x59cf18;
        },
        'OYAZE': function (_0x1a5374, _0x22913b) {
            return _0x1a5374 + _0x22913b;
        },
        'shGPp': function (_0x73cbc3, _0x2d23a4) {
            return _0x73cbc3 + _0x2d23a4;
        },
        'KkZwV': function (_0x2197e2, _0x717a34) {
            return _0x2197e2 + _0x717a34;
        },
        'gIFsu': 'https://github.com/login/oauth/authorize?client_id=',
        'siSlz': '&redirect_uri=',
        'QkLLY': '&scope=user&state=',
        'izMMX': 'GET',
        'AqjdU': '/github/oauth',
        'RbblV': 'json'
    };
    $['ajax']({
        'type': _0x1be31c['izMMX'],
        'url': _0x1be31c['AqjdU'],
        'dataType': _0x1be31c['RbblV'],
        'success': function (_0x50780f) {
            _0x1be31c['gSOGi'](openWindow, _0x1be31c['aXsfc'](_0x1be31c['OYAZE'](_0x1be31c['shGPp'](_0x1be31c['KkZwV'](_0x1be31c['KkZwV'](_0x1be31c['gIFsu'], _0x50780f['clientId']), _0x1be31c['siSlz']), _0x50780f['redirectUrl']), _0x1be31c['QkLLY']), _0x50780f['status']));
        }
    });
}

console['log']('你在电脑前看这段文字，\x0a说明你也是个程序猿(*^▽^*)。\x0a不合群者，\x0a独来独往的人。\x0a必有过人之处，\x0a整天混在朋友之间的人绝对不可能有多大的能力。\x0a\x0a'), console['log']('%c-->代码改变世界的同时先改变你自己<--', 'color:red');


function qqLogin() {
    $.ajax({
        type: "GET",
        url: "/qq/oauth",
        dataType: "json",
        success: function (response) {
            var qqlogin='https://graph.qq.com/oauth2.0/authorize?response_type=code' + '&client_id=' + response.qqAppId + '&redirect_uri=' + response.backUrl + '&state=' + response.state;
            openWindow(qqlogin);
        }
    });
}

