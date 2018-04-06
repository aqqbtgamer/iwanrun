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
            loginTitle:'用户登录'
        },
        methods:{
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
console.log("Vue 脚本绑定渲染完成..............");