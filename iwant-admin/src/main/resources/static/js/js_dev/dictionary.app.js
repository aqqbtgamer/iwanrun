/**
*查询数据字典专用js
*/

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

function itemInit(result,item,used_type,used_field,used_name,used_item){
	var ret = $.parseJSON(result);
	if(ret.length > 0){
		for(var i = 0; i< ret.length ; i++){
			var id = used_type+"_"+used_field+"_"+i
			if(used_item == "input"){
				var label = $("<label>").attr("for",id).text(ret[i].value);
				var input = $("<input>").attr("type",used_type).attr("id",id).attr("name",used_name).val(ret[i].code);
				item.append(label);
				item.append(input);
			}else if(used_item == "select"){
				var option = $("<option>").attr("name",used_name).val(ret[i].code).text(ret[i].value);
				item.append(option);
			}			
		}
	}
}

