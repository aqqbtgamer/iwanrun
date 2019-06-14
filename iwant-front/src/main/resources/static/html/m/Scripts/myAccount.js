var appIndex = new Vue({
    el: "#container",
    data: {
        loginId: jQuery.cookie('loginId'),
        accessToken: jQuery.cookie('accessToken'),
        account: {
            headimg: 'images/head_img.png',
            nickname: jQuery.cookie('loginId'),
            phone: jQuery.cookie('loginId'),
            company: {},
            ordersTotal: 0,
            favouriteTotal: 0,
            email: ''
        },
    },
    methods: {
        getuser: function () {
            var vm = this;
            vm.ValidateLogin(function (data) {
                var info = data.userInfo;
                var headImgs = data.headImgs;
                var companyCredentials = data.companyCredentials;
                var loginInfo = data.loginInfo;
                if (info) {
                    if (headImgs && headImgs.length > 0) {
                        vm.account.headimg = headImgs[0].filePath;
                    }
                    vm.account.nickname = info.name;
                    vm.account.email = info.email;
                    if (loginInfo) {
                        vm.account.phone = loginInfo.mobileNumber;
                    }
                    vm.account.company.name = info.companyName;
                    if (companyCredentials) {
                        vm.account.company.hasCredential = true;
                    }

                }
            }, function () {
                alert('登录失效，请重新登录');
                window.location.href = 'index.html';
            });
        },
        getOrders: function () {
            var vm = this, url = requestUrl.getOrderListByLoginId, param = {
                pageIndex: 0,
                loginId: vm.loginId
            };
            axios.post(url, param).then(function (response) {
                var data = response.data;
                if (data && data.pageInfo && data.pageInfo.total) {
                    vm.account.ordersTotal = data.pageInfo.total;
                }
            });
        },
        getFavourite: function () {
            var vm = this, url = requestUrl.favouriteQuery, types = ['location', 'product', 'case'];
            $.each(types, function (index, item) {
                var param = { type: item };
                axios.post(url, param).then(function (response) {
                    var data = response.data;
                    if (data && data.pageInfo && data.pageInfo.total) {
                        vm.account.favouriteTotal += data.pageInfo.total;
                    }
                });
            });
        },
        logout: function () {
            var vm = this, url = requestUrl.logout, param = {};
            axios.post(url, param).then(function (response) {
                //if (response.status == 200) {
                //alert('退出成功')
                jQuery.cookie('openId', '');
                window.location.href = "index.html";
                //}
            });
        }
    },
    created: function () {
        if (!jQuery.cookie('accessToken')) {
            alert('请先登录');
            window.location.href = 'index.html';
        }
        var vm = this;
        vm.getuser();
        vm.getOrders();
        vm.getFavourite();
    }
});