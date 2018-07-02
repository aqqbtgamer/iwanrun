var $http = {
	post : function(url, data, callback, dataType){
		
		if(!dataType){
			dataType = 'json';
		}
		
		$.ajax({
			url : url,
			type : 'POST',
			data : data,
			contentType : 'application/json',
			dataType : dataType,
			success : callback,
			error : callback
		});
	}
};

var pathnames = location.pathname.split('/');
var baseUrl = location.origin + "/" + pathnames[1] + "/";

/**
 *  方法:Array.remove(dx) 通过遍历,重构数组 
 *  功能:删除数组元素. 参数:dx删除元素的下标. 
 */
Array.prototype.remove=function(dx) 
{ 
  if(isNaN(dx)||dx>this.length){return false;} 
  for(var i=0,n=0;i<this.length;i++) 
  { 
    if(this[i]!=this[dx]) 
    { 
      this[n++]=this[i] 
    } 
  } 
  this.length-=1 
}

jQuery(document).ready(function(){
	if(jQuery.cookie('accessToken')){
		//检查token
		verifyToken();
	}
});

function verifyToken(){
	$http.post(baseUrl+'token/verify',{},verifyTokenBack);
}

function verifyTokenBack(data){
	if(data && data.token == 'success'){
		console.log('登录状态为已登录');
		loginSuccess();
	}else{
		console.log('还未登录');
	}
}

function accountReset(loginId) {
	lrApp.account = {
		loginId : (loginId ? loginId : ''),
		smsCode : '',
		password : '',
		rePassword : '',
		mobileNumber : '',
		errMsg : ''
	}
}

// 登录和注册App
var lrApp=new Vue({
	el : "#lrPannel",
	data : {
		loginWindow : false,
		autoLogin : false,
		messageLogin : false,// 是否短信登录
		counselor : false,// 是否咨询师
		loginTitle : '用户登录',
		registerTitle : '用户注册',
		registerWindow : false,
		registerBtn : true,
		forgetBtn : false,
		loginRole : {
			id : 1,
			role : '采购方'
		},
		account : {
			loginId : '',
			smsCode : '',
			password : '',
			rePassword : '',
			mobileNumber : '',
			errMsg : ''
        },
        SMS: {
            timer: null,
            disabled: false,
            count: 0,
            btnText: '短信验证'
        }
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
        accountSmsCodeGet: function () {
            var vm = this;
            
			var mobile = this.account.loginId;
			// 先校验手机号
			var msg = isMobile(mobile);
			if (msg) {
				showErrMsg(msg);
				return;
            }

            var TIME_COUNT = 60;
            if (!vm.SMS.timer) {
                vm.SMS.count = TIME_COUNT;
                vm.SMS.disabled = true;
                vm.SMS.timer = setInterval(() => {
                    if (vm.SMS.count > 0 && vm.SMS.count <= TIME_COUNT) {
                        console.log(vm.SMS.count);
                        vm.SMS.btnText = vm.SMS.count + 's后重发';
                        vm.SMS.count--;
                    } else {
                        vm.SMS.disabled = false;
                        clearInterval(vm.SMS.timer);
                        vm.SMS.timer = null;
                        vm.SMS.btnText = '短信验证';
                    }
                }, 1000);
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
			accountReset(vm.account.loginId);
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
			accountReset();
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
			accountReset(vm.account.loginId);
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
			accountReset(vm.account.loginId);
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
			accountReset();
			vm.mask = false;
			vm.registerWindow = false;
		},
		changeMessageLogin : function() {
			var vm = this;
			vm.messageLogin = !vm.messageLogin;
		}
	}

})

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
		loginSuccess();
	}
}

function loginSuccess() {
	var $ = jQuery;

	var loginId = lrApp.account.loginId;
	if (!loginId) {
		loginId = $.cookie('loginId');
	}
	// $.cookie('accessToken', accessToken);
	lrApp.account = {};
	lrApp.loginWindow = false;
	
	// 其他页面登录时需要写的方法，里面的appIndex替换成当前页面的Vue实例
	showLoginId(loginId);
	
	showErrMsg('登录成功');
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

    if (password.length < 6) {
        return "密码最少6个字符";
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
	var vm = lrApp;
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
		var loginId = lrApp.account.loginId;
		lrApp.loginWindow = true;
		lrApp.registerWindow = false;
		lrApp.account = {};
		lrApp.account.loginId = loginId;
		appIndex.mask = false;
		appIndex.loginId = loginId;
		showErrMsg('注册成功，请登录');
	}
}

function forgetBack(data){
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
		var loginId = lrApp.account.loginId;
		$.cookie('accessToken', accessToken);
		$.cookie('loginId', loginId);
		lrApp.loginWindow = true;
		lrApp.registerWindow = false;
		lrApp.account = {};
		appIndex.mask = false;
		appIndex.loginId = loginId;
		showErrMsg('密码修改成功，请登录');
	}
}

function getSMSCodeBack(data) {
	var vm = appIndex;
	var encryptedSMSCode = $.cookie('encryptedSMSCode');
	if (data) {
		console.log('短信验证码获取结束，结果' + data);

		var returnstatus = data.returnstatus;
		var message = data.message;

		if (returnstatus == 'Success') {
			showErrMsg('短信已发送');
			//$.cookie('encryptedSMSCode', data.encryptedSMSCode);
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
