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
        },
        product: {
            dataUrl: "../../production/getDetailsById"
        }
};
const sildePageSize = 3 ;
const autodisplayInterval = 3500;

var rollDirection = true;

var appProductDetails = new Vue(
		{
			el:"#container",
			data:{
				 loginId:null,
				 loginIdUl:false,
		         loginBtnUl:true,
		         detailType:"搜索场地列表",
		         init: {
		                id: getUrlParam('id'),
		                type: getUrlParam('type')
		            },
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
		        	 sildeShowImages:[]
		         },
		         detailName:"",
		         currentTopIndex: 0,
		         pageSize:sildePageSize,
                 isFavourite:false,
                 tailWeixinIcon:false
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
						vm.detail.description =  null2Blank(locationDetail.descirbeText3) ;
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
						vm.detail.description =  null2Blank(locationDetail.descirbeText3) ;
						vm.detail.region = locationDetail.activityProvinceCode +"|" +locationDetail.activityCityCode +"|"+ locationDetail.activityDistCode;
						vm.detail.type = locationDetail.activityProvinceCode ;
						vm.detail.presonNum = locationDetail.groupNumber;
						vm.detail.during = locationDetail.dur;
						vm.detail.mainImage = locationDetail.descirbeText2 ;
						vm.detail.describContext = locationDetail.descirbeText1;
						var listAttch = $.parseJSON(result.listAttch);
						if(listAttch != null && listAttch.length > 0){
							for(var i=0 ; i< listAttch.length ; i++){
								vm.detail.sildeImages.push(listAttch[i].filePath);
							}
						}	
					}else{
						vm.detailType="搜索产品列表";
						var productionDetail = result ;
						locationNull2Blank(productionDetail);
						vm.detail.no = productionDetail.id;
						vm.detail.description = null2Blank(productionDetail.descirbeText3);
						vm.detail.name = productionDetail.name ;
						vm.detail.region = productionDetail.provinceName +"|" +productionDetail.cityName +"|"+ productionDetail.distName;
						vm.detail.type = productionDetail.activityTypeName ;
						vm.detail.presonNum = productionDetail.groupNumberRange;
						vm.detail.during = productionDetail.duringRange;
						vm.detail.mainImage = productionDetail.mainImageLarge;
						vm.detail.describContext = productionDetail.descirbeText1;		
						var listAttch = $.parseJSON(result.listAttch);
						if(listAttch != null && listAttch.length > 0){
							for(var i=0 ; i< listAttch.length ; i++){
								vm.detail.sildeImages.push(listAttch[i].filePath);
							}
						}	
					}					
					vm.fufillShowSlides();
					setInterval(function(){
						vm.autoRoll();
					},autodisplayInterval);
				}
				callback.error = function(errorMsg){
					console.log("result from server :" +errorMsg)
				}
                $http_form.post(dataUrl, callback);
                $.cookie('loginId') && vm.isCollectioned();               
			},
			methods:{
				toOrderSubmit : function() {
					var vm = this
					window.location.href = "./ordersubmit.html"+"?id="+vm.init.id+"&type="+vm.init.type;
                },
				 showLogin: function (message) {
		                console.log("v-on  click method :showLogin");
		                lrApp.showLogin(message);
		            },
		          logout:function(){
		        		var vm = this ;
		        		
		        	  	var logoutBack = function(){
					    	vm.loginId = null ;
			        		vm.loginIdUl = false ;
			        		vm.loginBtnUl = true ;
			        		//$.cookie('accessToken',null,{path:"/"});
			        		//$.cookie('loginId', null,{path:"/"});
			        		lrApp.account = {};
    					}
    					
    					logoutCommon(logoutBack);
		          },
		          sliderPre: function () {
		                var vm = this;
		                vm.showSliderPre && vm.currentTopIndex--;
		            },
		          sliderNext: function () {
		                var vm = this;
		                vm.showSliderNext && vm.currentTopIndex++;
		            },
		          collection: function () {
		                var vm = this;
		          		//是否登录
		                var login = (vm.loginId);
		            	if(!login){
		            		vm.showLogin(0);
		            		return;
		            	}
		                //vm.isFavourite = !vm.isFavourite;
		                function add(id, type) {
		                    var url = baseUrl + 'favourite/add',
		                        parm = {
		                            id: id,
		                            type: type
		                        };
		                    axios.post(url, parm).then(
		                        function (response) {
		                            console.log(response.data);
		                            response.data && (vm.isFavourite = true);
		                        });
		                }
		                function remove(id, type) {
		                    var url = baseUrl + 'favourite/delete',
		                        parm = {
		                            id: id,
		                            type: type
		                        };
		                    axios.post(url, parm).then(
		                        function (response) {
		                            console.log(response.data);
		                            vm.isFavourite = false;
		                        });
		                }
		                if (vm.init && vm.init.id && vm.init.type) {
		                    vm.isFavourite ? remove(vm.init.id, vm.init.type) : add(vm.init.id, vm.init.type);
		                }
                  },
                  isCollectioned: function () {
                      var vm = this;
                      vm.init && vm.init.id && vm.init.type && (function (id, type) {
                          var url = baseUrl + "favourite/query",
                              parm = {
                                  type: type,
                                  id: id
                              };
                          axios.post(url, parm).then(function (response) {
                              console.log(response.data);
                              response.data && (function () {
                                  vm.isCollection = true;
                              })();
                          });
                      })(vm.init.id, vm.init.type);
                  },
                  fufillShowSlides:function(){
                	  var vm = this ;
                	  vm.detail.sildeShowImages = vm.detail.sildeImages.slice(vm.currentTopIndex,vm.currentTopIndex+vm.pageSize );
                  },
                  autoRoll:function(){
                	  var vm  = this ;
                	  if(vm.detail.sildeImages !=  null && vm.detail.sildeImages.length > vm.pageSize){
                		  if(rollDirection){
                			  if(vm.currentTopIndex < vm.detail.sildeImages.length - vm.pageSize){
                				  vm.currentTopIndex++
                			  }else{
                				  rollDirection = false ;
                			  }
                		  }else{
                			  if(vm.currentTopIndex > 0){
                				  vm.currentTopIndex -- ;
                			  }else{
                				  rollDirection = true;
                			  }
                		  }
                	  }
                  },
                  displayIcon:function(){
                  	this.tailWeixinIcon =  true ;
                  },
                  hideIcon:function(){
                  	this.tailWeixinIcon =  false ;
                  },
                  showShare:function(){
                	  sharApp.display();
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
		            },
		            favouriteColor: function () {
		                var vm = this;
		                return vm.isFavourite ? '#FF0000' : '#414141';
		            }
		        },
		    watch:{
		    	currentTopIndex:function(val,old){
		    		var vm = this ;
		    		//var scrollPosition = $(document).scrollTop();   
		    		//console.log("scrollPosition:"+scrollPosition);
		    		if(val>old && val -old == 1){
		    			vm.detail.sildeShowImages.shift();
		    			vm.detail.sildeShowImages.push(vm.detail.sildeImages[val+vm.pageSize-1]);		    			
		    		}else if(val < old && old - val == 1){
		    			vm.detail.sildeShowImages.pop();
		    			vm.detail.sildeShowImages.unshift(vm.detail.sildeImages[val]);		    			    			
		    		}		    		
		    	}
		    }    
		}
);

var sharApp = new Vue(
	{
		el:"#sharePannel",
		data:{
			showPannel:false
		},
		methods:{
			display:function(){
				var vm = this;
				qrcode.makeCode(window.location.href.split("#")[0]);
				vm.showPannel = true ;
			},
			undisplay:function(){
				var vm = this;				
				vm.showPannel = false ;
			}
			
		}
	}	
);

var qrcode = new QRCode(document.getElementById("qrcode"),{
	width : 380,
	height : 380
});

function showLoginId(loginId){
	var vm = appProductDetails;
	vm.mask = false;
	vm.loginId = loginId;
	vm.loginIdUl = true;
	vm.loginBtnUl = false;
}

function locationNull2Blank(locationDetail){
	var province = locationDetail.activityProvinceCode;
	var city = locationDetail.activityCityCode;
	var dist = locationDetail.activityDistCode;
	
	if(province == null){
		locationDetail.activityProvinceCode = '';
	}
	if(city == null){
		locationDetail.activityCityCode = '';
	}
	if(dist == null){
		locationDetail.activityDistCode = '';
	}
}





