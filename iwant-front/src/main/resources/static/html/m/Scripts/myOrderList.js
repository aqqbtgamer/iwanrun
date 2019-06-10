var appIndex = new Vue({
    el: "#container",
    data: {
        loginId: jQuery.cookie('loginId'),
        accessToken: jQuery.cookie('accessToken'),
        param: {
            pageIndex: 0,
            pageSize: 5
        },
        list: [],
        showbtnmore: false
    },
    methods: {
        getOrder: function () {
            var vm = this, url = requestUrl.getOrderListByLoginId, param = {
                loginId: vm.loginId,
                pageIndex: vm.param.pageIndex,
                pageSize: vm.param.pageSize
            };
            axios.post(url, param).then(function (response) {
                console.log(response.data);
                if (vm.param.pageIndex === 0) {
                    vm.list = [];
                }
                var data = response.data;
                if (Array.isArray(data.content)) {
                    var len = vm.list.length;
                    vm.list = vm.list.concat(data.content);
                    vm.showbtnmore = vm.list.length < data.pageInfo.total;
                    url = requestUrl.getOrderByID;
                    for (var i = 0; i < data.content.length; i++) {
                        (function (i) {
                            axios.post(url, { id: data.content[i].id }).then(function (response) {
                                console.log(response.data);
                                vm.list[len + i].orderNo = response.data.orders;
                            });
                        })(i);
                    }
                }
            });
        },
        getMore: function () {
            var vm = this;
            vm.param.pageIndex++;
            vm.getOrder();
        },
        linktoDetail: function (id) {
            location.href = 'myOrder.html?id=' + id;
        },
        dateformat: function (time) {
            var arr = time.split(' ');
            if (arr.length > 0) {
                return arr[0];
            }
            return time;
        }
    },
    created: function () {
        if (!jQuery.cookie('accessToken')) {
            window.location.href = 'index.html';
        }
        var vm = this;
        vm.ValidateLogin(function () {
            vm.getOrder();
        }, function () {
            window.location.href = 'index.html';
        });

    }
});