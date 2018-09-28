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
            loginId: '',
            loginBtnUl: true,
            loginIdUl: false,
            loginToken: 'uuixooppasyytvdbftrraskm',
            loginRole: { id: 1, role: '采购方' },
            showFlag: 'location',
            nickname:'',
            headimg:'',
            msgText:"请重新登录",
            msgWindow:false,
            account: {
                headimg: '../../img/accountImage.png',
                nickname: '用户001',
                phone: '180****6171',
                securityanswer: ''
            },
            locations: [
            ],
            products: [
                
            ],
            cases: []
        },
        created:function(){
        	var vm = this;
        	if(vm.loginId != ''){
        		
        	}else{
        		vm.msgWindow=true;
        	}


        },
        watch:{
        	loginId:function(newVal,oldVal){
        		var vm = this;
    			if(newVal!= ''&& oldVal != newVal){
    				vm.getUserInfo();
    				vm.msgWindow=false;
    				vm.queryCollectList('location');
    			}
        	}
        },
        methods: {
            queryCollectList: function (queryType) {
                var vm = this;
                var url = "../../favourite/query/";
                axios.post(url,{type:queryType}).then(
                    function (response) {
                        console.log(response.data);
                        if(queryType=='location'){
                        	vm.locations = response.data.content;
                        }
                        if(queryType=='product'){
                        	vm.products = response.data.content;
                        }
                        if(queryType=='case'){
                        	vm.cases = response.data.content;
                        }

                    })
            },
            showLogin: function (message) {
                console.log("v-on  click method :showLogin");
                /*var vm = this
                vm.mask = true;
                vm.loginWindow = true;
                vm.loginTitle = message;*/
                lrApp.showLogin(message);
            },
            closeMsgWindow: function(){
            	var vm = this;
                vm.msgWindow = false;
                vm.msgText = '';
            },
            changeAutoLogin: function () {
                var vm = this;
                vm.autoLogin = !vm.autoLogin;
            },
            tab: function ($event) {
                var vm = this;
                vm.showFlag = $event.target.name;
                vm.queryCollectList($event.target.name);
            },
            routeToDetail: function (id, type) {
                var vm = this;
                id && type && (window.location.href = './productdetail.html?type=' + type + '&id=' + id);
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
            }
        }
    }
);
function showLoginId(loginId, opt){
	var vm = appMyAccount;
	vm.mask = false;
	vm.loginId = loginId;
	vm.loginIdUl = true;
	vm.loginBtnUl = false;
	
	if(opt == 'login'){
//		initData();
	}
}
function clearLoginId() {
    var vm = appMyAccount;
    vm.loginId = '';
    vm.loginIdUl = false;
    vm.loginBtnUl = true;
}
console.log("Vue 脚本绑定渲染完成..............");

