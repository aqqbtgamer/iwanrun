/**
 * Created by WXP22 on 2018/4/13.
 */
const dataInitUrl = '/iwant_admin/location/findAll';
const dataQueryUrl = '/iwant_admin/location/queryAll';
const deleteUrl = '/iwant_admin/location/delete';
const dictionaryUrl = "/iwant_admin/dictionary/getPages";
const dictionaryCodeUrl = "/iwant_admin/dictionary/findByCode";
const modifyUrl = '/iwant_admin/createLocation.html?isModify=true&id=';
const dictionaryName = 'location';
const columns = new Array(
    "name",
    "activeTypeCodeDesc",
    "groupNumberLimitCodeDesc",
    "location",
    "simulatePriceCodeDesc"
);

$(document).ready(
    function(){
    	loadCommonTabs();
    	getDictionaryPages(dictionaryUrl,dicionaryCallBack);
        wrapCustomerItems();
    }
);


function wrapCustomerItems(){
    console.log("开始处理自定义组件功能");
    wrapCustomerButtons();
    pageDataInit("locationTable","pagination",dataInitUrl,deleteUrl,modifyUrl,columns);
    bindClickQuery("queryCondtion","queryField","fieldInput","locationTable","pagination",dataQueryUrl,modifyUrl,deleteUrl,columns);
    bindSelectAll("selectAll","locationTable");
    bindDeleteAll("deleteAll",deleteUrl,"locationTable",0,dataInitUrl,columns,null);
    dictionaryItemFilter("queryField","fieldInput","select",dictionaryCodeUrl);
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


