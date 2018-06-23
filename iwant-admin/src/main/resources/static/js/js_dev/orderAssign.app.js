/**
 * order assign js by 隔壁老吴
 */
console.log("order assign js 加载");

const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dataInitUrl = '/iwant_admin/purchaseAccount/findPurchaseUser';
const dataQueryUrl = '/iwant_admin/purchaseAccount/queryPurchaseUser';

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

var callObj = {
		dataInitUrl:dataQueryUrl,
		dataUrl:dataQueryUrl,
		pageId:"pagination",
		tableId:"userTable",
		columns:columns,
		call:function(){
			
		}
	};

$(document).ready(
		function(){
			getDictionaryPages(dictionaryUrl,dicionaryCallBack);
			var callData = new Object();
			callData.role = "2";
			callObj.data = JSON.stringify(callData);
			customerPageDataInit(callObj.tableId,callObj.dataInitUrl,0,callObj);
			var clickObj = new Object();
			$.extend(clickObj,callObj);
			clickObj.dataUrl = dataQueryUrl;
			bindCustomerClickFieldQuery("queryCondtion",queryField,clickObj.tableId,clickObj);
		}		
);		


function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}