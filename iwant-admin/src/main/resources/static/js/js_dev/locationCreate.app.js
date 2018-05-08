/**
 * Created by WXP22 on 2018/4/14.
 */

const uploadServer = '/iwant_admin/remote/fileupload';
const submitUrl = '/iwant_admin/location/add';
const dataGetUrl = '/iwant_admin/location/get'
var isModify = getUrlParam('isModify');
var locationId = getUrlParam('id');
const fields = new Array('name',
        'location_type_code',
        'special_tags',
        'group_number_limit_code',
        'activity_province_code',
        'activity_city_code',
        'activity_dist_code',
        'location',
        'mainImage',
        'imgManage'
    );

const mapping = new Array('name',
		"locationTypeCode",
		"specialTagsCode",
		"groupNumberLimitCode",
		"activityProvinceCode",
		"activityDistCode",
		"location",
		"mainImage",
		"imgManage"
);


$(document).ready(
    function(){    	
    	initUE();
        bindUploadFile('mainImageUpload',uploadServer,'mainImage',singleDisplay);
        bindUploadFile('uploadedLocationImages',uploadServer,'imgManage',mutipleDisplay);
        bindSeclectAll("checkAll");
        bindDeleteSelected("deleteAll");
        if(isModify == "true"){
        	console.log("修改页面 加载server数据");
        }else{
        	 bindDataSubmit('submitForm',fields,submitUrl,returnListPage);
        }
       
    }
);

function returnListPage(result){
	if(result == "failed"){
		alert("后台处理数据失败")
	}else{
		window.location.href="locationlist.html";
	}
}




