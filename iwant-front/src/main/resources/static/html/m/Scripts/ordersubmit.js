
var appIndex = new Vue(
    {
        el: "#container",
        data: {
            loginId: jQuery.cookie('loginId'),
            accessToken: jQuery.cookie('accessToken'),
            model: {
                isshowall: false,
                activityTypeList: [],
                companyTypeList: [],
                groupNumberList: [],
                durationList: [],
                provinceList: []
            },
            order: {
                contractMobile: jQuery.cookie('loginId'),
                activity_code: null,
                companyTypeId: null,
                groupNumberCode: null,
                activityDuringCode: null,
                activityProvinceCode: null
            },
            errMsg: {
                contractMobile: false,
                activity_code: false
            },
            showDialog: false,
            tab: 'product',
            collection: {
                product: {
                    list: [],
                    pageIndex: 0,
                    pageSize: 3
                },
                case: {
                    list: [],
                    pageIndex: 0,
                    pageSize: 3
                },
                location: {
                    list: [],
                    pageIndex: 0,
                    pageSize: 3
                }
            },
            styleClass: {
                tabClass: 'dib w80 ml30 mr30 h80 lh80 fz28 c333333 tac pr',
                tabActiveClass: 'dib w80 ml30 mr30 h80 lh80 fz28 c333333 tac pr search_actives'
            },
            orderId: 0
        },
        methods: {
            submitOrder: function () {
                var vm = this, url = requestUrl.orderSubmit;
                if (!vm.varify()) {
                    return false;
                }
                //console.log(vm.order);
                var request = {
                    order: vm.order,
                    user: { loginId: vm.loginId }
                };

                var param = { requestJson: JSON.stringify(request) };
                axios.post(url, param).then(function (response) {
                    console.log(response.data);
                    var data = response.data;
                    if (data != null && data.submitResult && data.orders) {
                        vm.showDialog = true;
                        vm.orderId = data.orders.id;
                        //alert("需求提交成功");
                        //window.location.href = "./orderlist.html";
                    }
                });
            },
            showsildemenu: function () {
                console.log(sildemenu);
                sildemenu.show = true;
            },
            buttonChange: function ($event, id, type) {
                var vm = this, $dom = $($event.target);
                if ($dom.hasClass('select-num')) {
                    vm.order[type] = null;
                    $dom.removeClass('select-num');
                } else {
                    vm.order[type] = id;
                    $dom.siblings('button').removeClass('select-num');
                    $dom.addClass('select-num');
                }
                //console.log(vm.order[type]);
                //console.log($dom);
            },
            varify: function () {
                var vm = this;
                vm.errMsg.contractMobile = false;
                vm.errMsg.activity_code = false;
                if (!vm.order.contractMobile) {
                    vm.errMsg.contractMobile = true;
                    return false;
                }
                if (!vm.order.activity_code || vm.order.activity_code === '0') {
                    vm.errMsg.activity_code = true;
                    return false;
                }
                return true;
            },
            closeDialog: function () {
                this.showDialog = false;
                if (this.orderId > 0) {
                    window.location.href = "/myOrder.html?id=" + this.orderId;
                }
            },
            changeTab: function (tab) {
                var vm = this;
                vm.tab = tab || 'product';
                vm.queryFavourite();
            },
            queryCaseByCondition: function () {
                var vm = this, url = requestUrl.queryCaseByCondition, param = {
                    pageIndex: vm.collection.case.pageIndex,
                    pageSize: vm.collection.case.pageSize
                };
                axios.post(url, param).then(function (response) {
                    //console.log(response.data);
                    vm.collection.case.list = response.data.content;
                })
            },
            queryProdutionByCondition: function () {
                var vm = this, url = requestUrl.queryProdutionByCondition, param = {
                    pageIndex: vm.collection.case.pageIndex,
                    pageSize: vm.collection.case.pageSize
                };
                axios.post(url, param).then(function (response) {
                    //console.log(response.data);
                    vm.collection.product.list = response.data.content;
                })
            },
            queryLocationByCondition: function () {
                var vm = this, url = requestUrl.querylocationByCondition, param = {
                    pageIndex: vm.collection.case.pageIndex,
                    pageSize: vm.collection.case.pageSize
                };
                axios.post(url, param).then(function (response) {
                    //console.log(response.data.content);
                    vm.collection.location.list = response.data.content;
                })
            },
            linktoDetail: function (id, type) {
                location.href = 'detail.html?id=' + id + '&type=' + type || 'product';
            },
            removeWant: function (id, type) {
                var vm = this, url = requestUrl.favouriteDelete, param = {
                    id: id,
                    type: type
                };
                axios.post(url, param).then(function (response) {
                    console.log(response.data);
                    if (response.data == 'success') {
                        vm.queryFavourite(true);
                    }
                });
            },//移除心愿清单
            queryFavourite: function (refresh) {
                var vm = this, url = requestUrl.favouriteQuery, param = {
                    type: vm.tab
                };
                if (!!!refresh && vm.collection[vm.tab].list.length >0) {
                    return;
                }
                axios.post(url, param).then(function (response) {
                    console.log(response.data);
                    if (response.data && Array.isArray(response.data.content)) {
                        vm.collection[vm.tab].list = response.data.content;
                    }
                });
            }
        },
        components: {
            companyfooter: companyfooter,
            sildemenu: sildemenu,
            login: login
        },
        created: function () {
            $(".arrowBtn").on("click", function () {
                if ($(".more-info").is(":hidden")) {
                    $(".more-info").show(500);
                    $(".arrowBtn img").addClass("rotate180").removeClass("rotate0");
                } else {
                    $(".more-info").hide(500);
                    $(".arrowBtn img").addClass("rotate0").removeClass("rotate180");
                }
            });

            var vm = this;
            //vm.login.show
            $.each(queryListByField, function (key, value) {
                var url = value.url + '?name=' + value.param["name"] + '&used_field=' + value.param["used_field"] + '&field=' + value.param["field"];
                axios.post(url, {}).then(
                    function (response) {
                        //console.log(response.data);
                        var data = response.data;
                        vm.model[key] = data;
                    });
            });

            login.callback = function () {
                vm.loginId = jQuery.cookie('loginId');
                vm.accessToken = jQuery.cookie('accessToken');
                sildemenu.loginId = jQuery.cookie('loginId');
                sildemenu.accessToken = jQuery.cookie('accessToken');
                console.log(vm.accessToken);
            };

            vm.queryFavourite();
            //vm.queryCaseByCondition(); //TODO favourite/{query
            //vm.queryProdutionByCondition();
            //vm.queryLocationByCondition();
        }
    });