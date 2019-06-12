
function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}

var pathnames = location.pathname.split('/');
var baseUrl = location.origin + "/" + pathnames[1] + "/";

if (IsPC()) {
    window.location.href = baseUrl;
}

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
    getProductionDetailsById: baseUrl + 'production/getDetailsById',
    getCaseDetailsById: baseUrl + 'case/getDetailsById',
    getLocationDetailsById: baseUrl + 'location/getDetailsById',
    fileupload: baseUrl + 'remote/fileupload',
    addAndModifyInfo: baseUrl + 'purchaserAccount/addAndModifyInfo',
    activityTypeList: baseUrl + 'dictionary/queryListByField',
    companyTypeList: baseUrl + 'dictionary/queryListByField',
    groupNumberList: baseUrl + 'dictionary/queryListByField',
    durationList: baseUrl + 'dictionary/queryListByField',
    provinceList: baseUrl + 'dictionary/queryListByField',
    orderSubmit: baseUrl + 'orders/submit',
    getOrderListByLoginId: baseUrl + 'orders/getOrderListByLoginId',
	getOrderByID:baseUrl + 'orders/get',
    favouriteQuery: baseUrl + 'favourite/query',
    favouriteAdd: baseUrl + 'favourite/add',
    favouriteDelete: baseUrl + 'favourite/delete',
    modifyPwd: baseUrl + 'purchaserAccount/modifyPwd',
    getWeixingConfig: baseUrl + 'weixing/getWeixingConfig',
	casesmobileQuery:baseUrl+'case/mobileQuery',
    locationmobileQuery:baseUrl+'location/mobileQuery',
    productionInfomobileQuery:baseUrl+'production/mobileQuery',
	mobileWeixinLoginUrl:baseUrl+'weixing/mobileWeixinLoginUrl',
    getMobileWeixinOpenid:baseUrl+'weixing/getMobileWeixinOpenid',
    checkMobileOpenIdExists:baseUrl+'weixing/checkMobileOpenIdExists',
    mobileWeixinCallBack:baseUrl+'weixing/mobileWeixinCallBack',
    bindMobileNumber :baseUrl+'weixing/bindMobileNumber',
	validateSmsCode :baseUrl+'purchaserAccount/validateSmsCode',
	wishcartAdd: baseUrl + 'wishcart/add',
    wishcartDelete: baseUrl + 'wishcart/delete',
    wishcartFind: baseUrl + 'wishcart/find',
    wishcartQuery: baseUrl + 'wishcart/query',
    wishcartFindOne: baseUrl + 'wishcart/findOne',

    //DEV
    //queryProdutionByCondition: 'Json/queryProdutionByCondition.json',
    //querylocationByCondition: 'Json/querylocationByCondition.json',
    //queryCaseByCondition: 'Json/queryCaseByCondition.json',
    //trade_status: 'Json/trade_status.json',
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
    //verify: 'Json/verify.json',
    //getSMSCode: 'Json/getSMSCode.json',
    //getProductionDetailsById: 'Json/getProductionDetailsById.json',
    //getLocationDetailsById: 'Json/getLocationDetailsById.json',
    //getCaseDetailsById: 'Json/getCaseDetailsById.json',
    //fileupload: 'Json/fileupload.json',
    //addAndModifyInfo: 'Json/addAndModifyInfo.json',
    //activityTypeList: 'Json/activityTypeList.json',
    //companyTypeList: 'Json/companyTypeList.json',
    //groupNumberList: 'Json/groupNumberList.json',
    //durationList: 'Json/durationList.json',
    //provinceList: 'Json/provinceList.json',
    //orderSubmit: 'Json/orderSubmit.json',
    //getOrderListByLoginId: 'Json/getOrderListByLoginId.json',
	//getOrderByID: 'Json/getOrderByID.json',
    //favouriteQuery: 'Json/favourite.json',
    //favouriteAdd: 'Json/favouriteAdd.json',
    //favouriteDelete: 'Json/favouriteDelete.json',
    //modifyPwd: 'Json/modifyPwd.json',
    //getWeixingConfig: 'Json/getWeixingConfig.json',
	//casesmobileQuery: 'Json/casesmobileQuery.json',
    //locationmobileQuery: 'Json/locationmobileQuery.json',
    //productionInfomobileQuery: 'Json/productionInfomobileQuery.json',
	// mobileWeixinLoginUrl: 'Json/mobileWeixinLoginUrl.json',
    // getMobileWeixinOpenid: 'Json/getMobileWeixinOpenid.json',
    // checkMobileOpenIdExists: 'Json/checkMobileOpenIdExists.json',
    // mobileWeixinCallBack: 'Json/mobileWeixinCallBack.json',
    // bindMobileNumber: 'Json/bindMobileNumber.json',
	//validateSmsCode: 'Json/validateSmsCode.json',
	// wishcartAdd: 'Json/wishcartAdd.json',
    // wishcartDelete: 'Json/wishcartDelete.json',
    // wishcartFind: 'Json/wishcartFind.json',
    // wishcartQuery: 'Json/wishcartQuery.json',
    // wishcartFindOne: 'Json/wishcartFindOne.json',
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
        //city = '上海'// TODO
        $.getScript('http://pv.sohu.com/cityjson?ie=utf-8', function () {
            console.log(returnCitySN)
            var name = returnCitySN.cname, index = name.indexOf('市');
            city = name.substr(0, index);
            console.log(city);
            jQuery.cookie('currentcity', city);
            if (typeof (callback) === 'function') {
                callback(city);
            }
        });
    } else {
        if (typeof (callback) === 'function') {
            callback(city);
        }
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

//文件上传
function fileUpload(contentId, url, uploadFile, callback) {
    var formData = new FormData();
    formData.append('fileUpload', uploadFile);
    var data = formData;
    $.ajax({
        url: url,
        data: data,
        type: "post",
        //type: "get", //DEV
        dataType: "text",
        cache: false,
        processData: false,// 用于对data参数进行序列化处理 这里必须false
        contentType: false, // 必须
        success: function (result) {
            if (typeof callback === 'function') {
                callback(result);
            }
        }
    });
}

//  REQUEST 请求异常拦截
axios.interceptors.request.use(config => {
    //==========  所有请求之前都要执行的操作  ==============
    return config;
}, err => {
    //==================  错误处理  ====================
    alert('请求超时!');
    return Promise.resolve(err);
});

//  RESPONSE 响应异常拦截
axios.interceptors.response.use(data => {
    //==============  所有请求完成后都要执行的操作  ==================
    console.log(data);

    if (data.status && data.status == 500 && data.message == 'No message available') {
        alert('请求失败,请刷新后重试');
        return;
    }
    return data;
}, err => {
    //==============  错误处理  ====================
    if (err && err.response) {
        switch (err.response.status) {
            case 400: err.message = '请求错误(400)'; break;
            case 401: err.message = '未授权，请重新登录(401)'; break;
            case 403: err.message = '拒绝访问(403)'; break;
            case 404: err.message = '请求出错(404)'; break;
            case 408: err.message = '请求超时(408)'; break;
            case 500: err.message = '服务器错误(500)'; break;
            case 501: err.message = '服务未实现(501)'; break;
            case 502: err.message = '网络错误(502)'; break;
            case 503: err.message = '服务不可用(503)'; break;
            case 504: err.message = '网络超时(504)'; break;
            case 505: err.message = 'HTTP版本不受支持(505)'; break;
            default: err.message = `连接出错(${err.response.status})!`;
        }
    } else {
        err.message = '连接服务器失败!'
    }
    alert(err.message);
    return Promise.resolve(err);
})


Vue.prototype.ValidateLogin = function (success, fail) { //判断是否登录有权限
    var vm = this, url = requestUrl.findMixedByLoginId, param = {};
    axios.post(url, param).then(function (response) {
        var data = response.data;
        if (data) {
            var errMsg = data.errMsg;
            if (!!errMsg) {
                jQuery.cookie('accessToken', '', { path: '/iwantrun'});
                if (typeof fail === 'function') {
                    fail(response.data)
                }
            } else {
                if (typeof success === 'function') {
                    success(response.data)
                }
            }
        }
    })
}

var $http_form = {
    post: function (url, callback, dataType) {
        if (!dataType) {
            dataType = 'json';
        }
        $.ajax({
            url: url,
            type: 'POST',
            data: callback.request,
            contentType: 'application/x-www-form-urlencoded',
            dataType: dataType,
            success: function (result) {
                callback.success(result);
            },
            error: function (XMLHttpRequest, textStatus) {
                callback.error(XMLHttpRequest, textStatus);
            }
        });
    }
}

//项目配置
var dataConfig = {
    ContactUs: 4008897003
};
