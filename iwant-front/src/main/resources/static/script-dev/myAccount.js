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
            msgWindow: false,
            msgText: '',
            loginTitle: '用户登录',
            loginId: '18018336171',
            loginToken: 'uuixooppasyytvdbftrraskm',
            loginBtnUl : true,
			loginIdUl : false,
            loginRole: { id: 1, role: '采购方' },
            account: {
                headimg: '../../img/accountImage.png',
                nickname: '用户001',
                phone: '180****6171',
                smsCode: '',
                securityanswer: {
                    question: '',
                    answer: ''
                },
                company: {
                    name: '',
                    license: '../../img/accountImage.png',
                    licenses:[],
                    licenseShow:'',
                    type: '',
                    personNum: 100
                }
            },
            setting: false,
            settingTitle: '昵称',
            settingFlag: 0 //0:nickname;1:phone;2:securityanswer
        },
        methods: {
        	initData: function(){
        		
        	},
            showLogin: function (message) {
                console.log("v-on  click method :showLogin");
                lrApp.showLogin(message);
            },
            uploadimg: function ($event) {
                var vm = this, $file = $($event.target).siblings('input:file');
                $file.trigger('click');
            },
            /*fileUpload: function ($event) {
                var vm = this, files = $event.target.files;
				
            },*/
            deleteimg:function(){
            	var vm = this;
            	var licenseShow = vm.account.company.licenseShow;
            	var licenses = vm.account.company.licenses;
            	var index = licenses.indexOf(licenseShow);
            	if(index>0){
            		vm.preCompanyCredential();
            	}else if(index==0){
            		vm.account.company.licenseShow='';
            		$("#companyCredentialUploadEle").val('');
            	}
            	licenses.remove(index);
            },
            companyCredentialUpload: function ($event) {
                var vm = this, files = $event.target.files;
                var uploadServer = '/iwantrun/remote/fileupload';
                var call = function(param){
            		mutipleDisplay('imgManage',param);
            	}
				fileUpload('uploadedCompanyCredentialImages',uploadServer,call,files[0]);
            },
            headImgUpload: function ($event) {
                var vm = this, files = $event.target.files;
                var uploadServer = '/iwantrun/remote/fileupload';
				fileUpload('uploadedHeadImages',uploadServer,headImgDisplay,files[0]);
            },
            preCompanyCredential:function(){
            	var vm = this;
            	var licenseShow = vm.account.company.licenseShow;
            	var licenses = vm.account.company.licenses;
            	var index = licenses.indexOf(licenseShow);
            	--index;
            	if(index >= 0){
            		vm.account.company.licenseShow=vm.account.company.licenses[index];
            	}
            },
            nextCompanyCredential:function(){
            	var vm = this;
            	var licenseShow = vm.account.company.licenseShow;
            	var licenses = vm.account.company.licenses;
            	var index = licenses.indexOf(licenseShow);
            	++index;
            	if(index < licenses.length){
            		vm.account.company.licenseShow=vm.account.company.licenses[index];
            	}
            },
            showSetting: function (flag) {
                flag = flag || 0;
                var vm = this;
                vm.setting = true;
                vm.settingFlag = flag;
                var title = {
                    0: '昵称',
                    1: '手机号',
                    2: '安全问题',
                    3: '公司信息',
                    4: '营业执照'
                };
                vm.settingTitle = title[flag];
            },
            closeSetting: function () {
                var vm = this;
                vm.setting = false;
            },
            closeMsgWindow: function(){
            	var vm = this;
                vm.msgWindow = false;
                vm.msgText = '';
            },
            smsCodeGet:function(){
            	var mobile = vm.account.phone;
				var msg = isMobile(mobile);
				if (msg) {
					console.log(msg);
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
            companyTypeSelect:function($event){
            	var vm=this;
            	account.company.companyTypeId=$event.target.value;
            },
            companySizeSelect:function($event){
            	var vm=this;
            	account.company.companySizeId=$event.target.value;
            },
			setBaseInfo:function(flag){
				var vm=this;
				var data=null;
				if(flag==0){
					var nickname = vm.account.nickname;
					data ={"name":nickname};
				}
				if(flag==1){
					var smsCode = vm.account.smsCode;
					var mobile = vm.account.phone;
					var msg = isMobile(mobile);
					if (msg) {
						console.log(msg);
						return;
					}
					msg = validateSMScode(smsCode);
					if (msg) {
						console.log(msg);
						return;
					}
					data={"mobileNumber":mobile,"smsCode":smsCode};
				}
				if(flag==2){
					var question=vm.account.securityanswer.question;
					var answer=vm.account.securityanswer.answer;
					data={"question":question,"answer":answer};
				}
				if(flag==3){
					var companyName=vm.account.company.companyName;
					var companySizeId=vm.account.company.companySizeId;
					var companyTypeId=vm.account.company.companyTypeId;
					data={"companyName":companyName,"companySizeId":companySizeId,"companyTypeId":companyTypeId};
				}
				if(data!=null){
					var dataJSON=JSON.stringify(data);
					setUserInfo(dataJSON);
				}
			}
        }
    }
);
console.log("Vue 脚本绑定渲染完成..............");

/* 
* 方法:Array.remove(dx) 通过遍历,重构数组 
* 功能:删除数组元素. 
* 参数:dx删除元素的下标. 
*/
function fileUpload(contentId, url, callback, uploadFile) {
	var formData = new FormData();
	formData.append('fileUpload', uploadFile);
	var data = formData;
	$.ajax({
				url : url,
				data : data,
				type : "post",
				dataType : "text",
				cache : false,
				processData : false,// 用于对data参数进行序列化处理 这里必须false
				contentType : false, // 必须
				success : function(result) {
					callback(result);
				}

			});

}

function mutipleDisplay(displayId, param) {
	var vm = appMyAccount;
	if (param) {
		vm.account.company.licenses.push(param);
		vm.account.company.licenseShow = param;
		setUserInfo({
					"imgManage[]" : vm.account.company.licenses
				});
	}
}

function headImgDisplay(param) {
	var vm = appMyAccount;
	if (param) {
		vm.account.headimg = param;
		setUserInfo({"headimg" : param});
	}
}

function setUserInfo(data) {
	var vm = appMyAccount;
	var url = baseUrl + 'purchaserAccount/addAndModifyInfo';
	var account = vm.account;
	/*
	 * var data={ "mobileNumber":account.phone, "name":account.nickname,
	 * "question":account.securityanswer.question,
	 * "answer":account.securityanswer.answer,
	 * "companyName":account.company.companyName,
	 * "companySizeId":account.company.companySizeId,
	 * "companyTypeId":account.company.companyTypeId,
	 * "imgManage[]":account.company.licenses, "headimg":account.headimg }
	 */
	var callback = function(result) {
		var flag = vm.settingFlag;
		console.log(result);
		setUserInfoBack(result.messageBody);
	}
	$http.post(url, data, callback);
}

function setUserInfoBack(data){
	var vm = appMyAccount;
	if(data){
		showMsg(data);
		vm.initData();
	}else{
		vm.closeSetting();
	}
}

function showMsg(msg) {
	var vm = appMyAccount;
	vm.msgWindow = true, 
	vm.msgText = msg;
}

function showLoginId(loginId){
	var vm = appMyAccount;
	vm.mask = false;
	vm.loginId = loginId;
	vm.loginIdUl = true;
	vm.loginBtnUl = false;
}