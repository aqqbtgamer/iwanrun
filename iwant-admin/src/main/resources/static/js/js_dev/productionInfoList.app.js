$(document).ready(function(){
	loadListData();// 加载列表数据
	wrapCustomerItems();
});
function loadListData(urlparamstr){
	var url = "./productionInfo/find?pageNum=" + currPage + "&pageSize=" + pageSize;
	
	if (urlparamstr != null && urlparamstr != undefined) {
		url +=  urlparamstr;
	}
	
	$.ajax({
	        type: 'GET',
	        url: url,
	        data:null,
	        contentType: "application/json;cherset=utf-8",
	        dataType: "json",
	        success: function (data){
	        	$('.node').empty()
	            // 取出后端传过来的list值
	        	var list = data.content;
	        	
	        	pagination(data.totalPages, pageSize);
	        	
				// 对list值进行便利
				$.each(list, function (index, n) {
					var mainImageIcon = list[index].mainImageIcon;
					var name = list[index].name;
					var activityTypeCode = list[index].activityTypeCode;
					var during = list[index].during;
					var groupNumberCode = list[index].groupNumberCode;
					var orderSimulatePriceCode = list[index].orderSimulatePriceCode;
					var orderGroupPriceCode = list[index].orderGroupPriceCode;
					
					groupNumberCode = groupNumberCode == null ? '' : groupNumberCode;
					groupNumberCode += ("id=" + list[index].id);
					orderSimulatePriceCode = orderSimulatePriceCode == null ? '' : orderSimulatePriceCode;
					orderGroupPriceCode = orderGroupPriceCode == null ? '' : orderGroupPriceCode;
					
					var rowTr = document.createElement('tr');
					// 找到html的tr节点
					rowTr.className = "node";
					var child = "<th><input type='checkbox'></th>"
//					+"<th><img src='"+list[index].mainImageIcon+"' alt='' /></th>"
					+"<td><img src='./images/1524319241.png' alt='' /></td>"	
					+"<td>"+name+"</td>"
					+"<td>"+activityTypeCode+"</td>"
					+"<td>"+during+"</td>"
					+"<td>"+groupNumberCode+"</td>"
					+"<td>"+orderSimulatePriceCode+"</td>"
					+"<td>"+orderGroupPriceCode+"</td>"
					+"<td> <a>修改</a> / <a>删除</a></td>"
					// 将要展示的信息写入页面
					rowTr.innerHTML = child;
					// 将信息追加
					($(".node")[0]).append(rowTr)
				});
	        }        
	 });
}
function search(){
	var searchSelected = $("#searchSelect option:selected").val();
	var searchText = $("#searchText").val();
	
	loadListData(searchSelected + "=" + searchText);
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
