/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
var appListProduction = new Vue(
    {
        el: "#container",
        data: {
            loginId: null,
            loginIdUl: false,
            loginBtnUl: true,
            init: {
                id: getUrlParam('id'),
                type: getUrlParam('type')
            },
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
            currentTopIndex: 0,
            isFavourite: false
        },
        created: function () {

        },
        methods: {
            showLogin: function (message) {
                console.log("v-on  click method :showLogin");
                lrApp.showLogin(message);
            },
            logout: function () {
                var vm = this;
                
                var logoutBack = function(){
			    	vm.loginId = null;
	                vm.loginIdUl = false;
	                vm.loginBtnUl = true;
	                //$.cookie('accessToken', null, { path: "/" });
	                //$.cookie('loginId', null, { path: "/" });
	                lrApp.account = {};
    			}
           		
    			logoutCommon(logoutBack);
            },
            sliderPre: function () {
                var vm = this;
                vm.showSliderPre && vm.currentTopIndex--;
            },
            sliderNext: function () {
                var vm = this;
                vm.showSliderNext && vm.currentTopIndex++;
            },
            get: function () {
                var vm = this;
                vm.init && vm.init.id && function () {
                    var url = baseUrl + "production/getDetailsById?id=" + vm.init.id;
                    axios.get(url).then(
                        function (response) {
                            console.log(response.data);
                            response.data && (vm.detail = response.data);
                        });
                }();
            },
            collection: function () {
                var vm = this;
                //vm.isFavourite = !vm.isFavourite;
                function add(id, type) {
                    var url = baseUrl + 'favourite/add',
                        parm = {
                            id: id,
                            type: type
                        };
                    axios.post(url, parm).then(
                        function (response) {
                            console.log(response.data);
                            response.data && (vm.isFavourite = true);
                        });
                }
                function remove(id, type) {
                    var url = baseUrl + 'favourite/delete',
                        parm = {
                            id: id,
                            type: type
                        };
                    axios.post(url, parm).then(
                        function (response) {
                            console.log(response.data);
                            vm.isFavourite = false;
                        });
                }
                if (vm.init && vm.init.id && vm.init.type) {
                    vm.isFavourite ? remove(vm.init.id, vm.init.type) : add(vm.init.id, vm.init.type);
                }
            },
            isCollection: function () {
                var vm = this;
                vm.init && vm.init.id && vm.init.type && function (id, type) {
                    //TODO
                }(vm.init.id, vm.init.type);
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
            },
            favouriteColor: function () {
                var vm = this;
                return vm.isFavourite ? '#FF0000' : '#414141';
            }
        }
    }
);
console.log("Vue 脚本绑定渲染完成..............");