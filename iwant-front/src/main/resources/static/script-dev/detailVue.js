/**
 * Created by WXP22 on 2018/3/2.
 */
console.log("Vue 脚本初始化..............");
/**
 * Created by WXP22 on 2018/3/2.
 */
console.log("Vue 脚本初始化..............");

var detail = new Vue(
		{
			el : "#common-tail-bg",
			data : {				
                tailWeixinIcon:false
			},
			created: function(){
	        
	        },
			methods : {				
                displayIcon:function(){
                	this.tailWeixinIcon =  true ;
                },
                hideIcon:function(){
                	this.tailWeixinIcon =  false ;
                }
			}
		});

console.log("Vue 脚本绑定渲染完成..............");