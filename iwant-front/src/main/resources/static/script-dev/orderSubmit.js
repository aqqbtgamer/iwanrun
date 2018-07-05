/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
const companyTypeParam = {"name":"common","used_field":24,"field":"company_type"};
const groupNumberTypeParam = {"name":"common","used_field":22,"field":"peopleNum"};
const dictParams = new Array(
		companyTypeParam,
		groupNumberTypeParam
);

var appMyAccount = new Vue(
    {
        el: "#container",
        data: {
            mask: false,
            loginWindow: false,
            autoLogin: false,
            loginTitle: '用户登录',
            loginId:null,
            loginIdUl:false,
            loginBtnUl:true,
            loginToken:null,
            loginRole:null,
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
            ],
            dictionaryObjList:[]
            
        },
        created:function(){
        	console.log("init server http data ....");
        	var vm = this ;
        	for(var i = 0 ; i<dictParams.length; i++ ){
        		
        	}
        	
        },
        methods: {
            showLogin: function (message) {
                console.log("v-on  click method :showLogin");
                lrApp.showLogin(message);
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

function showLoginId(loginId){
	var vm = appMyAccount;
	vm.mask = false;
	vm.loginId = loginId;
	vm.loginIdUl = true;
	vm.loginBtnUl = false;
}
console.log("Vue 脚本绑定渲染完成..............");