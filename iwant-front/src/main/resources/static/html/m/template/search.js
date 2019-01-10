var searchtemplate = ''
    + '<div class="search pf bgcffffff w100p h100p pf t0 l0" style="z-index: 100" v-show="show">                            '
    + '<div class="pf bgcffffff w100p h100p pf t0 l0" style="z-index: 777">     '
    + '    <header class="w100p h172 bgcffffff pf t0 l0">                                                                   '
    + '        <div class="w668 h70 bgcf5f5f5 br45 header_search pa t80 l40">                                               '
    + '            <input type="text" class="w608 h54 lh54 mt8 fl pl30 pr30 fz26" placeholder="产品、场地、案例" />         '
    + '        </div>                                                                                                       '
    + '        <a class="fr mr30 mt20" href="javascript:void(0)" @click="show=false">                                '
    + '                    <img src="images/close_icon_a.png" class="w24 h24" alt="">                                       '
    + '                </a>                                                                                                 '
    + '    </header>                                                                                                        '
    + '    <section class="mt180">                                                                                                        '
    + '            <div class="fz28 ml40 fw700">搜索历史</div>                                                              '
    + '            <div class="ml40 w670 pt30 fz26">                                                                        '
    + '                <a v-for="item in searchHistory" href="javascript:void(0)" class="dib h64 lh64 pl30 pr30 b1se5e5e5 c333333 br10 mr20">{{item}}</a>       '
    + '            </div>                                                                                                   '
    + '        </section>                                                                                                   '
    + '    </div>                                                                                                       '
    + '    </div>                                                                                                       '
    + '';

var search = new Vue({
    el: '#search',
    template: searchtemplate,
    data: {
        show: false,
        searchHistory: ['上海','轰趴'],
        callback: null
    },
    methods: {

    },
    created: function () {
        var vm = this, searchHistory = jQuery.cookie('searchHistory');
        if (searchHistory) {
            vm.searchHistory = searchHistory.splice(',');
        }
    }
})
