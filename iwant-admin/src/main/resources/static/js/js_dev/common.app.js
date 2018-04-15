/**
 * 
 */

var permittedUploadTypes = new Array("JPG","JPEG","PNG","BMP","BMP")

function fileUpload(contentId,url,callback){
	var formData = new FormData();
	var uploadFile = $("#"+contentId).prop("files")[0];
	if(typeof uploadFile == "undefined" || uploadFile.size <= 0){
	    alert("文件错误或者未选择");
	    return;
    }else{
	    if(!checkUploadedFile(contentId)){
	        alert("文件类型错误");
	        return;
        }
        formData.append('fileUpload',uploadFile);
        var data = formData ;
        $.ajax(
            {
                url:url,
                data:data,
                type:"post",
                dataType:"text",
                cache:false,
                processData: false,//用于对data参数进行序列化处理 这里必须false
                contentType: false, //必须
                success:function(result){
                    callback(result);
                }

            }
        )
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

}