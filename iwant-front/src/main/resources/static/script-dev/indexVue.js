/**
 * Created by WXP22 on 2018/3/2.
 */
console.log("Vue 脚本初始化..............");

/*var appIndex = new Vue(
		{
			el : "#container",
			data : {
				mask : false,
				loginWindow : false,
				autoLogin : false,
				messageLogin : false,// 是否短信登录
				counselor : false,// 是否咨询师
				loginTitle : '用户登录',
				registerTitle : '用户注册',
				registerWindow : false,
				registerBtn : true,
				forgetBtn : false,
				bannerList : [ 'img/banner/banner1.png' ],
				loginId : '18018336171',
				loginToken : 'uuixooppasyytvdbftrraskm',
				loginRole : {
					id : 1,
					role : '采购方'
				},
				loginBtnUl : true,
				loginIdUl : false,
				account : {
					loginId : '',
					smsCode : '',
					password : '',
					rePassword : '',
					mobileNumber : '',
					// errMsg:'密码格式不正确，请重新输入',
					errMsg : ''
				},

				productIndexList : [ {
					id : 1,
					img : 'img/normalpd1.png',
					text : '长沙南区10公里员工慢跑',
					price : '288/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				} ],
				locationIndexList : [
						{
							id : 1,
							img : 'img/location1.png',
							text : '夏日清凉专场 | 水上球类运动',
							long_text : 'You may of heard fast growing companies require motivated and healthy employees that communicate efficiently. Clearly it’s easier than ever to speak to one another online but we’ve found that doesn’t create meaningful relationships.'
						},
						{
							id : 1,
							img : 'img/location1.png',
							text : '夏日清凉专场 | 水上球类运动',
							long_text : 'You may of heard fast growing companies require motivated and healthy employees that communicate efficiently. Clearly it’s easier than ever to speak to one another online but we’ve found that doesn’t create meaningful relationships.'
						},
						{
							id : 1,
							img : 'img/location1.png',
							text : '夏日清凉专场 | 水上球类运动',
							long_text : 'You may of heard fast growing companies require motivated and healthy employees that communicate efficiently. Clearly it’s easier than ever to speak to one another online but we’ve found that doesn’t create meaningful relationships.'
						} ],
				caseIndexList : [ {
					id : 1,
					img : 'img/case1.png',
					isBig : true
				}, {
					id : 1,
					img : 'img/case2.png',
					isBig : false
				}, {
					id : 1,
					img : 'img/case3.png',
					isBig : false
				}, {
					id : 1,
					img : 'img/case4.png',
					isBig : false
				}, {
					id : 1,
					img : 'img/case5.png',
					isBig : false
				}, {
					id : 1,
					img : 'img/case6.png',
					isBig : true
				} ]
			},
			methods : {
				forget : function(){
					var account = this.account;
					var mobile = account.loginId;
					var smsCode = account.smsCode;
					var password = account.password;
					var rePassword = account.rePassword;

					var correct = validateAccountRegister(mobile, password,
							rePassword, smsCode);

					if (correct) {
						account.mobileNumber = mobile;
						var data = {};
						data.smsCode = smsCode;
						data.account = account;
						var url = baseUrl + "purchaserAccount/modifyPwd";
						$http.post(url, JSON.stringify(data), forgetBack);
					}
				},
				login : function() {
					var account = this.account;
					var mobile = account.loginId;
					var correct = false;
					var smsCode = account.smsCode;
					if (this.messageLogin) {
						correct = validateAccountMessageLogin(mobile, smsCode);
					} else {
						var password = account.password;
						correct = validateAccountPwdLogin(mobile, password);
					}
					if (correct) {
						var data = {};
						data.smsCode = smsCode;
						data.messageLogin = this.messageLogin;
						data.autoLogin = this.autoLogin;
						data.account = account;
						var url = baseUrl + "purchaserAccount/login";
						$http.post(url, JSON.stringify(data), loginBack);
					}
				},
				register : function() {
					var account = this.account;
					var mobile = account.loginId;
					var smsCode = account.smsCode;
					var password = account.password;
					var rePassword = account.rePassword;

					var correct = validateAccountRegister(mobile, password,
							rePassword, smsCode);

					if (correct) {
						account.mobileNumber = mobile;
						var data = {};
						data.smsCode = smsCode;
						data.account = account;
						var url = baseUrl + "purchaserAccount/register";
						$http.post(url, JSON.stringify(data), registerBack);
					}
				},
				accountSmsCodeGet : function() {
					var mobile = this.account.loginId;
					// 先校验手机号
					var msg = isMobile(mobile);
					if (msg) {
						showErrMsg(msg);
						return;
					}
					var url = baseUrl + 'smsCode/getSMSCode';
					var data = {
						'mobile' : mobile
					};
					data = JSON.stringify(data);

					console.log('短信验证码获取，参数：' + data);

					$http.post(url, data, getSMSCodeBack);
				},
				accountFocus : function(flag) {
					var vm = this;
					showErrMsg('');
				},
				showLogin : function(flag) {
					
					lrApp.showLogin(flag);
					
					console.log("v-on  click method :showLogin");
					var vm = this
					vm.mask = true;
					vm.counselor = !!flag;
					vm.registerWindow = false;
					vm.loginWindow = true;
					vm.loginTitle = (vm.counselor ? '咨询师' : '用户') + '登录';
					showErrMsg();
				},
				closeLogin : function() {
					console.log("v-on  click method :closeLogin");
					var vm = this;
					vm.mask = false;
					vm.loginWindow = false;
				},
				changeAutoLogin : function() {
					var vm = this;
					vm.autoLogin = !vm.autoLogin;
				},
				toOrderSubmit : function() {
					var vm = this;
					window.location.href = "html/iwantrun/ordersubmit.html"
				},
				showRegister : function() {
					console.log("v-on  click method :showRegister");
					var vm = this
					vm.mask = true;
					vm.loginWindow = false;
					vm.registerWindow = true;
					vm.forgetBtn = false;
					vm.registerBtn = true;
					vm.registerTitle = (vm.counselor ? '咨询师' : '用户') + '注册';
					showErrMsg();
				},
				showForget : function() {
					console.log("v-on  click method :showForget");
					var vm = this
					vm.mask = true;
					vm.loginWindow = false;
					vm.registerWindow = true;
					vm.registerBtn = false;
					vm.forgetBtn = true;
					vm.registerTitle = '忘记密码';
					showErrMsg();
				},
				closeRegister : function() {
					console.log("v-on  click method :closeRegister");
					var vm = this;
					vm.mask = false;
					vm.registerWindow = false;
				},
				changeMessageLogin : function() {
					var vm = this;
					vm.messageLogin = !vm.messageLogin;
				}
			}
		});*/
var appIndex = new Vue(
		{
			el : "#container",
			data : {
				mask : false,
				bannerList : [ 'img/banner/banner1.png' ],
				loginId : '18018336171',
				loginBtnUl : true,
				loginIdUl : false,
				productIndexList : [ {
					id : 1,
					img : 'img/normalpd1.png',
					text : '长沙南区10公里员工慢跑',
					price : '288/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				}, {
					id : 2,
					img : 'img/normalpd2.png',
					text : '南湖水上龙舟游',
					price : '388/人'
				} ],
				locationIndexList : [
						{
							id : 1,
							img : 'img/location1.png',
							text : '夏日清凉专场 | 水上球类运动',
							long_text : 'You may of heard fast growing companies require motivated and healthy employees that communicate efficiently. Clearly it’s easier than ever to speak to one another online but we’ve found that doesn’t create meaningful relationships.'
						},
						{
							id : 1,
							img : 'img/location1.png',
							text : '夏日清凉专场 | 水上球类运动',
							long_text : 'You may of heard fast growing companies require motivated and healthy employees that communicate efficiently. Clearly it’s easier than ever to speak to one another online but we’ve found that doesn’t create meaningful relationships.'
						},
						{
							id : 1,
							img : 'img/location1.png',
							text : '夏日清凉专场 | 水上球类运动',
							long_text : 'You may of heard fast growing companies require motivated and healthy employees that communicate efficiently. Clearly it’s easier than ever to speak to one another online but we’ve found that doesn’t create meaningful relationships.'
						} ],
				caseIndexList : [ {
					id : 1,
					img : 'img/case1.png',
					isBig : true
				}, {
					id : 1,
					img : 'img/case2.png',
					isBig : false
				}, {
					id : 1,
					img : 'img/case3.png',
					isBig : false
				}, {
					id : 1,
					img : 'img/case4.png',
					isBig : false
				}, {
					id : 1,
					img : 'img/case5.png',
					isBig : false
				}, {
					id : 1,
					img : 'img/case6.png',
					isBig : true
				} ]
			},
			methods : {
				showLogin : function(flag) {
					
					lrApp.showLogin(flag);
					
					/*console.log("v-on  click method :showLogin");
					var vm = this
					vm.mask = true;
					vm.counselor = !!flag;
					vm.registerWindow = false;
					vm.loginWindow = true;
					vm.loginTitle = (vm.counselor ? '咨询师' : '用户') + '登录';
					showErrMsg();*/
				},
				toOrderSubmit : function() {
					var vm = this;
					window.location.href = "html/iwantrun/ordersubmit.html"
				}
			}
		});

function showLoginId(loginId){
	var vm = appIndex;
	vm.mask = false;
	vm.loginId = loginId;
	vm.loginIdUl = true;
	vm.loginBtnUl = false;
}
console.log("Vue 脚本绑定渲染完成..............");