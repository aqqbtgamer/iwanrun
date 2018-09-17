/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
var appMyAccount = new Vue(
    {
        el: "#container",
        data: {
            mask: false,
            loginWindow: false,
            autoLogin: false,
            loginTitle: '用户登录',
            loginId: '',
            loginBtnUl: true,
            loginIdUl: false,
            loginToken: 'uuixooppasyytvdbftrraskm',
            loginRole: { id: 1, role: '采购方' },
            showFlag: 'location',
            nickname:'',
            headimg:'',
            msgText:"请重新登录",
            msgWindow:false,
            account: {
                headimg: '../../img/accountImage.png',
                nickname: '用户001',
                phone: '180****6171',
                securityanswer: ''
            },
            locations: [
                {
                    img: '../../img/list-location2.png',
                    title: '上海卢湾体育场—— 大型综合体育项目场所',
                    tips: ['现代化体育馆', '多种体育项目', '电子屏'],
                    price: '1000-1500元/人',
                    location: '上海市',
                    activitytype: '跑步运动',
                    personNum: '50-150人',
                    duration: '半天',
                    id: 1
                }/*,
                {
                    img: '../../img/list-location3.png',
                    title: '夏日水上球类运动—— 某集团户外团体水球趣味赛',
                    tips: ['循环泳池', '日光浴', '悠闲游戏', '五星级酒店'],
                    price: '1000-1500元/人',
                    location: '上海市',
                    activitytype: '跑步运动',
                    personNum: '50-150人',
                    duration: '半天'
                },
                {
                    img: '../../img/list-product1.png',
                    title: '探秘古道红枫，寻访仙谷奇缘——南黄古道+琼台仙谷3天2晚穿越之旅',
                    tips: ['隋代古刹', '仙谷奇缘', '古道红枫', '五星级酒店'],
                    price: '1000-1500元/人',
                    location: '上海市',
                    activitytype: '跑步运动',
                    personNum: '50-150人',
                    duration: '半天'
                }*/
            ],
            products: [
                {
                    img: '../../img/list-product1.png',
                    title: '探秘古道红枫，寻访仙谷奇缘——南黄古道+琼台仙谷3天2晚穿越之旅',
                    tips: ['隋代古刹', '仙谷奇缘', '古道红枫', '五星级酒店'],
                    price: '1000-1500元/人',
                    location: '上海市',
                    activitytype: '跑步运动',
                    personNum: '50-150人',
                    duration: '半天',
                    saled: '已售122份',
                    id: 9
                }/*,
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
                }*/
            ],
            cases: [
                {
                    img: '../../img/list-case2.png',
                    title: '探秘古道红枫，寻访仙谷奇缘——南黄古道+琼台仙谷3天2晚穿越之旅',
                    tips: ['隋代古刹', '仙谷奇缘', '古道红枫', '五星级酒店'],
                    price: '1000-1500元/人',
                    location: '上海市',
                    activitytype: '跑步运动',
                    personNum: '50-150人',
                    duration: '半天',
                    saled: '已售122份',
                    id: 10
                }/*,
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
                }*/
            ]
        },
        created:function(){
        	var vm = this;
        	if(vm.loginId != ''){
        		
        	}else{
        		vm.msgWindow=true;
        	}


        },
//        mounted: function () {
//            var vm = this;
//            if(vm.loginId != ''){
//            	vm.queryCollectList('location');
//            	vm.queryCollectList('product');
//            	vm.queryCollectList('case');
//        	}else{
//        		vm.msgWindow=true;
//        	}
//        },
        watch:{
        	loginId:function(newVal,oldVal){
        		var vm = this;
    			if(newVal!= ''&& oldVal != newVal){
    				vm.getUserInfo();
    				vm.msgWindow=false;
    				vm.queryCollectList('location');
    			}
        	}
        },
        methods: {
            queryCollectList: function (queryType) {
                var vm = this;
                var url = "../../favourite/query/";
                axios.post(url,{type:queryType}).then(
                    function (response) {
                        console.log(response.data);
                        if(queryType=='location'){
                        	vm.locations = response.data.content;
                        }
                        if(queryType=='product'){
                        	vm.products = response.data.content;
                        }
                        if(queryType=='case'){
                        	vm.cases = response.data.content;
                        }
                        
                        //if( list != ''){ 
//                        vm.locations = [{
//                            img: '../../img/list-product1.png',
//                            title: '探秘古道红枫，寻访仙谷奇缘——南黄古道+琼台仙谷3天2晚穿越之旅',
//                            tips: ['隋代古刹', '仙谷奇缘', '古道红枫', '五星级酒店'],
//                            price: '2000-2500元/人',
//                            location: '上海市',
//                            activitytype: '跑步运动',
//                            personNum: '50-150人',
//                            duration: '半天',
//                            saled: '已售122份'
//                        }]
                        //vm.products = vm.locations
                        //vm.cases = vm.locations

                        // vm.criteria.activityProvinceCode=list.activityProvinceCode;
                        // vm.criteria.activitytype=list.activitytype;
                        // vm.criteria.specialTagsCode=list.specialTagsCode;
                        // vm.criteria.personNum=list.personNum;
                        // vm.criteria.duration=list.duration;
                        // vm.criteria.orderSimulatePriceCode=list.orderSimulatePriceCode;
                        //}

                    })
            },
            showLogin: function (message) {
                console.log("v-on  click method :showLogin");
                /*var vm = this
                vm.mask = true;
                vm.loginWindow = true;
                vm.loginTitle = message;*/
                lrApp.showLogin(message);
            },
            closeMsgWindow: function(){
            	var vm = this;
                vm.msgWindow = false;
                vm.msgText = '';
            },
            changeAutoLogin: function () {
                var vm = this;
                vm.autoLogin = !vm.autoLogin;
            },
            tab: function ($event) {
                var vm = this;
                vm.showFlag = $event.target.name;
                vm.queryCollectList($event.target.name);
            },
            routeToDetail: function (id, type) {
                var vm = this;
                id && type && (window.location.href = './productdetail.html?type=' + type + '&id=' + id);
            },
            getUserInfo:function(){
            	var vm = this;
            	var url = "../../purchaserAccount/findMixedByLoginId";
            	axios.post(url).then(
            			function(response){
            				console.log(response.data.content);
            				var data = response.data;
            				if( data != ''){
            					var headImgs = data.headImgs;
                				var info = data.userInfo;
                				if(info != '' && info != undefined){
                					vm.nickname = info.name+'，您好';
                				}
                				if (headImgs != '' && headImgs != undefined && headImgs.length > 0) {
                					vm.headimg = headImgs[0].filePath;
                				}
            				}
            	})
            }
        }
    }
);
function showLoginId(loginId, opt){
	var vm = appMyAccount;
	vm.mask = false;
	vm.loginId = loginId;
	vm.loginIdUl = true;
	vm.loginBtnUl = false;
	
	if(opt == 'login'){
//		initData();
	}
}
function clearLoginId() {
    var vm = appMyAccount;
    vm.loginId = '';
    vm.loginIdUl = false;
    vm.loginBtnUl = true;
}
console.log("Vue 脚本绑定渲染完成..............");

