/**
 * Created by WXP22 on 2018/3/2.
 */
console.log("Vue 脚本初始化..............");

var baseUrl = "http://localhost:8088/iwantrun/";

var appIndex = new Vue(
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
				login : function() {
					var account = this.account;
					var mobile = account.loginId;
					var correct = false;
					if (this.messageLogin) {
						var smsCode = account.smsCode;
						correct = validateAccountMessageLogin(mobile, smsCode);
					} else {
						var password = account.password;
						correct = validateAccountPwdLogin(mobile, password);
					}
					if (correct) {
						var data = {};
						data.messageLogin = this.messageLogin;
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
					vm.registerTitle = (vm.counselor ? '咨询师' : '用户') + '注册';
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
				},
				forgetPassword : function() {

				}
			}
		});

function loginBack(data) {
	var messageBody = data.messageBody;
	if (messageBody) {
		var message = JSON.parse(messageBody);
		var errorMsg = message.errorMsg;
		if (errorMsg) {
			showErrMsg(errorMsg);
			return;
		}
	}
	var accessToken = data.accessToken;
	if (accessToken) {
		var $ = jQuery;
		var loginId = appIndex.account.loginId;
		$.cookie('accessToken', accessToken);
		$.cookie('loginId', loginId);
		appIndex.account = {};
		appIndex.loginWindow = false;
		appIndex.mask = false;
		appIndex.loginId = loginId;
		appIndex.loginIdUl = true;
		appIndex.loginBtnUl = false;
		showErrMsg('登录成功');
	}
}

function validateAccountPwdLogin(mobile, password) {
	// 先校验手机号
	var msg = isMobile(mobile);
	if (msg) {
		showErrMsg(msg);
		return false;
	}
	// 再校验密码
	msg = validatePwd(password);
	if (msg) {
		showErrMsg(msg);
		return false;
	}
	return true;
}

function validateAccountMessageLogin(mobile, smsCode) {
	// 先校验手机号
	var msg = isMobile(mobile);
	if (msg) {
		showErrMsg(msg);
		return false;
	}
	// 最后校验短信验证码
	msg = validateSMScode(smsCode);
	if (msg) {
		showErrMsg(msg);
		return false;
	}
	return true;
}

function validateAccountRegister(mobile, password, rePassword, smsCode) {
	// 先校验手机号
	var msg = isMobile(mobile);
	if (msg) {
		showErrMsg(msg);
		return false;
	}
	// 再校验密码
	msg = validatePwd(password, rePassword);
	if (msg) {
		showErrMsg(msg);
		return false;
	}
	// 最后校验短信验证码
	msg = validateSMScode(smsCode);
	if (msg) {
		showErrMsg(msg);
		return false;
	}
	return true;
}

function validateSMScode(smsCode) {
	if (!smsCode) {
		return '请输入短信验证码';
	}
	var regex = new RegExp('\\d{6}', 'g');
	var correct = regex.test(smsCode);
	if (!correct) {
		return '短信验证码格式不正确，请重新输入';
	}
}

function validatePwd(password, rePassword) {
	if (!password) {
		return "请输入密码";
	}

	var regex = new RegExp('(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}', 'g');
	var correct = regex.test(password);
	if (!correct) {
		return '密码格式不正确，请重新输入';
	}

	if (rePassword != undefined) {
		if (rePassword != password) {
			return '密码不匹配，请重新输入';
		}
	}
}

function isMobile(mobile) {
	if (!mobile) {
		return '请输入手机号';
	}
	var regex = new RegExp('[1][3578]\\d{9}', 'gim');
	var is = regex.test(mobile);
	if (!is) {
		return '手机号无效，请重新输入';
	}
	return null;
}

function showErrMsg(msg) {
	var vm = appIndex;
	vm.account.errMsg = msg;
}

function registerBack(data) {
	var messageBody = data.messageBody;
	if (messageBody) {
		var message = JSON.parse(messageBody);
		var errorMsg = message.errorMsg;
		if (errorMsg) {
			showErrMsg(errorMsg);
			return;
		}
	}
	var accessToken = data.accessToken;
	if (accessToken) {
		var $ = jQuery;
		var loginId = appIndex.account.loginId;
		$.cookie('accessToken', accessToken);
		$.cookie('loginId', loginId);
		appIndex.loginWindow = true;
		appIndex.registerWindow = false;
		appIndex.mask = false;
		appIndex.account = {};
		appIndex.loginId = loginId;
		showErrMsg('注册成功，请登录');
	}
}

function getSMSCodeBack(data) {
	var vm = appIndex;

	if (data) {
		console.log('短信验证码获取结束，结果' + data);

		var returnstatus = data.returnstatus;
		var message = data.message;

		if (returnstatus == 'Success') {
			showErrMsg('短信已发送');
		} else if (returnstatus == 'Faild') {
			console.log(message);
			showErrMsg('短信获取失败，请联系管理员');
		} else {
			if (message) {
				showErrMsg(message);
			} else {
				showErrMsg('短信获取失败，请重新获取');
			}
		}
	} else {
		showErrMsg('短信获取失败，请重新获取');
	}
}

console.log("Vue 脚本绑定渲染完成..............");