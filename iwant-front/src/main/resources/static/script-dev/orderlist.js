/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
var appListProduction = new Vue(
    {
        el: "#container",
        data: {
            mask: false,
            loginWindow: false,
            autoLogin: false,
            loginTitle: '用户登录',
            loginId:'18018336171',
            loginToken:'uuixooppasyytvdbftrraskm',
            loginRole:{id:1,role:'采购方'},
            list: [
                {
                    orderid: '40020171014',
                    createdate: '2017年10月12日',
                    status: '已完成',
                    counselor: '咨询师001'
                }, {
                    orderid: '40020171014',
                    createdate: '2017年10月12日',
                    status: '未完成',
                    counselor: '咨询师001'
                },{
                    orderid: '40020171014',
                    createdate: '2017年10月12日',
                    status: '已完成',
                    counselor: '咨询师001'
                },{
                    orderid: '40020171014',
                    createdate: '2017年10月12日',
                    status: '已完成',
                    counselor: '咨询师001'
                },{
                    orderid: '40020171014',
                    createdate: '2017年10月12日',
                    status: '已完成',
                    counselor: '咨询师001'
                }, {
                    orderid: '40020171014',
                    createdate: '2017年10月12日',
                    status: '已完成',
                    counselor: '咨询师001'
                }, {
                    orderid: '40020171014',
                    createdate: '2017年10月12日',
                    status: '已完成',
                    counselor: '咨询师001'
                }, {
                    orderid: '40020171014',
                    createdate: '2017年10月12日',
                    status: '已完成',
                    counselor: '咨询师001'
                }
            ],
            pageInfo:{
            	currentIndex:1,
            	totalPage:5,
            	pageSize:10
            }
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