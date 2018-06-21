var $http = {
	post : function(url, data, callback, dataType){
		
		if(!dataType){
			dataType = 'json';
		}
		
		$.ajax({
			url : url,
			type : 'POST',
			data : data,
			contentType : 'application/json',
			dataType : dataType,
			success : callback,
			error : callback
		});
	}
};