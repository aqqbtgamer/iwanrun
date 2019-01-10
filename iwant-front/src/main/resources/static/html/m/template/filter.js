var filtertemplate = ''
    + '<div class="filter" v-show="show">                                                                                                                                                                                       '
    + '    <section class="w100p h100p pf t0 l0" style="z-index: 777">                                                                                                                                                   '
    + '        <div class="w750 bgcffffff" style="max-height: 100vh;overflow-y: auto;">                                                                                                                                                                              '
    + '            <div class="w100p h120 bgcffffff">                                                                                                                                                                    '
    + '                <a href="javascript:void(0)" class="fz28 ml30 lh126 c333333 " @click="finish">                                                                                                         '
    + '                        <img src="images/arrow_back.png" class="w18 h34" alt="">                                                                                                                                  '
    + '                    </a>                                                                                                                                                                                          '
    + '                <a href="javascript:void(0)" @click="finish" class="w110 h48 lh48 br45 tac c333333 fz26 fr mr30 mt40" style="background-color: #f6b03e;">                                              '
    + '                        完成                                                                                                                                                                                      '
    + '                    </a>                                                                                                                                                                                          '
    + '            <a href="javascript:void(0)" @click="reset" class="w110 h48 lh48 br45 tac c333333 fz26 fr mr10 mt40" style="border: 1px solid #f6b03e;color: #f6b03e; ">                                          '
    + '                        重置                                                                                                                                                                                      '
    + '                    </a>                                                                                                                                                                                          '
    + '                </div>                                                                                                                                                                                            '
    + '    <h2 class="fz40 ml40 fw700 mb30">筛选</h2>                                                                                                                                                                    '
    + '    <div class="w670 h2 bgcf5f5f5 m0a"></div>                                                                                                                                                                     '
    + '    <div class="w596 m0a">                                                                                                                                                                                        '
    + '        <div class="pt20 pb20">活动类型</div>                                                                                                                                                                     '
    + '        <div class="w596 m0a ofh">                                                                                                                                                                                '
    + '            <button v-for="item in model.activityTypeList" @click="buttonChange($event,item.id,\'activitytype\')" class="w182 h64 lh64 b1se5e5e5 br10 mb20 mr16 fl">{{ item.value }}</button>     '
    + '    </div>                                                                                                                                                                                                        '
    + '</div>                                                                                                                                                                                                            '
    + '<div class="w596 m0a">                                                                                                                                                                                            '
    + '    <div class="pt20 pb20">公司类型</div>                                                                                                                                                                         '
    + '    <div class="w596 m0a ofh">                                                                                                                                                                                    '
    + '        <button v-for="item in model.companyTypeList" @click="buttonChange($event,item.id,\'companytype\')" class="w182 h64 lh64 b1se5e5e5 br10 mb20 mr16 fl">{{ item.value }}</button>                 '
    + '</div>                                                                                                                                                                                                            '
    + '                </div >                                                                                                                                                                                           '
    + '<div class="w596 m0a">                                                                                                                                                                                            '
    + '    <div class="pt20 pb20">出行天数</div>                                                                                                                                                                         '
    + '    <div class="w596 m0a ofh">                                                                                                                                                                                    '
    + '        <button v-for="item in model.durationList" @click="buttonChange($event,item.id,\'duration\')" class="w182 h64 lh64 b1se5e5e5 br10 mb20 mr16 fl">{{ item.value }}</button>                 '
    + '</div>                                                                                                                                                                                                            '
    + '                </div >                                                                                                                                                                                           '
    + '<div class="w596 m0a">                                                                                                                                                                                            '
    + '    <div class="pt20 pb20">人数限制</div>                                                                                                                                                                         '
    + '    <div class="w596 m0a ofh">                                                                                                                                                                                    '
    + '        <button v-for="item in model.groupNumberList" @click="buttonChange($event,item.id,\'personNum\')" class="w182 h64 lh64 b1se5e5e5 br10 mb20 mr16 fl">{{ item.value }}</button>               '
    + '</div>                                                                                                                                                                                                            '
    + '                </div >                                                                                                                                                                                           '
    + '            </div >                                                                                                                                                                                               '
    + '<div class="w100p h100p bgc000000 op30 "></div>                                                                                                                                                                   '
    + '        </section >                                                                                                                                                                                               '
    + '    </div >                                                                                                                                                                                                       '
    + '';

var filter = new Vue({
    el: '#filter',
    template: filtertemplate,
    data: {
        show: false,
        model: {
            activityTypeList: [],
            companyTypeList: [],
            groupNumberList: [],
            durationList: []
        },
        filterData: {
            activitytype: [],
            companytype: [],
            duration: [],
            personNum: []
        },
        filted: {
            //TODO 
        },
        callback: null
    },
    methods: {
        reset: function () {
            var vm = this;
            vm.filterData = {
                activitytype: [],
                companytype: [],
                duration: [],
                personNum: []
            };
            $('.filter .select-num').removeClass('select-num');
        },
        buttonChange: function ($event, id, type) {
            var vm = this, $dom = $($event.target);
            var idx = vm.filterData[type].indexOf(id);
            if (idx < 0) {
                vm.filterData[type].push(id);
                $dom.addClass('select-num');
            } else {
                vm.filterData[type].splice(idx, 1);
                $dom.removeClass('select-num');
            }
            console.log(vm.filterData[type]);
            console.log($dom);
        },
        finish: function () {
            var vm = this;
            vm.show = false;
            if (typeof vm.callback === 'function') {
                vm.callback(vm.filterData);
            }
        }
    },
    created: function () {
        var vm = this;
        //vm.login.show
        $.each(queryListByField, function (key, value) {
            axios.post(value.url, value.param).then(
                function (response) {
                    //console.log(response.data);
                    var data = response.data;
                    vm.model[key] = data;
                });
        });
    }
})
