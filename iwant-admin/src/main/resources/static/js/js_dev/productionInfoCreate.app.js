/**
 * Created by WXP22 on 2018/4/14.
 */

var uploadServer = '/iwant_admin/remote/fileupload';
var submitUrl = '/iwant_admin/location/add'

$(document).ready(
    function(){
        //initUE();
        bindUploadFile('mainImageUpload',uploadServer,'mainImage',singleDisplay);
        bindUploadFile('uploadedLocationImages',uploadServer,'imgManage',mutipleDisplay);
        bindDataSubmit('submitForm',
            new Array("name",
    				"activity_type_code",
    				"during",
    				"group_number",
    				"priority",
    				"activity_province_code",
    				"activity_city_code",
    				"activity_dist_code",
    				"descirbeText1",
    				"descirbeText2",
    				"descirbeText3",
    				"mainImage"
            ),
            submitUrl,returnListPage);
        bindSeclectAll("checkAll");
        bindDeleteSelected("deleteAll");
    }
);

function returnListPage(result){
	if(result == "failed"){
		alert("后台处理数据失败")
	}else{
		window.location.href="locationlist.html";
	}
}

/*function add(){
	getDatas();
}

function getDatas(){
	 activity_type_code  during_code group_number group_number_code priority  order_simulate_price_code order_group_price_code location descirbe_text3 
	 var name = getEleVal("name");
	 var name = getEleVal("during");
	 var name = getEleVal("name");
	 var name = getEleVal("name");
	 var name = getEleVal("name");
	 var name = getEleVal("name");
	 var name = getEleVal("name");
	 var name = getEleVal("name");
	 var name = getEleVal("name");
}

function getEleVal(id){
	return $("#" + id).val();
}

function getSelectedEleVal(){
	return $("#" + id + "")
}*/



