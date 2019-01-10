var appIndex = new Vue(
    {
        el: "#container",
        data: {
            loginId: jQuery.cookie('loginId'),
            accessToken: jQuery.cookie('accessToken'),
            model: {
                id: getUrlParam('id'),
                type: getUrlParam('type'),
                data: {}
            }
        },
        methods: {
            getProductionDetailsById: function () {
                var vm = this, url = requestUrl.getProductionDetailsById, param = {
                    id: vm.model.id,
                    type: vm.model.type
                };
                axios.post(url, param).then(
                    function (response) {
                        console.log(response.data);
                        vm.model.data = response.data;
                    })
            },
            getLocationDetailsById: function () {
                var vm = this, url = requestUrl.getLocationDetailsById, param = {
                    id: vm.model.id,
                    type: vm.model.type
                };
                axios.post(url, param).then(
                    function (response) {
                        console.log(response.data);
                        vm.model.data = response.data;
                    })
            },
            getCaseDetailsById: function () {
                var vm = this, url = requestUrl.getCaseDetailsById, param = {
                    id: vm.model.id,
                    type: vm.model.type
                };
                axios.post(url, param).then(
                    function (response) {
                        console.log(response.data);
                        vm.model.data = $.parseJSON(response.data.caseVo);
                    })
            },
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
        }
    });