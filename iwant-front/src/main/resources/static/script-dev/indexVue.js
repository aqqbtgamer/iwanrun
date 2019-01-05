/**
 * Created by WXP22 on 2018/3/2.
 */
console.log("Vue 脚本初始化..............");

var appIndex = new Vue(
		{
			el : "#container",
			data : {
				mask : false,
				bannerList : [ 'img/banner/banner1.png' ],
				loginId : '18018336171',
				loginBtnUl : true,
                loginIdUl: false,
				productIndexList : [ ],
				locationIndexList : [],
				caseIndexList : [],
                news: [],
                newsIndex: 0,
                trades: [],
                tradeIndex: 0,
                partners:[],
                tailWeixinIcon:false
			},
			created: function(){
	        	var vm = this;
	        	vm.queryProdutionByCondition('1');
	        	vm.queryLocationByCondition('1');
                vm.queryCaseByCondition("1");
                vm.queryLatestOrders();
                vm.queryPartners();
                vm.queryLatestNews();
	        },
			methods : {
				showLogin : function(flag) {
					
					lrApp.showLogin(flag);
				},
				toOrderSubmit : function() {
					var vm = this;
					window.location.href = "html/iwantrun/ordersubmit.html"
                },
                queryCaseByCondition:function(pageIndex){
                	var vm = this;
                	var url="case/queryCaseByCondition";
                	var param={};
                	param.pageIndex=pageIndex-1;
                	axios.post(url,param).then(
                			function(response){
                				console.log(response.data);
                				var list = response.data;
                				if( list != ''){
                					vm.caseIndexList=list.content;
                					if( vm.caseIndexList != '' && vm.caseIndexList.length>0){
                						for(var i=0;i<vm.caseIndexList.length;i++){
                							if(i==0 || i==5){
                								vm.caseIndexList[i].isBig=true;
                							}
                							if(i==1 || i==2|| i==3|| i==4){
                								vm.caseIndexList[i].isBig=false;
                							}
                							
                						}
                					}
                				}
                				
                	})
                },
                queryProdutionByCondition:function(pageIndex){
                	var vm = this;
                	var url="production/queryProdutionByCondition";
                	var param = {};
                	param.pageIndex=pageIndex-1;
                	axios.post(url,param).then(
                			function(response){
                				console.log(response.data);
                				var list = response.data;
                				if( list != ''){
                					vm.productIndexList=list.content;
                				}
                				
                	})
                },
                queryLocationByCondition:function(pageIndex){
                	var vm = this;
                	var url="location/querylocationByCondition";
                	var param = {};
                	param.pageIndex=pageIndex-1;
                	axios.post(url,param).then(
                			function(response){
                				console.log(response.data);
                				var list = response.data;
                				if( list != ''){
                					vm.List=list.content;
                					vm.locationIndexList=list.content;
                				}
                				
                	})
                },
                preNews: function () {
                    var vm = this;
                    vm.newsIndex > 0 && vm.newsIndex--;
                },
                nextNews: function () {
                    var vm = this;
                    Array.isArray(vm.news) && vm.newsIndex < vm.news.length - 4 && vm.newsIndex++;
                },
                queryLatestOrders: function () {
                    var vm = this, url = "trade_status/query";
                    axios.post(url, {}).then(function (response) {
                        if(Array.isArray(response.data) && response.data.length > 0){
                        	for (var i = 0; i < response.data.length; i++) {
                                var tradeStatus = new Object();
                                tradeStatus.time = response.data[i].changeTime ;
                                tradeStatus.content = response.data[i].changeInfo ;
                                vm.trades.push(tradeStatus);
                            }
                        } 
                    });
                },
                queryPartners:function(){
                	var vm  = this ;
                	url = "webSiteCooperativeLogo/query";
                	 axios.post(url, {}).then(function (response) {
                         if(Array.isArray(response.data) && response.data.length > 0){
                         	for (var i = 0; i < response.data.length; i++) {
                                 var partner = new Object();
                                 partner.url = "http://"+response.data[i].url ;
                                 partner.logoPath = response.data[i].logoPath ;
                                 vm.partners.push(partner);
                             }
                         } 
                     });
                },
                queryLatestNews:function(){
                	var vm  = this ;
                	url = "websiteNews/query";
                	 axios.post(url, {}).then(function (response) {
                         if(Array.isArray(response.data) && response.data.length > 0){
                         	for (var i = 0; i < response.data.length; i++) {
                                 var news = new Object();
                                 news.time = response.data[i].newsDate ;
                                 news.content = response.data[i].newsContent ;
                                 vm.news.push(news);
                             }
                         } 
                     });
                	
                },
                preTrade: function () {
                    var vm = this;
                    vm.tradeIndex > 0 && vm.tradeIndex--;
                },
                nextTrade: function () {
                    var vm = this;
                    Array.isArray(vm.trades) && vm.tradeIndex < vm.trades.length - 4 && vm.tradeIndex++;
                },
                displayIcon:function(){
                	this.tailWeixinIcon =  true ;
                },
                hideIcon:function(){
                	this.tailWeixinIcon =  false ;
                },
                ingornedLongText:function(input){
                	if(input != null && input.length > 8){
                		return input.substr(0,11)+"...";
                	}else{
                		return input;
                	}
                }
			}
		});

function showLoginId(loginId){
	var vm = appIndex;
	vm.mask = false;
	vm.loginId = loginId;
	vm.loginIdUl = true;
	vm.loginBtnUl = false;
}

function clearLoginId() {
    var vm = appIndex;
    vm.loginId = false;
    vm.loginIdUl = false;
    vm.loginBtnUl = true;
}


function toDetail(type, id) {
	type && id && (window.location.href = "./html/iwantrun/productdetail.html?type="+type+"&id="+ id);
}
console.log("Vue 脚本绑定渲染完成..............");