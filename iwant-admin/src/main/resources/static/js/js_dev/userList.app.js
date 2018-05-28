/**
 * user list app js
 */

const dictionaryUrl = "/iwant_admin/dictionary/getPages"; 

$(document).ready(
    function(){
        getDictionaryPages(dictionaryUrl,dicionaryCallBack);
    }
);



function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}