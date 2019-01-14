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
    + '    <div class="w596 m0a activitytype" v-show="model.activitytype.length>0">                                                                                                                                                                                        '
    + '        <div class="pt20 pb20">活动类型</div>                                                                                                                                                                     '
    + '        <div class="w596 m0a ofh">                                                                                                                                                                                '
    + '            <button v-for="item in model.activitytype" :data-id="item.id" @click="buttonChange($event,item,\'activitytype\')" class="w182 h64 lh64 b1se5e5e5 br10 mb20 mr16 fl">{{ item.value }}</button>     '
    + '    </div>                                                                                                                                                                                                        '
    + '</div>                                                                                                                                                                                                            '
    + '    <div class="w596 m0a specialTagsCode" v-show="model.specialTagsCode.length>0">                                                                                                                                                                                        '
    + '        <div class="pt20 pb20">关键字</div>                                                                                                                                                                     '
    + '        <div class="w596 m0a ofh">                                                                                                                                                                                '
    + '            <button v-for="item in model.specialTagsCode" :data-id="item.id" @click="buttonChange($event,item,\'specialTagsCode\')" class="w182 h64 lh64 b1se5e5e5 br10 mb20 mr16 fl">{{ item.value }}</button>     '
    + '    </div>                                                                                                                                                                                                        '
    + '</div>                                                                                                                                                                                                            '
    + '<div class="w596 m0a companytype" v-show="model.companytype.length>0">                                                                                                                                                                                            '
    + '    <div class="pt20 pb20">公司类型</div>                                                                                                                                                                         '
    + '    <div class="w596 m0a ofh">                                                                                                                                                                                    '
    + '        <button v-for="item in model.companytype" :data-id="item.id" @click="buttonChange($event,item,\'companytype\')" class="w182 h64 lh64 b1se5e5e5 br10 mb20 mr16 fl">{{ item.value }}</button>                 '
    + '</div>                                                                                                                                                                                                            '
    + '                </div >                                                                                                                                                                                           '
    + '<div class="w596 m0a duration" v-show="model.duration.length>0">                                                                                                                                                                                            '
    + '    <div class="pt20 pb20">出行天数</div>                                                                                                                                                                         '
    + '    <div class="w596 m0a ofh">                                                                                                                                                                                    '
    + '        <button v-for="item in model.duration" :data-id="item.id" @click="buttonChange($event,item,\'duration\')" class="w182 h64 lh64 b1se5e5e5 br10 mb20 mr16 fl">{{ item.value }}</button>                 '
    + '</div>                                                                                                                                                                                                            '
    + '                </div >                                                                                                                                                                                           '
    + '<div class="w596 m0a locationTypeCode" v-show="model.locationTypeCode.length>0">                                                                                                                                                                                            '
    + '    <div class="pt20 pb20">场地类型</div>                                                                                                                                                                         '
    + '    <div class="w596 m0a ofh">                                                                                                                                                                                    '
    + '        <button v-for="item in model.locationTypeCode" :data-id="item.id" @click="buttonChange($event,item,\'locationTypeCode\')" class="w182 h64 lh64 b1se5e5e5 br10 mb20 mr16 fl">{{ item.value }}</button>                 '
    + '</div>                                                                                                                                                                                                            '
    + '                </div >                                                                                                                                                                                           '
    + '<div class="w596 m0a personNum" v-show="model.personNum.length>0">                                                                                                                                                                                           '
    + '    <div class="pt20 pb20">人数限制</div>                                                                                                                                                                         '
    + '    <div class="w596 m0a ofh">                                                                                                                                                                                    '
    + '        <button v-for="item in model.personNum" :data-id="item.id" @click="buttonChange($event,item,\'personNum\')" class="w182 h64 lh64 b1se5e5e5 br10 mb20 mr16 fl">{{ item.value }}</button>               '
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
        type: 'production',
        model: {
            activitytype: [],
            companytype: [],
            duration: [],
            personNum: [],
            specialTagsCode: [],
            locationTypeCode: []
        },
        filterData: {
            activitytype: [],
            companytype: [],
            duration: [],
            personNum: [],
            specialTagsCode: [],
            locationTypeCode: []
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
        buttonChange: function ($event, item, type) {
            var vm = this, $dom = $($event.target);
            var idx = vm.filterData[type].indexOf(item);
            if (idx < 0) {
                vm.filterData[type].push(item);
                $dom.addClass('select-num');
            } else {
                vm.filterData[type].splice(idx, 1);
                $dom.removeClass('select-num');
            }
            //console.log(vm.filterData[type]);
            //console.log($dom);
        },
        finish: function () {
            var vm = this;
            vm.show = false;
            if (typeof vm.callback === 'function') {
                vm.callback(vm.filterData);
            }
        },
        remove: function (item) {
            var vm = this;
            console.log('-----filter remove');
            console.log(vm.filterData[item.type]);
            $.each(vm.filterData[item.type], function (index, it) {
                console.log(it);
                if (it.id == item.id) {
                    vm.filterData[item.type].splice(index, 1);
                }
            });
            $('.' + item.type).find('[data-id=' + item.id + ']').removeClass('select-num');
            console.log(vm.filterData[item.type]);
            console.log('-----filter remove');
        },
        init: function () {
            var vm = this, param = { name: 'common' }, type = vm.type;
            var typeUrl = {
                production: requestUrl.produtionSearchList,
                location: requestUrl.locationSearchList,
                case: requestUrl.caseSearchList
            };

            axios.post(typeUrl[type], param).then(function (response) {
                //console.log(response.data);
                var data = response.data;
                $.each(vm.model, function (key, value) {
                    if (Array.isArray(data[key])) {
                        vm.model[key] = data[key];
                    }
                });
                //console.log(vm.model);
            });
        }
    }
})
