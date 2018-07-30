/**
 * wxp22 创建于2018年
 */
console.log("Vue 脚本初始化..............");
const appProductDetailsConfig = {
		location:{
			dataUrl:"../../location/getDetailsById"
		},
		case:{
			dataUrl:"../../case/getDetailsById"
		}
};
const sildePageSize = 2 ;

var appProductDetails = new Vue(
		{
			el:"#container",
			data:{
				 loginId:null,
				 loginIdUl:false,
		         loginBtnUl:true,
		         detailType:"搜索场地列表",
		         detail:{		        	 
		        	 no:"",
		        	 name:"",
		        	 description:"",
		        	 region:"",
		        	 type:"",
		        	 presonNum:"",
		        	 during:"",
		        	 mainImage:"",
		        	 sildeImages:[],
		        	 displayedSlideImages:[]	 
		         },
		         detailName:"",
		         currentTopIndex: 0,
		         pageSize:sildePageSize		         
			},
			created:function(){
				var vm = this ;
				var typdId =  getUrlParam("id");
				var typeDesc = getUrlParam("type");
				var dataUrl = appProductDetailsConfig[typeDesc].dataUrl ;
				console.log("page mod : "+typeDesc+" page id :  "+typdId);
				var callback = new Object();
				callback.request = {"id":typdId,"type":typeDesc};
				callback.success = function(result){
					if(callback.request.type == "location"){
						var locationDetail = $.parseJSON(result.location) ;
						vm.detailType = "搜索场地列表";
						vm.detail.no = locationDetail.id ;
						vm.detail.name =  locationDetail.name;
						vm.detail.description =  locationDetail.descirbeText3 ;
						vm.detail.region = locationDetail.activityProvinceCodeDesc +"|" +locationDetail.activityCityCodeDesc +"|"+ locationDetail.activityDistCodeDesc;
						vm.detail.type = locationDetail.activeTypeCodeDesc ;
						vm.detail.presonNum = locationDetail.groupNumberLimitCodeDesc;
						//vm.detail.during = locationDetail.
						vm.detail.mainImage = locationDetail.descirbeText2 ;
						vm.detail.describContext = locationDetail.descirbeText1;
						var listAttch = $.parseJSON(result.listAttch);
						if(listAttch != null && listAttch.length > 0){
							for(var i=0 ; i< listAttch.length ; i++){
								vm.detail.sildeImages.push(listAttch[i].filePath);
							}
						}						
					}else if(callback.request.type == "case"){
						vm.detailType = "搜索案例列表";
						var locationDetail = $.parseJSON(result.caseVo) ;
						vm.detail.no = locationDetail.id ;
						vm.detail.name =  locationDetail.name;
						vm.detail.description =  locationDetail.descirbeText3 ;
						vm.detail.region = locationDetail.activityProvinceCode +"|" +locationDetail.activityCityCode +"|"+ locationDetail.activityDistCode;
						vm.detail.type = locationDetail.activityProvinceCode ;
						vm.detail.presonNum = locationDetail.groupNumber;
						vm.detail.during = locationDetail.dur;
						vm.detail.mainImage = locationDetail.descirbeText2 ;
						vm.detail.describContext = locationDetail.descirbeText1;
						//var listAttch = $.parseJSON(result.listAttch);
					}					
					
				}
				callback.error = function(errorMsg){
					console.log("result from server :" +errorMsg)
				}
				$http_form.post(dataUrl,callback);
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
		          },
		          sliderPre: function () {
		                var vm = this;
		                vm.showSliderPre && vm.currentTopIndex--;
		            },
		          sliderNext: function () {
		                var vm = this;
		                vm.showSliderNext && vm.currentTopIndex++;
		            }		            
		            
			},
			computed:{
		            showSliderPre: function () {
		                var vm = this;
		                return vm.currentTopIndex > 0;
		            },
		            showSliderNext: function () {
		                var vm = this;
		                return vm.currentTopIndex < vm.detail.sildeImages.length - sildePageSize;
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

