/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
var appListProduction = new Vue(
    {
        el: "#container",
        data: {
            mask: false,
            loginWindow: false,
            autoLogin: false,
            loginTitle: '用户登录',
            loginId: '18018336171',
            loginBtnUl: true,
            loginIdUl: false,
            loginToken: 'uuixooppasyytvdbftrraskm',
            loginRole: { id: 1, role: '采购方' },
            page: 1,  //显示的是哪一页
            pageSize: 1, //每一页显示的数据条数
            total: 0, //记录总数
            maxPage: 1,  //最大页数
            criteria: {},
            List: [],
            pageInfo: {},
            searchCriteria: {
                activityProvinceCode: [],
                activitytype: [],
                personNum: [],
                duration: [],
                specialTagsCode: [],
                orderSimulatePriceCode: []
            }
        },
        created: function () {
            var vm = this;
            vm.pageHandler(1);
            vm.queryProdutionByCondition('1');
            vm.queryDictionaryList();

        },

        methods: {
        	toOrderSubmit : function() {
				var vm = this;
				window.location.href = "./ordersubmit.html"
            },
            showLogin: function (message) {
                console.log("v-on  click method :showLogin");
                lrApp.showLogin(message);
            },
            closeLogin: function () {
                console.log("v-on  click method :closeLogin");
                var vm = this;
                vm.mask = false;
                vm.loginWindow = false;
            },
            changeAutoLogin: function () {
                var vm = this;
                vm.autoLogin = !vm.autoLogin;
            },
            checkchange: function ($event) {
                var vm = this, $dom = $($event.target), $input = $dom.siblings('input'), name = $input.attr('name'), val = $input.val();
                if ($dom.hasClass('icon-checkbox-non')) {
                    $dom.removeClass('icon-checkbox-non').addClass('icon-checkbox-blank');
                    vm.searchCriteria[name].push(val);
                } else {
                    $dom.removeClass('icon-checkbox-blank').addClass('icon-checkbox-non');
                    var index = vm.searchCriteria[name].indexOf(val);
                    vm.searchCriteria[name].splice(index, 1);
                }
                console.log(vm.searchCriteria[name]);
                //查询
                vm.queryProdutionByCondition("1");
            }
            ,
            queryDictionaryList: function () {
                var vm = this;
                var url = "../../production/produtionSearchList";
                var param = {
                    name: "common"
                };
                axios.post(url, param).then(
                    function (response) {
                        console.log(response.data);
                        var list = response.data;
                        if (list != '') {
                            vm.criteria.activityProvinceCode = list.activityProvinceCode;
                            vm.criteria.activitytype = list.activitytype;
                            vm.criteria.specialTagsCode = list.specialTagsCode;
                            vm.criteria.personNum = list.personNum;
                            vm.criteria.duration = list.duration;
                            vm.criteria.orderSimulatePriceCode = list.orderSimulatePriceCode;
                        }

                    })

            },
            queryProdutionByCondition: function (pageIndex) {
                var vm = this;
                var url = "../../production/queryProdutionByCondition";
                var param = vm.searchCriteria;
                param.pageIndex = pageIndex - 1;
                param.pageSize = vm.pageSize;
                axios.post(url, param).then(
                    function (response) {
                        console.log(response.data);
                        var list = response.data;
                        if (list != '') {
                            vm.List = list.content;
                            vm.pageInfo = list.pageInfo;
                            vm.total = list.pageInfo.total;
                        }

                    })
            },
            //pagehandler方法 跳转到page页
            pageHandler: function (page) {
                //here you can do custom state update
                var vm = this;
                vm.page = page;
                vm.queryProdutionByCondition(vm.page);
            },
            routeToDetail: function (id) {
                id && (window.location.href = "./productdetail.html?type=product&id=" + id);
            }

        }
    }
);
console.log("Vue 脚本绑定渲染完成..............");

function showLoginId(loginId) {
    var vm = appListProduction;
    vm.mask = false;
    vm.loginId = loginId;
    vm.loginIdUl = true;
    vm.loginBtnUl = false;
}