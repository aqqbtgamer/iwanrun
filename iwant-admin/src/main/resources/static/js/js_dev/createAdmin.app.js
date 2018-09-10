/**
 * create user app js
 */
console.log("加载create admin app .js");

const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dictionaryName = 'common';
const submitUrl = '/iwant_admin/userAccount/add';
const dataGetUrl ='/iwant_admin/userAccount/get';
const dataModifyUrl = '/iwant_admin/userAccount/modify';
var isModify = getUrlParam('isModify');
var userId = getUrlParam('id');
const modifyTitle = "修改管理员";

const fields = new Array(
		"username",
		"mobileNumber",
		"password",
		"weixinAccount"
);

const modifyFields1 = new Array(
		"mobileNumber",
		"weixinAccount"
);
const modifyFields2 = new Array(
		"mobileNumber",
		"weixinAccount",
		"password"
);


$(document).ready(
		function(){
			loadCommonTabs();
			getDictionaryPages(dictionaryUrl,dicionaryCallBack);
			if(isModify == "true"){
				console.log("修改页面 加载server数据");
				adjustModifyField();
				var requestObj = new Object();
	        	requestObj.id = userId ;
				commonLoadForModify(requestObj,dataGetUrl,mappingData);
				bindDataModifyVeifySubmit('submitButton',"submitForm",userId,modifyFields1,dataModifyUrl,returnLisPageModify);
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
		window.location.href="adminlist.html";
	}
}

function returnLisPageModify(result){
	var ret = $.parseJSON(result);
	if(ret.successful){
		window.location.href="adminlist.html";
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
	$("#username").attr("readonly",true);
	$("#password").attr("readonly",true).removeAttr("verify");
	$("#password").parent().parent().find("span.requierd").removeClass("requierd").text("");
	var enablePassword = $("<input>").attr("type","checkbox")
	.attr("enablePasswordEdit",true).attr("name","enablePassword").attr("id","enablePassword");
	enablePassword.bind("click",function(){
		if($(this).prop("checked")){
			$("#password").attr("readonly",false).attr("value","").attr("verify","password");
			$("#password").parent().parent().find("label span").addClass("requierd").text("*");
			$("#submitButton").unbind();
			bindDataModifyVeifySubmit('submitButton',"submitForm",userId,modifyFields2,dataModifyUrl,returnLisPageModify);
		}else{
			$("#password").attr("readonly",true).removeAttr("verify");
			$("#password").parent().parent().find("label span.requierd").removeClass("requierd").text("");
			$("#submitButton").unbind();
			bindDataModifyVeifySubmit('submitButton',"submitForm",userId,modifyFields1,dataModifyUrl,returnLisPageModify);
		}
	})
	$("#password").after(enablePassword);
	var enablePasswordInfo = $("<span>").text("启用密码修改");
	enablePassword.after(enablePasswordInfo);
	
}

function mappingData(result){
	//console.log("获得server返回"+result);
	var data = $.parseJSON(result);
	mappingTextItem("username",data.username);
	mappingTextItem("mobileNumber",data.mobileNumber);
	mappingTextItem("password",data.password);
	mappingTextItem("weixinAccount",data.weixinAccount);
}