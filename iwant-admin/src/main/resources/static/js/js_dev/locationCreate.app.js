/**
 * Created by WXP22 on 2018/4/14.
 */

const uploadServer = '/iwant_admin/remote/fileupload';
const submitUrl = '/iwant_admin/location/add';
const dataGetUrl = '/iwant_admin/location/get';
const dataModifyUrl = '/iwant_admin/location/modify'
var isModify = getUrlParam('isModify');
var locationId = getUrlParam('id');
const fields = new Array('name',
        'location_type_code',
        'activity_type_code',
        'special_tags',
        'group_number_limit_code',
        'activity_province_code',
        'activity_city_code',
        'activity_dist_code',
        'location',
        'priority',
        'mainImage',
        'imgManage'
    );
const modifyTitle = "修改场地";


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
        	requestObj.id = locationId ;
        	commonLoadForModify(requestObj,dataGetUrl,mappingData);
        	bindDataModifySubmit('submitForm',locationId,fields,dataModifyUrl,returnLisPageModify);
        }else{
        	 bindDataSubmit('submitForm',fields,submitUrl,returnListPage);
        }
       
    }
);

function returnListPage(result){
	if(result == "failed"){
		alert("后台新增数据失败")
	}else{
		window.location.href="locationlist.html";
	}
}

function returnLisPageModify(result){
	var ret = $.parseJSON(result);
	if(ret.successful){
		window.location.href="locationlist.html";
	}else{
		alert("后台更新数据失败");
	}
}

function adjustModifyField(){
	$("div#content h2.jquery_tab_title").html(modifyTitle);
}

function mappingData(result){
	var data = $.parseJSON(result);
	var locationData = $.parseJSON(data.location);
	var specialTags = $.parseJSON(data.listTag);
	var imgs = $.parseJSON(data.listAttch);
	if(locationData != null){
		mappingTextItem("name",locationData.name);
		mappingSelectItem("location_type_code",locationData.locationTypeCode);
		mappingSelectItem("activity_type_code",locationData.activeTypeCode);
		mappingSelectItem("group_number_limit_code",locationData.groupNumberLimitCode);
		mappingSelectItem("activity_province_code",locationData.activityProvinceCode);
		mappingSelectItem("activity_city_code",locationData.activityCityCode);
		mappingSelectItem("activity_dist_code",locationData.activityDistCode);
		mappingTextItem("location",locationData.location);
		mappingTextItem("priority",locationData.priority);
		$("#mainImage").prop("src",locationData.descirbeText2);
		ue.ready(function() {
			ue.setContent(locationData.descirbeText1);
		});
	}
	if(specialTags != null && specialTags.length > 0){
		var tagsArray = new Array();
		for(var i = 0 ; i< specialTags.length ; i++){
			tagsArray.push(specialTags[i].tagsCode);
		}
		mappingCheckItem("special_tags",tagsArray);
	}
	if(imgs != null && imgs.length > 0){
		for(var i = 0; i<imgs.length ; i++){
			mutipleDisplay('imgManage',imgs[i].filePath);
		}		
	}
}




