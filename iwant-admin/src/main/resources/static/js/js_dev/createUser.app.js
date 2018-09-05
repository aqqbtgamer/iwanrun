/**
 * create user app js
 */
console.log("加载create user app .js");
const uploadServer = '/iwant_admin/remote/fileupload';
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dictionaryCodeUrl = "/iwant_admin/dictionary/findByCode";
const dictionaryName = 'common';
const submitUrl = '/iwant_admin/purchaseAccount/add';
const dataGetUrl ='/iwant_admin/purchaseAccount/get';
const dataModifyUrl = '/iwant_admin/purchaseAccount/modify';
const dataApplyUrl = '/iwant_admin/purchaseAccount/apply';
var isModify = getUrlParam('isModify');
var userId = getUrlParam('id');
const modifyTitle = "修改用户";

const fields = new Array(
		"name",
		"role",
		"loginId",
		"mobileNumber",
		"password",
		"gender",
		"wec",
		"aliPayId",
		"email",
		"contractMobile",
		"thirdPartyId1",
		"thirdPartyId2",
		"thirdPartyId3",
		"companyTypeId",
		"companySizeId",
		"companyName",
		"imgManage"
);

const modifyFields = new Array(
		"name",
		"role",
		"loginId",
		"mobileNumber",
		"gender",
		"wec",
		"aliPayId",
		"email",
		"contractMobile",
		"thirdPartyId1",
		"thirdPartyId2",
		"thirdPartyId3",
		"companyTypeId",
		"companySizeId",
		"companyName",
		"imgManage"
);


$(document).ready(
		function(){
			loadCommonTabs();
			bindUploadFile('uploadedLocationImages',uploadServer,'imgManage',mutipleDisplay);
			dictionaryItemsInit(dictionaryName,dictionaryCodeUrl);
			bindSelectRole("role","submitForm");
			bindSeclectAll("checkAll");
	        bindDeleteSelected("deleteAll");
			getDictionaryPages(dictionaryUrl,dicionaryCallBack);
			if(isModify == "true"){
				console.log("修改页面 加载server数据");
				adjustModifyField();
				var requestObj = new Object();
	        	requestObj.id = userId ;
				commonLoadForModify(requestObj,dataGetUrl,mappingData);
				bindDataModifySubmit('submitButton',userId,modifyFields,dataModifyUrl,returnLisPageModify);
			}else{
				bindDataVerifySubmit("submitButton","submitForm",fields,submitUrl,returnListPage)
			}
		}
)
	
function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}

function returnListPage(result){
	var ret = $.parseJSON(result);
	if(!ret.successful){
		alert("后台新增数据失败:"+ret.description+ret.extraInfo);
	}else{
		window.location.href="userlist.html";
	}
}

function returnLisPageModify(result){
	var ret = $.parseJSON(result);
	if(ret.successful){
		window.location.href="userlist.html";
	}else{
		alert("后台更新数据失败");
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


function adjustModifyField(){
	$("div#content h2.jquery_tab_title").html(modifyTitle);
	$("#role").attr("disabled",true);
	$("#mobileNumber").attr("readonly",true);
	$("#password").attr("readonly",true);
}

function mappingData(result){
	//console.log("获得server返回"+result);
	var data = $.parseJSON(result);
	var userInfo = $.parseJSON(data.userInfo) ;
	var purchaseAccount = $.parseJSON(data.purchaseAccount);
	var attachList = $.parseJSON(data.attachList);
	if(purchaseAccount != null){
		mappingSelectItem("role",purchaseAccount.sysRoleId);
		$("#submitForm").find("tr").each(
				function(){
					var roleAttr = $(this).attr("role");
					if(roleAttr != null){
						if(roleAttr == purchaseAccount.sysRoleId){
							$(this).show();
						}else{
							$(this).hide();
						}
					}
				}
		);
		mappingTextItem("mobileNumber",purchaseAccount.mobileNumber);
		mappingTextItem("loginId",purchaseAccount.loginId);
		mappingTextItem("password",purchaseAccount.password);
		mappingTextItem("wec",purchaseAccount.wec);
		mappingTextItem("aliPayId",purchaseAccount.aliPayId);
		mappingTextItem("thirdPartyId1",purchaseAccount.thirdPartyId1);
		mappingTextItem("thirdPartyId2",purchaseAccount.thirdPartyId2);
		mappingTextItem("thirdPartyId3",purchaseAccount.thirdPartyId3);
	}
	if(userInfo != null){
		mappingTextItem("name",userInfo.name);
		mappingRadioItem("gender",userInfo.gender);
		mappingTextItem("contractMobile",userInfo.contractMobile);
		mappingTextItem("companyName",userInfo.companyName);
		mappingTextItem("email",userInfo.email);
		mappingSelectItem("companySizeId",userInfo.companySizeId);
		mappingSelectItem("companyTypeId",userInfo.companyTypeId);
	}
	if(attachList != null && attachList.length > 0){
		for(var i = 0 ; i<attachList.length; i++){
			mutipleDisplay('imgManage',attachList[i].filePath);
		}
	}	
	var button = $("<input>").attr("type","button").attr("value","审核通过").attr("disabled",true);
	if(purchaseAccount != null && userInfo != null && attachList != null && attachList.length > 0){
		if(purchaseAccount.sysRoleId == 1 && userInfo.companyName != null && userInfo.companyName !=""){
			button.attr("disabled",false).addClass("button");
			button.bind("click",function(){
				var request = new Object();
				request.id = userId ;
				$.ajax(
						{
	        				url:dataApplyUrl,
	        				cache:false,
	        				data:request,
	                        dataType:"text",
	                        type:"POST",
	                        success:function(result){
	                        	console.log("提交到"+dataApplyUrl+"成功："+result);
	                        	returnLisPageModify(result);
	                        },
	    	    			error:function(XMLHttpRequest ,error,exception){
	    	                    console.log("提交到"+dataApplyUrl+"失败,原因是: "+ exception.toString());
	    	                }
	        			}
				
				);
			})
		}
	}
	var span = $("<span>").attr("style","width:10px");
	span.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
	$("#submitButton").after(span);
	span.after(button);
}