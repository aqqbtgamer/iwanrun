var $http = {
	post : function(url, data, callback){
		$.ajax({
			url : url,
			type : 'POST',
			data : data,
			contentType : 'application/json',
			dataType : 'json',
			success : callback,
			error : callback
		});
	}
};