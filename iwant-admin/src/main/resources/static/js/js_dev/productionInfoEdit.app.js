var uploadServer = '/iwant_admin/remote/fileupload';
var submitUrl = '/iwant_admin/productionInfo/edit';
const dictionaryUrl = "/iwant_admin/dictionary/getPages";

var dataArr = new Array("name", "activityTypeCode", "during", "duringCode",
	"location", "orderGroupPriceCode", "orderSimulatePriceCode",
	"groupNumber", "groupNumberCode", "priority", "activityProvinceCode",
	"activityCityCode", "activityDistCode", "mainImageLarge", 'imgManage'
);

$(document).ready(
    function(){
    	loadCommonTabs();
    	getDictionaryPages(dictionaryUrl,dicionaryCallBack);
    	initPageData();// 设置产品详情数据
        bindUploadFile('mainImageUpload', uploadServer, 'mainImageLarge', singleDisplay);
        bindDataSubmitJSON('submitForm', dataArr, submitUrl, returnListPage);
        bindUploadFile('uploadedProductionInfoImages',uploadServer,'imgManage',mutipleDisplay);
        bindSeclectAll("checkAll");
        bindDeleteSelected("deleteAll");
    }
);

function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}

function returnListPage(result){
	if(result == "failed"){
		alert("后台处理数据失败")
	}else{
		window.location.href = "productionInfoList.html";
	}
}

function bindDataSubmitJSON(id, fieldArray, url, callback){
    $("#" + id).bind('click', function() {
        var formData = collectFormDatas(fieldArray);
		var infoRequest = {};
		formData.descirbeText1 = formData['_ue'];// UEeditor编辑器数据
		formData.id = getUrlParam().id;
		infoRequest.info = formData;
		var param = {};
		param.messageBody = JSON.stringify(infoRequest);
		var paramJSON = JSON.stringify(param);
        $.ajax(
            {
                url : url,
				cahce : false,
				data : paramJSON,
				dataType : "json",
				contentType : "application/json;charset=utf-8",
				type : "POST",
				success : function(result) {
					console.log("提交到" + url + "成功");
					callback();
				},
				error : function(XMLHttpRequest, error, exception) {
					console.log("提交到" + url + "失败,原因是: " + error.toString());
					alert("上传失败 服务端无法连接")
				}
            }
        )
    });
}

function getUrlParam() {   
   var url = location.search; // 获取url中"?"符后的字串
   var theRequest = new Object();   
   if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}   
	return theRequest;   
}   
function initPageData(){
	var id = getUrlParam();
    $.post('./productionInfo/detail', id, function(dataJson){
    	var data = JSON.parse(dataJson);
		fillPageDatas(data);
    });  
}

function fillPageDatas(data) {
	setUEData(data);// 设置UE编辑器内容
	
	//设置附件
	var imgs = data.listAttch ? JSON.parse(data.listAttch) : null;;
	if(imgs != null && imgs.length > 0){
		for(var i = 0; i<imgs.length ; i++){
			mutipleDisplay('imgManage',imgs[i].filePath);
		}		
	}
	
	//填充一般数据
	for (var i = 0; i < dataArr.length; i++) {
		var name = dataArr[i];
		var val = data[name];
		if (val != null) {
			var ele = $("[name='" + name + "']");
			fillItem(ele, val);
		}
	}
}
function setUEData(data) {
	var descirbeText1 = data.descirbeText1 == null ? '' : data.descirbeText1;
	var descirbeText2 = data.descirbeText2 == null ? '' : data.descirbeText2;
	var descirbeText3 = data.descirbeText3 == null ? '' : data.descirbeText3;
	var ueData = descirbeText1 + descirbeText2 + descirbeText3;
	if (ueData == '') {
		ueData = '产品详情编辑';
	}
	ue.ready(function() {
		ue.setContent(ueData);
	});
}
function fillItem(ele, val){
	if (ele.prop('nodeName').toLowerCase() == 'input') {
		if (ele.prop('type').toLowerCase() == 'text' || ele.prop('type').toLowerCase() == 'number') {
			ele.val(val);
		} else if (ele.prop('type').toLowerCase() == 'checkbox' || ele.prop('type').toLowerCase() == 'radio') {
			ele.attr('checked','checked');
		}
	} else if (ele.prop("nodeName").toLowerCase() == "select") {
		var opts = ele.find('option');
		for (var j = 1; j < opts.length; j++) {
			if ($(opts[j]).val() == val) {
				$(opts[j]).attr("selected", "selected");
			}
		}
	} else if (ele.prop("nodeName").toLowerCase() == "img") {
		ele.attr('src', val);
	} else if (ele.prop("nodeName").toLowerCase() == "ul") {
		
	}
}