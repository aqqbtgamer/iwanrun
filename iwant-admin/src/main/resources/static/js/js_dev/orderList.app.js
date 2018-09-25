/**
 * 
 */
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dataInitUrl = '/iwant_admin/orders/findAll';
const dataQueryUrl = '/iwant_admin/orders/findByExample';
const orderDetailUrl = '/iwant_admin/orderDetails.html?id=';
const orderAssignUrl = '/iwant_admin/orderAssign.html?id=';
const orderCloseUrl = '/iwant_admin/orders/close';	
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
			loadCommonTabs();
			getDictionaryPages(dictionaryUrl,dicionaryCallBack);
			initDataPicker(dateFieldsArray);
			callObj.call = function(tr){
				//console.log("自定义 data table option栏位");
				var td = $("<td>")
				var linkDetail = $("<a>").text("查看订单详情");
				var linkAssign = $("<a>").text("订单指派");
				var linkClose = $("<a>").text("关闭订单");
				linkDetail.bind("click",function(event){
					var self = $(event.target);
					var parentTr = self.parent().parent();
					var orderId = parentTr.find("th input").attr("id");
					window.location.href = orderDetailUrl+orderId;
				});
				linkAssign.bind("click",function(event){
					var self = $(event.target);
					var parentTr = self.parent().parent();
					var orderId = parentTr.find("th input").attr("id");
					window.location.href = orderAssignUrl+orderId;
				});
				linkClose.bind("click",function(event){
					var self = $(event.target);
					var parentTr = self.parent().parent();
					var orderId = parentTr.find("th input").attr("id");
					var orderStatusDesc = parentTr.find("td:eq(4)").text();
					var confirmClose = confirm("是否确定关闭此交易?");
					if(confirmClose){
						if(orderStatusDesc == "已关闭"){
							return;
						}
						var request = new Object();
						request.id = orderId ;
						$.ajax(
					            {
					                url: orderCloseUrl,
					                data: request,
					                type: "post",
					                dataType: "text",
					                cache: false,
					                success: function (result) {					                	
//					                	parentTr.find("td:eq(4)").text("已关闭"); 
					                	 window.location.reload();
					                },
					                error:function(XMLHttpRequest ,error,exception){
					                    console.log("提交到"+orderCloseUrl+"失败,原因是: "+ error.toString());					           
					                }

					            }
					        )
					}
				});
				td.append(linkDetail);
				td.append("/");
				var orderStatusDescTr = $(tr).find("td:eq(4)").text();
				if(orderStatusDescTr != "已关闭"){
					td.append(linkAssign);
					td.append("/");
				}
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