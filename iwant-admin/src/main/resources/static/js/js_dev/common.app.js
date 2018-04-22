/**
 * 
 */
console.log("common.js加载")
var permittedUploadTypes = new Array("JPG","JPEG","PNG","BMP","BMP")

window.alert = function(e){
	alert("准备开始alert啦");
	alert(e);
}

function fileUpload(contentId,url,callback) {
    var formData = new FormData();
    var uploadFile = $("#" + contentId).prop("files")[0];
    if (typeof uploadFile == "undefined" || uploadFile.size <= 0) {
        alert("文件错误或者未选择");
        return;
    } else {
        if (!checkUploadedFile(contentId)) {
            alert("文件类型错误");
            return;
        }
        formData.append('fileUpload', uploadFile);
        var data = formData;
        $.ajax(
            {
                url: url,
                data: data,
                type: "post",
                dataType: "text",
                cache: false,
                processData: false,//用于对data参数进行序列化处理 这里必须false
                contentType: false, //必须
                success: function (result) {
                    callback(result);
                }

            }
        )
    }
}

    function checkUploadedFile(contentId){
	    var checkValid = false ;
        var filePath = $("#"+contentId).val();
        var extIndex = filePath.lastIndexOf(".");
        var ext = filePath.substring(extIndex+1,filePath.length).toUpperCase();
        for(var i =0 ; i<permittedUploadTypes.length ; i++){
            if(permittedUploadTypes[i] == ext){
                checkValid = true ;
            }
        }
        return checkValid;
    }


    function initUE() {
        ue.ready(function (obj) {
            var editorId = '#' + ue.container.id;
            $(editorId).css('max-width', 800);
        });
    }
   

    function bindUploadFile(id,url,displayId,callback){
    	console.log("绑定："+id+"的上传事件")
        $("#"+id).change(
            function(){
            	var call = function(param){
            		callback(displayId,param);
            	}
               fileUpload(id,url,call);
            }
        );
    }

    function bindDataSubmit(id,fieldArray,url,callback){
        $("#"+id).bind('click',function(){
            var formData = collectFormDatas(fieldArray);
            $.ajax(
                {
                    url:url,
                    cahce:false,
                    data:formData,
                    dataType:"text",
                    type:"POST",
                    success:function(result){
                        console.log("提交到"+url+"成功");
                        callback();
                    },
                    error:function(XMLHttpRequest ,error,exception){
                        console.log("提交到"+url+"失败,原因是: "+ error.toString());
                        alert("上传失败 服务端无法连接")
                    }
                }
            )
        });
    }

    function singleDisplay(displayId,param){
    	 $("#"+displayId).prop("src",param);  
    }

    function mutipleDisplay(displayId,param){
    	var imgManager = $('#'+displayId);
    	var newImgLi = $("<li></li>");
    	var newImg = $("<img />").prop('src',param);
    	var newCheckBox = $("<input />").prop('type','checkbox');
    	var newDeleteLink = $("<a></a>").html('删除');
    	newImgLi.append(newImg);
    	newImgLi.append(newCheckBox);
    	newImgLi.append(newDeleteLink);
    	imgManager.append(newImgLi);
    	newDeleteLink.bind("click",function(){
    		newImgLi.remove();
    	})
    }

    function collectFormDatas(fieldArray) {
        var formdData = new Object();
        for(var i = 0; i<fieldArray.length ; i++){
            var name = fieldArray[i];
            var collect = $("[name='"+name+"']");
            if(collect.length == 1){
                formdData[name] = fufilItem(collect);
            }else if(collect.length >1){
                var itemArray = new Array()
                collect.each(
                    function(){
                        itemArray.push(fufilItem($(this)));
                    }
                )
                formdData[name] = itemArray ;
            }
        }
        formdData['_ue']= ue.getContent();
        return formdData;
    }
    

    function fufilItem(collect){
        if(collect.prop('nodeName').toLowerCase() == 'input'){
            if(collect.prop('type').toLowerCase() == 'text' || collect.prop('type').toLowerCase() == 'number' ){
                return  collect.val();
            }else if(collect.prop('type').toLowerCase() == 'checkbox' || collect.prop('type').toLowerCase() == 'radio'){
                if(collect.prop('checked')){
                   return collect.val();
                }
            }
        }else if(collect.prop("nodeName").toLowerCase() == "select"){
            return collect.find('option:selected').val();
        }else if(collect.prop("nodeName").toLowerCase() == "img"){
            return collect.prop("src");
        }else if(collect.prop("nodeName").toLowerCase() == "ul"){
            var imgArray = new Array();
            collect.find("img").each(
                function () {
                    imgArray.push($(this).prop('src'));
                }
            )
            return imgArray ;
        }
    }
    
    
    function bindSeclectAll(bindId){
    	console.log(bindId+"绑定bindSeclectAll方法");
    	$("#"+bindId).bind("change",function(){
    		if(($("#"+bindId).is(":checked"))){
    			$("#"+bindId).parent().parent().find("ul").find("input[type='checkbox']").prop("checked",true);
    		}else{
    			$("#"+bindId).parent().parent().find("ul").find("input[type='checkbox']").prop("checked",false);
    		}
    	})
    	
    }


    function bindDeleteSelected(bindId){
    	console.log(bindId+"绑定bindDeleteSelected方法");
    	$("#"+bindId).bind("click",function(){
    		 var checkedBoxList = $("#"+bindId).parent().parent().find("ul").find("li");
    		 var confirmDelete = confirm("是否确认删除全部?");
    		 if(confirmDelete){
    	            checkedBoxList.each(
    	                function(){
    	                    if($(this).find("input[type='checkbox']").is(":checked")){
    	                        $(this).remove();
    	                    }
    	                }
    	            )
    	        }
    	})
       
        
       

    }
