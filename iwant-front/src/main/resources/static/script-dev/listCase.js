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
            numberPages:[1],// 分页模块      页面显示哪些值
            pageSizeFront:5,// 分页模块      页面显示几个值
            currentPFront:1,
            pages:0,
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
//        	numberPages:function(){//计算页数
//        		var number = 1;
//        		var total = this.pageInfo.total;
//        		var pageSize = this.pageInfo.pageSize;
//        		if( total != '' && total != undefined && pageSize != '' && pageSize != undefined){
//            		number = Math.floor(total/pageSize);//向下舍入
//        		}
//        		if( number == 0){//至少显示一页
//        			number=1;
//        		}
//        		return 13;
//        	}
        },
        watch:{
        	pageInfo:function(newVal,oldVal){
        		var vm = this;
    			if( oldVal.total != newVal.total ){
    				vm.numberPagesInit();
    			}
        	},
        	indexClick:function(newVal,oldVal){
        		var vm = this;
        		var param1 = parseInt(vm.pageSizeFront*vm.currentPFront)+parseInt(1);
        		var param2 = parseInt(vm.pageSizeFront*(parseInt(vm.currentPFront)-parseInt(1)));
    			if( oldVal != newVal && newVal ==param1){
    				vm.currentPFront =parseInt( vm.currentPFront) + parseInt( 1);
    			}
    			if( oldVal != newVal && newVal ==param2){
    				vm.currentPFront =parseInt( vm.currentPFront) - parseInt( 1);
    			}
        	},
        	currentPFront:function(newVal,oldVal){
        		var vm = this;
    			if( oldVal != newVal){
    				vm.numberPagesCat();
    			}
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
                vm.queryCaseByCondition("1");
                
            },
            queryCaseByCondition(pageIndex){
            	var vm = this;
            	var url="../../case/queryCaseByCondition";
            	vm.indexClick=pageIndex;
            	var param = vm.searchCriteria;
            	param.pageIndex=pageIndex-1;
            	axios.post(url,param).then(
            			function(response){
            				console.log(response.data);
            				var list = response.data;
            				if( list != ''){
            					vm.List=list.content;
            					vm.pageInfo=list.pageInfo;
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
            numberPagesInit:function(){ // 分页模块      页面显示值初始化
        		var vm = this;
        		var pages;
        		var total = vm.pageInfo.total;
        		var pageSize = vm.pageInfo.pageSize;
        		if( total != '' && total != undefined && pageSize != '' && pageSize != undefined){
        			pages = Math.floor(total/pageSize);//向下舍入     总页数
        			vm.pages=pages;
            		if( pages <= vm.pageSizeFront){ 
            			for(var i=0;i<pages;i++){
            				vm.numberPages.push(i+1);
            			}
            		}else{
            			vm.numberPages=[];
            			for(var i=0;i<vm.pageSizeFront;i++){
            				vm.numberPages.push(i+1);
            			}
            		}
        		}
        	},
        	numberPagesCat:function(){ // 分页模块      页面显示值初始化
        		var vm = this;
        		var pages=vm.pages;
        		if( vm.currentPFront > 1){
        			vm.numberPages=[];
        			//pagePart =  Math.floor(pages/vm.pageSizeFront);
        			var currentPFrontA = parseInt(vm.currentPFront)-parseInt(1);
        			var result1 =  parseInt(vm.pageSizeFront*currentPFrontA)+parseInt(1);
        			
        			var result2 =  parseInt(vm.pageSizeFront*vm.currentPFront);
        			if(  pages >= result1 && pages <= result2){
        				for(var j=result1;j<=pages;j++){
                			vm.numberPages.push(j);
            			}
        			}
        			if(  pages >= result2 ){
        				for(var i=result1;i<=result2;i++){
                			vm.numberPages.push(i);
            			}
        			}
        		}else{
        			vm.numberPages=[];
        			for(var i=0;i<vm.pageSizeFront;i++){
        				vm.numberPages.push(i+1);
        			}
        		}
        	},
            nextPageClick:function(){
            	var vm = this;
            	if( vm.indexClick < vm.pages){//vm.pages  当前页是最后一页，点击无效
            		vm.indexClick = parseInt(vm.indexClick)+parseInt(1);//当前页加一
                	vm.queryCaseByCondition(vm.indexClick);
            	}
            },
            previousPageClick:function(){
            	var vm = this;
            	if(vm.indexClick !=1){//当前页是第一页，点击无效
            		vm.indexClick = parseInt(vm.indexClick)-parseInt(1);//当前页减一
                	vm.queryCaseByCondition(vm.indexClick);
            	}
            	
            }
        },
        created: function(){
        	var vm = this;
        	vm.queryDictionaryList();
        	vm.queryCaseByCondition("1");
        }
    }
);
console.log("Vue 脚本绑定渲染完成..............");