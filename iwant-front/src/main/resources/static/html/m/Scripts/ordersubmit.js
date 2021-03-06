﻿
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
            showWish: false,
            showDialog: false,
            tab: 'product',
            collection: {
                product: {
                    list: [],
                    pageIndex: 0,
                    pageSize: 3,
                    showbtnmore: false
                },
                case: {
                    list: [],
                    pageIndex: 0,
                    pageSize: 3,
                    showbtnmore: false
                },
                location: {
                    list: [],
                    pageIndex: 0,
                    pageSize: 3,
                    showbtnmore: false
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
                if (!jQuery.cookie('accessToken')) {
                    login.show = true;
                    return;
                }
                if (!vm.varify()) {
                    return false;
                }

                var request = {
                    order: vm.order,
                    user: { loginId: vm.loginId }
                };
                var requestData = {
                    requestJson: JSON.stringify(request)
                };
                var callback = {
                    request: requestData,
                    vm: vm,
                    success: function (result) {
                        console.log(result);
                        if (result != null && result.submitResult && result.orders) {
                            vm.showDialog = true;
                            vm.orderId = result.orders.id;
                            //alert("需求提交成功");
                            //window.location.href = "./orderlist.html";
                        }
                    }
                };
                $http_form.post(url, callback);
            },
            showsildemenu: function () {
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
                    window.location.href = "myOrder.html?id=" + this.orderId;
                }
            },
            changeTab: function (tab) {
                var vm = this;
                vm.tab = tab || 'product';
                vm.wishcartQuery(false, false);
            },
            queryCaseByCondition: function () {
                var vm = this, url = requestUrl.queryCaseByCondition, param = {
                    pageIndex: vm.collection.case.pageIndex,
                    pageSize: vm.collection.case.pageSize
                };
                axios.post(url, param).then(function (response) {
                    vm.collection.case.list = response.data.content;
                })
            },
            queryProdutionByCondition: function () {
                var vm = this, url = requestUrl.queryProdutionByCondition, param = {
                    pageIndex: vm.collection.case.pageIndex,
                    pageSize: vm.collection.case.pageSize
                };
                axios.post(url, param).then(function (response) {
                    vm.collection.product.list = response.data.content;
                })
            },
            queryLocationByCondition: function () {
                var vm = this, url = requestUrl.querylocationByCondition, param = {
                    pageIndex: vm.collection.case.pageIndex,
                    pageSize: vm.collection.case.pageSize
                };
                axios.post(url, param).then(function (response) {
                    vm.collection.location.list = response.data.content;
                })
            },
            linktoDetail: function (id, type) {
                location.href = 'detail.html?id=' + id + '&type=' + type || 'product';
            },
            removeWant: function (id, type) {
                var vm = this, type = type === 'product' ? 'production' : type;
                var url = requestUrl.wishcartDelete, param = {
                    id: id,
                    type: type,
                    loginId: vm.loginId
                };
                axios.post(url, param).then(function (response) {
                    if (response.data && response.data.success) {
                        vm.wishcartQuery(true, false);
                    }
                });
            },//移除心愿清单
            wishcartQuery: function (refresh, getMore) {
                var vm = this, type = vm.tab === 'product' ? 'production' : vm.tab;
                var url = requestUrl.wishcartQuery, param = {
                    type: type,
                    loginId: vm.loginId,
                    pageIndex: vm.collection[vm.tab].pageIndex,
                    pageSize: vm.collection[vm.tab].pageSize
                };

                var getDetailById = {
                    Production: requestUrl.getProductionDetailsById,
                    Case: requestUrl.getCaseDetailsById,
                    Location: requestUrl.getLocationDetailsById
                };

                if (!!!refresh && vm.collection[vm.tab].list.length > 0 && !getMore) {
                    return;
                }
                if (!!refresh) {
                    vm.collection[vm.tab].list = [];
                }
                axios.post(url, param).then(function (response) {
                    if (response.data && Array.isArray(response.data.content)) {
                        $.each(response.data.content, function (index, item) {
                            var getUrl = getDetailById[item.type] + '?id=' + item.typeId;
                            axios.post(getUrl, {}).then(function (response) {
                                console.log(response.data);
                                item.type = item.type.toLowerCase();
                                item.type = item.type === 'production' ? 'product' : item.type;
                                if (item.type === 'case' && response.data.caseVo) {
                                    item.class = JSON.parse(response.data.caseVo) //Case
                                } else {
                                    item.class = response.data;
                                }
                            });
                        });
                        vm.collection[vm.tab].list = vm.collection[vm.tab].list.concat(response.data.content);

                        vm.collection[vm.tab].showbtnmore = vm.collection[vm.tab].list.length < response.data.pageInfo.total;
                    } else {
                        vm.collection[vm.tab].list = [];
                    }
                });
            },
            getMore: function () {
                var vm = this;
                vm.collection[vm.tab].pageIndex += 1;
                vm.wishcartQuery(false, true);
            },
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
            $.each(queryListByField, function (key, value) {
                var url = value.url + '?name=' + value.param["name"] + '&used_field=' + value.param["used_field"] + '&field=' + value.param["field"];
                axios.post(url, {}).then(function (response) {
                    var data = response.data;
                    vm.model[key] = data;
                });
            });

            login.callback = function () {
                vm.loginId = jQuery.cookie('loginId');
                vm.accessToken = jQuery.cookie('accessToken');
                sildemenu.loginId = jQuery.cookie('loginId');
                sildemenu.accessToken = jQuery.cookie('accessToken');
                vm.showWish = true;
                vm.wishcartQuery(true, false);
            };

            vm.ValidateLogin(function () {
                vm.showWish = true;
                vm.wishcartQuery(true, false);
            }, function () {
                //login.show = true;
            });
        }
    });