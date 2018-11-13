/**
 * Created by WXP22 on 2018/10/14.
 */

console.log("add relation app js 加载");
//----------------定参数--------------------------------
const locationQueryUrl = '/iwant_admin/location/findAll';
const locationSearchUrl = '/iwant_admin/location/queryAll';
const caseQueryUrl = '/iwant_admin/cases/findAll';
const caseSearchUrl = '/iwant_admin/cases/queryAll'
const productionQueryUrl = '/iwant_admin/productionInfo/findAll';
const productionSearchUrl = '/iwant_admin/productionInfo/queryAll';
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const addRelationUrl = "/iwant_admin/relations/add";
const queryRelationUrl = "/iwant_admin/relations/queryBindings";
const deleteRelationUrl = "/iwant_admin/relations/deleteBindings";
const locationTableHead = new Array(
		'场地名称',
		'活动类型',
		'最大活动人数',
		'地址',
		'参考报价'
);

const caseTableHead = new Array(
		'案例名称',
		'交通信息',
		'餐饮信息',
		'住宿信息',
		'策划周期'
);

const productionTableHead = new Array(
		'产品名称',
		'活动类型',
		'活动天数',
		'人数上限',
		'人均参考报价范围'		
);

const locationTableColumns = new Array(
		"name",
	    "activeTypeCodeDesc",
	    "groupNumberLimitCodeDesc",
	    "location",
	    "simulatePriceCodeDesc"
);

const caseTableColumns = new Array(
		 "name",
		 "trafficInfo",
		 "foodInfo",
		 "hotelInfo",
		 "designDuringCode"
);

const productionTableColumns = new Array(
		"name",		
		"activityTypeCode",
		"during",
		"groupNumber",
		"orderSimulatePrice"		
);
const typeMapObj = {
		location:{
			case:{
				title:'场地关联案例信息',
				listTitle:"已关联的案例",
				listTableHead:caseTableHead,
				dataUrl:caseQueryUrl,
				dataQueryUrl:caseSearchUrl,
				columns:caseTableColumns
			},
			production:{
				title:'场地关联产品信息',
				listTitle:"已关联的产品",
				listTableHead:productionTableHead,
				dataUrl:productionQueryUrl,
				dataQueryUrl:productionSearchUrl,
				columns:productionTableColumns
			}
		},
		case:{
			location:{
				title:'案例关联场地信息',
				listTitle:"已关联的场地",
				listTableHead:locationTableHead,
				dataUrl:locationQueryUrl,
				dataQueryUrl:locationSearchUrl,
				columns:locationTableColumns
			},
			production:{
				title:'案例关联产品信息',
				listTitle:"已关联的产品",
				listTableHead:productionTableHead,
				dataUrl:productionQueryUrl,
				dataQueryUrl:productionSearchUrl,
				columns:productionTableColumns
			}
		},
		production:{
			case:{
				title:'产品关联案例信息',
				listTitle:"已关联的产品",
				listTableHead:caseTableHead,
				dataUrl:caseQueryUrl,
				dataQueryUrl:caseSearchUrl,
				columns:caseTableColumns
			},
			location:{
				title:'产品关联场地信息',
				listTitle:"已关联的场地",
				listTableHead:locationTableHead,
				dataUrl:locationQueryUrl,
				dataQueryUrl:locationSearchUrl,
				columns:locationTableColumns
			}
		}		
};

const queryField = new Array(
		"name"
);
//----------------全局参数--------------------------------
var type = getUrlParam('type');
var oprationId = getUrlParam('id');
var target = getUrlParam('add');
var dataCallObj = {
		dataInitUrl:null,
		dataUrl:null,
		pageId:"pagination",
		tableId:"dataTable",
		columns:null,
		call:function(){
			
		}
	};
//---------主页面jquery入口-------------------------------------
$(document).ready(
		function(){
			//公共方法区-----------------------------------	
				loadCommonTabs();
				getDictionaryPages(dictionaryUrl,dicionaryCallBack);
			//页面方法区
				filterTabs();//文字标题处理
				initTableHeader($("#dataTable thead"),true,typeMapObj[type][target].listTableHead);//选择列表处理
				initTableHeader($("#bindTable thead"),false,typeMapObj[type][target].listTableHead,new Array("操作"));
				initDataTableContent(type,target);//表内容填充
				initBindDataTableContent(type,target,oprationId);//绑定表内容
				bindSelectAll("selectAll","dataTable");//表选择功能绑定
				bindAddRelation("addRelation",target);//主功能 添加绑定关系
				queryBindTarget("queryCondition",type,target);
		}		
)

//-----------------页面主方法区----------------------------
//-----去除多余的tab头修复标题的信息
function filterTabs(){
	$("div.jquery_tab_container a:not(:first)").remove();
	var title = typeMapObj[type][target].title;
	$("div.jquery_tab_container a:first").text(title);
	$("h2.jquery_tab_title:first").text(title);
	var listTitle = typeMapObj[type][target].listTitle;
	$("h2.jquery_tab_title:eq(1)").text(listTitle);
}

function initTableHeader(thead,headSelect,columns,extraColumns){
		//var thead = $("#dataTable thead");
		var tr = $("<tr></tr>");
		if(headSelect){
			var select = $("<input></input>").attr("type","checkbox").attr("id","selectAll");
			var selectTh = $("<th></th>");		
			selectTh.append(select).append("选择全部");
			tr.append(selectTh);
		}else{
			var selectTh = $("<th></th>");
			tr.append(selectTh);
		}		
		//var columns = typeMapObj[type].tableHeader ;
		for(var i = 0 ; i< columns.length ; i++){
			var th = $("<th></th>").text(columns[i]);
			tr.append(th);
		}
		if(extraColumns != null && extraColumns.length > 0){
			for(var i = 0 ; i< extraColumns.length ; i++){
				var th = $("<th></th>").text(extraColumns[i]);
				tr.append(th);
			}
		}
		thead.append(tr);		
}

function initDataTableContent(type,target){	
	var dataFetchUrl = typeMapObj[type][target].dataUrl ;
	var columns = typeMapObj[type][target].columns ;
	var newCallObj  = new Object();
	$.extend(true ,newCallObj,dataCallObj);
	newCallObj.columns = columns ;
	newCallObj.dataInitUrl = dataFetchUrl;
	newCallObj.dataUrl = dataFetchUrl ;
	customerPageDataInit(newCallObj.tableId,newCallObj.dataInitUrl,0,newCallObj);	
}

function initBindDataTableContent(type,target,oprationId){
	var newCallObj = new Object();
	$.extend(true ,newCallObj,dataCallObj);
	newCallObj.columns = typeMapObj[type][target].columns ;
	newCallObj.dataInitUrl = queryRelationUrl;
	newCallObj.dataUrl = queryRelationUrl ;
	newCallObj.tableId = "bindTable";
	newCallObj.pageId = "bindPagination";
	newCallObj.call = function(tr){
		var td = $("<td>");
		var linkDelBind = $("<a>").text("删除绑定");
		linkDelBind.bind("click",function(event){
			var clickDel = confirm("是否确认删除绑定?");
			if(clickDel){
				var self = $(event.target);
				var parentTr = self.parent().parent();
				var targetId = parentTr.find("th input").attr("id");
				var request = new Object();
				request.oprationId = targetId ;
				request.typeId = oprationId
				request.type = type ;			
				request.target = target ;
				$.ajax(
		                {
		                    url:deleteRelationUrl,
		                    cache:false,
		                    data:request,
		                    dataType:"text",
		                    type:"POST",
		                    success:function(result){
		                       console.log("提交到"+deleteRelationUrl+"成功");
		                       if(result != fail){
		                    	   window.location.reload();   
		                       }else{
		                    	   alert("删除绑定关系失败");
		                       }
		                                 
		                    },
		                    error:function(XMLHttpRequest ,error,exception){
		                        alert("提交到"+deleteRelationUrl+"失败,原因是: "+ exception.toString());	                        
		                    }
		                }
		            )
			}			
		})
		td.append(linkDelBind);
		tr.append(td);
	}
	var request = new Object();
	request.type = type ;
	request.target = target ;
	request.oprationId = oprationId ;	
	newCallObj.data = JSON.stringify(request) ;
	customerPageDataInit(newCallObj.tableId,newCallObj.dataInitUrl,0,newCallObj);
}

function bindAddRelation(bindId,target){
	$("#"+bindId).bind("click",function(){
		var selectItem = $("#dataTable tbody input[type='checkbox']:checked");
		if(selectItem.length > 0){
			var ids = new Array();
			selectItem.each(
					function(){
						ids.push($(this).attr("id"));
					}
			);
			console.log("selected bind items :");
			console.log(ids);
			var request = {"ids":ids,"oprationId":oprationId,"type":type,"target":target};
			$.ajax(
	                {
	                    url:addRelationUrl,
	                    cache:false,
	                    data:request,
	                    dataType:"text",
	                    type:"POST",
	                    success:function(result){
	                       console.log("提交到"+addRelationUrl+"成功");
	                       if(result != fail){
	                    	   window.location.reload();   
	                       }else{
	                    	   alert("添加绑定关系失败");
	                       }
	                                 
	                    },
	                    error:function(XMLHttpRequest ,error,exception){
	                        alert("提交到"+addRelationUrl+"失败,原因是: "+ exception.toString());	                        
	                    }
	                }
	            )
		}else{
			alert("至少选择一项进行关联")
		}
	})
}


function queryBindTarget(bindId,type,target){
	//var 
	var clickObj = new Object();
	$.extend(true ,clickObj,dataCallObj);
	clickObj.dataInitUrl = typeMapObj[type][target].dataQueryUrl;
	clickObj.dataUrl = typeMapObj[type][target].dataQueryUrl;
	clickObj.columns = typeMapObj[type][target].columns;
	bindCustomerClickFieldQuery(bindId,queryField,clickObj.tableId,clickObj);
}

//----------------回调方法区-----------------
function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}