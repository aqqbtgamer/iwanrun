
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
            }
        },
        methods: {
            submitOrder: function () {
                var vm = this, url = requestUrl.orderSubmit;
                //console.log(vm.order);
                var request = {
                    order: vm.order,
                    user: { loginId: vm.loginId }
                };

                var param = { requestJson: JSON.stringify(request) };
                axios.post(url, param).then(function (response) {
                    console.log(response.data);
                    var data = response.data;
                    if (data != null && data.submitResult == true) {
                        alert("需求提交成功");
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
        }
    });