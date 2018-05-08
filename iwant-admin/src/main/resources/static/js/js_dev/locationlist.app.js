/**
 * Created by WXP22 on 2018/4/13.
 */
const dataInitUrl = '/iwant_admin/location/findAll';
const dataQueryUrl = '/iwant_admin/location/queryAll';
const deleteUrl = '/iwant_admin/location/delete';
const modifyUrl = '/iwant_admin/createLocation.html?isModify=true&id=';
const columns = new Array(
    "name",
    "activityCityCode",
    "groupNumberLimitCode",
    "location",
    "simulatePriceCode"
);

$(document).ready(
    function(){
        wrapCustomerItems();
    }
);


function wrapCustomerItems(){
    console.log("开始处理自定义组件功能");
    wrapCustomerButtons();
    pageDataInit("locationTable","pagination",dataInitUrl,deleteUrl,modifyUrl,columns);
    bindClickQuery("queryCondtion","queryField","fieldInput","locationTable","pagination",dataQueryUrl,modifyUrl,columns);
    bindSelectAll("selectAll","locationTable");
    bindDeleteAll("deleteAll",deleteUrl,"locationTable",0,dataInitUrl,columns,null);
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


