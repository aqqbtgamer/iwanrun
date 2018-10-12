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
	        bindUploadFile('uploadedLocationImages',uploadServer,'imgManage',mutipleDisplayIndex);
	        var requestObj = new Object();
	        commonLoadForModify(requestObj,dataGetUrl,mappingData);
	        bindModifySubmit("submitForm");
		}
		
)

 function mutipleDisplayIndex(displayId,param1,param2){
    	var imgManager = $('#'+displayId);
    	var newImgLi = $("<li></li>");
    	var newImg = $("<img />").prop('src',param1);
    	var newCheckBox = $("<input />").prop('type','checkbox');
    	var newDeleteLink = $("<a style='text-decoration:none;'></a>").html('删除');
    	var fontUrl = $("<a style='text-decoration:none;'></a>").html('链接  ');
    	var newUrl = $("<input type='text' name='imgUrl' style='width:178px'/>").prop('value',param2);
    	newImgLi.append(newImg);
    	newImgLi.append(fontUrl);
    	newImgLi.append(newUrl);
    	newImgLi.append(newCheckBox);
    	newImgLi.append(newDeleteLink);
    	imgManager.append(newImgLi);
    	newDeleteLink.bind("click",function(){
    		newImgLi.remove();
    	})
}

function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}

function mappingData(result){
	var ret = $.parseJSON(result);
	var imgs = $.parseJSON(ret.list);
	for(var i = 0; i<imgs.length ; i++){
		mutipleDisplayIndex('imgManage',imgs[i].filePath,imgs[i].imgUrl);
	}		
}

function bindModifySubmit(bindId){
	$("#"+bindId).click(
			function(){
				var imgArray = new Array();
				$("#imgManage li").each(
						function(){
							var castPositon = new Object();
							var img = $(this).find("img");
							var imgUrl = $(this).find("input[name='imgUrl']");
							castPositon.filePath = $(img).attr("src");
							castPositon.fileName = filterNameFromPath(castPositon.filePath);
							castPositon.pagePath = "index";
							castPositon.imgUrl =$(imgUrl).val();
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
function selectCheckBox(){
	if($("#checkAll").prop("checked")){
		$("#imgManage li input[type='checkbox']").each(
				function(){
					$(this).prop("checked",true);
		});
	}else{
		$("#imgManage li input[type='checkbox']").each(
				function(){
					$(this).prop("checked",false);
		});
	}
}
function deleteClick(){
	$("#imgManage li").each(
			function(){
				var deleteCheckBoxSelect = $(this).find("input[type='checkbox']").prop("checked");
				if(deleteCheckBoxSelect){
					$(this).remove();
				}
	});
	
	
}
