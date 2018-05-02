/**
 * Created by WXP22 on 2018/4/14.
 */

var uploadServer = '/iwant_admin/remote/fileupload';
var submitUrl = '/iwant_admin/productionInfo/add';

$(document).ready(
    function(){
        //initUE();
    	//            
        bindUploadFile('mainImageUpload',uploadServer,'mainImageLarge',singleDisplay);
        //bindUploadFile('uploadedProductionInfoImages',uploadServer,'imgManage',mutipleDisplay);
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

function bindDataSubmitJSON(id,fieldArray,url,callback){
    $("#"+id).bind('click',function(){
        var formData = collectFormDatas(fieldArray);
        var infoRequest={};
        formData.descirbeText1=formData['_ue'];//UEeditor编辑器数据
        infoRequest.info=formData;
        var param = {};
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



