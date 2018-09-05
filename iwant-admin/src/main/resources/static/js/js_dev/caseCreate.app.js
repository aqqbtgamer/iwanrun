/**
 * Created by WXP22 on 2018/4/14.
 */

const uploadServer = '/iwant_admin/remote/fileupload';
const submitUrl = '/iwant_admin/cases/add';
const dataGetUrl = '/iwant_admin/cases/get';
const dataModifyUrl = '/iwant_admin/cases/modify'
var isModify = getUrlParam('isModify');
var caseId = getUrlParam('id');
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dictionaryCodeUrl = "/iwant_admin/dictionary/findByCode";
const dictionaryName ="case";
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
        'imgManage',
        'descirbeText3'
	    
	   
    );
const modifyTitle = "修改案例";


$(document).ready(
    function(){    	
    	initUE();
    	loadCommonTabs();
    	dictionaryItemsInit(dictionaryName,dictionaryCodeUrl);
        bindUploadFile('mainImageUpload',uploadServer,'mainImage',singleDisplay);
        bindUploadFile('uploadedLocationImages',uploadServer,'imgManage',mutipleDisplay);
        bindSeclectAll("checkAll");
        bindDeleteSelected("deleteAll");
        bindAssignToDictionary("activityCityCode","activityProvinceCode");
        bindAssignToDictionary("activityDistCode","activityCityCode");
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
        getDictionaryPages(dictionaryUrl,dicionaryCallBack);
    }
);
function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}
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
	var specialKeyWord = $.parseJSON(data.listTag);
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
//		mappingSelectItem("specialKeyWord",caseData.specialKeyWord);
		mappingSelectItem("designDuringCode",caseData.designDuringCode);
		mappingSelectItem("executeDuringCode",caseData.executeDuringCode);
		mappingTextItem("trafficInfo",caseData.trafficInfo);
		
		mappingTextItem("foodInfo",caseData.foodInfo);
		mappingTextItem("hotelInfo",caseData.hotelInfo);
		mappingTextItem("priority",caseData.priority);
		mappingTextItem("simulatePriceCode",caseData.simulatePriceCode);
		
		mappingTextItem("orderId",caseData.orderId);
		mappingTextItem("descirbeText3",caseData.descirbeText3);
		$("#mainImage").prop("src",caseData.descirbeText2);
		ue.ready(function() {
			ue.setContent(caseData.descirbeText1);
		});
	}
	if(specialKeyWord != null && specialKeyWord.length > 0){
		var tagsArray = new Array();
		for(var i = 0 ; i< specialKeyWord.length ; i++){
			tagsArray.push(specialKeyWord[i].tagsCode);
		}
		mappingCheckItem("specialKeyWord",tagsArray);
	}
	if(imgs != null && imgs.length > 0){
		for(var i = 0; i<imgs.length ; i++){
			mutipleDisplay('imgManage',imgs[i].filePath);
		}		
	}
}




