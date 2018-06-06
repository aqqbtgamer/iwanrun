/**
 * 
 */
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dataInitUrl = '/iwant_admin/userAccount/findAll';
const dataQueryUrl = '/iwant_admin/userAccount/findByExample';
const dataModifyUrl= '/iwant_admin/createAdmin.html?isModify=true&id='; ;
const dataDeleteUrl = '/iwant_admin/userAccount/delete';

const columns = new Array(
	    "username",
	    "mobileNumber",
	    "weixinAccount"
	);

const queryField = new Array(
		"username",
		"mobileNumber"
);

$(document).ready(
		function(){
			getDictionaryPages(dictionaryUrl,dicionaryCallBack);
			pageDataInit("userTable","pagination",dataInitUrl,dataDeleteUrl,dataModifyUrl,columns);
			 bindSelectAll("selectAll","userTable");
		     wrapCustomerButtons();
		     bindClickFieldQuery("queryCondtion",queryField,"userTable","pagination",dataQueryUrl,dataModifyUrl,dataDeleteUrl,columns);
		}
);

function wrapCustomerButtons(){
    $("button").unbind();
    console.log("开始处理自定义byutton功能");
    $("button[method='href']").each(
        function(index,element){
            $(element).click(
                function(){
                    window.location.href=$(element).attr('to');
                }
            );
        }
    )
}

function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}