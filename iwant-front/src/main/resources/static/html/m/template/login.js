var logintemplate =
    '<div class="login" v-show="show">                                                                                                                                                                                                             '
    + '<div class="pf bgcffffff w100p h100p pf t0 l0" style="z-index: 777">                                                                                                                                                           '
    + '<header class="w100p h80 bgcffffff pf t0 l0" >                                                                                                                                                                                 '
    + '     <a class="fl ml30 mt20" href="javascript:void(0)" @click="show=false" >                                                                                                                                              '
    + '         <img src="images/close_icon_a.png" class="w24 h24" alt="">                                                                                                                                                             '
    + '             </a>                                                                                                                                                                                                               '
    + '         </header >                                                                                                                                                                                                             '
    //非微信登陆
    + '<div v-show="model.showTab!=\'bindphone\'">'
    + ' <nav class="mt100 w670 ml40 pt18 ofh">                                                                                                                                                                                         '
    + '     <a :class="model.showTab==\'login\'?\'c333333 fz30 fl ml30 mr30 login-active\':\'c333333 fz30 fl ml30 mr30 \'" href="javascript:void(0)" @click="switchLogin(\'login\')">登 录</a>                                                      '
    + ' <a :class="model.showTab==\'register\'?\'c333333 fz30 fl ml30 mr30 login-active\':\'c333333 fz30 fl ml30 mr30\'" href="javascript:void(0)" @click="switchLogin(\'register\')" > 注 册</a >                                                        '
    + ' <a :class="model.showTab==\'forgetpassword\'?\'c333333 fz30 fl ml30 mr30 login-active\':\'c333333 fz30 fl ml30 mr30\'" href="javascript:void(0)" @click="switchLogin(\'forgetpassword\')" > 忘记密码</a >                                                        '
    + '         </nav >                                                                                                                                                                                                                '
    + '         <!--登录-->                                                                                                                                                                                                            '
    + ' <section class="pr login_party" v-show="model.showTab==\'login\'">                                                                                                                                                                '
    + '     <div class="w750 h640 pa t0 l0 zi1"><img src="images/login_bg_01.png" class="object-fit-cover" alt=""></div>                                                                                                               '
    + '         <div class="tac pt100 pr zi2">                                                                                                                                                                                         '
    + '             <div class="login_label w380 ofh m0a">                                                                                                                                                                             '
    + '                 <a href="javascript:void(0)" :class="model.isbymess?\'login_label_btn login_label_btn_01\':\'login_label_btn login_label_btn_01 label_active\'" @click="switchLogintype(false)">已有登录账号</a>                   '
    + '             <a href="javascript:void(0)" :class="model.isbymess?\'login_label_btn login_label_btn_02 label_active\':\'login_label_btn login_label_btn_02 \'" @click="switchLogintype(true)">动态码登录</a>                         '
    + '     </div>                                                                                                                                                                                                                     '
    + '     <!--已有账号登录-->                                                                                                                                                                                                        '
    + '                 <div class="login_area_01 pt40" v-show="!model.isbymess">                                                                                                                                                      '
    + '         <div class="w596 h78 br10 bgcf5f5f5 m0a tac">                                                                                                                                                                          '
    + '             <input type="text" class="w540 h60 lh60 mt8" v-model="loginId" placeholder="请输入手机号" />                                                                                                                       '
    + '         </div>                                                                                                                                                                                                                 '
    + '         <div class="w596 m0a">                                                                                                                                                                                                 '
    + '             <div class="w596 h78 br10 bgcf5f5f5 mt30 tac">                                                                                                                                                                     '
    + '                 <input type="password" class="w540 h60 lh60 mt8" v-model="model.password" placeholder="请输入密码" />                                                                                                          '
    + '             </div>                                                                                                                                                                                                             '
    + '         </div>                                                                                                                                                                                                                 '
    + '         <div class="w596 m0a ofh" v-show="model.errMsg">                                                                                                                                                                       '
    + '             <span style="color:red;" class="c888888 fz22 mt20 fl">{{ model.errMsg }}</span>                                                                                                                                    '
    + '         </div>                                                                                                                                                                                                                 '
    + '         <div class="w596 m0a ofh">                                                                                                                                                                                             '
    + '             <a href="javascript:void(0)" @click="login" class="db w596 h78 lh78 tac cffffff fz36 login_btn fl mt76">登 录</a>                                                                                                  '
    + '     </div>                                                                                                                                                                                                                     '
    //+ '     <div class="w596 m0a ofh">                                                                                                                                                                                                 '
    //+ '         <a href="javascript:void(0)" @click="switchLogin(\'forgetpassword\')" class="fz24 c333333 fr mt20">忘记密码</a>                                                                                                                                                       '
    //+ '     </div>                                                                                                                                                                                                                     '
    + '                 </div>                                                                                                                                                                                                         '
    + ' <!--动态码登录-->                                                                                                                                                                                                              '
    + '     <div class="login_area_02 pt40" v-show="model.isbymess">                                                                                                                                                                   '
    + '         <div class="w596 h78 br10 bgcf5f5f5 m0a tac">                                                                                                                                                                          '
    + '             <input type="text" class="w540 h60 lh60 mt8" v-model="loginId" placeholder="请输入手机号" />                                                                                                                       '
    + '         </div>                                                                                                                                                                                                                 '
    + '         <div class="w596 m0a ofh">                                                                                                                                                                                             '
    + '             <div class="w412 h78 br10 bgcf5f5f5 tac mt30 fl">                                                                                                                                                                  '
    + '                 <input type="text" class="w350 h60 lh60 mt8" v-model="model.smsCode" placeholder="请输入验证码" />                                                                                                             '
    + '             </div>                                                                                                                                                                                                             '
    + '             <div class="w184 h78 lh78 tac fl mt30">                                                                                                                                                                            '
    + '                 <a href="javascript:void(0)" style="color: #f6b03e" @click="sendVerifyCode">{{ SMS.btnText }}</a>                                                                                                              '
    + '         </div>                                                                                                                                                                                                                 '
    + '     </div>                                                                                                                                                                                                                     '
    + '     <div class="w596 m0a ofh" v-show="model.errMsg">                                                                                                                                                                           '
    + '         <span style="color:red;" class="c888888 fz22 mt20 fl">{{ model.errMsg }}</span>                                                                                                                                        '
    + '     </div>                                                                                                                                                                                                                     '
    + '     <div class="w596 m0a ofh">                                                                                                                                                                                                 '
    + '         <a href="javascript:void(0)" @click="login" class="db w596 h78 lh78 tac cffffff fz36 login_btn fl mt76">登 录</a>                                                                                                      '
    + '                     </div >                                                                                                                                                                                                    '
    //+ ' <div class="w596 m0a ofh">                                                                                                                                                                                                     '
    //+ '     <a href="javascript:void(0)" @click="switchLogin(\'forgetpassword\')" class="fz24 c333333 fr mt20">忘记密码</a>                                                                                                                                                           '
    //+ ' </div>                                                                                                                                                                                                                         '
    + '                 </div >                                                                                                                                                                                                        '
    + '             </div >                                                                                                                                                                                                            '
    + '         </section >                                                                                                                                                                                                            '
    + '         <!--注册-->                                                                                                                                                                                                            '
    + ' <section class="pr re_party" v-show="model.showTab==\'register\'">                                                                                                                                                                    '
    + '     <div class="w750 h690 pa t0 l0 zi1"><img src="images/login_bg_02.png" class="object-fit-cover" alt=""></div>                                                                                                               '
    + '         <div class="tac pt40 pr zi2">                                                                                                                                                                                          '
    + '             <div class="login_area_02 pt40">                                                                                                                                                                                   '
    + '                 <div class="w596 h78 br10 bgcf5f5f5 m0a tac">                                                                                                                                                                  '
    + '                     <input type="text" class="w540 h60 lh60 mt8" v-model="loginId" placeholder="请输入手机号" />                                                                                                               '
    + '                 </div>                                                                                                                                                                                                         '
    + '                 <div class="w596 m0a ofh">                                                                                                                                                                                     '
    + '                     <div class="w412 h78 br10 bgcf5f5f5 tac mt30 fl">                                                                                                                                                          '
    + '                         <input type="text" class="w350 h60 lh60 mt8" v-model="model.smsCode" placeholder="请输入验证码" />                                                                                                     '
    + '                     </div>                                                                                                                                                                                                     '
    + '                     <div class="w184 h78 lh78 tac fl mt30">                                                                                                                                                                    '
    + '                         <a href="javascript:void(0)" style="color: #f6b03e" @click="sendVerifyCode">{{ SMS.btnText }}</a>                                                                                                      '
    + '                 </div>                                                                                                                                                                                                         '
    + '             </div>                                                                                                                                                                                                             '
    + '             <div class="w596 m0a ofh">                                                                                                                                                                                         '
    + '                 <div class="w596 h78 br10 bgcf5f5f5 mt30 tac">                                                                                                                                                                 '
    + '                     <input type="password" v-model="model.password" class="w540 h60 lh60 mt8" placeholder="请输入密码" />                                                                                                      '
    + '                 </div>                                                                                                                                                                                                         '
    + '             </div>                                                                                                                                                                                                             '
    + '             <div class="w596 m0a ofh">                                                                                                                                                                                         '
    + '                 <span class="c888888 fz22 mt20 fl">*8-20个字符：可由数字、字母、符号组成</span>                                                                                                                                '
    + '             </div>                                                                                                                                                                                                             '
    + '             <div class="w596 m0a ofh" v-show="model.errMsg">                                                                                                                                                                   '
    + '                 <span style="color:red;" class="c888888 fz22 mt20 fl">{{ model.errMsg }}</span>                                                                                                                                '
    + '             </div>                                                                                                                                                                                                             '
    + '             <div class="w596 m0a ofh">                                                                                                                                                                                         '
    + '                 <a href="javascript:void(0)" @click="register" class="db w596 h78 lh78 tac cffffff fz36 login_btn fl mt76">注册并登录</a>                                                                                      '
    + '         </div>                                                                                                                                                                                                                 '
    + '     </div>                                                                                                                                                                                                                     '
    + '             </div>                                                                                                                                                                                                             '
    + '         </section >                                                                                                                                                                                                            '
    + '                                                                                                                                                                                                                                '
    + '         <!--忘记密码-->                                                                                                                                                                                                            '
    + ' <section class="pr re_party" v-show="model.showTab==\'forgetpassword\'">                                                                                                                                                                    '
    + '     <div class="w750 h768 pa t0 l0 zi1"><img src="images/login_bg_03.png" class="object-fit-cover" alt=""></div>                                                                                                               '
    + '         <div class="tac pt40 pr zi2">                                                                                                                                                                                          '
    + '             <div class="login_area_02 pt40">                                                                                                                                                                                   '
    + '                 <div class="w596 h78 br10 bgcf5f5f5 m0a tac">                                                                                                                                                                  '
    + '                     <input type="text" class="w540 h60 lh60 mt8" v-model="loginId" placeholder="请输入手机号" />                                                                                                               '
    + '                 </div>                                                                                                                                                                                                         '
    + '                 <div class="w596 m0a ofh">                                                                                                                                                                                     '
    + '                     <div class="w412 h78 br10 bgcf5f5f5 tac mt30 fl">                                                                                                                                                          '
    + '                         <input type="text" class="w350 h60 lh60 mt8" v-model="model.smsCode" placeholder="请输入验证码" />                                                                                                     '
    + '                     </div>                                                                                                                                                                                                     '
    + '                     <div class="w184 h78 lh78 tac fl mt30">                                                                                                                                                                    '
    + '                         <a href="javascript:void(0)" style="color: #f6b03e" @click="sendVerifyCode">{{ SMS.btnText }}</a>                                                                                                      '
    + '                 </div>                                                                                                                                                                                                         '
    + '             </div>                                                                                                                                                                                                             '
    + '             <div class="w596 m0a ofh">                                                                                                                                                                                         '
    + '                 <div class="w596 h78 br10 bgcf5f5f5 mt30 tac">                                                                                                                                                                 '
    + '                     <input type="password" v-model="model.password" class="w540 h60 lh60 mt8" placeholder="请输入密码" />                                                                                                      '
    + '                 </div>                                                                                                                                                                                                         '
    + '             </div>                                                                                                                                                                                                             '
    + '             <div class="w596 m0a ofh">                                                                                                                                                                                         '
    + '                 <div class="w596 h78 br10 bgcf5f5f5 mt30 tac">                                                                                                                                                                 '
    + '                     <input type="password" v-model="model.rePassword" class="w540 h60 lh60 mt8" placeholder="请再次输入密码" />                                                                                                      '
    + '                 </div>                                                                                                                                                                                                         '
    + '             </div>                                                                                                                                                                                                             '
    + '             <div class="w596 m0a ofh">                                                                                                                                                                                         '
    + '                 <span class="c888888 fz22 mt20 fl">*8-20个字符：可由数字、字母、符号组成</span>                                                                                                                                '
    + '             </div>                                                                                                                                                                                                             '
    + '             <div class="w596 m0a ofh" v-show="model.errMsg">                                                                                                                                                                   '
    + '                 <span style="color:red;" class="c888888 fz22 mt20 fl">{{ model.errMsg }}</span>                                                                                                                                '
    + '             </div>                                                                                                                                                                                                             '
    + '             <div class="w596 m0a ofh">                                                                                                                                                                                         '
    + '                 <a href="javascript:void(0)" @click="modifyPwd" class="db w596 h78 lh78 tac cffffff fz36 login_btn fl mt76">修改密码</a>                                                                                      '
    + '         </div>                                                                                                                                                                                                                 '
    + '     </div>                                                                                                                                                                                                                     '
    + '             </div>                                                                                                                                                                                                             '
    + '         </section >                                                                                                                                                                                                            '
    + '                                                                                                                                                                                                                                '
    + '         <!--快速登录-->                                                                                                                                                                                                        '
    + ' <section class="w550 h174 pa b40 l100">                                                                                                                                                                                        '
    + '     <div class="tac fz24 c999999 ofh">                                                                                                                                                                                         '
    + '         <div class="w210 fl bb1se5e5e5 pt16"></div>                                                                                                                                                                            '
    + '         <div class="w130 fl">快速登录</div>                                                                                                                                                                                    '
    + '         <div class="w210 fl bb1se5e5e5 pt16"></div>                                                                                                                                                                            '
    + '     </div>                                                                                                                                                                                                                     '
    + '     <div class="tac mt30">                                                                                                                                                                                                     '
    //+ '         <a href="javascript:;" class="ml30 mr30">                                                                                                                                                                              '
    //+ '             <img src="images/icon_qq.png" class="w80 h80" alt="">                                                                                                                                                              '
    //+ '                 </a>                                                                                                                                                                                                           '
    + '             <a href="javascript:;" @click="wechatLogin" class="ml30 mr30">                                                                                                                                                                          '
    + '                 <img src="images/icon_weixin.png" class="w80 h80" alt="">                                                                                                                                                      '
    + '                 </a>                                                                                                                                                                                                           '
    + '             </div>                                                                                                                                                                                                             '
    + '         </section>   '
    + '          </div>  '
    //微信登陆绑手机号
    + '<div v-show="model.showTab==\'bindphone\'">'
    + ' <nav class="mt100 w670 ml40 pt18 ofh">                                                                                                                                                                                         '
    + '     <a class="c333333 fz30 fl ml30 mr30 login-active" href="javascript:void(0)" >绑定手机号</a>                                                      '
    + '         </nav >                                                                                                                                                                                                                '
    + ' <section class="pr re_party">                                                                                                                                                                    '
    + '     <div class="w750 h690 pa t0 l0 zi1"><img src="images/login_bg_02.png" class="object-fit-cover" alt=""></div>                                                                                                               '
    + '         <div class="tac pt40 pr zi2">                                                                                                                                                                                          '
    + '             <div class="login_area_02 pt40">                                                                                                                                                                                   '
    + '                 <div class="w596 h78 br10 bgcf5f5f5 m0a tac">                                                                                                                                                                  '
    + '                     <input type="text" class="w540 h60 lh60 mt8" v-model="loginId" placeholder="请输入手机号" />                                                                                                               '
    + '                 </div>                                                                                                                                                                                                         '
    + '                 <div class="w596 m0a ofh">                                                                                                                                                                                     '
    + '                     <div class="w412 h78 br10 bgcf5f5f5 tac mt30 fl">                                                                                                                                                          '
    + '                         <input type="text" class="w350 h60 lh60 mt8" v-model="model.smsCode" placeholder="请输入验证码" />                                                                                                     '
    + '                     </div>                                                                                                                                                                                                     '
    + '                     <div class="w184 h78 lh78 tac fl mt30">                                                                                                                                                                    '
    + '                         <a href="javascript:void(0)" style="color: #f6b03e" @click="sendVerifyCode">{{ SMS.btnText }}</a>                                                                                                      '
    + '                 </div>                                                                                                                                                                                                         '
    + '             </div>                                                                                                                                                                                                             '
    + '             <div class="w596 m0a ofh" v-show="model.errMsg">                                                                                                                                                                   '
    + '                 <span style="color:red;" class="c888888 fz22 mt20 fl">{{ model.errMsg }}</span>                                                                                                                                '
    + '             </div>                                                                                                                                                                                                             '
    + '             <div class="w596 m0a ofh">                                                                                                                                                                                         '
    + '                 <a href="javascript:void(0)" @click="wechatBindPhone" class="db w596 h78 lh78 tac cffffff fz36 login_btn fl mt76">绑定登录</a>                                                                                      '
    + '         </div>                                                                                                                                                                                                                 '
    + '     </div>                                                                                                                                                                                                                     '
    + '             </div>                                                                                                                                                                                                             '
    + '         </section >                                                                                                                                                                                                            '
    + '          </div>  '
    + '     </div>'
    + '     </div>';

var login = new Vue({
    el: '#login',
    template: logintemplate,
    data: {
        show: false,
        loginId: jQuery.cookie('loginId'),
        accessToken: jQuery.cookie('accessToken'),
        model: {
            showTab: 'login', //login register forgetpassword
            isbymess: false, //动态码登录，
            smsCode: '',//短信验证码
            errMsg: null,
            autoLogin: false,
            password: null,
            rePassword: null
        },
        SMS: {
            timer: null,
            disabled: false,
            count: 0,
            btnText: '发送验证码'
        },
        wechatInfo: null,
        callback: null
    },
    methods: {
        switchLogin: function (tab) {
            var vm = this;
            vm.model.showTab = tab;
            vm.model.errMsg = false;
        },
        switchLogintype: function (isbymess) {
            var vm = this;
            vm.model.isbymess = !!isbymess;
            vm.model.errMsg = false;
        },
        sendVerifyCode: function () {
            var TIME_COUNT = 60, vm = this, url = requestUrl.getSMSCode, param = {
                'mobile': vm.loginId
            };
            if (vm.SMS.count > 0) {
                return;
            }
            if (!vm.SMS.timer) {
                vm.SMS.count = TIME_COUNT;
                vm.SMS.disabled = true;
                vm.SMS.timer = setInterval(() => {
                    if (vm.SMS.count > 0 && vm.SMS.count <= TIME_COUNT) {
                        console.log(vm.SMS.count);
                        vm.SMS.btnText = vm.SMS.count + 's后重发';
                        vm.SMS.count--;
                    } else {
                        vm.SMS.disabled = false;
                        clearInterval(vm.SMS.timer);
                        vm.SMS.timer = null;
                        vm.SMS.btnText = '发送验证码';
                        vm.model.errMsg = null;
                    }
                }, 1000);
            }


            axios.post(url, param).then(function (response) {
                //console.log(response.data);
                var data = response.data;
                var encryptedSMSCode = $.cookie('encryptedSMSCode');
                if (data) {
                    var returnstatus = data.returnstatus;
                    var message = data.message;

                    if (returnstatus == 'Success') {
                        vm.model.errMsg = '短信已发送';
                    } else if (returnstatus == 'Faild') {
                        console.log(message);
                        vm.model.errMsg = '短信获取失败，请联系管理员';
                    } else {
                        if (message) {
                            vm.model.errMsg = message;
                        } else {
                            vm.model.errMsg = '短信获取失败，请重新获取';
                        }
                    }
                } else {
                    vm.model.errMsg = '短信获取失败，请重新获取';
                }
            });
        },
        login: function () {
            var vm = this, url = requestUrl.login, param = {
                smsCode: vm.model.smsCode,
                messageLogin: vm.model.isbymess,
                autoLogin: vm.model.autoLogin,
                account: {
                    loginId: vm.loginId,
                    smsCode: vm.model.smsCode,
                    password: vm.model.password,
                    mobileNumber: vm.loginId
                }
            };

            vm.model.errMsg = null;
            // 先校验手机号
            vm.model.errMsg = validate.isMobile(vm.loginId);
            if (vm.model.errMsg) {
                return false;
            }


            if (vm.model.isbymess) {
                //验证码
                vm.model.errMsg = validate.validateSMScode(vm.model.smsCode);
            } else {
                //密码
                vm.model.errMsg = validate.validatePwd(vm.model.password);
            }
            if (vm.model.errMsg) {
                return false;
            }

            axios.post(url, param).then(function (response) {
                console.log(response.data);
                var data = response.data;
                var messageBody = data.messageBody;
                if (messageBody) {
                    var message = JSON.parse(messageBody);
                    vm.model.errMsg = message.errorMsg;
                    if (vm.model.errMsg) {
                        return;
                    }
                }
                vm.accessToken = data.accessToken;
                jQuery.cookie('loginId', vm.loginId);
                jQuery.cookie('accessToken', vm.accessToken);
                vm.show = false;
                if (typeof vm.callback === 'function') {
                    vm.callback();
                }
            });
        },
        logout: function () {
            var vm = this;
            jQuery.cookie('loginId', '');
            jQuery.cookie('accessToken', '');
            vm.loginId = '';
            vm.accessToken = '';
            vm.show = false;
        },
        verify: function () {
            var vm = this;
            // 先校验手机号
            vm.model.errMsg = validate.isMobile(vm.loginId);
            if (vm.model.errMsg) {
                return false;
            }
            //验证码
            vm.model.errMsg = validate.validateSMScode(vm.model.smsCode);
            if (vm.model.errMsg) {
                return false;
            }
            //密码
            vm.model.errMsg = validate.validatePwd(vm.model.password);

            if (vm.model.errMsg) {
                return false;
            }

            return true;
        },
        register: function () {
            var vm = this, url = requestUrl.register, param = {
                smsCode: vm.model.smsCode,
                account: {
                    loginId: vm.loginId,
                    smsCode: vm.model.smsCode,
                    password: vm.model.password,
                    rePassword: vm.model.password,
                    mobileNumber: vm.loginId
                }
            };

            vm.model.errMsg = null;
            if (!vm.verify()) {
                return false;
            }

            axios.post(url, param).then(function (response) {
                console.log(response.data);
                var data = response.data;
                var messageBody = data.messageBody;
                if (messageBody) {
                    var message = JSON.parse(messageBody);
                    vm.model.errMsg = message.errorMsg;

                    if (vm.model.errMsg) {
                        return;
                    }
                }
                vm.accessToken = data.accessToken;
                jQuery.cookie('loginId', vm.loginId);
                jQuery.cookie('accessToken', vm.accessToken);
                vm.show = false;
                if (typeof vm.callback === 'function') {
                    vm.callback();
                }
            });
        },
        modifyPwd: function () {
            var vm = this, url = requestUrl.modifyPwd, param = {
                smsCode: vm.model.smsCode,
                account: {
                    loginId: vm.loginId,
                    smsCode: vm.model.smsCode,
                    password: vm.model.password,
                    rePassword: vm.model.rePassword,
                    mobileNumber: vm.loginId,
                    smsCode: vm.model.smsCode
                }
            };

            vm.model.errMsg = null;
            if (!vm.verify()) {
                return false;
            }

            //两次密码不一致
            if (vm.model.password !== vm.model.rePassword) {
                vm.model.errMsg = '您两次输入的密码不一致';
                return false;
            }

            axios.post(url, param).then(function (response) {
                console.log(response.data);
                var data = response.data;
                var messageBody = data.messageBody;
                if (messageBody) {
                    var message = JSON.parse(messageBody);
                    vm.model.errMsg = message.errorMsg;
                    if (vm.model.errMsg) {
                        return;
                    }
                }
                vm.accessToken = data.accessToken;
                jQuery.cookie('loginId', vm.loginId);
                jQuery.cookie('accessToken', vm.accessToken);
                vm.show = false;
                if (typeof vm.callback === 'function') {
                    vm.callback();
                }
            });

        },
        wechatLogin: function () {
            var appid = 'wx1fd018c55846c8c9';
            var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=' + appid + '&redirect_uri=' + encodeURIComponent(window.location.href) + '&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect';
            window.location.href = url;
        },
        wechatCallback: function () {
            var vm = this, openId = jQuery.cookie('openId'), param = vm.wechatInfo, url = requestUrl.mobileWeixinCallBack + '?openId=' + openId;//微信登陆回调
            if (param) {
                axios.post(url, param).then(function (response) {
                    console.log(response.data);
                    var data = response.data;
                    if (data && data.token) {
                        vm.accessToken = data.token;
                        jQuery.cookie('loginId', vm.loginId);
                        jQuery.cookie('accessToken', data.token);
                        vm.show = false;
                        if (typeof vm.callback === 'function') {
                            vm.callback();
                        }
                    }
                });
            }
        },
        wechatBindPhone: function () {
            var vm = this, openId = jQuery.cookie('openId');
            var url = requestUrl.bindMobileNumber + '?openId=' + openId + '&mobileNumber=' + vm.loginId;//微信号绑手机号
            axios.post(url).then(function (response) {
                console.log(response.data);
                if (response.data) {
                    vm.wechatCallback();
                }
            });
        }
    },
    created: function () {
        var vm = this, code = getUrlParam('code');
        if (code && !vm.accessToken) {
            var url = requestUrl.getMobileWeixinOpenid + '?code=' + code;
            axios.post(url).then(function (response) {
                console.log(response.data);
                console.log(vm);
                var data = response.data;
                if (data && data.openid) {
                    jQuery.cookie('openId', data.openid);
                    vm.wechatInfo = data;
                    var openId = data.openid, url = requestUrl.checkMobileOpenIdExists + '?openId=' + openId;//判断微信号是否绑过手机号
                    if (openId) {
                        axios.post(url).then(function (response) {
                            console.log(vm);
                            console.log(response.data);
                            if (!response.data) { //绑定手机号
                                vm.show = true;
                                vm.model.showTab = 'bindphone';
                            } else {
                                var data = response.data;
                                if (data && data.mobileNumber) {
                                    jQuery.cookie('loginId', data.mobileNumber);
                                    vm.wechatCallback();
                                }
                            }
                        });
                    }
                }
            });
        }
    }
})
