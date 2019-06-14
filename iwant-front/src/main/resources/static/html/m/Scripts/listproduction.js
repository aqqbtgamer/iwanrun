
var appIndex = new Vue({
    el: "#container",
    data: {
        loginId: jQuery.cookie('loginId'),
        accessToken: jQuery.cookie('accessToken'),
        model: {
            searchlist: [],
            param: {
                duration: [],
                activitytype: [],
                personNum: [],
                specialTagsCode: [],
                pageIndex: 0,
                pageSize: 10
            },
            list: [],
            showbtnmore: true
        },
        currentcity: '上海'
    },
    methods: {
        showSlidemenu: function () {
            sildemenu.show = true;
            if (!sildemenu.init && jQuery.cookie('accessToken')) {
                sildemenu.getuser();
                sildemenu.init = true;
            }
        },
        showSearch: function () {
            search.show();
        },
        showFilter: function () {
            filter.show = true;
        },
        linktoDetail: function (id) {
            location.href = 'detail.html?id=' + id + '&type=product';
        },
        query: function (pageIndex) {
            var vm = this, url = requestUrl.queryProdutionByCondition, param = vm.model.param;
            axios.post(url, param).then(function (response) {
                if (vm.model.param.pageIndex === 0) {
                    vm.model.list = [];
                }
                if (Array.isArray(response.data.content)) {
                    vm.model.list = vm.model.list.concat(response.data.content);
                    vm.model.showbtnmore = vm.model.list.length < response.data.pageInfo.total;
                }
            });
        },
        getMore: function () {
            var vm = this;
            vm.model.param.pageIndex += 1;
            vm.query();
        },
        remove: function (item) {
            var vm = this;
            var idx = vm.model.searchlist.indexOf(item);
            vm.model.searchlist.splice(idx, 1);

            if (Array.isArray(vm.model.param[item.type])) {
                idx = vm.model.param[item.type].indexOf(item.id);
                vm.model.param[item.type].splice(idx, 1);
            }

            filter.remove(item);
            vm.model.param.pageIndex = 0;
            vm.query();
        }
    },
    components: {
        companyfooter: companyfooter,
        helporder: helporder,
        login: login,
        sildemenu: sildemenu,
        filter: filter,
        search: search
    },
    created: function () {
        var vm = this;
        vm.query();
        setCurrentCity(function (city) {
            vm.currentcity = city;
        });

        login.callback = function () {
            vm.loginId = jQuery.cookie('loginId');
            vm.accessToken = jQuery.cookie('accessToken');
            sildemenu.loginId = jQuery.cookie('loginId');
            sildemenu.accessToken = jQuery.cookie('accessToken');
            console.log(vm.accessToken);
        };

        filter.type = 'production';

        filter.callback = function (data) {
            vm.model.param = {
                duration: [],
                activitytype: [],
                personNum: [],
                companytype: [],
                pageIndex: 0,
                pageSize: 10
            };
            vm.model.searchlist = [];
            $.each(data, function (key, value) {
                if (Array.isArray(value) && value.length > 0) {
                    vm.model.param[key] = [];
                    $.each(value, function (index, item) {
                        vm.model.searchlist.push({ type: key, id: item.id, name: item.value });
                        vm.model.param[key].push(item.id);
                    });
                }
            });
            vm.query();
        }
        filter.init();
    }
});