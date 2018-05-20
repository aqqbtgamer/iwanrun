/**
 * Created by WXP22 on 2018/4/14.
 */

const uploadServer = '/iwant_admin/remote/fileupload';
const submitUrl = '/iwant_admin/cases/add';
const dataGetUrl = '/iwant_admin/cases/get';
const dataModifyUrl = '/iwant_admin/cases/modify'
var isModify = getUrlParam('isModify');
var caseId = getUrlParam('id');
const fields = new Array(
		'name',
		'activityProvinceCode',
		'activityCityCode',
		'activityDistCode',
		'location',
		'activityTypeCode',
		'companyTypeCode',
		'groupNumber',
		'during',
		'specialKeyWord',
		'designDuringCode',
	    'executeDuringCode',
	    'trafficInfo',
	    'foodInfo',
	    'hotelInfo',
	    'priority',
	    'simulatePriceCode',
	    'orderId',
        'mainImage',
        'imgManage'
	    
	   
    );
const modifyTitle = "修改案例";


$(document).ready(
    function(){    	
    	initUE();
        bindUploadFile('mainImageUpload',uploadServer,'mainImage',singleDisplay);
        bindUploadFile('uploadedLocationImages',uploadServer,'imgManage',mutipleDisplay);
        bindSeclectAll("checkAll");
        bindDeleteSelected("deleteAll");
        if(isModify == "true"){
        	console.log("修改页面 加载server数据");
        	adjustModifyField(); 
        	var requestObj = new Object();
        	requestObj.id = caseId ;
        	commonLoadForModify(requestObj,dataGetUrl,mappingData);
        	bindDataModifySubmit('submitForm',caseId,fields,dataModifyUrl,returnLisPageModify);
        }else{
        	 bindDataSubmit('submitForm',fields,submitUrl,returnListPage);
        }
       
    }
);

function returnListPage(result){
	if(result == "failed"){
		alert("后台新增数据失败")
	}else{
		window.location.href="casesList.html";
	}
}

function returnLisPageModify(result){
	var ret = $.parseJSON(result);
	if(ret.successful){
		window.location.href="casesList.html";
	}else{
		alert("后台更新数据失败");
	}
}

function adjustModifyField(){
	$("div#content h2.jquery_tab_title").html(modifyTitle);
}

function mappingData(result){
	var data = $.parseJSON(result);
	var caseData = $.parseJSON(data.caseVo);
	var specialTags = $.parseJSON(data.listTag);
	var imgs = $.parseJSON(data.listAttch);
	if(caseData != null){
		mappingTextItem("name",caseData.name);
		mappingSelectItem("activityProvinceCode",caseData.activityProvinceCode);
		mappingSelectItem("activityCityCode",caseData.activityCityCode);
		mappingSelectItem("activityDistCode",caseData.activityDistCode);
		mappingTextItem("location",caseData.location);
		mappingSelectItem("activityTypeCode",caseData.activityTypeCode);
		mappingSelectItem("companyTypeCode",caseData.companyTypeCode);
		
		mappingSelectItem("groupNumber",caseData.groupNumber);
		mappingSelectItem("during",caseData.during);
		mappingSelectItem("specialKeyWord",caseData.specialKeyWord);
		mappingSelectItem("designDuringCode",caseData.designDuringCode);
		mappingSelectItem("executeDuringCode",caseData.executeDuringCode);
		mappingTextItem("trafficInfo",caseData.trafficInfo);
		
		mappingTextItem("foodInfo",caseData.foodInfo);
		mappingTextItem("hotelInfo",caseData.hotelInfo);
		mappingTextItem("priority",caseData.priority);
		mappingTextItem("simulatePriceCode",caseData.simulatePriceCode);
		
		mappingTextItem("orderId",caseData.orderId);
		$("#mainImage").prop("src",caseData.descirbeText2);
		ue.ready(function() {
			ue.setContent(caseData.descirbeText1);
		});
	}
	/*if(specialTags != null && specialTags.length > 0){
		var tagsArray = new Array();
		for(var i = 0 ; i< specialTags.length ; i++){
			tagsArray.push(specialTags[i].tagsCode);
		}
		mappingCheckItem("special_tags",tagsArray);
	}*/
	if(imgs != null && imgs.length > 0){
		for(var i = 0; i<imgs.length ; i++){
			mutipleDisplay('imgManage',imgs[i].filePath);
		}		
	}
}




