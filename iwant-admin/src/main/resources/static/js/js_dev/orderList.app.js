/**
 * 
 */
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dataInitUrl = '/iwant_admin/orders/findAll';
const dataQueryUrl = '/iwant_admin/orders/findByExample';
const dateFieldsArray = new Array(
		"createTimeFrom",
		"createTimeTo",
		"modifyTimeFrom",
		"modifyTimeTo"
);
const columns = new Array(
		"name",
		"mobileNumber",
		"orderNo",
		"createTime",
		"orderStatusCodeString"
);

const queryField = new Array(
		"orderNO",
		"mobile1",
		"mobile2",
		"createTimeFrom",
		"createTimeTo",
		"modifyTimeFrom",
		"modifyTimeTo",
		"orderStatus"
);

var callObj = {
	dataInitUrl:dataInitUrl,
	dataUrl:dataInitUrl,
	pageId:"pagination",
	tableId:"orderTable",
	columns:columns
};


$(document).ready(		
		function(){
			getDictionaryPages(dictionaryUrl,dicionaryCallBack);
			initDataPicker(dateFieldsArray);
			callObj.call = function(tr){
				//console.log("自定义 data table option栏位");
				var td = $("<td>")
				var linkDetail = $("<a>").text("查看订单详情");
				var linkAssign = $("<a>").text("订单指派");
				var linkClose = $("<a>").text("关闭订单");
				td.append(linkDetail);
				td.append("/");
				td.append(linkAssign);
				td.append("/");
				td.append(linkClose);
				tr.append(td);
			};
			customerPageDataInit(callObj.tableId,callObj.dataInitUrl,0,callObj);
			var clickObj = new Object();
			$.extend(clickObj,callObj);
			clickObj.dataUrl = dataQueryUrl;
			bindCustomerClickFieldQuery("queryCondtion",queryField,clickObj.tableId,clickObj);
		}
		
)

function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}