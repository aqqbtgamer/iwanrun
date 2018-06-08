/**
 * Created by ths on 2018/3/20.
 */
console.log("api 脚本初始化..............");

var CookieJS = {
    set: function (name, value) {
        var Days = 30;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    },
    get: function (name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg)) {
            return unescape(arr[2]);
        }
        return null;
    },
    delete: function (name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null) {
            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
        }
    }
};

$(function () {
    if (!Vue.http) {
        console.log('未加载vue-resourse');
        return false;
    }


    Vue.http.interceptors.push(function (request, next) {

        var token = CookieJS.get('X-CSRF-TOKEN');

        // modify method
        request.method = 'POST';

        // modify headers
        //request.headers.set('X-CSRF-TOKEN', token);

        //后台请求等待
        next(function (response) {
            //接触等待效果
            if (!response.ok) {
                //异常处理
                console.log(response);
                return false;
            }
            return response
        })
    });
});

var apiService = {
    sourse: 'http://139.196.228.29:9999/iwant_app',
    url: {
        loginToken: '/application/loginToken',
        file: '/application/file',
        location: {
            add: '/application/location/add',
            get: '/application/location/get',
            modify: '/application/location/modify',
            findAll: '/application/location/findAll',
            queryAll: '/application/location/queryAll',
            delete: '/application/location/delete',
        },
        dictionary: {
            getPages: '/application/dictionary/getPages',
            getTabs: '/application/dictionary/getTabs',
            findByCode: '/application/dictionary/findByCode',
            add: '/application/dictionary/add',
            delete: '/application/dictionary/delete'
        },
        productionInfo: {
            add: '/application/productionInfo/add',
            edit: '/application/productionInfo/edit',
            find: '/application/productionInfo/find',
            detail: '/application/productionInfo/detail',
            unShift: '/application/productionInfo/unShift'
        },
        cases: {
            add: '/application/cases/add',
            get: '/application/cases/get',
            modify: '/application/cases/modify',
            findAll: '/application/cases/findAll',
            queryAll: '/application/cases/findByExample',
            delete: '/application/cases/delete'
        },
    },
    Methods: {
        Login: function (name, password, type) {
            Vue.http.post(apiService.sourse + apiService.url.loginToken, {
                accessToken: '',
                messageBody: {
                    username: '',
                    role: '',
                    certification: '',
                    sessionId: ''
                },
                requestMethod: ''
            }).then((response) => {
                // 响应成功回调
            }, (response) => {
                // 响应错误回调
            });
        }
    }
}

console.log("api 脚本初始化.............. 完成");

//API
    //首页
        //短信验证
        //登录
        //我们如何工作?

