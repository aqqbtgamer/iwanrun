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
            loginId:'18018336171',
            loginToken:'uuixooppasyytvdbftrraskm',
            loginRole:{id:1,role:'采购方'},
            order: {
                orderid: '40020171014',
                createdate: '2017年10月12日',
                status: '已完成',
                orderType:'户外拓展',
                location:'浙西大峡谷',
                duration:'2天',
                simulatedPrice:'800元/人',
                other:'其他需求'
            },
            activityList:[
            	{
            		id:1,
            		text:'野外拓展',
            		dbcode:1,
            		code:1
            	},
            	{
            		id:2,
            		text:'真人cs',
            		dbcode:1,
            		code:2
            	}
            ],
            companyTypeList:[
            	{
            		id:3,
            		text:'民营企业',
            		dbcode:2,
            		code:1
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