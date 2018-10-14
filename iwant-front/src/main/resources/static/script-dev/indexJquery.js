/**
 * Created by WXP22 on 2018/3/6.
 */
const bannerImgeUrl = "castposition/findAll";
const bannerWidthValue = 94;
const bannerWidthUnit = "vw";
const scrollFrequency = 3000;
var currentIndex = 0 ;

console.log("Jquery 脚本初始化..............");
$(document).ready(
    //$('#float-collect-requirement').portamento({disableWorkaround: true,wrapper:$("#container")})
	function(){
		//首页滚动资源位
		getAllCastPosition();		
	}
)
console.log("Jquery 脚本初始化.............. 完成");


function castPositionScroll(index){
	var castpositionList = $(".banner-point span");
	var current =  0 ;
	for(var i = 0 ; i < castpositionList.length ; i++ ){
		if(castpositionList.eq(i).hasClass("on")){
			current = i ;
		}
	}
	if(index != current && index != 0){
		$(".banner-list").animate({"margin-left": -1*bannerWidthValue*index+bannerWidthUnit});		
	}
	if(index == 0){
		$(".banner-list").css("margin-left",0);
	}
	castpositionList.eq(current).removeClass("on");
	castpositionList.eq(index).addClass("on");
}

function autoScroll(){
	var castpositionList = $(".banner-point span");
	var maxIndex = castpositionList.length -1 ;
	if(currentIndex < maxIndex){
		castPositionScroll(currentIndex);
		currentIndex ++ ;
	}else{
		castPositionScroll(currentIndex);
		currentIndex = 0 ;
	}
}

function getAllCastPosition(){
	var callback = new Object();
	callback.request = new Object(); 
	callback.error = function(XMLHttpRequest, textStatus){
		console.log("获取首页轮播失败"+textStatus);
	}
	callback.success = function(data){
		var castpositionList = $.parseJSON(data.list);
		for(var i = 0 ; i<castpositionList.length ; i++){
			var imgUrl = $("<a></a>").prop("href",castpositionList[i].imgUrl);
			var img = $("<img></img>").prop("src",castpositionList[i].filePath).prop("alt",castpositionList[i].fileName );
			$(".banner-list").append(imgUrl);
			$(imgUrl).append(img);
			var span = $("<span></span>").addClass("point");
			if(i == 0){
				span.addClass("on");
			}
			$(".banner-point").append(span);
		}
		
		setInterval(autoScroll,scrollFrequency);
		
	}
	$http_form.post(bannerImgeUrl,callback);
}