/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
const activityTypeParam = {"name":"common","used_field":9,"field":"activityTypeList"};
const companyTypeParam = {"name":"common","used_field":24,"field":"companyTypeList"};
const groupNumberTypeParam = {"name":"common","used_field":22,"field":"groupNumberList"};
const peopleTagParam = {"name":"common","used_field":26,"field":"peopleTagList"};
const provinceParam = {"name":"common","used_field":9,"field":"provinceList"};
const dictParams = new Array(
		activityTypeParam,
		companyTypeParam,
		groupNumberTypeParam,
		peopleTagParam,
		provinceParam
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
            contractName:null,
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
            selectedActivityType:"",
            activityTypeList:[],
            companyTypeList:[],
            selectedCompanyType:"",
            groupNumberList:[],
            selectedGroupNumber:"",
            peopleTagList:[],
            selectedPeopleTag:"",
            distributionList:[],
            selectedDistribution:"",
            provinceList:[],
            selectedProvince:""
        },
        created:function(){
        	console.log("init server http data ....");
        	var vm = this ;
        	for(var i = 0 ; i<dictParams.length; i++ ){
        		var callback = new Object();
        		callback.request = dictParams[i] ;
        		callback.vm = vm ;
        		callback.success = function(result){
        			var field = this.request.field ;
        			var vm = this.vm ;
        			vm[field.toString()] = result ;
        		};  		
        		$http_form.post(dictionaryQueryUrl,callback);
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