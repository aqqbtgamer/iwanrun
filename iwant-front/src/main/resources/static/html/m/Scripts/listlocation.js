
var appIndex = new Vue({
    el: "#container",
    data: {
        loginId: jQuery.cookie('loginId'),
        accessToken: jQuery.cookie('accessToken'),
        model: {
            searchlist: {},
            param: {
                duration: [],
                activitytype: [],
                personNum: [],
                locationTypeCode: []
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
            location.href = 'detail.html?id=' + id + '&type=location';
        },
        query: function (pageIndex) {
            var vm = this, url = requestUrl.querylocationByCondition, param = vm.model.param;
            //param.pageIndex = pageIndex - 1;
            axios.post(url, param).then(
                function (response) {
                    //console.log(response.data);
                    vm.model.list = response.data.content;
                    vm.model.showbtnmore = response.data.content.length == vm.model.param.pageSize;
                })
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

            console.log('-----list remove');
            console.log(vm.model.param[item.type]);
            //param
            $.each(vm.model.param[item.type], function (index, it) {
                console.log(it);
                if (it.id == item.id) {
                    vm.model.param[item.type].splice(index, 1);
                }
            });
            console.log(vm.model.param[item.type]);
            console.log('-----list remove');

            filter.remove(item);
            vm.model.page.pageIndex = 1;
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

        filter.type = 'location';
      
        filter.callback = function (data) {
            console.log(data);
            vm.model.param = {};
            vm.model.searchlist = [];
            $.each(data, function (key, value) {
                if (Array.isArray(value) && value.length > 0) {
                    vm.model.param[key] = value;
                    $.each(value, function (index, item) {
                        vm.model.searchlist.push({ type: key, id: item.id, name: item.value });
                    });
                }
            });
            vm.model.page.pageIndex = 1;
            //console.log(vm.model.param);
            //console.log(vm.model.searchlist);
            vm.query();
        }
        filter.init();
    }
});