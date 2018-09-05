/**
 * 首页轮播图js
 */
const uploadServer = '/iwant_admin/remote/fileupload';
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dataGetUrl =  "/iwant_admin/castposition/findAll";
const setDataUrl = "/iwant_admin/castposition/addAll";
$(document).ready(
		function(){
			loadCommonTabs();
	        getDictionaryPages(dictionaryUrl,dicionaryCallBack);
	        bindUploadFile('uploadedLocationImages',uploadServer,'imgManage',mutipleDisplay);
	        var requestObj = new Object();
	        commonLoadForModify(requestObj,dataGetUrl,mappingData);
	        bindModifySubmit("submitForm");
		}
		
)



function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}

function mappingData(result){
	var ret = $.parseJSON(result);
	var imgs = $.parseJSON(ret.list);
	for(var i = 0; i<imgs.length ; i++){
		mutipleDisplay('imgManage',imgs[i].filePath);
	}		
}

function bindModifySubmit(bindId){
	$("#"+bindId).click(
			function(){
				var imgArray = new Array();
				$("#imgManage li img").each(
						function(){
							var castPositon = new Object();
							castPositon.filePath = $(this).attr("src");
							castPositon.fileName = filterNameFromPath(castPositon.filePath);
							castPositon.pagePath = "index";
							imgArray.push(castPositon);
						}
				);
				var requestJSON = JSON.stringify(imgArray);
				$.ajax(
		    			{
		    				url:setDataUrl,
		    				cache:false,
		    				data:{"requestJson":requestJSON},
		                    dataType:"text",
		                    type:"POST",
		                    success:function(result){
		                    	console.log("提交到"+setDataUrl+"成功："+result);
		                    	window.location.reload();
		                    },
			    			error:function(XMLHttpRequest ,error,exception){
			                    console.log("提交到"+setDataUrl+"失败,原因是: "+ exception.toString());
			                }
		    			}
		    	);
			}
	);
}

