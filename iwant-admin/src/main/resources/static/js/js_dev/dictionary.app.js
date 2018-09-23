/**
*查询数据字典专用js
*/
const dictionaryAssign = "/iwant_admin/dictionary/findByAssign";

console.log("字典js加载");
function dictionaryItemsInit(dictionaryName,dictionaryUrl){
	var allDicElements = $("[used_field]");
	for(var i = 0 ; i<allDicElements.length ; i++){
		var item = allDicElements.eq(i);
		var used_field = item.attr("used_field");
		var used_type = item.attr("used_type");
		var used_name = item.attr("used_name");
		var used_item = item.attr("used_item");
		var dictionary_name = item.attr("dictionary_name");
		var callbackObj = new Object();
		callbackObj.item = item;
		callbackObj.used_field = used_field;
		callbackObj.used_type = used_type ;
		callbackObj.used_name = used_name ;
		callbackObj.used_item = used_item ;
		callbackObj.callback = function(dbId,result){
			itemInit(result,this.item,this.used_type,this.used_field,this.used_name,this.used_item);
		};
		if(dictionary_name == null){
			findDictionaryCode(dictionaryUrl,used_field,dictionaryName,callbackObj);
		}else{
			findDictionaryCode(dictionaryUrl,used_field,dictionary_name,callbackObj);
		}
		
	}
}


function dictionaryItemFilter(filterSelect,filterInput,type,dictionaryUrl){
	var select = $("#"+filterSelect);
	select.bind("change",function(){
		var item = select.find("option:selected");
		var dictionaryName = item.attr("dicName");
		var dicField = item.attr("dicField");
		var callback = function(dbId,result){
			 $("#"+filterInput).remove();
			if(type == "select"){				
				var addedSelected = $("<select>").attr("id",filterInput).attr("name",filterInput);
				select.after(addedSelected);
				var ret = $.parseJSON(result);				
				for(var i = 0 ; i< ret.length ; i++){					
					var option = $("<option>").val(ret[i].code).text(ret[i].value);
					addedSelected.append(option);
				}		
			}				
		}
		if(dicField != null && dictionaryName != null){
			findDictionaryCode(dictionaryUrl,dicField,dictionaryName,callback);
		}else{
			$("#"+filterInput).remove();
			var addedInput = $("<input>").attr("type","text").attr("id",filterInput).attr("name",filterInput);	
			select.after(addedInput);
		}
	})
}


function itemInit(result,item,used_type,used_field,used_name,used_item){
	var ret = $.parseJSON(result);
	if(ret.length > 0){
		for(var i = 0; i< ret.length ; i++){
			var id = used_type+"_"+used_field+"_"+i
			if(used_item == "input"){
				var label = $("<label>").attr("for",id).text(ret[i].value);
				var input = $("<input>").attr("type",used_type).attr("id",id).attr("name",used_name).attr("dbId",ret[i].id).val(ret[i].code);
				item.append(label);
				item.append(input);
			}else if(used_item == "select"){
				var option = $("<option>").attr("name",used_name).val(ret[i].code).attr("dbId",ret[i].id).text(ret[i].value);
				item.append(option);
			}			
		}
	}
}

function bindAssignToDictionary(bindId,assignId){
	$("#"+assignId).bind("change",function(event){
		var request = new Object();
		request.assignTo = $(event.target).find("option:selected").attr("dbId");
		$.ajax(
    			{
    				url:dictionaryAssign,
    				cache:false,
    				data:request,
                    dataType:"text",
                    type:"POST",
                    success:function(result){
                    	console.log("提交到"+dictionaryAssign+"成功："+result);
                    	$("#"+bindId).empty();
                    	var ret = $.parseJSON(result);
                    	for(var i = 0; i< ret.length ; i++){
                    		var option = $("<option>").val(ret[i].code).attr("dbId",ret[i].id).text(ret[i].value);
                    		$("#"+bindId).append(option);
                    	}
                    	$("#"+bindId).change();
                    },
	    			error:function(XMLHttpRequest ,error,exception){
	                    console.log("提交到"+dictionaryAssign+"失败,原因是: "+ exception.toString());
	                }
    			}
    	);
	})
}

