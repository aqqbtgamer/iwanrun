/**
 * 
 */
console.log("common.app.js加载")
//上传文件限制
const permittedUploadTypes = new Array("JPG","JPEG","PNG","BMP","BMP");
//分页栏除了当前页面之外最多展示位数  必须是奇数
const displayPagenationlLimit = 7 ;
//数据字典主页面
const dictionaryPageUrl ="./dictionarylist.html";
const emailReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
const mobileReg = /^1[3|4|5|8][0-9]\d{4,8}$/;
const passwordReg = /^([a-zA-Z]+[0-9]+[!@#$%^&*]+)|([a-zA-Z]+[!@#$%^&*]+[0-9]+)|([0-9]+[!@#$%^&*]+[a-zA-Z]+)|([0-9]+[a-zA-Z]+[!@#$%^&*]+)|([!@#$%^&*]+[a-zA-Z]+[0-9]+)|([!@#$%^&*]+[0-9]+[a-zA-Z]+)$/

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


    function initUE(){
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
               $("#"+id).attr("uploaded",true);
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
                        callback(result);
                    },
                    error:function(XMLHttpRequest ,error,exception){
                        console.log("提交到"+url+"失败,原因是: "+ error.toString());
                        alert("添加失败 服务端无法连接")
                    }
                }
            )
        });
    }
    
    function bindDataVerifySubmit(id,formId,fieldArray,url,callback){
        $("#"+id).bind('click',function(){
        	if(verifyRequired(formId)){
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
                             callback(result);
                         },
                         error:function(XMLHttpRequest ,error,exception){
                             console.log("提交到"+url+"失败,原因是: "+ error.toString());
                             alert("添加失败 服务端无法连接")
                         }
                     }
                 )
        	}           
        });
    }
    
    
    function bindDataModifySubmit(bindId,id,fieldArray,url,callback){
    	$("#"+bindId).bind("click",function(){
    		var formData = collectFormDatas(fieldArray);
    		formData.id = id ;
    		 $.ajax(
    	                {
    	                    url:url,
    	                    cahce:false,
    	                    data:formData,
    	                    dataType:"text",
    	                    type:"POST",
    	                    success:function(result){
    	                        console.log("提交到"+url+"成功");
    	                        if(callback != null){
    	                        	 callback(result);
    	                        }    	                       
    	                    },
    	                    error:function(XMLHttpRequest ,error,exception){
    	                        console.log("提交到"+url+"失败,原因是: "+ error.toString());
    	                        alert("更新失败 服务端无法连接")
    	                    }
    	                }
    	            )
    	});
    }
    
    function bindDataModifyVeifySubmit(bindId,formId,id,fieldArray,url,callback){
    	$("#"+bindId).bind("click",function(){
    		if(verifyRequired(formId)){
    			var formData = collectFormDatas(fieldArray);
        		formData.id = id ;
        		 $.ajax(
        	                {
        	                    url:url,
        	                    cahce:false,
        	                    data:formData,
        	                    dataType:"text",
        	                    type:"POST",
        	                    success:function(result){
        	                        console.log("提交到"+url+"成功");
        	                        if(callback != null){
        	                        	 callback(result);
        	                        }    	                       
        	                    },
        	                    error:function(XMLHttpRequest ,error,exception){
        	                        console.log("提交到"+url+"失败,原因是: "+ error.toString());
        	                        alert("更新失败 服务端无法连接")
        	                    }
        	                }
        	            )
    		}    		
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
            var collect = $("[name='"+name+"']").not("a");
            if(collect.length == 1){
                formdData[name] = fufilItem(collect);
            }else if(collect.length >1){
                var itemArray = new Array()
                collect.each(
                    function(){
                    	var val = fufilItem($(this))
                    	if( val!= null){
                    		 itemArray.push(val);
                    	}
                       
                    }
                )
                formdData[name] = itemArray ;
            }
        }
        if( typeof ue != "undefined"){
        	 formdData['_ue']= ue.getContent();
        }       
        return formdData;
    }
    

    function fufilItem(collect){
        if(collect.prop('nodeName').toLowerCase() == 'input'){
            if(collect.prop('type').toLowerCase() == 'text' || collect.prop('type').toLowerCase() == 'number'|| collect.prop('type').toLowerCase() == 'password' ){
                return  collect.val();
            }else if(collect.prop('type').toLowerCase() == 'checkbox' || collect.prop('type').toLowerCase() == 'radio'){
                if(collect.prop('checked')){
                   return collect.val();
                }else{
                	return null;
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
    
    function bindClickQuery(bindId,filedId,inputId,tableId,pageId,dataUrl,modifyUrl,deleteUrl,columns){
    	$("#"+bindId).click(
    	    function (){
    	        var requestObj = new Object();
    	        requestObj.pageIndex = 0 ;
                var fieldName = $("#"+filedId).find("option:selected").val();
                var obj = new Object();
                if(fieldName != ""){
                    obj[fieldName] = $("#"+inputId).val();
                }else{
                    obj['*'] = $("#"+inputId).val();
                }

                requestObj.obj = obj ;
                pageDataInit(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,requestObj.pageIndex,JSON.stringify(requestObj.obj));
            }
        )
    }
    
    function bindClickFieldQuery(bindId,filedArray,tableId,pageId,dataUrl,modifyUrl,deleteUrl,columns){
    	$("#"+bindId).click(
        	    function (){
        	        var requestObj = new Object();
        	        requestObj.pageIndex = 0 ;
        	        var obj = new Object();
                    for(var i =0 ; i<filedArray.length ; i++){
                    	obj[$("#"+filedArray[i]).attr("name")] = $("#"+filedArray[i]).val();
                    }
                    requestObj.obj = obj ;
                    pageDataInit(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,requestObj.pageIndex,JSON.stringify(requestObj.obj));
                }
            )
    }
    
  
    function pageDataInit(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,pageIndex,data){
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
                success:function(result){
                    console.log("提交到"+dataUrl+"成功");
                    insertTableData(result,tableId,pageId,columns,dataUrl,deleteUrl,modifyUrl,data);
                },
                error:function(XMLHttpRequest ,error,exception){
                    console.log("提交到"+dataUrl+"失败,原因是: "+ exception.toString());
                }
            }
        )
    }
    

    function insertTableData(result,tableId,pageId,columns,dataUrl,deleteUrl,modifyUrl,data){
        var ret = $.parseJSON(result);
        var tableData = ret.content;
        var table = $("#"+tableId).find("tbody");
        table.empty();
        if(tableData != null){
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
                    var columnName = columns[j];
                    var propertyNames = columnName.split(".");
                    var property = column ;
                    for(var k=0 ; k<propertyNames.length ; k++){
                    	if(property != null){
                    		property = property[propertyNames[k]];
                    	}                    	
                    }
                    td.text(property);
                    tr.append(td);
                }
                var tdOpration = $("<td></td>");
                var linkModify = $("<a></a>").text("修改");
                linkModify.attr("dbid",column.id)
                linkModify.bind("click",function(event){
                	var linkUrl = modifyUrl+$(event.target).attr("dbid");
                	window.location.href = linkUrl;
                });
                var linkDelete = $("<a></a>").text("删除");          
                linkDelete.attr("dbid",column.id);
                linkDelete.bind("click",function(event){            	
                	deleteSingle($(event.target).attr("dbid"),deleteUrl,modifyUrl,tableId,pageId,dataUrl,columns,data);
                });
                tdOpration.append(linkModify);
                tdOpration.append("/");
                tdOpration.append(linkDelete);
                tr.append(tdOpration);
                table.append(tr);
            }
        }        
        var pagedata = ret.pageInfo;
        //最多显示前三和后三 多了添加一个省略号
        var totalPage = pagedata.totalpage ;
        var pageDiv = $("#"+pageId);
        pageDiv.empty();
       //首页
        var first = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,0,"« 首页",false,data);
        var previous = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,Math.max(0,pagedata.currentPage-1),"« 前一页",false,data);
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
        			var pageLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,i,null,false,data);
        			pageDiv.append(pageLink);
        		}
        		var currentLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,current,null,false,data).addClass("current");
        		pageDiv.append(currentLink);
        		for(var i=0 ; i< displayPagenationlLimit - leftMargin -1 ; i++){
        			var pageLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,i+current+1,null,false,data);
        			pageDiv.append(pageLink);
        		}
        		var blankPageLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,"#","...",true);
        		pageDiv.append(blankPageLink);
        	}else if(leftMargin > (displayPagenationlLimit-1)/2 && righrMargin <= (displayPagenationlLimit-1)/2){
        		//省略号位于左半边
        		var blankPageLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,"#","...",true);
        		pageDiv.append(blankPageLink);
        		var leftStart = max-displayPagenationlLimit +1 ;
        		for(var i = leftStart; i < leftMargin ; i++){
        			var pageLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,i,null,false,data);
        			pageDiv.append(pageLink);
        		}
        		var currentLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,i).addClass("current");
        		pageDiv.append(currentLink);
        		for(var i=current+1 ; i < max+1 ; i++){
        			var pageLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,i,null,false,data);
        			pageDiv.append(pageLink);
        		}
        	}else{
    			//左右都有省略号
    			var blankPageLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,"#","...",true);
        		pageDiv.append(blankPageLink);
        		var leftStart = current - (displayPagenationlLimit-1)/2;
        		for(var i = leftStart; i < leftMargin ; i++){
        			var pageLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,i,null,false,data);
        			pageDiv.append(pageLink);
        		}
        		var currentLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,i).addClass("current");
        		pageDiv.append(currentLink);
        		var rightEnd = current + (displayPagenationlLimit-1)/2;
        		for(var i = current+1; i < rightEnd +1; i++){
        			var pageLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,i,null,false,data);
        			pageDiv.append(pageLink);
        		}
        		var blankPageLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,"#","...",true);
        		pageDiv.append(blankPageLink);
    		}
        	

        }else{
        	//小于等于限制页数直接展示
            for(var i = 0 ; i<totalPage ; i++){
                var pageLink = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,i,null,false,data);
                if(i == pagedata.currentPage){
                	pageLink.addClass("current");                	
                }
                pageDiv.append(pageLink);
            }
        }
        var next = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,Math.min(pagedata.totalpage-1,pagedata.currentPage+1),"后一页  »",null,false,data);
        var end = generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,pagedata.totalpage-1,"尾页  »",null,false,data);
        pageDiv.append(next);
        pageDiv.append(end);
        
    }
    
    
    function generatePageLink(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,pageIndex,content,blank,data){
    	var pageLink = $("<a></a>").attr("page",pageIndex);
    	if(content == null){
    		pageLink.addClass("number").text(pageIndex+1);
    	}else{
    		pageLink.text(content);
    	}
    	if(!blank){
    		pageLink.bind("click",function(){
            	pageDataInit(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,pageIndex,data);
            });
    	}        
        return pageLink;
    }
    
    function deleteSingle(id,deleteUrl,modifyUrl,tableId,pageId,dataUrl,columns,data){
    	var requestData = new Object();
    	requestData.id = id ;
    	var isDelete = confirm("确认删除吗?");
    	if(isDelete){
    		$.ajax(
        			{
        				url:deleteUrl,
        				cache:false,
        				data:requestData,
                        dataType:"text",
                        type:"POST",
                        success:function(result){
                        	console.log("提交到"+deleteUrl+"成功："+result);
                        	pageDataInit(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,0,data);
                        },
    	    			error:function(XMLHttpRequest ,error,exception){
    	                    console.log("提交到"+deleteUrl+"失败,原因是: "+ exception.toString());
    	                }
        			}
        	);
    	}
    	
    }
    
    
    function deleteAll(deleteUrl,tableId,pageId,dataUrl,columns,data){
    	var requestData = new Object();
    	var deleteArray = new Array();
    	$("#"+tableId).find("tbody input[type='checkbox']:checked").each(
    			function(){
    				deleteArray.push($(this).prop("id"));
    			}
    	)
    	requestData.id = deleteArray ;
    	var isDelete = confirm("确认删除吗?");
    	if(isDelete){
    		$.ajax(
        			{
        				url:deleteUrl,
        				cache:false,
        				data:requestData,
                        dataType:"text",
                        type:"POST",
                        success:function(result){
                        	console.log("提交到"+deleteUrl+"成功："+result);
                        	pageDataInit(tableId,pageId,dataUrl,deleteUrl,modifyUrl,columns,0,data);
                        },
    	    			error:function(XMLHttpRequest ,error,exception){
    	                    console.log("提交到"+deleteUrl+"失败,原因是: "+ exception.toString());
    	                }
        			}
        	);
    	}    	
    }
    
    
    function selectAll(tableId){
    	var selector = $("#"+tableId).find("thead input[type='checkbox'");
    	if(selector.prop("checked")){
    		$("#"+tableId).find("tbody input[type='checkbox'] ").prop("checked",true);
    	}else{
    		$("#"+tableId).find("tbody input[type='checkbox'] ").prop("checked",false);
    	}    	
    }
    
    function bindSelectAll(bindId,tableId){
    	$("#"+bindId).click(function(){
    		selectAll(tableId);
    	})
    }
    
    function bindDeleteAll(bindId,deleteUrl,tableId,pageId,dataUrl,columns,data){
    	$("#"+bindId).click(function(){
    		var confirmDelete = confirm("是否确认删除全部");
    		if(confirmDelete){
    			deleteAll(deleteUrl,tableId,pageId,dataUrl,columns,data);
    		}
    	})
    }
    
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
    
    function commonLoadForModify(request,loadUrl,mapping){
    	$.ajax(
    			{
    				url:loadUrl,
    				cache:false,
    				data:request,
                    dataType:"text",
                    type:"POST",
                    success:function(result){
                    	console.log("提交到"+loadUrl+"成功："+result);
                    	mapping(result);
                    },
	    			error:function(XMLHttpRequest ,error,exception){
	                    console.log("提交到"+loadUrl+"失败,原因是: "+ exception.toString());
	                }
    			}
    	);
    }
    
    function mappingTextItem(id,value){
    	$("#"+id).val(value);
    }
    
    function mappingSelectItem(id,value){
    	$("#"+id+" option").each(
    		function(){
    			if($(this).val() == value){
    				$(this).prop("selected",true)
    			}
    		}	
    	);
    }
    
    function mappingCheckItem(name,array){
    	$("input[name='"+name+"']").each(
    			function(){
    				for(var i = 0 ; i<array.length ; i++){
    					if($(this).val() == array[i]){
    						$(this).prop("checked",true);
    						break;
    					}
    				}
    			}
    	);
    }
    
    
    function mappingRadioItem(name,value){
    	$("input[name='"+name+"']").each(
    			function(){    			
    				if($(this).val() == value){
    					$(this).prop("checked",true);
    				}    			
    			}
    	);
    }
    
    function getDictionaryPages(url,callback){
    	var request = new Object();
    	$.ajax(
    			{
    				url:url,
    				cache:false,
    				data:request,
                    dataType:"text",
                    type:"POST",
                    success:function(result){
                    	console.log("提交到"+url+"成功："+result);
                    	if(callback != null){
                    		callback(result);
                    	}
                    },
	    			error:function(XMLHttpRequest ,error,exception){
	                    console.log("提交到"+url+"失败,原因是: "+ exception.toString());
	                }
    			}
    	);
    }
    
    function initDictionaryPage(id,result){
    	var ret = $.parseJSON(result);
    	var ul = $("#"+id);
    	for(var i = 0 ; i< ret.length ; i++){
    		var li = $("<li></li>");
    		var link = $("<a></a>").html(ret[i].desc).attr("name",ret[i].name);
    		li.append(link);
    		link.prop("href",dictionaryPageUrl+"?name="+ret[i].name);
    		ul.append(li);
    	}
    }
    
    function getDictinaryPageTabs(pageName,url,callback){
    	var request = new Object();
    	request.name = pageName;
    	$.ajax(
    			{
    				url:url,
    				cache:false,
    				data:request,
                    dataType:"text",
                    type:"POST",
                    success:function(result){
                    	console.log("提交到"+url+"成功："+result);
                    	if(callback != null){
                    		callback(result);
                    	}
                    },
	    			error:function(XMLHttpRequest ,error,exception){
	                    console.log("提交到"+url+"失败,原因是: "+ exception.toString());
	                }
    			}
    	);
    }
    
    function findDictionaryCode(url,dbId,name,callback){
    	var request = new Object();
    	request.name = name ;
    	request.used_field = dbId;
    	$.ajax(
    			{
    				url:url,
    				cache:false,
    				data:request,
                    dataType:"text",
                    type:"POST",
                    success:function(result){
                    	console.log("提交到"+url+"成功："+result);
                    	if(callback != null){
                    		if(typeof callback == "function"){
                    			callback(dbId,result);
                    		}else{
                    			callback.callback(dbId,result);
                    		}                    		
                    	}
                    },
	    			error:function(XMLHttpRequest ,error,exception){
	                    console.log("提交到"+url+"失败,原因是: "+ exception.toString());
	                }
    			}
    	);
    }
    
    function bindSelectRole(bindId,formId){
    	$("#"+bindId).bind("change",function(){
    		var roleId = $("#"+bindId).val();
    		$("#"+formId).find("tr").each(
    				function(){
    					var roleAttr = $(this).attr("role");
    					if(roleAttr != null){
    						if(roleAttr == roleId){
    							$(this).show();
    						}else{
    							$(this).hide();
    						}
    					}
    				}
    		);
    	})	
    }
    
    function verifyRequired(formId){
    	var result = true ;
    	$("#"+formId).find("tr:visible").each(
    		function(){
    			var id = $(this).find("td").children().attr("id");
    			if(id != null){
    				if($(this).find("th label span.requierd").length > 0){
    					if($("#"+id).val() == null || $("#"+id).val() == ""){
    						$("#"+id).addClass("verifyFailed");
    						result = false;
    					}else{
    						$("#"+id).removeClass("verifyFailed");
    					}        				
        			}
    				if($("#"+id).attr("verify") != null){
    					var val = $("#"+id).val() ;
    					if(val != null && val != ""){
    						if(!verifyFormat(val,$("#"+id).attr("verify"))){
    							$("#"+id).addClass("verifyFailed");
    							result = false;
    						}else{
    							$("#"+id).removeClass("verifyFailed");
    						}
    					}
    				}
				}    			
    		}	
    	);
    	return result ;
    }
    
    function verifyFormat(val,format){
    	if(format == "email"){
    		return verifyEmail(val);
    	}else if(format == "phone"){
    		return verifyPhone(val);
    	}else if(format == "password"){
    		return verifyPassword(val);
    	}
    }
    
    function verifyEmail(val){
    	return emailReg.test(val);
    }
    
    function verifyPhone(val){
    	return mobileReg.test(val);
    }
    
    function verifyPassword(val){
    	if(val.length >= 8 && val.length <= 16 ){
    		return passwordReg.test(val);
    	}else{
    		return false;
    	}
    	
    }
    
    
