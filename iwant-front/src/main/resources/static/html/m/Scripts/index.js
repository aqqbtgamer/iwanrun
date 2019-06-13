
window.onload = function () {
    var swiper = new Swiper('.swiper-container', {
        autoplay: true,
        speed: 2000,
        autoplayDisableOnInteraction: false,
        loop: true,
        centeredSlides: true,
        slidesPerView: 3,
        pagination: '.swiper-pagination',
        paginationClickable: true,
        //onInit: function (swiper) {
        //    swiper.slides[1].className = "swiper-slide swiper-slide-active";
        //},
        breakpoints: {
            668: {
                slidesPerView: 1,
            }
        }
    });
}


var appIndex = new Vue({
    el: "#container",
    data: {
        loginId: jQuery.cookie('loginId'),
        accessToken: jQuery.cookie('accessToken'),
        model: {
            bannerList: [],
            currentmenu: 'prodution',
            cases: [],
            productions: [],
            locations: [],
            webSiteCooperatives: []
        },
        currentcity: '上海',//当前定位城市
        isPlus: $(window).width() > 400
    },
    methods: {
        showSlidemenu: function () {
            sildemenu.show = true;
            if (!sildemenu.init && jQuery.cookie('accessToken')) {
                sildemenu.getuser();
                sildemenu.init = true;
            }
        },//显示右侧侧滑栏
        showSearch: function () {
            search.show();
        },//显示搜索
        linktoDetail: function (id, type) {
            location.href = 'detail.html?id=' + id + '&type=' + type;
        },//跳转到详情页
        linktoWeb: function (url) {
            location.href = "http://" + url;
        },//跳转到外部web地址
        getBanner: function () {
            var vm = this, url = requestUrl.castposition, param = {};
            axios.post(url, param).then(function (response) {
                var data = response.data;
                vm.model.bannerList = $.parseJSON(data.list);
            })
        },//获取轮播图数据
        getwebSiteCooperativeLogo: function () {
            var vm = this, url = requestUrl.webSiteCooperativeLogo, param = {};
            axios.post(url, param).then(function (response) {
                var data = response.data;
                vm.model.webSiteCooperatives = data;
            })
        },//合作单位logo 
        queryCaseByCondition: function (pageIndex) {
            var vm = this, url = requestUrl.queryCaseByCondition, param = {};
            param.pageIndex = pageIndex - 1;
            axios.post(url, param).then(function (response) {
                vm.model.cases = response.data.content;
            })
        },
        queryProdutionByCondition: function (pageIndex) {
            var vm = this, url = requestUrl.queryProdutionByCondition, param = {};
            param.pageIndex = pageIndex - 1;
            axios.post(url, param).then(function (response) {
                vm.model.productions = response.data.content;
            })
        },
        queryLocationByCondition: function (pageIndex) {
            var vm = this, url = requestUrl.querylocationByCondition, param = {};
            param.pageIndex = pageIndex - 1;
            axios.post(url, param).then(function (response) {
                vm.model.locations = response.data.content;
            })
        },
    },
    components: {
        companyfooter: companyfooter,
        helporder: helporder,
        login: login,
        sildemenu: sildemenu,
        search: search
    },
    created: function () {
        var vm = this;
        setCurrentCity(function (city) {
            vm.currentcity = city;
        });

        login.callback = function () {
            vm.loginId = jQuery.cookie('loginId');
            vm.accessToken = jQuery.cookie('accessToken');
            sildemenu.loginId = jQuery.cookie('loginId');
            sildemenu.accessToken = jQuery.cookie('accessToken');
        };

        vm.getBanner();
        vm.queryCaseByCondition(1);
        vm.queryProdutionByCondition(1);
        vm.queryLocationByCondition(1);
        vm.getwebSiteCooperativeLogo();
    }
});