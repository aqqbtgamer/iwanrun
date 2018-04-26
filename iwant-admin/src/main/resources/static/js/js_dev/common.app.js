/**
 * 
 */
console.log("common.app.js加载")
//上传文件限制
const permittedUploadTypes = new Array("JPG","JPEG","PNG","BMP","BMP");
//分页栏除了当前页面之外最多展示位数  必须是奇数
const displayPagenationlLimit = 7 ;



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
    
  
    function pageDataInit(tableId,pageId,dataUrl,columns,pageIndex,data){
    	var table = $("#"+tableId);
    	var requestData = new Object();
    	if(pageIndex == null){
    		requestData.pageIndex = 0 ;
    	}else{
    		requestData.pageIndex = pageIndex ;
    	}
    	if(data != null){
    		requestData.obj = data;
    	}
    	
    	$.ajax(
            {
                url:dataUrl,
                cache:false,
                data:requestData,
                dataType:"text",
                type:"POST",
                type:"POST",
                success:function(result){
                    console.log("提交到"+dataUrl+"成功");
                    insertTableData(result,tableId,pageId,columns,dataUrl);
                },
                error:function(XMLHttpRequest ,error,exception){
                    console.log("提交到"+dataUrl+"失败,原因是: "+ exception.toString());
                }
            }
        )
    }
    

    function insertTableData(result,tableId,pageId,columns,dataUrl){
        //console.log("获取后台信息:"+result);
        var ret = $.parseJSON(result);
        var tableData = ret.content;
        var table = $("#"+tableId).find("tbody");
        table.empty();
        for(var i=0; i<tableData.length ; i++ ){
            var column = tableData[i] ;
            var tr = $("<tr></tr>");
            if(i%2 == 0){
            	tr.addClass("odd");
            }else{
            	tr.addClass("even");
            }
            var tdCheck = $("<th></th>");
            var checkBox = $("<input>").prop("type","checkbox").attr("id",column.id);
            tdCheck.append(checkBox);
            tr.append(tdCheck);
            for(var j=0 ; j<columns.length ; j++){
                var td = $("<td></td>");
                td.text(column[columns[j]]);
                tr.append(td);
            }
            var tdOpration = $("<td></td>");
            var linkModify = $("<a></a>").text("修改");
            var linkDelete = $("<a></a>").text("删除");
            tdOpration.append(linkModify);
            tdOpration.append("/");
            tdOpration.append(linkDelete);
            tr.append(tdOpration);
            table.append(tr);
        }
        var pagedata = ret.pageInfo;
        //最多显示前三和后三 多了添加一个省略号
        var totalPage = pagedata.totalpage ;
        var pageDiv = $("#"+pageId);
        pageDiv.empty();
       //首页
        var first = generatePageLink(tableId,pageId,dataUrl,columns,0,"« 首页");
        var previous = generatePageLink(tableId,pageId,dataUrl,columns,Math.max(0,pagedata.currentPage-1),"« 前一页");
        pageDiv.append(first);
        pageDiv.append(previous);
        if(totalPage > displayPagenationlLimit){
        	//大于等于限制页数 显示
        	//1.计算cuurent page 到两端的距离 
        	var begin =  0 ;
        	var max = pagedata.totalpage-1 ;
        	var current = pagedata.currentPage ;
        	var leftMargin = current - begin ;
        	var righrMargin = max - current ;
        	if(leftMargin <= (displayPagenationlLimit-1)/2 && righrMargin > (displayPagenationlLimit-1)/2){
        		//省略号位于右半边
        		for(var i = 0 ; i< leftMargin ; i++){
        			var pageLink = generatePageLink(tableId,pageId,dataUrl,columns,i);
        			pageDiv.append(pageLink);
        		}
        		var currentLink = generatePageLink(tableId,pageId,dataUrl,columns,current).addClass("current");
        		pageDiv.append(currentLink);
        		for(var i=0 ; i< displayPagenationlLimit - leftMargin -1 ; i++){
        			var pageLink = generatePageLink(tableId,pageId,dataUrl,columns,i+current+1);
        			pageDiv.append(pageLink);
        		}
        		var blankPageLink = generatePageLink(tableId,pageId,dataUrl,columns,"#","...",true);
        		pageDiv.append(blankPageLink);
        	}else if(leftMargin > (displayPagenationlLimit-1)/2 && righrMargin <= (displayPagenationlLimit-1)/2){
        		//省略号位于左半边
        		var blankPageLink = generatePageLink(tableId,pageId,dataUrl,columns,"#","...",true);
        		pageDiv.append(blankPageLink);
        		var leftStart = max-displayPagenationlLimit +1 ;
        		for(var i = leftStart; i < leftMargin ; i++){
        			var pageLink = generatePageLink(tableId,pageId,dataUrl,columns,i);
        			pageDiv.append(pageLink);
        		}
        		var currentLink = generatePageLink(tableId,pageId,dataUrl,columns,i).addClass("current");
        		pageDiv.append(currentLink);
        		for(var i=current+1 ; i < max+1 ; i++){
        			var pageLink = generatePageLink(tableId,pageId,dataUrl,columns,i);
        			pageDiv.append(pageLink);
        		}
        	}else{
    			//左右都有省略号
    			var blankPageLink = generatePageLink(tableId,pageId,dataUrl,columns,"#","...",true);
        		pageDiv.append(blankPageLink);
        		var leftStart = current - (displayPagenationlLimit-1)/2;
        		for(var i = leftStart; i < leftMargin ; i++){
        			var pageLink = generatePageLink(tableId,pageId,dataUrl,columns,i);
        			pageDiv.append(pageLink);
        		}
        		var currentLink = generatePageLink(tableId,pageId,dataUrl,columns,i).addClass("current");
        		pageDiv.append(currentLink);
        		var rightEnd = current + (displayPagenationlLimit-1)/2;
        		for(var i = current+1; i < rightEnd +1; i++){
        			var pageLink = generatePageLink(tableId,pageId,dataUrl,columns,i);
        			pageDiv.append(pageLink);
        		}
        		var blankPageLink = generatePageLink(tableId,pageId,dataUrl,columns,"#","...",true);
        		pageDiv.append(blankPageLink);
    		}
        	

        }else{
        	//小于等于限制页数直接展示
            for(var i = 0 ; i<totalPage ; i++){
                var pageLink = generatePageLink(tableId,pageId,dataUrl,columns,i);
                if(i == pagedata.currentPage){
                	pageLink.addClass("current");                	
                }
                pageDiv.append(pageLink);
            }
        }
        var next = generatePageLink(tableId,pageId,dataUrl,columns,Math.min(pagedata.totalpage-1,pagedata.currentPage+1),"后一页  »");
        var end = generatePageLink(tableId,pageId,dataUrl,columns,pagedata.totalpage-1,"尾页  »");
        pageDiv.append(next);
        pageDiv.append(end);
        
    }
    
    
    function generatePageLink(tableId,pageId,dataUrl,columns,pageIndex,content,blank){
    	var pageLink = $("<a></a>").attr("page",pageIndex);
    	if(content == null){
    		pageLink.addClass("number").text(pageIndex+1);
    	}else{
    		pageLink.text(content);
    	}
    	if(!blank){
    		pageLink.bind("click",function(){
            	pageDataInit(tableId,pageId,dataUrl,columns,pageIndex);
            });
    	}        
        return pageLink;
    }
