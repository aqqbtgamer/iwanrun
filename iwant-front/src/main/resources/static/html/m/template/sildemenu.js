var sildemenutemplate =
    '<div class="sildeMenu w100p h100p pf t0 r0" v-show="show"> '
    + '<div class="w610 h100p bgcffffff fr pa t0 r0 zi2" >                                                              '
    + '<div class="" v-show="loginId&&accessToken">                                                                     '
    + '    <div class="w174 h174  ml218 mt90 ofh pr">                                                                   '
    + '      <i class="pa r10 t0"><img src="images/vip_img.png" class="w28 h28" alt=""></i>                           '
    + '        <img :src="account.headimg" class="object-fit-cover br50p" alt="">                                '
    + '          </div>                                                                                           '
    + '    <div class="fz38 tac mt20">{{ loginId }}</div>                                                       '
    + '  <div class="fz26 ccccccc tac mt10 mb20">{{ account.company.hasCredential?"已认证企业用户":"未认证企业用户"}}</div>  '
    + '</div>                                                                                               '
    + '          <div class="" v-show="!loginId||!accessToken" @click="showLogin">                      '
    + '            <div class="w174 h174  ml218 mt90 ofh pr">                                                       '
    + '      <img src="images/head_img_grey.png" class="object-fit-cover br50p" alt="">                           '
    + '        </div>                                                                                           '
    + '  <div class="fz34 tac mt20 mb20">点击登录</div>                                                       '
    + '</div>                                                                                                   '
    + '        <div class="w406 h2 bgcf5f5f5 m0a"></div>                                                                '
    + '      <ul>                                                                                                     '
    + '        <li class="tac lh100" >                                                                               '
    + '          <a href="javascript:void(0)" @click="location.href=\'myAccount.html\'">                          '
    + '                <img src="images/menu_icon_01.png" class="w36 h36" alt="">                               '
    + '          <span class="fz30 c333333">个人中心</span>                                                   '
    + '        </a>                                                                                         '
    + ' </li>                                                                                                '
    + '<li class="tac lh100">                                                                   '
    + '  <a href="ordersubmit.html">                                                                                   '
    + '    <img src="images/menu_icon_02.png" class="w36 h36" alt="">                                   '
    + '      <span class="fz30 c333333">提交需求</span>                                               '
    + '</a>                                                                                         '
    + '</li>                                                                                            '
    + '<li class="tac lh100">                                                                           '
    + '  <a href="tel:4008897003">                                                                               '
    + '    <img src="images/menu_icon_03.png" class="w36 h36" alt="">                               '
    + '      <span class="fz30 c333333">联系我们</span>                                           '
    + '</a>                                                                                         '
    //+ '</li>                                                                                            '
    //+ '                    <li class="tac lh100">                                                                        '
    //+ '                        <a href="###">                                                                            '
    //+ '                            <img src="images/menu_icon_04.png" class="w36 h36" alt="">                            '
    //+ '                                <span class="fz30 c333333">设置</span>                                            '
    //+ '                    </a>                                                                                          '
    //+ '                </li>                                                                                             '
    //+ '                                                                                                                  '
    + '            </ul>                                                                                                 '
    + '        </div>                                                                                                    '
    + '                <div class="sildeBg w100p h100p bgcffffff op60" @click="show=false"></div>              '
    + '    </div>';

//依赖login 
var sildemenu = new Vue({
    el: '#sildemenu',
    template: sildemenutemplate,
    data: {
        show: false,
        init: false,
        loginId: jQuery.cookie('loginId'),
        accessToken: jQuery.cookie('accessToken'),
        account: {
            headimg: 'images/head_img.png',
            nickname: jQuery.cookie('loginId'),
            phone: jQuery.cookie('loginId'),
            company: {
                licenses: [], hasCredential: false
            }
        }
    },
    components: {
        login: login
    },
    methods: {
        showLogin: function () {
            this.show = false;
            login.show = true;
        },
        getuser: function () {
            var vm = this, url = requestUrl.findMixedByLoginId, param = {};
            axios.post(url, param).then(function (response) {
                //console.log(response.data);
                var data = response.data;
                if (data) {
                    var errMsg = data.errMsg;
                    if (errMsg) {
                        jQuery.cookie('accessToken', '');
                        login.show = true;
                    }
                    vm.IsValidated = true;
                    var info = data.userInfo;
                    var headImgs = data.headImgs;
                    var companyCredentials = data.companyCredentials;
                    if (info) {
                        if (headImgs && headImgs.length > 0) {
                            vm.account.headimg = headImgs[0].filePath;
                        }
                        vm.account.nickname = info.name;
                        if (companyCredentials) {
                            for (var i = 0; i < companyCredentials.length; i++) {
                                vm.account.company.licenses.push(companyCredentials[i].filePath);
                            }
                            vm.account.company.hasCredential = true;
                        }
                    }
                }
            })
        }
    },
    created: function () {
        var vm = this;
        if (vm.loginId && vm.accessToken) {
            vm.getuser();
        }
    }
})