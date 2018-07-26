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
            loginId: '18018336171',
            loginToken: 'uuixooppasyytvdbftrraskm',
            loginRole: { id: 1, role: '采购方' },
            detail: {
                name: '探秘古道红枫，寻访仙谷奇缘',
                description: '南黄古道+琼台仙谷3天2晚穿越之路',
                no: '020120',
                region: '浙江省台州市',
                type: '户外拓展',
                presonNum: '50-150人',
                during: '2-3天',
                route: '上海-浙江临海-上海',
                mainImg: '../../img/main-image.png',
                imgs: [
                    '../../img/side-image1.png',
                    '../../img/side-image2.png',
                    '../../img/side-image1.png',
                    '../../img/side-image1.png',
                    '../../img/side-image2.png',
                ]
            },
            currentTopIndex: 0
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
            sliderPre: function () {
                var vm = this;
                vm.showSliderPre && vm.currentTopIndex--;
            },
            sliderNext: function () {
                var vm = this;
                vm.showSliderNext && vm.currentTopIndex++;
            }
        },
        computed: {
            showSliderPre: function () {
                var vm = this;
                return vm.currentTopIndex > 0;
            },
            showSliderNext: function () {
                var vm = this;
                return vm.currentTopIndex < vm.detail.imgs.length - 2;
            }
        }
    }
);
console.log("Vue 脚本绑定渲染完成..............");