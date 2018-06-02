/**
 * Created by WXP22 on 2018/4/13.
 */
const dataInitUrl = '/iwant_admin/cases/findAll';
const dataQueryUrl = '/iwant_admin/cases/queryAll';
const deleteUrl = '/iwant_admin/cases/delete';
const modifyUrl = '/iwant_admin/createCase.html?isModify=true&id=';
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const columns = new Array(
    "name",
    "trafficInfo",
    "foodInfo",
    "hotelInfo",
    "designDuringCode"
);

$(document).ready(
    function(){
        wrapCustomerItems();
        getDictionaryPages(dictionaryUrl,dicionaryCallBack);
    }
);


function wrapCustomerItems(){
    console.log("开始处理自定义组件功能");
    wrapCustomerButtons();
    pageDataInit("casesTable","pagination",dataInitUrl,deleteUrl,modifyUrl,columns);
    bindClickQuery("queryCondtion","queryField","fieldInput","casesTable","pagination",dataQueryUrl,modifyUrl,deleteUrl,columns);
    bindSelectAll("selectAll","casesTable");
    bindDeleteAll("deleteAll",deleteUrl,"casesTable",0,dataInitUrl,columns,null);
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

function dicionaryCallBack(result){
	initDictionaryPage("dictionarys",result);
}


