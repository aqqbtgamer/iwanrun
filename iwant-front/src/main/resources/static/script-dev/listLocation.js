/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
var appListLocation = new Vue(
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
                    { id: 0, text: '现代化体育馆' },
                    { id: 1, text: '环境优美' },
                    { id: 2, text: '多种体育项目' },
                    { id: 3, text: '体育配套设施' }
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
                sitetype: [
                    { id: 0, text: '剧院' },
                    { id: 1, text: '星级影院' },
                    { id: 2, text: '体育场' },
                    { id: 3, text: '室内体育馆' },
                    { id: 4, text: '轰趴馆' },
                    { id: 5, text: '俱乐部' },
                    { id: 6, text: '艺术中心' },
                    { id: 7, text: '电影院' },
                    { id: 8, text: '其他' }
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
                ]
            },
            List: [
                {
                    img: '../../img/list-location2.png',
                    title: '上海卢湾体育场—— 大型综合体育项目场所',
                    tips: ['现代化体育馆', '多种体育项目', '电子屏'],
                    price: '1000-1500元/人',
                    location: '上海市',
                    activitytype: '跑步运动',
                    personNum: '50-150人',
                    duration: '半天'
                },
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
                },
                {
                    img: '../../img/list-location2.png',
                    title: '探秘古道红枫，寻访仙谷奇缘——南黄古道+琼台仙谷3天2晚穿越之旅',
                    tips: ['隋代古刹', '仙谷奇缘', '古道红枫', '五星级酒店'],
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
                }
            ],
            search: {
                criteria: {
                    keyword: [],
                    location: [],
                    activitytype: [],
                    sitetype:[],
                    personNum: [],
                    duration: []
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