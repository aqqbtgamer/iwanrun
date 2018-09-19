/**
 * Created by WXP22 on 2018/3/20.
 */
console.log("Vue 脚本初始化..............");
var appMyAccount = new Vue(
    {
        el: "#container",
        data: {
        	msgText:"请重新登录",
            msgWindow:false,
            mask: false,
            loginWindow: false,
            autoLogin: false,
            loginTitle: '用户登录',
            loginId: '',
            loginBtnUl: true,
            loginIdUl: false,
            page: 1,  //显示的是哪一页
            pageSize: 10, //每一页显示的数据条数
            total: 0, //记录总数
            maxPage: 1,  //最大页数
            loginToken: 'uuixooppasyytvdbftrraskm',
            loginRole: { id: 1, role: '采购方' },
            nickname:'',
            headimg:'',
            isRead:'',
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
            onlyUnread: false,
            detail: {},
            showDetail: false
        },
        methods: {
            showLogin: function (message) {
                console.log("v-on  click method :showLogin");
                /*var vm = this
                vm.mask = true;
                vm.loginWindow = true;
                vm.loginTitle = message;*/
                lrApp.showLogin(message);
            },
            /*closeLogin: function () {
                console.log("v-on  click method :closeLogin");
                var vm = this;
                vm.mask = false;
                vm.loginWindow = false;
            },*/
            changeAutoLogin: function () {
                var vm = this;
                vm.autoLogin = !vm.autoLogin;
            },
            GetNewsByPage: function (pageIndex) {
                var vm = this;
                var url = '../../site_message/query', parm = {};
                parm.isRead = vm.isRead;
                parm.pageIndex = pageIndex - 1;
                parm.pageSize = vm.pageSize;
                axios.post(url, parm).then(function (response) {
                	var list = response.data;
                    if (list != '') {
                    	vm.List = list.content;
                    	vm.showList = list.content;
                    	vm.pageInfo = list.pageInfo;
                    	vm.total = list.pageInfo.total;
					}
                });
            },
            read: function (item) {
                var vm = this;
                item.msgid && !item.blread && (function () {
                    var url = '../../site_message/update', parm = { msgid: item.msgid };
                    axios.post(url, parm).then(
                        function (response) {
                            console.log(response.data);
                            item.blread = true;
                            vm.GetNewsByPage(vm.page);
                        });
                })();
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
                if(vm.isRead){
                	vm.isRead='';
                }else{
                	vm.isRead='0';
                }
				vm.GetNewsByPage(1);
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
                return text.length > 10 ? text.slice(0, 10) + '...' : text;
            },
            orderNoSlice: function (orderNo) {
            	var orderNoShow = '';
            	if(orderNo){
            		orderNoShow = orderNo.slice(0, 8)+" ...... "+orderNo.slice(orderNo.length-6, orderNo.length);
            	}
                return orderNoShow;
            },
            closeMsgWindow: function(){
            	var vm = this;
                vm.msgWindow = false;
                vm.msgText = '';
            },
            getUserInfo:function(){
            	var vm = this;
            	var url = "../../purchaserAccount/findMixedByLoginId";
            	axios.post(url).then(
            			function(response){
            				console.log(response.data.content);
            				var data = response.data;
            				if( data != ''){
            					var headImgs = data.headImgs;
                				var info = data.userInfo;
                				if(info != '' && info != undefined){
                					vm.nickname = info.name+'，您好';
                				}
                				if (headImgs != '' && headImgs != undefined && headImgs.length > 0) {
                					vm.headimg = headImgs[0].filePath;
                				}
            				}
            	})
            },
            //pagehandler方法 跳转到page页
            pageHandler: function (page) {
                //here you can do custom state update
                var vm = this;
                vm.page = page;
                vm.GetNewsByPage(page);
            }
        },
        watch:{
        	loginId : function(newVal, oldVal) {
				var vm = this;
				if (newVal != '' && oldVal != newVal) {
					vm.getUserInfo();
					vm.msgWindow = false;
					vm.GetNewsByPage(1);
				}
			}
        },
        created : function() {
			var vm = this;
			if (vm.loginId != '') {
				vm.pageHandler(1);
			} else {
				vm.msgWindow = true;
			}
		}
    }
);
console.log("Vue 脚本绑定渲染完成..............");

function showLoginId(loginId) {
    var vm = appMyAccount;
    vm.mask = false;
    vm.loginId = loginId;
    vm.loginIdUl = true;
    vm.loginBtnUl = false;
}