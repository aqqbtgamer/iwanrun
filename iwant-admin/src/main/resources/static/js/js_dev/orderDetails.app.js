/**
 * 
 */
console.log("加载create order details .js");
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dataGetUrl ='/iwant_admin/orders/get';
const orderMessageurl='/iwant_admin/orders/getMessage';

const columns = new Array(
		"messageText",
		"messageFrom",
		"createTimeString",
		"alreadyReadyString"
);

var orderId = getUrlParam('id');

var callObj = {
		dataInitUrl:orderMessageurl,
		dataUrl:orderMessageurl,
		pageId:"pagination",
		tableId:"orderMessageTable",
		columns:columns,
		call:function(){
			
		}
	};

$(document).ready(
		function(){
			getDictionaryPages(dictionaryUrl,dicionaryCallBack);
			cleanTabs();
			var requestObj = new Object();
			requestObj.id = orderId;
			commonLoadForModify(requestObj,dataGetUrl,mappingData);
			var callData = new Object();
			callData.orderId = orderId ;
			callObj.data = JSON.stringify(callData) ;
			customerPageDataInit(callObj.tableId,callObj.dataInitUrl,0,callObj);
		}		
);

function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}

function cleanTabs(){
	var tabConTainer = $("div.jquery_tab_container");
	var tabLinks = tabConTainer.find("a");
	for(var i=0 ; i< tabLinks.length ; i++){
		if(i != 0){
			$(tabLinks[i]).remove();
		}else{
			$(tabLinks[i]).addClass("active")
		}
	}
}

function mappingData(result){
	var ret = $.parseJSON(result);
	if(ret.orders != null){
		var orders = ret.orders ;
		mappingTextItem("orderNO",orders.orderNo);
		mappingTextItem("createTime",parseDateStr(orders.createTime));
		mappingTextItem("orderStatus",orders.orderStatus);
		mappingTextItem("companyTypeId",orders.companyTypeId);
		mappingTextItem("contract",orders.contract);
		mappingTextItem("groupNumberCode",orders.groupNumber);
		mappingTextItem("contractMobile",orders.contractMobile);
		mappingTextItem("activityTypeCode",orders.activitysCode);
		mappingTextItem("activityDuringCode",orders.activityDuring);
		mappingTextItem("activityDate",parseDateStr(orders.activityStart) + " - " + parseDateStr(orders.activityEnd));
		mappingTextItem("orderSimulatePriceCode",orders.orderSimulatePrice);		
	}
	if(ret.purchaserAccountInfo != null){
		var userInfo = ret.purchaserAccountInfo
		mappingTextItem("purchaseName",userInfo.name);
		mappingTextItem("purchaseGender",userInfo.genderString)
	}
	if(ret.purchaserAccount != null){
		var user = ret.purchaserAccount ;
		mappingTextItem("purchaseMobile",user.mobileNumber);
		mappingTextItem("purchaseWeixin",user.wec);
	}
	mappingFileDownloadItem("case_draft",ret.caseDraft);
	mappingFileDownloadItem("appointment",ret.appointment);
	mappingFileDownloadItem("project_conclude",ret.projectConclude);
}
