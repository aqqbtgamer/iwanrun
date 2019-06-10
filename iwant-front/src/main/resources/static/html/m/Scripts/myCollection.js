var appIndex = new Vue({
    el: "#container",
    data: {
        loginId: jQuery.cookie('loginId'),
        accessToken: jQuery.cookie('accessToken'),
        tab: 'product',
        collection: {
            product: {
                list: [],
                pageIndex: 0,
                pageSize: 10,
                showbtnmore: false
            },
            case: {
                list: [],
                pageIndex: 0,
                pageSize: 10,
                showbtnmore: false
            },
            location: {
                list: [],
                pageIndex: 0,
                pageSize: 10,
                showbtnmore: false
            }
        },
        styleClass: {
            tabClass: 'dib w80 ml30 mr30 h80 lh80 fz28 c333333 tac pr',
            tabActiveClass: 'dib w80 ml30 mr30 h80 lh80 fz28 c333333 tac pr search_actives'
        }
    },
    methods: {
        changeTab: function (tab) {
            var vm = this;
            vm.tab = tab || 'product';
            if (vm.collection[vm.tab].list.length === 0) {
                vm.getFavourite();
            }
        },
        getFavourite: function () {
            var vm = this, url = requestUrl.favouriteQuery, page = vm.collection[vm.tab], param = {
                type: vm.tab,
                pageIndex: page.pageIndex,
                pageSize: page.pageSize
            };
            axios.post(url, param).then(function (response) {
                console.log(response.data);
                if (vm.collection[vm.tab].pageIndex === 0) {
                    vm.collection[vm.tab].list = [];
                }
                var data = response.data;
                if (Array.isArray(data.content)) {
                    vm.collection[vm.tab].list = vm.collection[vm.tab].list.concat(data.content);
                    vm.collection[vm.tab].showbtnmore = vm.collection[vm.tab].list.length < data.pageInfo.total;
                }
            });
        },
        getMore: function () {
            var vm = this;
            vm.collection[vm.tab].pageIndex++;
            vm.getFavourite();
        },
        remove: function (id) {
            var vm = this, url = requestUrl.favouriteDelete, param = {
                id: id,
                type: vm.tab
            };
            if (!id || !vm.tab) {
                return;
            }
            axios.post(url, param).then(function (response) {
                console.log(response.data);
                if (response.data == 'success') {
                    vm.collection[vm.tab].pageIndex = 0;
                    vm.getFavourite();
                }
            });
        },
        linktoDetail: function (id, type) {
            location.href = 'detail.html?id=' + id + '&type=' + type || 'product';
        }
    },
    created: function () {
        if (!jQuery.cookie('accessToken')) {
            window.location.href = 'index.html';
        }
        var vm = this;

        vm.ValidateLogin(function () {
            vm.getFavourite();
        }, function () {
            window.location.href = 'index.html';
        });
    }
});