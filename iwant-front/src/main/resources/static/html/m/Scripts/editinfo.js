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
            var vm = this;
            vm.ValidateLogin(function (data) {
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
            }, function () {
                alert('登录失效，请重新登录');
                window.location.href = 'index.html';
            });
        },
        update: function ($event) {
            var vm = this, $dom = $($event.target), name = $dom.attr('name'), val = vm.account[name], param = {};
            param[name] = val;
            vm.save(param);
        },
        save: function (param) {
            var vm = this, url = requestUrl.addAndModifyInfo;
            axios.post(url, param).then(function (response) {
                if (response.status != 200) {
                    alert('保存失败,请刷新后重试');
                }
            });
        },
        uploadimg: function ($event) {
            var vm = this;
            $($event.target).siblings('input:file').trigger('click');
        },
        headImgUpload: function ($event) {
            var vm = this, files = $event.target.files, url = requestUrl.fileupload
            fileUpload('uploadedHeadImages', url, files[0], function (data) {
                vm.account.headimg = data;
                var param = {};
                param["headimg"] = data;
                vm.save(param)
            });
        }
    },
    created: function () {
        var vm = this;
        vm.getuser();
    }
});