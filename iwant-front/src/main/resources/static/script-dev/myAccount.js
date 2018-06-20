/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
var appMyAccount = new Vue(
    {
        el: "#container",
        data: {
            mask: false,
            loginWindow: false,
            autoLogin: false,
            loginTitle: '用户登录',
            loginId: '18018336171',
            loginToken: 'uuixooppasyytvdbftrraskm',
            loginRole: { id: 1, role: '采购方' },
            account: {
                headimg: '../../img/accountImage.png',
                nickname: '用户001',
                phone: '180****6171',
                securityanswer: {
                    question: '',
                    answer: ''
                },
                company: {
                    name: '',
                    license: '../../img/accountImage.png',
                    type: '',
                    personNum: 100
                }
            },
            setting: false,
            settingTitle: '昵称',
            settingFlag: 0 //0:nickname;1:phone;2:securityanswer
        },
        methods: {
            showLogin: function (message) {
                console.log("v-on  click method :showLogin");
                var vm = this
                vm.mask = true;
                vm.loginWindow = true;
                vm.loginTitle = message;
            },
            closeLogin: function () {
                console.log("v-on  click method :closeLogin");
                var vm = this;
                vm.mask = false;
                vm.loginWindow = false;
            },
            changeAutoLogin: function () {
                var vm = this;
                vm.autoLogin = !vm.autoLogin;
            },
            uploadimg: function ($event) {
                var vm = this, $file = $($event.target).siblings('input:file');
                $file.trigger('click');
            },
            fileUpload: function ($event) {
                var vm = this, files = $event.target.files;

            },
            showSetting: function (flag) {
                flag = flag || 0;
                var vm = this;
                vm.setting = true;
                vm.settingFlag = flag;
                var title = {
                    0: '昵称',
                    1: '手机号',
                    2: '安全问题',
                    3: '公司信息',
                    4: '营业执照'
                };
                vm.settingTitle = title[flag];
            },
            closeSetting: function () {
                var vm = this;
                vm.setting = false;
            }

        }
    }
);
console.log("Vue 脚本绑定渲染完成..............");