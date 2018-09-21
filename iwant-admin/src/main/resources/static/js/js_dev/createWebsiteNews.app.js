/**
 * 
 */
const submitUrl = '/iwant_admin/websiteNews/add';
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const fields = new Array(
		"newsContent"
    );

$(document).ready(
		function(){
			loadCommonTabs();
			getDictionaryPages(dictionaryUrl,dicionaryCallBack);
			bindDataVerifySubmit("submitButton","submitForm",fields,submitUrl,returnListPage);			
		}
);	


function returnListPage(result){
	if(result == "failed"){
		alert("后台新增数据失败")
	}else{
		window.location.href="websiteNewsList.html";
	}
}

function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}