/**
 * Created by WXP22 on 2018/10/14.
 */

console.log("add relation app js 加载");
//----------------定参数--------------------------------
const locationQueryUrl = '/iwant_admin/location/queryAll';
const caseQueryUrl = '/iwant_admin/cases/queryAll';
const productionQueryUrl = '/iwant_admin/productionInfo/find';
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
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
				listTableHead:caseTableHead
			},
			production:{
				title:'场地关联产品信息',
				listTitle:"已关联的产品",
				listTableHead:productionTableHead
			},
			tableHeader:locationTableHead
		},
		case:{
			location:{
				title:'案例关联场地信息',
				listTitle:"已关联的场地",
				listTableHead:locationTableHead
			},
			production:{
				title:'案例关联产品信息',
				listTitle:"已关联的产品",
				listTableHead:productionTableHead
			},
			tableHeader:caseTableHead
		},
		production:{
			case:{
				title:'产品关联案例信息',
				listTitle:"已关联的产品",
				listTableHead:caseTableHead
			},
			location:{
				title:'产品关联场地信息',
				listTitle:"已关联的场地",
				listTableHead:locationTableHead
			},
			tableHeader:productionTableHead
		}		
};
//----------------全局参数--------------------------------
var type = getUrlParam('type');
var oprationId = ('id');
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
				initTableHeader($("#dataTable thead"),true,typeMapObj[type].tableHeader);//选择列表处理
				initTableHeader($("#bindTable thead"),false,typeMapObj[type][target].listTableHead,new Array("操作"));
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
//----------------回调方法区-----------------
function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}