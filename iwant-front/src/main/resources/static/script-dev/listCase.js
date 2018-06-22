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
                location: [  
                    { id: 1, value: '上海市区',dbCode:1,code:1 },
                    { id: 2, value: '周边城市',dbCode:1,code:2 },
                    { id: 3, value: '境外城市',dbCode:1,code:3 }
                ],
                activitytype: [],
                companytype: [],
                personNum: [],
                duration: [],
                price: [
                    { id: 0, value: '200以下' ,dbCode:6,code:1 },
                    { id: 1, value: '200-300' ,dbCode:6,code:2},
                    { id: 2, value: '300-400' ,dbCode:6,code:3},
                    { id: 3, value: '400-500' ,dbCode:6,code:4},
                    { id: 4, value: '500以上' ,dbCode:6,code:5}
                ]
            },
            List: [
               
            ],
            pageInfo:{
            },
            search: {
                criteria: {
                    location: [],
                    activitytype: [],
                    companytype: [],
                    personNum: [],
                    duration: [],
                    price: []
                }
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
        		return number;
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
                    vm.search.criteria[name].push(val);
                } else {
                    $dom.removeClass('icon-checkbox-blank').addClass('icon-checkbox-non');
                    var index = vm.search.criteria[name].indexOf(val);
                    vm.search.criteria[name].splice(index, 1);
                }
                console.log(vm.search.criteria[name]);
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