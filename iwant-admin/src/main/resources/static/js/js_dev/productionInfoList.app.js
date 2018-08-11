const dictionaryUrl = "/iwant_admin/dictionary/getPages";

$(document).ready(function() {
	loadListData(null, fillPagesData);// 加载列表数据
	wrapCustomerItems();
	getDictionaryPages(dictionaryUrl,dicionaryCallBack);
});
function loadListData(urlparamstr, loadback) {
	var url = "./productionInfo/find?pageNum=" + currPage + "&pageSize="
			+ pageSize;

	if (urlparamstr != null && urlparamstr != undefined) {
		url += ("&" + urlparamstr);
	}

	if (!loadback) {
		loadback = fillPagesData;
	}
	postJSON(url, null, 'GET', loadback);
}

function fillPagesData(data){
	$('.node').empty();
	pagination(data.totalPages, pageSize);
	
	// 取出后端传过来的list值
	var list = data.content;
				// 对list值进行便利
	$.each(list, function(index, n) {
		var mainImageIcon = list[index].mainImageIcon;
		var name = list[index].name;
		var during = list[index].during;
		var id = list[index].id;
		
		var activityType = list[index].activityTypeCode;
		var groupNumber = list[index].groupNumber;
		var orderSimulatePrice = list[index].orderSimulatePrice;
		var orderGroupPrice = list[index].orderGroupPrice;
		
		mainImageIcon = null2Blank(mainImageIcon);
		activityType = null2Blank(activityType);
		groupNumber = null2Blank(groupNumber);
		orderSimulatePrice = null2Blank(orderSimulatePrice);
		orderGroupPrice = null2Blank(orderGroupPrice);
		
		var rowTr = document.createElement('tr');
		// 找到html的tr节点
		rowTr.className = "node";
		var child = "<th><input type='checkbox'></th>"
				+ "<td><img src='"
				+ mainImageIcon
				+ "' alt='"
				+ mainImageIcon
				+ "' width='100' height='30'/></td>"
				+ "<td>"
				+ name
				+ "</td>"
				+ "<td>"
				+ activityType
				+ "</td>"
				+ "<td>"
				+ during
				+ "</td>"
				+ "<td>"
				+ groupNumber
				+ "</td>"
				+ "<td>"
				+ orderSimulatePrice
				+ "</td>"
				+ "<td>"
				+ orderGroupPrice
				+ "</td>"
				+ "<td> <a href='editProductionInfo.html?id="
				+ id
				+ "'>修改</a> / <a onclick='unShift(" + id + ")'>删除</a></td>"
		// 将要展示的信息写入页面
		rowTr.innerHTML = child;
		// 将信息追加
		($(".node").eq(0)).append(rowTr)
	});
}
function search(flag){
	var searchSelected = $("#searchSelect option:selected").val();
	var searchText = $("#searchText").val();
	
	if(searchSelected && searchText){
		if(flag == 'research'){
			currPage = 0;//	搜索时 当前页重置为0
		}
		loadListData(searchSelected + "=" + searchText, fillPagesData);
	}else{
		loadListData(null, fillPagesData)
	}
	
}

function wrapCustomerItems() {
	console.log("开始处理自定义组件功能");
	wrapCustomerButtons();
}

function wrapCustomerButtons() {
	$("button").unbind();
	console.log("开始处理自定义byutton功能");
	$("button[method='href']").each(function(index, element) {
		$(element).click(function() {
			window.location.href = $(element).attr('to');
		});
	})
}
function unShift(id) {
	postJSON('./productionInfo/unShift', "{\"id\":" + id + "}", null, function(
			data) {
		var num = parseInt(data);
		if (num > 0) {
			window.location = 'productionInfoList.html';
		}
		console.log("下架：" + num);
	});
}

function postJSON(url, data, requestMethod, callback){
	if(!requestMethod){
		requestMethod = 'POST';
	}
	$.ajax({
		url:url,
		data:data,
		type:requestMethod,
		dataType:'json',
		contentType:'application/json;charset=utf-8',
		success:callback,
		error:callback
	});
}

function selectAll(ele) {
	$('tbody').find('input[type=checkbox]').attr('checked', ele.checked);
}

function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}
