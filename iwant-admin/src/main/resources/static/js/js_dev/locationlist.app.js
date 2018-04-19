/**
 * Created by WXP22 on 2018/4/13.
 */
$(document).ready(
    function(){
        wrapCustomerItems();
    }
);


function wrapCustomerItems(){
    console.log("开始处理自定义组件功能");
    wrapCustomerButtons();
}

function wrapCustomerButtons(){
    $("button").unbind();
    console.log("开始处理自定义byutton功能");
    $("button[method='href']").each(
        function(index,element){
            $(element).click(
                function(){
                    window.location.href=$(element).attr('to');
                }
            );
        }
    )
}
