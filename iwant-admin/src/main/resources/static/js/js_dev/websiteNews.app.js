/**
 * 
 * 
 */
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dataInitUrl = '/iwant_admin/websiteNews/findAll';
const dataDeleteUrl = '/iwant_admin/websiteNews/delete';
const dataQueryUrl = '/iwant_admin/websiteNews/query';
const columns = new Array(
		"newsContent",
		"newsDate"
);

var callObj = {
		dataInitUrl:dataInitUrl,
		dataUrl:dataInitUrl,
		pageId:"pagination",
		tableId:"newsTable",
		columns:columns
	};

const dateFieldsArray = new Array(
		"start",
		"end"
);

const queryField = new Array(
		"start",
		"end",
		"newsContent"
);

$(document).ready(
		function() {
			loadCommonTabs();
			getDictionaryPages(dictionaryUrl, dicionaryCallBack);
			initDataPicker(dateFieldsArray);
			wrapCustomerButtons();
			bindSelectAll("selectAll","newsTable");
			callObj.call = function(tr) {
				var td = $("<td>");
				var linkDel = $("<a>").text("删除");
				linkDel.bind("click", function(event) {
					var self = $(event.target);
					var parentTr = self.parent().parent();
					var id = parentTr.find("th input").attr("id");
					var confirmClose = confirm("是否确定删除");
					if (confirmClose) {
						var request = new Object();
						request.id = id;
						$.ajax({
							url : dataDeleteUrl,
							data : request,
							type : "post",
							dataType : "text",
							cache : false,
							success : function(result) {
								window.location.reload();
							},
							error : function(XMLHttpRequest, error, exception) {
								console.log("提交到" + dataDeleteUrl + "失败,原因是: "
										+ error.toString());
							}

						})
					}
				});
				td.append(linkDel);
				tr.append(td);
			}
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
