/**
 * order assign js by 隔壁老吴
 */
console.log("order assign js 加载");

const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dataInitUrl = '/iwant_admin/purchaseAccount/findPurchaseUser';
const dataQueryUrl = '/iwant_admin/purchaseAccount/queryPurchaseUser';
const orderGetUrl = '/iwant_admin/orders/simpleGet';
const orderUpdateUrl = '/iwant_admin/orders/assign';

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

var orderId = getUrlParam('id');

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
			var requestObj = new Object();
			requestObj.id = orderId;
			commonLoadForModify(requestObj,orderGetUrl,mappingData);
			bindAssignButton("assignOrder","userTable");
		}		
);



function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}

function  mappingData(result){
	var ret = $.parseJSON(result);
	$(".jquery_tab_title").append(ret.orderNo);
	$(".jquery_tab_title").data("orders",ret);
}

function bindAssignButton(bindId,tableId){
	$("#"+bindId).bind("click",function(){
		var selectedUserCheckBoxArray = $("#"+tableId).find(":checkbox:checked");
		if(selectedUserCheckBoxArray.length == 0){
			alert("请选择一个需要指派的咨询师");
		}else if(selectedUserCheckBoxArray.length > 1){
			alert("一次只能指派以为咨询师");
		}else{
			var userId = selectedUserCheckBoxArray.eq(0).attr("id");
			var orders = $(".jquery_tab_title").data("orders");
			assignOrders(orders,userId);
		}			
	});
}

function assignOrders(orders,userId){
	var request = new Object();
	request.orderAdviserId = userId ;
	request.id = orders.id ;
	$.ajax(
            {
                url: orderUpdateUrl,
                data: request,
                type: "post",
                dataType: "text",
                cache: false,
                success: function (result) {
                    var ret = $.parseJSON(result);
                    if(ret.successful){
                    	window.location.href = "/iwant_admin/orderList.html";
                    }else{
                    	alert(ret.description);
                    }
                },
                error:function(XMLHttpRequest ,error,exception){
                    console.log("提交到"+orderUpdateUrl+"失败,原因是: "+ error.toString());
                    alert("指派失败 服务后台问题")
                }

            }
        )
}


