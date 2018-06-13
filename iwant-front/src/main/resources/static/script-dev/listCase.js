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
                {
                    img: '../../img/list-case1.png',
                    title: '上海卢湾体育场—— 大型综合体育项目场所',   
                    tips: ['现代化体育馆', '体育配套设施', '专人维护'],
                    price: '1000-1500元/人',
                    location: '上海市',
                    activitytype: '跑步运动',
                    personNum: '50-150人',
                    duration: '半天',
                    saled: '已售122份'
                },
                {
                    img: '../../img/list-case2.png',
                    title: '探秘古道红枫，寻访仙谷奇缘——南黄古道+琼台仙谷3天2晚穿越之旅',
                    tips: ['隋代古刹', '仙谷奇缘', '古道红枫', '五星级酒店'],
                    price: '1000-1500元/人',
                    location: '上海市',
                    activitytype: '跑步运动',
                    personNum: '50-150人',
                    duration: '半天',
                    saled: '已售122份'
                },
                {
                    img: '../../img/list-product1.png',
                    title: '探秘古道红枫，寻访仙谷奇缘——南黄古道+琼台仙谷3天2晚穿越之旅',
                    tips: ['隋代古刹', '仙谷奇缘', '古道红枫', '五星级酒店'],
                    price: '1000-1500元/人',
                    location: '上海市',
                    activitytype: '跑步运动',
                    personNum: '50-150人',
                    duration: '半天',
                    saled: '已售122份'
                },
                {
                    img: '../../img/list-location2.png',
                    title: '探秘古道红枫，寻访仙谷奇缘——南黄古道+琼台仙谷3天2晚穿越之旅',
                    tips: ['隋代古刹', '仙谷奇缘', '古道红枫', '五星级酒店'],
                    price: '1000-1500元/人',
                    location: '上海市',
                    activitytype: '跑步运动',
                    personNum: '50-150人',
                    duration: '半天',
                    saled: '已售122份'
                },
                {
                    img: '../../img/list-product1.png',
                    title: '探秘古道红枫，寻访仙谷奇缘——南黄古道+琼台仙谷3天2晚穿越之旅',
                    tips: ['隋代古刹', '仙谷奇缘', '古道红枫', '五星级酒店'],
                    price: '1000-1500元/人',
                    location: '上海市',
                    activitytype: '跑步运动',
                    personNum: '50-150人',
                    duration: '半天',
                    saled: '已售122份'
                }
            ],
            pageInfo:{
            	currentIndex: 1,
            	totalPage:5,
            	pageSize:10
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
            	
            }
            
        },
        created: function(){
        	var vm = this;
        	vm.queryDictionaryList();
        }
    }
);
console.log("Vue 脚本绑定渲染完成..............");