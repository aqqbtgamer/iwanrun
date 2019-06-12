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
            wishId: false,
        },
        ContactUs: 'tel:' + dataConfig.ContactUs
    },
    methods: {
        getProductionDetailsById: function () {
            var vm = this, url = requestUrl.getProductionDetailsById + '?id=' + vm.model.id + '&type=' + vm.model.type;
            axios.post(url, {}).then(function (response) {
                vm.model.data = response.data;
            });
        },
        getLocationDetailsById: function () {
            var vm = this, url = requestUrl.getLocationDetailsById + '?id=' + vm.model.id + '&type=' + vm.model.type;
            axios.post(url, {}).then(function (response) {
                if (response.data && response.data.location) {
                    vm.model.data = $.parseJSON(response.data.location);
                }
            });
        },
        getCaseDetailsById: function () {
            var vm = this, url = requestUrl.getCaseDetailsById + '?id=' + vm.model.id + '&type=' + vm.model.type;
            axios.post(url, {}).then(function (response) {
                if (response.data && response.data.caseVo) {
                    vm.model.data = $.parseJSON(response.data.caseVo);
                }
            });
        },
        getFavourite: function () {
            var vm = this, url = requestUrl.favouriteQuery, param = {
                type: vm.model.type
            };
            axios.post(url, param).then(function (response) {
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
            if (!jQuery.cookie('accessToken')) {
                login.show = true;
                return;
            }

            var vm = this, url = vm.model.isFavourite ? requestUrl.favouriteDelete : requestUrl.favouriteAdd, param = {
                id: vm.model.id,
                type: vm.model.type
            };

            axios.post(url, param).then(function (response) {
                if (response.data == 'success') {
                    vm.model.isFavourite = !vm.model.isFavourite;
                }
            });
        },
        wishcartFindOne: function () {
            var vm = this, type = vm.model.type === 'product' ? 'production' : vm.model.type;
            var url = requestUrl.wishcartFindOne, param = {
                typeId: vm.model.id,
                loginId: vm.loginId,
                type: type
            };
            axios.post(url, param).then(function (response) {
                if (response.data && response.data.id > 0) {
                    vm.model.wishId = response.data.id;
                }
            });
        },
        wishChange: function () {
            var vm = this, type = vm.model.type === 'product' ? 'production' : vm.model.type;
            if (!jQuery.cookie('accessToken')) {
                login.show = true;
                return;
            }
            var url = vm.model.wishId ? requestUrl.wishcartDelete : requestUrl.wishcartAdd, param = {
                id: vm.model.wishId || vm.model.id,
                type: type,
                loginId: vm.loginId
            };
            axios.post(url, param).then(function (response) {
                if (response.data && response.data.success) {
                    vm.model.wishId = vm.model.wishId ? false : response.data.message;
                }
            });
        }
    },
    components: {
        login: login
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

        login.callback = function () {
            vm.loginId = jQuery.cookie('loginId');
            vm.accessToken = jQuery.cookie('accessToken');
            vm.getFavourite();
            vm.wishcartFindOne();
        };
        vm.ValidateLogin(function () {
            vm.getFavourite();
            vm.wishcartFindOne();

        }, function () {
            login.show = true;
        });
    }
});