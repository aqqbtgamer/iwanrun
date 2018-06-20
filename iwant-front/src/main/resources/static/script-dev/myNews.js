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
                securityanswer: ''
            },
            List: [
                {
                    time: '2017年10月12日',
                    username: '理查德',
                    content: '怎么说怎么说怎么说',
                    status: true
                },
                {
                    time: '2017年10月12日',
                    username: '理查德',
                    content: '怎么说怎么说怎么说',
                    status: false
                },
                {
                    time: '2017年10月12日',
                    username: '理查德',
                    content: '怎么说怎么说怎么说',
                    status: true
                },
                {
                    time: '2017年10月12日',
                    username: '理查德',
                    content: '怎么说怎么说怎么说',
                    status: false
                }
            ]
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
            }

        }
    }
);
console.log("Vue 脚本绑定渲染完成..............");