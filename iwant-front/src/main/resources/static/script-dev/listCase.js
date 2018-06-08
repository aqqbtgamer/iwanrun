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
                    { id: 1, text: '上海市区',dbCode:1,code:1 },
                    { id: 2, text: '周边城市',dbCode:1,code:2 },
                    { id: 3, text: '境外城市',dbCode:1,code:3 }
                ],
                activitytype: [
                    { id: 4, text: '室内球类运动' ,dbCode:2,code:1 },
                    { id: 5, text: '户外水上运动' ,dbCode:2,code:2},
                    { id: 6, text: '户外拓展' ,dbCode:2,code:3},
                    { id: 7, text: '室内趣味运动' ,dbCode:2,code:4},
                    { id: 8, text: '户外体育场' ,dbCode:2,code:5},
                    { id: 9, text: '拓展培训' ,dbCode:2,code:6}
                ],
                companytype: [
                    { id: 10, text: '个体私营企业',dbCode:3,code:1 },
                    { id: 11, text: '外资企业' ,dbCode:3,code:2},
                    { id: 12, text: '国企' ,dbCode:3,code:3},
                    { id: 13, text: '中外合资' ,dbCode:3,code:4 },
                    { id: 14, text: '自由职业',dbCode:3,code:5 }
                ],
                personNum: [
                    { id: 0, text: '20人以下' ,dbCode:4,code:1 },
                    { id: 1, text: '20-40人',dbCode:4,code:2 },
                    { id: 2, text: '40-60人' ,dbCode:4,code:3},
                    { id: 3, text: '60人以上'  ,dbCode:4,code:4}
                ],
                duration: [
                    { id: 0, text: '半天' ,dbCode:5,code:1},
                    { id: 1, text: '1天' ,dbCode:5,code:2},
                    { id: 2, text: '2天'  ,dbCode:5,code:3},
                    { id: 3, text: '3天'  ,dbCode:5,code:4},
                    { id: 4, text: '3天以上'  ,dbCode:5,code:5}
                ],
                price: [
                    { id: 0, text: '200以下' ,dbCode:6,code:1 },
                    { id: 1, text: '200-300' ,dbCode:6,code:2},
                    { id: 2, text: '300-400' ,dbCode:6,code:3},
                    { id: 3, text: '400-500' ,dbCode:6,code:4},
                    { id: 4, text: '500以上' ,dbCode:6,code:5}
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
            }
        }
    }
);
console.log("Vue 脚本绑定渲染完成..............");