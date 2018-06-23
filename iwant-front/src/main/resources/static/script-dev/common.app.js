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

var baseUrl = 'http://localhost:8088/iwantrun/';

jQuery(document).ready(function(){
	if(jQuery.cookie('accessToken')){
		//检查token
		//verifyToken();
	}
});

function verifyToken(){
	$http.post(baseUrl+'token/verify',{},verifyTokenBack);
}

function verifyTokenBack(data){
	if(data && data.token == 'success'){
		console.log('登录状态为已登录');
		loginSuccess();
	}else{
		console.log('还未登录');
	}
}