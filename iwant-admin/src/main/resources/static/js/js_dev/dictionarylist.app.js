/**
 * 
 */
console.log("dictionary.app.js加载");
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dictionaryTabsUrl = "/iwant_admin/dictionary/getTabs";
const queryDictionaryDataUrl = "/iwant_admin/dictionary/findByCode";
const addDictionaryUrl = "/iwant_admin/dictionary/add";
const deletDictionaryUrl = "/iwant_admin/dictionary/delete";
const assignArray = new Array(
		"7",
		"8"
);
const assignToArray = new Array(
		"6",
		"7"
);
var dictionaryName = getUrlParam('name');

$(document).ready(
		function(){
			loadCommonTabs();
			getDictionaryPages(dictionaryUrl,dicionaryCallBack);
			getDictinaryPageTabs(dictionaryName,dictionaryTabsUrl,dictionaryTabCallBack);
		}
);

function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
	$("#dictionarys").show();
	$("#dictionarys li a[name='"+dictionaryName+"']").parent().addClass("current");
}

function dictionaryTabCallBack(result){
	var ret = $.parseJSON(result);
	var content = $("#content");
	var tabContainer = content.find("div.jquery_tab_container");
	for(var i = 0; i< ret.length; i++){
		var tabLink = $("<a></a>").prop("href","#").addClass("heading_tab").addClass("advanced_link").attr("dbId",ret[i].id).text(ret[i].name);
		tabLink.bind("click",function(event){
			var id = $(event.target).attr("dbId");
			tabContainer.find("a.heading_tab").removeClass("active");
			$(event.target).addClass("active");
			content.find("div.jquery_tab[dbId!='"+id+"']").hide();
			content.find("div.jquery_tab[dbId='"+id+"']").show();
			findDictionaryCode(queryDictionaryDataUrl,$(event.target).attr("dbId"),dictionaryName,initDictionaryData);
		});
		tabContainer.append(tabLink);
		var tabList = $("<div></div>").addClass("jquery_tab").addClass("tablist").attr("dbId",ret[i].id);;
		var contentBlock = $("<div></div>").addClass("content_block").attr("dbId",ret[i].id);
		var h2 = $("<h2></h2>").addClass("jquery_tab_title").text(ret[i].name);
		contentBlock.attr("dbName",ret[i].name);
		content.append(tabList);
		tabList.append(contentBlock);
		contentBlock.append(h2);
		var button = $("<button></button>").text("添加");
		button.attr("dbId",ret[i].id);
		button.attr("dbName",ret[i].name);
		contentBlock.append(button);
		createAddForm(button);
		button.bind("click",function(event){
			var dbId = $(event.target).attr("dbId");
			var text = $(event.target).text();
			if(text == "添加"){
				$("#form"+dbId).show();
				$(event.target).text("关闭");
			}else{
				$("#form"+dbId).hide();
				$(event.target).text("添加");
			}
		});
		tabList.hide();
	}
	
	function createAddForm(target){
		var dbId = target.attr("dbId");
		var p = $("<div></div>").attr("style","margin-top:20px").attr("id","form"+dbId);
		var code = $("<input>").prop("type","number").prop("name","code").addClass("input-small");
		var label = $("<span></span>").text("序列号：");
		var labe2 = $("<span></span>").text("显示值：");
		var value = $("<input>").prop("type","text").prop("name","value").addClass("input-small");		
		p.append(label);
	    p.append(code);
	    p.append("&nbsp;&nbsp;&nbsp;&nbsp;");
	    p.append(labe2);
	    p.append(value);
	    p.append("&nbsp;&nbsp;&nbsp;&nbsp;");
	    var index = $.inArray(dbId,assignArray)
		if(index >= 0){
			 p.append("&nbsp;&nbsp;&nbsp;&nbsp;");
			 var label3 = $("<span></span>").text("关联值：");
			 var select = $("<select>");
			 var assignToDbId = assignToArray[index];
			 var callObj = new Object();
			 callObj.obj = select;
			 callObj.callback = function(dbId,result){
				 var ret = $.parseJSON(result);
				 for(var i =0 ; i< ret.length ; i++){
					var option = $("<option>").val(ret[i].id).text(ret[i].value);
					this.obj.append(option);
				 }				 
			 } 
			 findDictionaryCode(queryDictionaryDataUrl,assignToDbId,dictionaryName,callObj);
			 p.append(label3);
			 p.append(select); 
		}
	    var submit = $("<button></button>").attr("dbId",dbId).text("保存");
	    submit.bind("click",function(event){
	    	var requestData = new Object();
	    	requestData.code = code.val();
	    	requestData.value = value.val();
	    	requestData.used_field = dbId ;
	    	requestData.name = dictionaryName ;
	    	if(select != null){
	    		requestData.assignTo=select.val();
	    	}
	    	submitDictionaryCode(addDictionaryUrl,requestData,findDictionaryCode);
	    })
		p.append(submit);
		target.after(p);
		p.hide();
		return p ;
	}
	
	
	function submitDictionaryCode(url,requestData,callback){
		$.ajax(
    			{
    				url:url,
    				cache:false,
    				data:requestData,
                    dataType:"text",
                    type:"POST",
                    success:function(result){
                    	console.log("提交到"+url+"成功："+result);
                    	if(callback != null){
                    		callback(queryDictionaryDataUrl,requestData.used_field,dictionaryName,initDictionaryData);
                    	}
                    },
	    			error:function(XMLHttpRequest ,error,exception){
	                    console.log("提交到"+url+"失败,原因是: "+ exception.toString());
	                }
    			}
    	);
	}
	
	function initDictionaryData(target,result){
		var ret = $.parseJSON(result);		
		if(ret.length > 0){	
			var dbId =  ret[0].used_field;			
			var content = $("div.content_block[dbId='"+dbId+"']");
			content.find("table#table_liquid"+dbId).remove();
			var table = $("<table></table>").attr("id","table_liquid"+dbId).prop("cellspacing",0)
			.prop("style","margin-top:20px");
			//var caption = $("<caption>").text(content.attr("dbName")+"列表");
			var headTr = $("<tr></tr>");
			var headTh1 = $("<th>").addClass("nobg").text(content.attr("dbName")+"列表");
			var headTh2 = $("<th>").text("序列号");
			var headTh3 = $("<th>").text("显示值");
			var headTh4 = $("<th>").text("操作");
			headTr.append(headTh1).append(headTh2).append(headTh3).append(headTh4);
			//table.append(caption);
			table.append(headTr);
			content.append(table);
			for(var i = 0 ; i< ret.length ; i++){			
				var tr = $("<tr></tr>");
				var th1 = $("<th></th>");
				var td1 = $("<td></td>").text(ret[i].code);
				var td2 = $("<td></td>").text(ret[i].value);
				var td3 = $("<td></td>");
				var linkDelete = $("<a></a>").attr("dbId",ret[i].id).text("删除");
				linkDelete.bind("click",function(event){
					var confirmDelete = confirm("确认删除吗");
					if(confirmDelete){
						var id = $(event.target).attr("dbId");
						var requestData = new Object();
						requestData.id = id; 
						requestData.used_field = dbId ;
						submitDelete(deletDictionaryUrl,requestData,findDictionaryCode);
					}					
				});
				td3.append(linkDelete);
				if(i%2 == 0){
					th1.addClass("spec");
				}else{
					th1.addClass("specalt");
					td1.addClass("alt");
					td2.addClass("alt");
					td3.addClass("alt");
				}
				tr.append(th1).append(td1).append(td2).append(td3);
				table.append(tr);
			}
		}
		
	}
	
	
	function submitDelete(url,requestData,callback){
		$.ajax(
    			{
    				url:url,
    				cache:false,
    				data:requestData,
                    dataType:"text",
                    type:"POST",
                    success:function(result){
                    	console.log("提交到"+url+"成功："+result);
                    	if(callback != null){
                    		callback(queryDictionaryDataUrl,requestData.used_field,dictionaryName,initDictionaryData);
                    	}
                    },
	    			error:function(XMLHttpRequest ,error,exception){
	                    console.log("提交到"+url+"失败,原因是: "+ exception.toString());
	                }
    			}
    	);
	}
	

	
}