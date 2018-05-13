/**
 * 
 */
console.log("dictionary.app.js加载");
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
var dictionaryName = getUrlParam('name');

$(document).ready(
		function(){
			getDictionaryPages(dictionaryUrl,dicionaryCallBack);			
		}
);

function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
	$("#dictionarys").show();
	$("#dictionarys li a[name='"+dictionaryName+"']").parent().addClass("current");
}