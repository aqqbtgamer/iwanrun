
var appIndex = new Vue(
    {
        el: "#container",
        data: {
            loginId: jQuery.cookie('loginId'),
            accessToken: jQuery.cookie('accessToken'),
            model: {
                searchlist: {},
                param: {
                    duration: [],
                    activitytype: [],
                    personNum: []
                },
                page: {
                    pageIndex: 1,
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
            },
            showSearch: function () {
                search.show = true;
            },
            showFilter: function () {
                filter.show = true;
            },
            linktoDetail: function (id) {
                location.href = 'detail.html?id=' + id + '&type=case';
            },
            query: function () {
                var vm = this, url = requestUrl.queryCaseByCondition, param = vm.model.param;
                param.pageIndex = vm.model.page.pageIndex - 1;
                param.pageSize = vm.model.page.pageSize;
                axios.post(url, param).then(function (response) {
                    //console.log(response.data);
                    vm.model.list = response.data.content;
                    vm.model.showbtnmore = response.data.content.length == vm.model.param.pageSize;
                })
            },
            getMore: function () {
                var vm = this;
                vm.model.page.pageIndex += 1;
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

            filter.callback = function (data) {
                console.log(data);
                vm.model.param = data;
                vm.model.page.pageIndex = 1;
                vm.query();
            }
        }
    });