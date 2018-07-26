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
            loginId: '',
            loginToken: 'uuixooppasyytvdbftrraskm',
            loginBtnUl : true,
			loginIdUl : false,
            loginRole: { id: 1, role: '采购方' },
            account: {
                headimg: '../../img/accountImage.png',
                nickname: '用户001',
                phone: '',
                smsCode: '',
                securityanswer: {
                    question: '',
                    answer: ''
                },
                company: {
                	types:{0:'', 1:'国企', 2:'民营', 3:'外企'},
                	personNums:{0:'', 1:'1~10人', 2:'10~50人', 3:'50人以上'},
                	typeSelected: new Array(4),
                	sizeSelected: new Array(4),
                    name: '',
                    license: '../../img/accountImage.png',
                    licenses:[],
                    licenseShow:'',
                    licenseShowIndex:-1,
                    type: '',
                    personNum: 100,
                    companyTypeId: 0,
                    companySizeId: 0,
                    hasCredential: false
                },
                infoSetErrMsg:''
            },
            SMS: {
	            timer: null,
	            disabled: false,
	            count: 0,
	            btnText: '短信验证'
	        },
            setting: false,
            settingTitle: '昵称',
            settingFlag: 0 //0:nickname;1:phone;2:securityanswer
        },
        created: function(){
        	initData();
        },
        methods: {
        	sizeSelected: function($event){
        		
        	},
            showLogin: function (message) {
                console.log("v-on  click method :showLogin");
                lrApp.showLogin(message);
            },
            uploadimg: function ($event) {
                var vm = this, $file = $($event.target).siblings('input:file');
                $file.trigger('click');
                
                if(vm.account.company.licenses.length>0){
            		vm.account.company.hasCredential = true;
            	}
            },
            /*fileUpload: function ($event) {
                var vm = this, files = $event.target.files;
				
            },*/
            deleteimg:function(){
            	var vm = this;
            	/*var licenseShow = vm.account.company.licenseShow;
            	var licenses = vm.account.company.licenses;
            	var index = licenses.indexOf(licenseShow);*/
            	var company = vm.account.company;
				var licenses = company.licenses;
				var index = company.licenseShowIndex;
            	if(index>0){
            		vm.preCompanyCredential();
            		licenses.remove(index);
            	}else if(index==0){
            		if(licenses.length>0){
            			company.licenseShow=licenses[index+1];
            			licenses.remove(index);
            		}
            		$("#companyCredentialUploadEle").val('');
            	}
            	if(licenses.length==0){
            		vm.account.company.hasCredential = false;
            	}
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
            	var company = vm.account.company; 
				var licenses = company.licenses;
				var index = company.licenseShowIndex;
				if (index >= 1) {
					company.licenseShowIndex = --index;
					company.licenseShow = company.licenses[index];
				}
            },
            nextCompanyCredential : function() {
				var vm = this;
				var company = vm.account.company;
				var licenses = company.licenses;
				var index = company.licenseShowIndex;
				if (index < licenses.length - 1) {
					company.licenseShowIndex = ++index;
					company.licenseShow = company.licenses[index];
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
            	var vm = this;
            	var mobile = vm.account.phone;
				var msg = isMobile(mobile);
				if (msg) {
					console.log(msg);
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
            companyTypeSelect:function($event){
            	var vm=this;
            	vm.account.company.companyTypeId=$event.target.value;
            },
            companySizeSelect:function($event){
            	var vm=this;
            	vm.account.company.companySizeId=$event.target.value;
            },
			setBaseInfo:function(flag){
				var vm=this;
				var data=null;
				if(flag==0){
					var nickname = vm.account.nickname;
					if(nickname){
						data ={"name":nickname};
					}
				}
				if(flag==1){
					var smsCode = vm.account.smsCode;
					var mobile = vm.account.phone;
					var msg = isMobile(mobile);
					if (msg) {
						console.log(msg);
						showInfoSetErrMsg(msg);
						return;
					}
					msg = validateSMScode(smsCode);
					if (msg) {
						console.log(msg);
						showInfoSetErrMsg(msg);
						return;
					}
					if(mobile&&smsCode){
						data={"mobileNumber":mobile,"smsCode":smsCode};
					}
				}
				if(flag==2){
					var question=vm.account.securityanswer.question;
					var answer=vm.account.securityanswer.answer;
					if(question&&answer){
						data={"question":question,"answer":answer};
					}
				}
				if(flag==3){
					var companyName=vm.account.company.name;
					var companySizeId=vm.account.company.companySizeId;
					var companyTypeId=vm.account.company.companyTypeId;
					if(companyName&&companySizeId&&companyTypeId){
						data={"companyName":companyName,"companySizeId":companySizeId,"companyTypeId":companyTypeId};
					}
				}//imgManage[]
				if(flag==4){
					var company = vm.account.company; 
					var licenses = company.licenses;
					if(licenses){
						data = {"imgManage[]":licenses};
					}
				}
				if(data){
					setUserInfo(data);
				}
			}
        }
    }
);

function showInfoSetErrMsg(msg){
	var vm = appMyAccount;
	vm.account.infoSetErrMsg = msg;
}

console.log("Vue 脚本绑定渲染完成..............");

function initData() {
	$http.post(baseUrl + 'purchaserAccount/findMixedByLoginId', null, initDataBack);
}

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
		if(vm.account.company.licenses.length>0){
			vm.account.company.hasCredential = true;
		}
		/*setUserInfo({
		 	"imgManage[]" : vm.account.company.licenses
		 });*/
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
	data = JSON.stringify(data);
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

function setUserInfoBack(data) {
	var vm = appMyAccount;
	if (data) {
		if (data.indexOf("{") == -1) {
			showMsg(data);
			initData();
		} else {
			initDataBack(JSON.parse(data));
		}
	}else{
		smsReset(vm);
	}
	vm.account.smsCode=null;
	vm.closeSetting();
}

function initDataBack(data) {
	if (data) {
		var vm = appMyAccount;
		var errMsg = data.errMsg;
		if (errMsg) {
			if(errMsg == '请重新登录'){
				if(!vm.loginId){
					errMsg='请登录后再试';
				}
				showMsg(errMsg);
			}
			return;
		}
		var info = data.userInfo;
		var headImgs = data.headImgs;
		var companyCredentials = data.companyCredentials;
		var loginInfo = data.loginInfo;
		if (info) {
			if (headImgs && headImgs.length > 0) {
				vm.account.headimg = headImgs[0].filePath;
			}
			vm.account.nickname = info.name;
			if (loginInfo) {
				vm.account.phone = loginInfo.mobileNumber;
			}
			vm.account.securityanswer.question = info.question;
			vm.account.securityanswer.answer = info.answer;
			vm.account.company.name = info.companyName;
			if (companyCredentials) {
				for(var i = 0; i < companyCredentials.length; i++){
					vm.account.company.licenses.push(companyCredentials[i].filePath);
				}
				vm.account.company.hasCredential = true;
				vm.nextCompanyCredential();
			}
			
			var company = vm.account.company;
			
			company.companyTypeId = info.companyTypeId;
			company.companySizeId = info.companySizeId;
			company.type = company.types[info.companyTypeId];
			company.personNum = company.personNums[info.companySizeId];
			
			setCompanyOption();
		}
	}
}

function setCompanyOption(){
	var vm = appMyAccount;
	var type = vm.account.company.companyTypeId;
	var personNum = vm.account.company.companySizeId;
	if(type){
		var typeSelected = vm.account.company.typeSelected;
		typeSelected[type] = 'selected';
	}
	if(personNum){
		vm.account.company.sizeSelected[personNum] = 'selected';
	}
}

function showMsg(msg) {
	var vm = appMyAccount;
	vm.msgWindow = true, 
	vm.msgText = msg;
}

function showLoginId(loginId, opt){
	var vm = appMyAccount;
	vm.mask = false;
	vm.loginId = loginId;
	vm.loginIdUl = true;
	vm.loginBtnUl = false;
	
	if(opt == 'login'){
		initData();
	}
}