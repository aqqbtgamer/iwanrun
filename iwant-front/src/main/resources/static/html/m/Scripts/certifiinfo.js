var appIndex = new Vue({
    el: "#container",
    data: {
        loginId: jQuery.cookie('loginId'),
        accessToken: jQuery.cookie('accessToken'),
        account: {
            headimg: 'images/head_img.png',
            nickname: jQuery.cookie('loginId'),
            phone: jQuery.cookie('loginId'),
            company: {
                types: { 0: '', 1: '国企', 2: '民营', 3: '外企' },
                personNums: { 0: '', 1: '1~10人', 2: '10~50人', 3: '50人以上' }
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
                    vm.account.nickname = info.name;
                    if (loginInfo) {
                        vm.account.phone = loginInfo.mobileNumber;
                    }
                    vm.account.company.name = info.companyName;
                    if (companyCredentials) {
                        vm.account.company.hasCredential = true;
                    }

                    var company = vm.account.company;

                    company.companyTypeId = info.companyTypeId;
                    company.companySizeId = info.companySizeId;
                    company.type = company.types[info.companyTypeId];
                    company.personNum = company.personNums[info.companySizeId];
                    //setCompanyOption();
                }
            }, function () {
                alert('登录失效，请重新登录');
                window.location.href = 'index.html';
            });
        }
    },
    created: function () {
        var vm = this;
        vm.getuser();
    }
});