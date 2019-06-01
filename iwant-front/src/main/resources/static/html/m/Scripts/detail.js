var appIndex = new Vue({
    el: "#container",
    data: {
        loginId: jQuery.cookie('loginId'),
        accessToken: jQuery.cookie('accessToken'),
        model: {
            id: getUrlParam('id'),
            type: getUrlParam('type'),
            data: {},
            isFavourite: false,
            isWish: false
        }
    },
    methods: {
        getProductionDetailsById: function () {
            var vm = this, url = requestUrl.getProductionDetailsById + '?id=' + vm.model.id + '&type=' + vm.model.type;
            axios.post(url, {}).then(function (response) {
                console.log(response.data);
                vm.model.data = response.data;
            });
        },
        getLocationDetailsById: function () {
            var vm = this, url = requestUrl.getLocationDetailsById + '?id=' + vm.model.id + '&type=' + vm.model.type;
            axios.post(url, {}).then(function (response) {
                console.log(response.data);
                vm.model.data = response.data;
            });
        },
        getCaseDetailsById: function () {
            var vm = this, url = requestUrl.getCaseDetailsById + '?id=' + vm.model.id + '&type=' + vm.model.type;
            axios.post(url, {}).then(function (response) {
                console.log(response.data);
                vm.model.data = $.parseJSON(response.data.caseVo);
            });
        },
        getFavourite: function () {
            var vm = this, url = requestUrl.favouriteQuery, param = {
                type: vm.model.type
            };
            axios.post(url, param).then(function (response) {
                console.log(response.data);
                var data = response.data.content;
                if (Array.isArray(data)) {
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].id == vm.model.id && data[i].caseType == vm.model.type) {
                            vm.model.isFavourite = true;
                            break;
                        }
                    }
                }
            });
        },
        changeFavourite: function () {
            var vm = this, url = vm.model.isFavourite ? requestUrl.favouriteDelete : requestUrl.favouriteAdd, param = {
                id: vm.model.id,
                type: vm.model.type
            };
            axios.post(url, param).then(function (response) {
                console.log(response.data);
                if (response.data == 'success') {
                    vm.model.isFavourite = !vm.model.isFavourite;
                }
            });
        },
        wishcartFindOne: function () {
            var vm = this, url = requestUrl.wishcartFindOne, param = {
                typeId: vm.model.id,
                loginId: vm.loginId,
                type: vm.model.type
            };
            axios.post(url, param).then(function (response) {
                console.log(response.data);
                if (response.data) {
                    vm.model.isWish = true;
                }
            });
        },
        wishChange: function () {
            var vm = this, type = vm.model.type === 'product' ? 'production' : vm.model.type;
            var url = vm.model.isWish ? requestUrl.wishcartDelete : requestUrl.wishcartAdd, param = {
                id: vm.model.id,
                type: type,
                loginId: vm.loginId
            };
            axios.post(url, param).then(function (response) {
                console.log(response.data);
                if (response.data && response.data.success) {
                    vm.model.isWish = !vm.model.isWish;
                }
            });
        }
    },
    created: function () {
        var vm = this;
        if (!vm.model.id || !vm.model.type) {
            history.back(-1);
        }
        var init = {
            product: vm.getProductionDetailsById,
            location: vm.getLocationDetailsById,
            case: vm.getCaseDetailsById
        }
        if (typeof (init[vm.model.type]) === 'function') {
            init[vm.model.type]();
        }
        vm.getFavourite();
        vm.wishcartFindOne();
    }
});