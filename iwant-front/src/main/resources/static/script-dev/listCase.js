/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
var appListCase = new Vue(
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
            indexClick:1,
            criteria: {
            },
            List: [
               
            ],
            pageInfo:{
            },
            searchCriteria: {
            	activityProvinceCode:[],
				activitytype:[],
				companytype:[],
				personNum:[],
				duration:[]
            }
        },
        computed: {
        	numberPages:function(){//计算页数
        		var number = 1;
        		var total = this.pageInfo.total;
        		var pageSize = this.pageInfo.pageSize;
        		if( total != '' && total != undefined && pageSize != '' && pageSize != undefined){
            		number = Math.floor(total/pageSize);//向下舍入
        		}
        		if( number == 0){//至少显示一页
        			number=1;
        		}
        		return 10;
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
                vm.queryCaseByCondition();
                
            },
            queryCaseByCondition(){
            	var vm = this;
            	var url="../../case/queryCaseByCondition";
            	var param = vm.searchCriteria;
            	param.pageIndex="0";
            	axios.post(url,param).then(
            			function(response){
            				console.log(response.data);
            				var list = response.data;
            				if( list != ''){
            					vm.criteria.activityProvinceCode=list.activityProvinceCode;
            					vm.criteria.activitytype=list.activitytype;
            					vm.criteria.companytype=list.companytype;
            					vm.criteria.personNum=list.personNum;
            					vm.criteria.duration=list.duration;
            				}
            				
            	})
            },
            queryDictionaryList:function(){
            	var vm = this;
            	var url="../../case/caseSearchList";
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
            					vm.criteria.companytype=list.companytype;
            					vm.criteria.personNum=list.personNum;
            					vm.criteria.duration=list.duration;
            				}
            				
            	})
            	
            },
            queryCaseList:function(pageIndex){
            	
            	var vm = this;
            	var url="../../case/queryCaseList";
            	var param = {
            			pageIndex:pageIndex-1	
            	};
            	vm.indexClick=pageIndex;
            	axios.post(url,param).then(
            			function(response){
            				var list = response.data;
            				if( list != ''){
            					vm.List=list.content;
            					vm.pageInfo=list.pageInfo;
            				}
            				
            	})
            	
            },
            nextPageClick:function(){
            	var vm = this;
            	if( vm.indexClick != 5){//vm.numberPages  当前页是最后一页，点击无效
            		vm.indexClick = parseInt(vm.indexClick)+parseInt(1);//当前页加一
                	vm.queryCaseList(vm.indexClick);
            	}
            },
            previousPageClick:function(){
            	var vm = this;
            	if(vm.indexClick !=1){//当前页是第一页，点击无效
            		vm.indexClick = parseInt(vm.indexClick)-parseInt(1);//当前页减一
                	vm.queryCaseList(vm.indexClick);
            	}
            	
            }
        },
        created: function(){
        	var vm = this;
        	vm.queryDictionaryList();
        	vm.queryCaseList("1");
        }
    }
);
console.log("Vue 脚本绑定渲染完成..............");