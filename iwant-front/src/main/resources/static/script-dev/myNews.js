/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
var appMyAccount = new Vue(
    {
        el: "#container",
        data: {
            mask: false,
            loginWindow: false,
            autoLogin: false,
            loginTitle: '用户登录',
            loginId: '18018336171',
            loginToken: 'uuixooppasyytvdbftrraskm',
            loginRole: { id: 1, role: '采购方' },
            account: {
                headimg: '../../img/accountImage.png',
                nickname: '用户001',
                phone: '180****6171',
                securityanswer: ''
            },
            List: [
                {
                    id: 4,
                    timestamp: '2017年10月12日',
                    from_user: '理查德',
                    message: '怎么说怎么说怎么说',
                    blread: true,
                    order_no: '100001'
                },
                {
                    id: 1,
                    timestamp: '2017年10月12日',
                    from_user: '理查德',
                    message: '怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说怎么说11\r\n怎么说怎么说怎么说22\r\n怎么说怎么说怎么说\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n怎\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n怎',
                    blread: false,
                    order_no: '100001'
                },
                {
                    id: 2,
                    timestamp: '2017年10月12日',
                    from_user: '理查德',
                    message: '怎么说怎么说怎么说',
                    blread: true,
                    order_no: '100001'
                },
                {
                    id: 3,
                    timestamp: '2017年10月12日',
                    from_user: '理查德',
                    message: '怎么说怎么说怎么说',
                    blread: false,
                    order_no: '100001'
                }
            ],
            showList: [],
            onlyUnread: true,
            detail: {},
            showDetail: false
        },
        methods: {
            showLogin: function (message) {
                console.log("v-on  click method :showLogin");
                var vm = this
                vm.mask = true;
                vm.loginWindow = true;
                vm.loginTitle = message;
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
            GetNewsByPage: function () {
                var vm = this;
                vm.setShowList();
                var url = '../../site_message';
                axios.get(url).then(
                    function (response) {
                        console.log(response.data);
                        Array.isArray(response.data) && function () {
                            vm.List = response.data;
                            vm.setShowList();
                        }();
                    });
            },
            read: function (item) {
                var vm = this;
                item.id && !item.blread && function () {
                    var url = '../../site_message/state', parm = { msgid: item.id };
                    axios.put(url, parm).then(
                        function (response) {
                            console.log(response.data);
                            item.blread = true;
                        });
                }();
            },
            show: function (item) {
                var vm = this;
                vm.detail = item;
                vm.showDetail = true;
                vm.read(item);
            },
            close: function () {
                var vm = this;
                vm.showDetail = false;
            },
            changeOnlyUnread: function () {
                var vm = this;
                vm.onlyUnread = !vm.onlyUnread;
                vm.setShowList();

            },
            setShowList: function () {
                var vm = this;
                !vm.onlyUnread ? (vm.showList = vm.List) : function () {
                    vm.showList = vm.List.filter(function (f) {
                        return !f.blread;
                    });
                }();
            },
            maxSlice: function (text) {
                return text.length > 20 ? text.slice(0, 20) + '...' : text;
            }
        },
        created: function () {
            var vm = this;
            vm.GetNewsByPage();
        }
    }
);
console.log("Vue 脚本绑定渲染完成..............");