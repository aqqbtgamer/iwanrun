/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
var appIndex = new Vue(
    {
        el: "#container",
        data: {
            mask: false,
            loginWindow: false,
            autoLogin: false,
            loginBtnUl : true,
			loginIdUl : false,
            loginTitle: '用户登录',
            loginId:'18018336171',
            loginToken:'uuixooppasyytvdbftrraskm',
            loginRole:{id:1,role:'采购方'},
            page: 1,  //显示的是哪一页
            pageSize: 10, //每一页显示的数据条数
            total: 0, //记录总数
            maxPage:1,  //最大页数
            criteria: {},
            List: [],
            pageInfo:{},
            searchCriteria: {
            	activityProvinceCode:[],
				activitytype:[],
				personNum:[],
				duration:[],
				specialTagsCode:[],
				locationTypeCode:[]   
            },
            tailWeixinIcon:false,
            nickname:''
        },
        created:function(){
        	var vm = this;
        	vm.pageHandler(1);
        	vm.queryLocationByCondition('1');
        	vm.queryDictionaryList();
        	
        },
       
        methods: {
        	toOrderSubmit : function() {
				var vm = this;
				window.location.href = "./ordersubmit.html"
            },
            showLogin: function (flag) {
            	lrApp.showLogin(flag);
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
            checkchange: function ($event) {
                var vm = this, $dom = $($event.target), $input = $dom.siblings('input'), name = $input.attr('name'), val = $input.val();
                if ($dom.hasClass('icon-checkbox-non')) {
                    $dom.removeClass('icon-checkbox-non').addClass('icon-checkbox-blank');
                    vm.searchCriteria[name].push(val);
                } else {
                    $dom.removeClass('icon-checkbox-blank').addClass('icon-checkbox-non');
                    var index = vm.searchCriteria[name].indexOf(val);
                    vm.searchCriteria[name].splice(index, 1);
                }
                console.log(vm.searchCriteria[name]);
                //查询
                vm.queryLocationByCondition("1");
            },
            queryDictionaryList:function(){
            	var vm = this;
            	var url="../../location/locationSearchList";
            	var param = {
            		name:"common"	
            	};
            	axios.post(url,param).then(
            			function(response){
            				console.log(response.data);
            				var list = response.data;
            				if( list != ''){ 
            					vm.criteria.activityProvinceCode=list.activityProvinceCode;
            					vm.criteria.activitytype=list.activitytype;
            					vm.criteria.specialTagsCode=list.specialTagsCode;
            					vm.criteria.personNum=list.personNum;
            					vm.criteria.duration=list.duration;
            					vm.criteria.locationTypeCode=list.locationTypeCode;
            				}
            				
            	})
            	
            },
            queryLocationByCondition:function(pageIndex){
            	var vm = this;
            	var url="../../location/querylocationByCondition";
            	var param = vm.searchCriteria;
            	param.pageIndex=pageIndex-1;
            	param.pageSize=vm.pageSize;
            	axios.post(url,param).then(
            			function(response){
            				console.log(response.data);
            				var list = response.data;
            				if( list != ''){
            					vm.List=list.content;
            					vm.pageInfo=list.pageInfo;
            					vm.total=list.pageInfo.total;
            				}
            				
            	})
            },
           
            //pagehandler方法 跳转到page页
            pageHandler: function (page) {
            	//here you can do custom state update
            	var vm=this;
            	vm.page = page;
            	vm.queryLocationByCondition(vm.page);
            },
            toDetails:function(id){
            	console.log("v-on click toDetails :"+id);
            	window.location.href = "./productdetail.html?type=location&id="+id ;
            },
            displayIcon:function(){
            	this.tailWeixinIcon =  true ;
            },
            hideIcon:function(){
            	this.tailWeixinIcon =  false ;
            }
        	
        }
       
        
    });
console.log("Vue 脚本绑定渲染完成..............");

function showLoginId(loginId){
	var vm = appIndex;
	vm.mask = false;
	vm.loginId = loginId;
	vm.loginIdUl = true;
	vm.loginBtnUl = false;
	$http.post(baseUrl + 'purchaserAccount/findMixedByLoginId', null,displayNick);
}

function displayNick(data){
	if(data){
		var errMsg = data.errMsg;
		if(!errMsg){
			var info = data.userInfo;
			var vm = appIndex;
			vm.nickname= info.name;
		}
	}
}