/**
 * Created by WXP22 on 2018/3/2.
 */
console.log("Vue 脚本初始化..............");
var appIndex = new Vue(
    {
        el:"#container",
        data:{
            mask:false,
            loginWindow:false,
            autoLogin:false,
            loginTitle:'用户登录',
            bannerList:['img/banner/banner1.png'],
            loginId:'18018336171',
            loginToken:'uuixooppasyytvdbftrraskm',
            loginRole:{id:1,role:'采购方'},
            productIndexList:[
            	{	
            		id:1,
            		img:'img/normalpd1.png',
            		text:'长沙南区10公里员工慢跑',
            		price:'288/人'
            	},
            	{
            		id:2,
            		img:'img/normalpd2.png',
            		text:'南湖水上龙舟游',
            		price:'388/人'
            	}
            ],
            locationIndexList:[
            	{
            		id:1,
            		img:'img/location1.png',
            		text:'夏日清凉专场 | 水上球类运动',
            		long_text:'You may of heard fast growing companies require motivated and healthy employees that communicate efficiently. Clearly it’s easier than ever to speak to one another online but we’ve found that doesn’t create meaningful relationships.'
            	}
            ],
            caseIndexList:[
            	{
            		id:1,
            		img:'img/case1.png'
            	}
            ]
        },
        methods:{
            showLogin:function(message){
                console.log("v-on  click method :showLogin");
                var vm = this
                vm.mask = true;
                vm.loginWindow = true ;
                vm.loginTitle = message ;
            },
            closeLogin:function(){
                console.log("v-on  click method :closeLogin");
                var vm = this;
                vm.mask = false ;
                vm.loginWindow = false;
            },
            changeAutoLogin:function(){
                var vm = this ;
                vm.autoLogin = !vm.autoLogin;
            },
            toOrderSubmit:function(){
                var vm = this  ;
                window.location.href = "html/iwantrun/ordersubmit.html"
            }
        }
    }
);
console.log("Vue 脚本绑定渲染完成..............");