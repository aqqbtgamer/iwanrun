var pathnames = location.pathname.split('/');
var baseUrl = location.origin + "/" + pathnames[1] + "/";
var requestUrl = {
    //Release
    queryProdutionByCondition: baseUrl + 'production/queryProdutionByCondition',
    querylocationByCondition: baseUrl + 'location/querylocationByCondition',
    queryCaseByCondition: baseUrl + 'case/queryCaseByCondition',
    trade_status: baseUrl + 'trade_status/query',
    webSiteCooperativeLogo: baseUrl + 'webSiteCooperativeLogo/query',
    websiteNews: baseUrl + 'websiteNews/query',
    castposition: baseUrl + 'castposition/findAll',
    produtionSearchList: baseUrl + 'production/produtionSearchList',
    locationSearchList: baseUrl + 'location/locationSearchList',
    caseSearchList: baseUrl + 'case/caseSearchList',
    login: baseUrl + 'purchaserAccount/login',
    register: baseUrl + 'purchaserAccount/register',
    logout: baseUrl + 'purchaserAccount/logout',
    findMixedByLoginId: baseUrl + 'purchaserAccount/findMixedByLoginId',
    verify: baseUrl + 'token/verify',
    getSMSCode: baseUrl + 'smsCode/getSMSCode',
    getProductionDetailsById: baseUrl + 'product/getDetailsById',
    getCaseDetailsById: baseUrl + 'case/getDetailsById',
    getLocationDetailsById: baseUrl + 'location/getDetailsById',
    fileupload: baseUrl + 'remote/fileupload',
    addAndModifyInfo:baseUrl + 'purchaserAccount/addAndModifyInfo',
    activityTypeList:baseUrl + 'dictionary/queryListByField',
    companyTypeList:baseUrl + 'dictionary/queryListByField',
    groupNumberList:baseUrl + 'dictionary/queryListByField',
    durationList:baseUrl + 'dictionary/queryListByField',
    provinceList:baseUrl + 'dictionary/queryListByField',
    orderSubmit:baseUrl + 'orders/submit',
    getOrderListByLoginId:baseUrl + 'orders/getOrderListByLoginId',
    favourite:baseUrl + 'favourite/query',

    //DEV
    //queryProdutionByCondition: 'Json/queryProdutionByCondition.json',
    //querylocationByCondition: 'Json/querylocationByCondition.json',
    //queryCaseByCondition: 'Json/queryCaseByCondition.json',
   // trade_status: 'Json/trade_status.json',
    //webSiteCooperativeLogo: 'Json/webSiteCooperativeLogo.json',
    //websiteNews: 'Json/websiteNews.json',
    //castposition: 'Json/castposition.json',
    //produtionSearchList: 'Json/produtionSearchList.json',
    //locationSearchList: 'Json/locationSearchList.json',
    //caseSearchList: 'Json/caseSearchList.json',
    //login: 'Json/login.json',
    //register: 'Json/register.json',
    //logout: 'Json/logout.json',
    //findMixedByLoginId: 'Json/findMixedByLoginId.json',
   // verify: 'Json/verify.json',
   // getSMSCode: 'Json/getSMSCode.json',
    //getProductionDetailsById: 'Json/getProductionDetailsById.json',
   // getLocationDetailsById: 'Json/getLocationDetailsById.json',
    //getCaseDetailsById: 'Json/getCaseDetailsById.json',
    //fileupload: 'Json/fileupload.json',
    //addAndModifyInfo: 'Json/addAndModifyInfo.json',
    //activityTypeList: 'Json/activityTypeList.json',
   // companyTypeList: 'Json/companyTypeList.json',
   // groupNumberList: 'Json/groupNumberList.json',
   // durationList: 'Json/durationList.json',
    //provinceList: 'Json/provinceList.json',
    //orderSubmit: 'Json/orderSubmit.json',
    //getOrderListByLoginId: 'Json/getOrderListByLoginId.json',
   // favourite: 'Json/favourite.json',
};

//dev
//axios.post = axios.get;

var validate = {
    isMobile: function (mobile) {
        if (!mobile) {
            return '请输入手机号';
        }
        var regex = new RegExp('[1][3578]\\d{9}', 'gim');
        var is = regex.test(mobile);
        if (!is) {
            return '手机号无效，请重新输入';
        }
        return null;
    },
    validatePwd: function (password) {
        if (!password) {
            return "请输入密码";
        }

        var length = password.length;
        if (length < 8 || length > 16) {
            return "密码长度必须大于等于8位，小于等于16位";
        }

        //	var regex = new RegExp('(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}', 'g');
        //	var regex = new RegExp('^([a-zA-Z]+.*[0-9]+.*[!@#$%^&*]+)|([a-zA-Z]+.*[!@#$%^&*]+.*[0-9]+)|([0-9]+.*[!@#$%^&*]+.*[a-zA-Z]+)|([0-9]+.*[a-zA-Z]+.*[!@#$%^&*]+)|([!@#$%^&*]+.*[a-zA-Z]+.*[0-9]+)|([!@#$%^&*]+.*[0-9]+.*[a-zA-Z]+)$', 'g');
        var regex = new RegExp('^([a-zA-Z]+.*[0-9]+.*[~`@#$%^&*\\-_=+|\?/()<>\\[\\]{}\",.;\'!]+)' +
            '|([a-zA-Z]+.*[~`@#$%^&*\\-_=+|\?/()<>\\[\\]{}\",.;\'!]+.*[0-9]+)' +
            '|([0-9]+.*[~`@#$%^&*\\-_=+|\?/()<>\\[\\]{}\",.;\'!]+.*[a-zA-Z]+)' +
            '|([0-9]+.*[a-zA-Z]+.*[~`@#$%^&*\\-_=+|\?/()<>\\[\\]{}\",.;\'!]+)' +
            '|([~`@#$%^&*\\-_=+|\?/()<>\\[\\]{}\",.;\'!]+.*[a-zA-Z]+.*[0-9]+)' +
            '|([~`@#$%^&*\\-_=+|\?/()<>\\[\\]{}\",.;\'!]+.*[0-9]+.*[a-zA-Z]+)$', 'g');
        var correct = regex.test(password);
        if (!correct) {
            return '密码格式不正确，请重新输入';
        }
    },
    validateSMScode: function (smsCode) {
        if (!smsCode) {
            return '请输入短信验证码';
        }
        var regex = new RegExp('\\d{6}', 'g');
        var correct = regex.test(smsCode);
        if (!correct) {
            return '短信验证码格式不正确，请重新输入';
        }
    }
};

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

function setCurrentCity(callback) {
    city = jQuery.cookie('currentcity');
    if (!city) {
        //获取当前位置信息
        city = '上海'// TODO
    }
    if (typeof (callback) === 'function') {
        callback(city);
    }
}


///查询条件
var queryListByField = {
    activityTypeList: {
        url: requestUrl.activityTypeList,
        param: { "name": "common", "used_field": 9, "field": "activityTypeList" }
    },
    companyTypeList: {
        url: requestUrl.companyTypeList,
        param: { "name": "common", "used_field": 24, "field": "companyTypeList" }
    },
    groupNumberList: {
        url: requestUrl.groupNumberList,
        param: { "name": "common", "used_field": 22, "field": "groupNumberList" }
    },
    durationList: {
        url: requestUrl.durationList,
        param: { "name": "common", "used_field": 23, "field": "durationList" }
    },
    provinceList: {
        url: requestUrl.provinceList,
        param: { "name": "common", "used_field": 6, "field": "provinceList" }
    }
};