/**
 * 
 */
console.log("homepage.app.js加载");
const dictionaryUrl = "/iwant_admin/dictionary/getPages";

$(document).ready(
		function(){
			loadCommonTabs();
			getDictionaryPages(dictionaryUrl,dicionaryCallBack);
		}		
);


function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}