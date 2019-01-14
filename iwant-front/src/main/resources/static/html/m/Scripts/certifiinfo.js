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
                //name: '上海沐跑科技有限公司',
                //licenses: [],
                //hasCredential: false,
                //companyTypeId: 0,
                //companySizeId: 10,
                //type: '互联网软件',
                //personNum: 200
            }
        },
    },
    methods: {
        getuser: function () {
            var vm = this, url = requestUrl.findMixedByLoginId, param = {};
            axios.post(url, param).then(
                function (response) {
                    console.log(response.data);
                    var data = response.data;
                    if (data) {
                        var errMsg = data.errMsg;
                        if (errMsg) {
                            login.show = true;
                        }
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
                    }
                    console.log(vm.account);
                })
        }
    },
    components: {
        login: login
    },
    created: function () {
        var vm = this;
        vm.getuser();
    }
});