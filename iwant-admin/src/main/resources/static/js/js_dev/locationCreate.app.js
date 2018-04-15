/**
 * Created by WXP22 on 2018/4/14.
 */

$(document).ready(
    function(){
        initUE();
        bindUploadFile('mainImageUpload','/iwant_admin/remote/fileupload','mainImage');
    }
);

function initUE() {
    ue.ready(function (obj) {
        var editorId = '#' + ue.container.id;
        $(editorId).css('max-width', 800);
    });
}


function bindUploadFile(id,url,displayId){
	console.log("绑定："+id+"的上传事件")
    $("#"+id).change(
        function(){
           var callback = function(param){
               $("#"+displayId).prop("src",param);              
           }
           fileUpload(id,url,callback);
        }
    );
}
