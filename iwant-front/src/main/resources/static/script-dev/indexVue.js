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
                }],
                news: [
                    { time: '2018-06-18', content: '轰趴集合了更多的互动好玩的元素，让员工人人参与，让领导与员工互动，达到了很好的效果。' },
                    { time: '2018-06-19', content: '门的轰趴集合了更多的互动好玩的元素，让员工人人参与，让领导与员工互动，达到了很好的效果。' },
                    { time: '2018-06-20', content: '近门的轰趴集合了更多的互动好玩的元素，让员工人人参与，让领导与员工互动，达到了很好的效果。' },
                    { time: '2018-06-21', content: '近期热门的轰多的互动好玩的元素，让员工人人参与，让领导与员工互动，达到了很好的效果。' },
                    { time: '2018-06-22', content: '近期热门的轰更多的互动好玩的元素，让员工人人参与，让领导与员工互动，达到了很好的效果。' },
                    { time: '2018-06-23', content: '好玩的元素，让员工人人参与，让领导与员工互动，达到了很好的效果。' },
                    { time: '2018-06-24', content: '，达到了很好的效果。' }
                ],
                newsIndex: 0,
                trades: [
                    { time: '2018-06-18', content: '138****8888购买了88888套餐。' },
                    { time: '2018-06-19', content: '138****8888购买了88888套餐。。。' },
                    { time: '2018-06-20', content: '138****8888购买了88888套餐近门的轰趴集合了更多的互动好玩的元素，让员工人人参与，让领导与员工互动，达到了很好的效果。' },
                    { time: '2018-06-21', content: '138****8888购买了88888套餐近期热门的轰多的互动好玩的元素，让员工人人参与，让领导与员工互动，达到了很好的效果。' },
                    { time: '2018-06-23', content: '138****8888购买了88888套餐好玩的元素，让员工人人参与，让领导与员工互动，达到了很好的效果。' },
                    { time: '2018-06-24', content: '138****8888购买了88888套餐，达到了很好的效果。' }
                ],
                tradeIndex: 0
			},
			created: function(){
	        	var vm = this;
	        	vm.queryProdutionByCondition('1');
	        	vm.queryLocationByCondition('1');
                vm.queryCaseByCondition("1");
                vm.queryLatestOrders();
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
                        Array.isArray(response.data) && response.data.length > 0 && function () {
                            vm.trades = response.data;
                        };
                    });
                },
                preTrade: function () {
                    var vm = this;
                    vm.tradeIndex > 0 && vm.tradeIndex--;
                },
                nextTrade: function () {
                    var vm = this;
                    Array.isArray(vm.trades) && vm.tradeIndex < vm.trades.length - 4 && vm.tradeIndex++;
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
console.log("Vue 脚本绑定渲染完成..............");