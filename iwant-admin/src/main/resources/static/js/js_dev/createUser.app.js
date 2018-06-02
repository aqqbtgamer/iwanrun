/**
 * create user app js
 */
console.log("加载create user app .js");
const uploadServer = '/iwant_admin/remote/fileupload';
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dictionaryCodeUrl = "/iwant_admin/dictionary/findByCode";
const dictionaryName = 'common';
const submitUrl = '/iwant_admin/purchaseAccount/add';
var isModify = getUrlParam('isModify');
const fields = new Array(
		"name",
		"role",
		"loginId",
		"mobileNumber",
		"password",
		"wec",
		"aliPayId",
		"email",
		"contractMobile",
		"thirdPartyId1",
		"thirdPartyId2",
		"thirdPartyId3",
		"companySizeId",
		"companyName",
		"imgManage"
);
$(document).ready(
		function(){
			bindUploadFile('uploadedLocationImages',uploadServer,'imgManage',mutipleDisplay);
			dictionaryItemsInit(dictionaryName,dictionaryCodeUrl);
			bindSelectRole("role","submitForm");
			bindSeclectAll("checkAll");
	        bindDeleteSelected("deleteAll");
			getDictionaryPages(dictionaryUrl,dicionaryCallBack);
			if(isModify == "true"){
				
			}else{
				bindDataVerifySubmit("submitButton","submitForm",fields,submitUrl,returnListPage)
			}
		}
)
	
function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}

function returnListPage(result){
	if(result == "failed"){
		alert("后台新增数据失败");
	}else{
		window.location.href="userlist.html";
	}
}
	
function bindSelectRole(bindId,formId){
	$("#"+bindId).bind("change",function(){
		var roleId = $("#"+bindId).val();
		$("#"+formId).find("tr").each(
				function(){
					var roleAttr = $(this).attr("role");
					if(roleAttr != null){
						if(roleAttr == roleId){
							$(this).show();
						}else{
							$(this).hide();
						}
					}
				}
		);
	})	
}