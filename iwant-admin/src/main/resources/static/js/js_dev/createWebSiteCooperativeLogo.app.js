/**
 * 
 */
const uploadServer = '/iwant_admin/remote/fileupload';
const submitUrl = '/iwant_admin/webSiteCooperativeLogo/add';
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const fields = new Array(
		"name",
		"url",
		"logoPath"
)

$(document).ready(
	    function(){
	    	loadCommonTabs();
	    	getDictionaryPages(dictionaryUrl,dicionaryCallBack);
	    	bindUploadFile('mainImageUpload',uploadServer,'logoPath',singleDisplay);
	    	bindDataVerifySubmit("submitButton","submitForm",fields,submitUrl,returnListPage);
	    }
);


function returnListPage(result){
	if(result == "failed"){
		alert("后台新增数据失败")
	}else{
		window.location.href="webSiteCooperativeLogoList.html";
	}
}

function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}
	    