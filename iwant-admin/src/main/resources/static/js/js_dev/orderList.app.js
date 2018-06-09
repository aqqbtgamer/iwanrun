/**
 * 
 */
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dataInitUrl = '/iwant_admin/orders/findAll';
const dateFieldsArray = new Array(
		"createTimeFrom",
		"createTimeTo",
		"modifyTimeFrom",
		"modifyTimeTo"
);


$(document).ready(		
		function(){
			getDictionaryPages(dictionaryUrl,dicionaryCallBack);
			initDataPicker(dateFieldsArray);
			var callObj = new Object();
			callObj.tableId = "orderTable";
			callObj.pageId = "pagination";
			callObj.dataInitUrl = dataInitUrl;
			callObj.call = function(tr){
				console.log("自定义 data table option栏位")
			}
			customerPageDataInit(callObj.tableId,callObj.dataInitUrl,0,callObj);
		}
		
)

function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}