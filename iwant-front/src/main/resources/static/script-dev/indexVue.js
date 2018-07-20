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
				productIndexList : [ {
					id : 1,
					img : 'img/normalpd1.png',
					text : '长沙南区10公里员工慢跑',
					price : '288/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}],
				locationIndexList : [
						{
							id : 1,
							img : 'img/location1.png',
							text : '夏日清凉专场 | 水上球类运动',
							long_text : 'You may of heard fast growing companies require motivated and healthy employees that communicate efficiently. Clearly it’s easier than ever to speak to one another online but we’ve found that doesn’t create meaningful relationships.'
						},
						{
							id : 1,
							img : 'img/location1.png',
							text : '夏日清凉专场 | 水上球类运动',
							long_text : 'You may of heard fast growing companies require motivated and healthy employees that communicate efficiently. Clearly it’s easier than ever to speak to one another online but we’ve found that doesn’t create meaningful relationships.'
						},
						{
							id : 1,
							img : 'img/location1.png',
							text : '夏日清凉专场 | 水上球类运动',
							long_text : 'You may of heard fast growing companies require motivated and healthy employees that communicate efficiently. Clearly it’s easier than ever to speak to one another online but we’ve found that doesn’t create meaningful relationships.'
						} ],
				caseIndexList : [ {
					id : 1,
					img : 'img/case1.png',
					isBig : true
				}, {
					id : 1,
					img : 'img/case2.png',
					isBig : false
				}, {
					id : 1,
					img : 'img/case3.png',
					isBig : false
				}, {
					id : 1,
					img : 'img/case4.png',
					isBig : false
				}, {
					id : 1,
					img : 'img/case5.png',
					isBig : false
				}, {
					id : 1,
					img : 'img/case6.png',
					isBig : true
				} ]
			},
			created: function(){
	        	var vm = this;
	        	vm.queryProdutionByCondition('1');
	        	vm.queryLocationByCondition('1');
	        	vm.queryCaseByCondition("1");
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
console.log("Vue 脚本绑定渲染完成..............");