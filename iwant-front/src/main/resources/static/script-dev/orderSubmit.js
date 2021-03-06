/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
const activityTypeParam = {"name":"common","used_field":9,"field":"activityTypeList"};
const companyTypeParam = {"name":"common","used_field":24,"field":"companyTypeList"};
const groupNumberTypeParam = {"name":"common","used_field":22,"field":"groupNumberList"};
const peopleTagParam = {"name":"common","used_field":26,"field":"peopleTagList"};
const provinceParam = {"name":"common","used_field":6,"field":"provinceList"};
const durationparam = {"name":"common","used_field":23,"field":"durationList"};
const simulatePriceparam = {"name":"production","used_field":13,"field":"simulatePriceList"};
const dictParams = new Array(
		activityTypeParam,
		companyTypeParam,
		groupNumberTypeParam,
		peopleTagParam,
		provinceParam,
		durationparam,
		simulatePriceparam
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
            contractMobile:null,
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
            productionList:{},
            locationList:{},
            caseList:{},
            productionCurrentPage:1,
            locationCurrentPage:1,
            caseCurrentPage:1,
            pageSize:3,
            total:1,
            productionMaxPage:1,
            locationMaxPage:1,
            caseMaxPage:1,
            productionNext:true,
            locationNext:true,
            caseNext:true,
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
            selectedProvince:"",
            durationList:[],
            selectedDuration:"",
            simulatePriceList:[],
            selectedSimulatePrice:"",
            otherRequire:"",
            loginVerifyInteval:null,
            tailWeixinIcon:false,
            preferItem:{
            	id:getUrlParam('id'),
            	type:getUrlParam("type"),
            	perfered:false
            },
            nickname:''
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
        	vm.queryProdutionByCondition('1');
        	vm.queryLocationByCondition('1');
        	vm.queryCaseByCondition("1");
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
            },
            queryProdutionByCondition:function(pageIndex){
            	var vm = this;
            	var url="../../production/queryProdutionByCondition";
            	var param = {};
            	param.pageIndex=pageIndex-1;
            	param.pageSize=vm.pageSize;
            	axios.post(url,param).then(
            			function(response){
            				console.log(response.data);
            				var list = response.data;
            				if( list != ''){
            					vm.productionList=list.content;
            					for(var i = 0 ; i<vm.productionList.length ; i++){
            						if(vm.preferItem.type=='product' && vm.preferItem.id != null){
            							if(vm.productionList[i].id == vm.preferItem.id ){
            								vm.productionList[i].selected = true ;
            								vm.perfered = true ;
            							}
            						}else{
            							vm.productionList[i].selected = false ;
            						}
            						
            					}
            					vm.productionMaxPage=list.pageInfo.totalpage;//最大页数
            					if(vm.productionCurrentPage==vm.productionMaxPage){
            						vm.productionNext=false;
            					}
            					if( vm.productionMaxPage > vm.productionCurrentPage){
            	            		vm.productionNext=true;
            	            	}
            					if(!vm.perfered && vm.preferItem.type=='product'){
            						vm.productionNextClick();
            					}
            				}
            				
            				
            	})
            },
            queryLocationByCondition:function(pageIndex){
            	var vm = this;
            	var url="../../location/querylocationByCondition";
            	var param = {};
            	param.pageIndex=pageIndex-1;
            	param.pageSize=vm.pageSize;
            	axios.post(url,param).then(
            			function(response){
            				console.log(response.data);
            				var list = response.data;
            				if( list != ''){
            					vm.locationList=list.content;
            					for(var i = 0 ; i<vm.locationList.length ; i++){
            						if(vm.preferItem.type=='location' && vm.preferItem.id != null){
            							if(vm.locationList[i].id == vm.preferItem.id ){
            								vm.locationList[i].selected = true ;
            								vm.perfered = true ;
            							}
            						}else{
            							vm.locationList[i].selected = false ;
            						}
            						
            					}
            					vm.locationMaxPage=list.pageInfo.totalpage;//最大页数
            					if(vm.locationCurrentPage==vm.locationMaxPage){
            						vm.locationNext=false;
            					}
            					if(vm.locationMaxPage > vm.locationCurrentPage){
            	            		vm.locationNext=true;
            	            	}
            					if(!vm.perfered && vm.preferItem.type=='location'){
            						vm.locationNextClick();
            					}
            				}
            				
            	})
            },
            queryCaseByCondition:function(pageIndex){
            	var vm = this;
            	var url="../../case/queryCaseByCondition";
            	var param = {};
            	param.pageIndex=pageIndex-1;
            	param.pageSize=vm.pageSize;
            	axios.post(url,param).then(
            			function(response){
            				console.log(response.data);
            				var list = response.data;
            				if( list != ''){
            					vm.caseList=list.content;
            					vm.caseMaxPage=list.pageInfo.totalpage;//最大页数
            					for(var i = 0 ; i<vm.caseList.length ; i++){
            						if(vm.preferItem.type=='case' && vm.preferItem.id != null){
            							if(vm.caseList[i].id == vm.preferItem.id ){
            								vm.caseList[i].selected = true ;
            								vm.perfered = true ;
            							}
            						}else{
            							vm.caseList[i].selected = false ;
            						}
            						
            					}
            					
            					if(vm.caseCurrentPage==vm.caseMaxPage){
            						vm.caseNext=false;
            					}
            					if( vm.caseMaxPage > vm.caseCurrentPage ){
            	            		vm.caseNext=true;
            	            	}
            					if(!vm.perfered && vm.preferItem.type=='case'){
            						vm.caseNextClick();
            					}
            				}
            				
            	})
            },
            submitOrder:function(){
            	console.log("v-on click method :submit order");
            	var vm = this ;
            	if(lrApp == null){
            		alert("系统错误 无法提交订单")
            	}else{
            		
            		//校验
            		if(!verifyNotEmpty($.cookie("accessToken")) ||  !verifyNotEmpty(vm.loginId) ){
            			lrApp.showLogin();
            			vm.loginVerifyInteval = setInterval(function(){
            				verifyLoginTokenJob(vm);
            			},1000);
            		}else{
            			var verifyFieldList = new Array();
            			verifyFieldList.push({"id":"company_type","value":vm.selectedCompanyType});
            			verifyFieldList.push({"id":"contractName","value":vm.contractName});
            			verifyFieldList.push({"id":"peopleNum","value":vm.selectedGroupNumber});
            			verifyFieldList.push({"id":"contractMobile","value":vm.contractMobile});  
            			//verifyFieldList.push({"id":"people-special1","value":vm.selectedPeopleTag})
            			verifyFieldList.push({"id":"activity-type","value":vm.selectedActivityType});
            			verifyFieldList.push({"id":"province","value":vm.selectedProvince});
            			verifyFieldList.push({"id":"duration-type","value":vm.selectedDuration});
            			verifyFieldList.push({"id":"simulatePrice","value":vm.selectedSimulatePrice});
            			//verifyFieldList.push({"id":"activityDate","value":$("#activityDate").val()});
            			var verifyResult = verifyFieldsNotEmpty(verifyFieldList);
            			if(verifyResult.length > 0){
            				verifyFieldList.forEach(
            						function(item){
            							$("#"+item.id).removeClass("validate-error");
            						}
            				);
            				//$("#activityDate").attr("style","");
            				verifyResult.forEach(
            						function(item){
            							$("#"+item).addClass("validate-error");
            							/*if(item == "activityDate"){
            								$("#"+item).removeClass("validate-error");
            								//$("#"+item).attr("style","border:1px solid red");
            							}*/
            						}
            				);
            				alert("必填项没有全部填写或选择");
            			}else{
            				verifyFieldList.forEach(
            						function(item){
            							$("#"+item.id).removeClass("validate-error");
            						}
            				);
            				var order = new Object();
            				order.companyTypeId = vm.selectedCompanyType ;
            				order.contract = vm.contractName;
            				order.groupNumberCode = vm.selectedGroupNumber;
            				order.activity_code = vm.selectedActivityType;
            				order.peopleTagCode = vm.selectedPeopleTag;
            				order.contractMobile = vm.contractMobile;
            				order.activityProvinceCode = vm.selectedProvince;
            				order.activityDuringCode = vm.selectedDuration;
            				order.orderSimulatePriceCode = vm.selectedSimulatePrice;
            				order.activityStart = $("#activityDate").val();
            				order.otherRequest = vm.otherRequire;
            				var user = new Object();
            				user.loginId =  $.cookie("loginId");
            				//感兴趣内容
            				var selectProductList = [],selectLocationList=[],selectCaseList=[];
            				var selectProductInput = $("input[name='selectProduct']:checked");
            				var selectLocationInput = $("input[name='selectLocation']:checked");
            				var selectCaseInput = $("input[name='selectCase']:checked");
            				if(selectProductInput.length>0){
            					for(var i=0;i<selectProductInput.length;i++){
            						var selectProduct = {};
                					selectProduct.locatioCase='product';
                					selectProduct.locatioId=$(selectProductInput[i]).val();
                					selectProductList.push(selectProduct);
            					}
            				}
            				if(selectLocationInput.length>0){
            					for(var i=0;i<selectLocationInput.length;i++){
            						var selectLocation = {};
            						selectLocation.locatioCase='location';
            						selectLocation.locatioId=$(selectLocationInput[i]).val();
            						selectLocationList.push(selectLocation);
            					}
            				}
            				if(selectCaseInput.length>0){
            					for(var i=0;i<selectCaseInput.length;i++){
            						var selectCase = {};
            						selectCase.locatioCase='case';
            						selectCase.locatioId=$(selectCaseInput[i]).val();
            						selectCaseList.push(selectCase);
            					}
            				}
            				var request = new Object();
            				request.order = order ;
            				request.user = user ;
            				request.selectProductList=selectProductList;
            				request.selectLocationList=selectLocationList;
            				request.selectCaseList=selectCaseList;
            				var requestData = new Object();
            				requestData.requestJson = JSON.stringify(request);
            				var callback = new Object();
            				callback.request = requestData ;
            				callback.vm = vm ;
                    		callback.success = function(result){
                    			if(result != null && result.submitResult == true){
                    				alert("需求提交成功");
                    				window.location.href = "./orderlist.html";
                    			}
                    		}; 
            				$http_form.post(ordersubmit,callback);
            			}
            		}      
            	}
            },
            productionNextClick:function(){  
            	var vm=this;
            	vm.productionCurrentPage=vm.productionCurrentPage+1;
            	vm.queryProdutionByCondition(vm.productionCurrentPage);
            },
            locationNextClick:function(){
            	var vm=this;
            	vm.locationCurrentPage=vm.locationCurrentPage+1;
            	vm.queryLocationByCondition(vm.locationCurrentPage);
            },
            caseNextClick:function(){
            	var vm=this;
            	vm.caseCurrentPage=vm.caseCurrentPage+1;
            	vm.queryCaseByCondition(vm.caseCurrentPage);
            },
            productionBeforeClick:function(){  
            	var vm=this;
            	if( vm.productionCurrentPage >1){
            		vm.productionCurrentPage=vm.productionCurrentPage-1;
                	vm.queryProdutionByCondition(vm.productionCurrentPage);
            	}
            },
            locationBeforeClick:function(){
            	var vm=this;
            	if( vm.locationCurrentPage > 1){
            		vm.locationCurrentPage=vm.locationCurrentPage-1;
                	vm.queryLocationByCondition(vm.locationCurrentPage);
            	}
            },
            caseBeforeClick:function(){
            	var vm=this;
            	if( vm.caseCurrentPage >1){
            		vm.caseCurrentPage=vm.caseCurrentPage-1;
                	vm.queryCaseByCondition(vm.caseCurrentPage);
            	}
            },
            displayIcon:function(){
            	this.tailWeixinIcon =  true ;
            },
            hideIcon:function(){
            	this.tailWeixinIcon =  false ;
            },
            ingornedLongText:function(input){
            	if(input != null && input.length > 12){
            		return input.substr(0,11)+"...";
            	}else{
            		return input;
            	}
            },
            toDetailPage:function(id,type){            	
            	window.open(baseUrl+"html/iwantrun/productdetail.html?type="+type+"&id="+id);
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
	$http.post(baseUrl + 'purchaserAccount/findMixedByLoginId', null,displayNick);
}

function displayNick(data){
	if(data){
		var errMsg = data.errMsg;
		if(!errMsg){
			var info = data.userInfo;
			var vm = appMyAccount;
			vm.nickname= info.name;
		}
	}
}

function verifyLoginTokenJob(vm){
	console.log("checking login interval start :"+vm.loginVerifyInteval);
	var acessToken = $.cookie("accessToken");
	var loginId = $.cookie("loginId");
	if(acessToken != null && loginId != null){
		$http.post(tokenVerifyUrl,null,function(data){
			console.log("checking token result :"+data.token);
			if(data.token == "success"){
				clearInterval(vm.loginVerifyInteval);
				vm.submitOrder();
			}
		});
	}
}


console.log("Vue 脚本绑定渲染完成..............");