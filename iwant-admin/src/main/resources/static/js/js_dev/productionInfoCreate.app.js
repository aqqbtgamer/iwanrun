/**
 * Created by WXP22 on 2018/4/14.
 */

var uploadServer = '/iwant_admin/remote/fileupload';
var submitUrl = '/iwant_admin/productionInfo/add';
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dictionaryCodeUrl = "/iwant_admin/dictionary/findByCode";
const dictionaryName ="production";

/**
 * 页面完成后，需要手动进行省份的change，不然当前省份，比如上海，也会把其它省份下的市显示出来
 */
window.onload = function() {
	$("#activityProvinceCode").change();
}

$(document).ready(
    function(){
        //initUE();
    	showSidebar('产品管理');
    	loadCommonTabs();
    	dictionaryItemsInit(dictionaryName,dictionaryCodeUrl);
        bindUploadFile('mainImageUpload',uploadServer,'mainImageLarge',singleDisplay);
        bindDataSubmitJSON('submitForm',
            new Array("name",
    				"activityTypeCode",
    				"during",
    				"duringCode",
    				"location",
    				"orderGroupPriceCode",
    				"orderSimulatePriceCode",
    				"groupNumber",
    				"groupNumberCode",
    				"priority",
    				"activityProvinceCode",
    				"activityCityCode",
    				"activityDistCode",
    				"mainImageLarge",
    				'imgManage'
            ),
        submitUrl,returnListPage);
        
        bindUploadFile('uploadedProductionInfoImages',uploadServer,'imgManage',mutipleDisplay);
        bindSeclectAll("checkAll");
        bindDeleteSelected("deleteAll");
        bindAssignToDictionary("activityCityCode","activityProvinceCode");
        bindAssignToDictionary("activityDistCode","activityCityCode");
        getDictionaryPages(dictionaryUrl,dicionaryCallBack);
    }
);
function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}
function showSidebar(title){
	var liLabels = $("#sidebar").find("li");
	$(liLabels).each(function(){
		if($(this).text() == title){
			$(this).attr('class', 'current');
			$(this).parent().removeAttr('class');
			$(this).parent().removeAttr('style');
		}
	});
}
function returnListPage(result){
	if(result == "failed"){
		alert("后台处理数据失败")
	}else{
		window.location.href="productionInfoList.html";
	}
}

function bindDataSubmitJSON(id,fieldArray,url,callback){
    $("#"+id).bind('click',function(){
        var formData = collectFormDatas(fieldArray);
    	
    	var validatedResult = validateDatas(formData);
    	
    	if(validatedResult){
    		alert(validatedResult);
    		return;
    	}
    	
        var infoRequest={};
        var param = {};
        
        formData.descirbeText1=formData['_ue'];//UEeditor编辑器数据
        infoRequest.info=formData;
		param.messageBody = JSON.stringify(infoRequest);
		
        var paramJSON = JSON.stringify(param);
        $.ajax(
            {
                url:url,
                cahce:false,
                data:paramJSON,
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                type:"POST",
                success:function(result){
                    console.log("提交到"+url+"成功");
                    callback();
                },
                error:function(XMLHttpRequest ,error,exception){
                    console.log("提交到"+url+"失败,原因是: "+ error.toString());
                    alert("上传失败 服务端无法连接")
                }
            }
        )
    });
}
function validateDatas(formData){
	if(!formData['name']){
		return '请输入产品名称';
	}
	if(!formData['during']){
		return '请输入活动天数';
	}
	return null;
}


