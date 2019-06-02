var appIndex = new Vue({
    el: "#container",
    data: {
        loginId: jQuery.cookie('loginId'),
        accessToken: jQuery.cookie('accessToken'),
        account: {
            headimg: 'images/head_img.png',
            name: jQuery.cookie('loginId'),
            phone: jQuery.cookie('loginId'),
            gender: 0,
            email: null,
            companyName: '上海沐跑科技有限公司',
            companySizeId: 0,
            companyTypeId: 0,
            company: {
                hasCredential: false,
                types: { 0: '', 1: '国企', 2: '民营', 3: '外企' },
                personNums: { 0: '', 1: '1~10人', 2: '10~50人', 3: '50人以上' },
                licenses: []
            }
        },
    },
    methods: {
        getuser: function () {
            var vm = this, url = requestUrl.findMixedByLoginId, param = {};
            axios.post(url, param).then(function (response) {
                console.log(response.data);
                var data = response.data;
                if (data) {
                    var errMsg = data.errMsg;
                    if (errMsg) {
                        jQuery.cookie('accessToken', '');
                        window.location.href = 'index.html';
                    }
                    var info = data.userInfo;
                    var headImgs = data.headImgs;
                    var companyCredentials = data.companyCredentials;
                    var loginInfo = data.loginInfo;
                    if (info) {
                        if (headImgs && headImgs.length > 0) {
                            vm.account.headimg = headImgs[0].filePath;
                        }
                        vm.account.name = info.name;
                        vm.account.gender = info.gender;
                        vm.account.email = info.email;

                        vm.account.company.name = info.companyName;
                        vm.account.companyTypeId = info.companyTypeId;
                        vm.account.companySizeId = info.companySizeId;

                        if (loginInfo) {
                            vm.account.phone = loginInfo.mobileNumber;
                        }

                        if (companyCredentials) {
                            for (var i = 0; i < companyCredentials.length; i++) {
                                vm.account.company.licenses.push(companyCredentials[i].filePath);
                            }
                            vm.account.company.hasCredential = true;
                        }
                    }
                    //console.log(vm.account);
                }
            })
        },
        update: function ($event) {
            var vm = this, $dom = $($event.target), name = $dom.attr('name'), val = vm.account[name], param = {};
            param[name] = val;
            vm.save(param);
        },
        save: function (param) {
            var vm = this, url = requestUrl.addAndModifyInfo;
            console.log(param);
            axios.post(url, param).then(function (response) {
                console.log(response.data);
            });
        },
        uploadimg: function ($event) {
            var vm = this;
            //是否登录

            $($event.target).siblings('input:file').trigger('click');
        },
        headImgUpload: function ($event) {
            var vm = this, files = $event.target.files, url = requestUrl.fileupload
            fileUpload('uploadedHeadImages', url, files[0], function (data) {
                //console.log(data);
                vm.account.headimg = data;
                var param = {};
                param["headimg"] = data;
                vm.save(param)
            });
        }
    },
    created: function () {
        var vm = this;
        if (!jQuery.cookie('accessToken')) {
            window.history.back();
        }
        vm.getuser();
    }
});