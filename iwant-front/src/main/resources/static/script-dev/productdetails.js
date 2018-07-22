/**
 * wxp22 创建于2018年
 */
console.log("Vue 脚本初始化..............");
var appProductDetails = new Vue(
		{
			el:"#container",
			data:{
				 loginId:null,
				 loginIdUl:false,
		         loginBtnUl:true
			},
			created:function(){
				
			},
			methods:{
				 showLogin: function (message) {
		                console.log("v-on  click method :showLogin");
		                lrApp.showLogin(message);
		            },
		          logout:function(){
		        	  var vm = this ;
		        	  vm.loginId = null ;
		        	  vm.loginIdUl = false ;
		        	  vm.loginBtnUl = true ;
		        	  $.cookie('accessToken',null,{path:"/"});
		        	  $.cookie('loginId', null,{path:"/"});
		        	  lrApp.account = {};
		          }   
			}
		}
);

function showLoginId(loginId){
	var vm = appProductDetails;
	vm.mask = false;
	vm.loginId = loginId;
	vm.loginIdUl = true;
	vm.loginBtnUl = false;
}