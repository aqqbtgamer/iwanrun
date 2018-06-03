/**
 * user list app js
 */

const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dataInitUrl = '/iwant_admin/purchaseAccount/findPurchaseUser';
const dataQueryUrl = '/iwant_admin/purchaseAccount/queryPurchaseUser';
const dataModifyUrl= '/iwant_admin/createUser.html?isModify=true&id='; ;
const dataDeleteUrl = null;
var isModify = getUrlParam('isModify');
const columns = new Array(
	    "userInfo.name",
	    "userRole.roleName",
	    "loginInfo.loginId",
	    "loginInfo.mobileNumber",
	    "loginInfo.wec",
	    "userInfo.verifiedString"
	);
const queryField = new Array(
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