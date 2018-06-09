/**
 * Created by WXP22 on 2018/3/2.
 */
console.log("Vue 脚本初始化..............");
var appIndex = new Vue(
    {
        el:"#container",
        data:{
            mask:false,
            loginWindow:false,
            autoLogin:false,
            bannerList:['img/banner/banner1.png'],
            loginId:'18018336171',
            loginToken:'uuixooppasyytvdbftrraskm',
            loginRole:{id:1,role:'采购方'},
            
            user:{
            	loginId:'',
            	loginTitle:'用户登录',
            	loginIdErrMsg : "手机号码格式不正确",
            	pwdErrMsg : "密码格式不正确，请重新输入",
            	smsErrMsg : "",//smsErrMsg : "验证码不正确，请重新输入",
            	smscodeDivShow : false,
            	autoLoginDivShow : true,
            	pwdChangDivShow : true,
            	btnName : '登录'
            },
            
            
            productIndexList:[
            	{	
            		id:1,
            		img:'img/normalpd1.png',
            		text:'长沙南区10公里员工慢跑',
            		price:'288/人'
            	},
            	{
            		id:2,
            		img:'img/normalpd2.png',
            		text:'南湖水上龙舟游',
            		price:'388/人'
            	}
            ],
            locationIndexList:[
            	{
            		id:1,
            		img:'img/location1.png',
            		text:'夏日清凉专场 | 水上球类运动',
            		long_text:'You may of heard fast growing companies require motivated and healthy employees that communicate efficiently. Clearly it’s easier than ever to speak to one another online but we’ve found that doesn’t create meaningful relationships.'
            	}
            ],
            caseIndexList:[
            	{
            		id:1,
            		img:'img/case1.png'
            	}
            ]
        },
        methods:{
        	userSmsCodeGet:function(){
        		var mobile = this.user.loginId;
        		//先校验手机号
        		//validateMobile(mobile);
        		var url = 'http://localhost:8088/iwantrun/smsCode/getSMSCode';
        		var data = {'mobile' : mobile};
        		data = JSON.stringify(data);
        		
        		console.log('短信验证码获取，参数：' + data);
        		
        		$http.post(url, data, getSMSCodeBack);
//        		$.post(url, data, getSMSCodeBack);
        	},
        	userRegisterEnter:function(){
        		var vm = this;
        		vm.user.loginTitle = '用户注册';
        		vm.user.btnName = '注册';
        		vm.user.smscodeDivShow = true;
        		vm.user.autoLoginDivShow = false;
        		vm.user.pwdChangDivShow = false;
        	},
        	userFocus:function(flag){
        		var vm = this;
        		if('loginId' == flag){
        			vm.user.loginIdErrMsg="";
        		} 
        		if('pwd' == flag){
        			vm.user.pwdErrMsg="";
        		}
        		if('sms' == flag){
        			vm.user.smsErrMsg="";
        		}
        	},
            showLogin:function(message){
                console.log("v-on  click method :showLogin");
                var vm = this
                vm.mask = true;
                vm.loginWindow = true ;
                vm.loginTitle = message ;
            },
            closeLogin:function(){
                console.log("v-on  click method :closeLogin");
                var vm = this;
                vm.mask = false ;
                vm.loginWindow = false;
            },
            changeAutoLogin:function(){
                var vm = this ;
                vm.autoLogin = !vm.autoLogin;
            },
            toOrderSubmit:function(){
                var vm = this  ;
                window.location.href = "html/iwantrun/ordersubmit.html"
            }
        }
    }
);

function getSMSCodeBack(data){
	var vm = appIndex;
	
	if(data){
		console.log('短信验证码获取结束，结果' + data);
		
		var returnstatus = data.returnstatus;
		var message = data.message;
		
		if(returnstatus == 'Success'){
			vm.user.smsErrMsg = '短信已发送';
		}else if(returnstatus == 'Faild'){
			console.log(message);
			vm.user.smsErrMsg = '短信获取失败，请联系管理员';
		}else{
			if(message){
				vm.user.smsErrMsg = message;
			}else{
				vm.user.smsErrMsg = '短信获取失败，请重新获取';
			}
		}
	}else{
		vm.user.smsErrMsg = '短信获取失败，请重新获取';
	}
}

console.log("Vue 脚本绑定渲染完成..............");