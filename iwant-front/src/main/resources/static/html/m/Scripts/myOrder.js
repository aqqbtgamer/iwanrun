var appIndex = new Vue({
    el: "#container",
    data: {
        loginId: jQuery.cookie('loginId'),
        accessToken: jQuery.cookie('accessToken'),
        id: getUrlParam('id'),
        model: {
            orders: {},
            purchaserAccountInfo: {},
            purchaserAccount: {}
        }
    },
    methods: {
        getOrderByID: function () {
            var vm = this, url = requestUrl.getOrderByID, param = {
                id: vm.id
            };
            axios.post(url, param).then(function (response) {
                console.log(response.data);
                vm.model = response.data;
            });
        },
        dateformat: function (timeMills) {
            if (timeMills != undefined) {
                var date = new Date();
                date.setTime(timeMills);
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                var dates = date.getDate();
                var hours = date.getHours();
                var minutes = date.getMinutes();
                var seconds = date.getSeconds();
                return year + "/" + month + "/" + dates;
            }
            return '';
        }
    },
    created: function () {
        var vm = this;
        if (!jQuery.cookie('accessToken') || !vm.id) {
            window.location.href = 'index.html';
        }
        vm.getOrderByID();
    }
});