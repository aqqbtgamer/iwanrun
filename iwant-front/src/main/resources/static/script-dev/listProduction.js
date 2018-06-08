/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
var appListProduction = new Vue(
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
                keyword: [
                    { id: 0, text: '五星级酒店' },
                    { id: 1, text: '风景区' },
                    { id: 2, text: '热门地区' },
                    { id: 3, text: '多人数互动' },
                    { id: 4, text: '休闲' },
                    { id: 5, text: '激烈' },
                    { id: 6, text: '增强意志力' },
                    { id: 7, text: '信任搭建' }
                ],
                location: [
                    { id: 0, text: '上海市区' },
                    { id: 1, text: '周边城市' },
                    { id: 2, text: '境外城市' }
                ],
                activitytype: [
                    { id: 0, text: '室内球类运动' },
                    { id: 1, text: '户外水上运动' },
                    { id: 2, text: '户外拓展' },
                    { id: 3, text: '室内趣味运动' },
                    { id: 4, text: '户外体育场' },
                    { id: 5, text: '拓展培训' }
                ],
                personNum: [
                    { id: 0, text: '20人以下' },
                    { id: 1, text: '20-40人' },
                    { id: 2, text: '40-60人' },
                    { id: 3, text: '60人以上' }
                ],
                duration: [
                    { id: 0, text: '半天' },
                    { id: 1, text: '1天' },
                    { id: 2, text: '2天' },
                    { id: 3, text: '3天' },
                    { id: 4, text: '3天以上' }
                ],
                price: [
                    { id: 0, text: '200以下' },
                    { id: 1, text: '200-300' },
                    { id: 2, text: '300-400' },
                    { id: 3, text: '400-500' },
                    { id: 4, text: '500以上' }
                ]
            },
            List: [
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
            search: {
                criteria: {
                    keyword: [],
                    location: [],
                    activitytype: [],
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
                if ($dom.hasClass('icon-checkbox-non') ) {
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