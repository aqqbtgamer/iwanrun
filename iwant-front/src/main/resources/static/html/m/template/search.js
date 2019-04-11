var searchtemplate = ''
    + '<div class="search pf bgcffffff w100p h100p pf t0 l0" style="z-index: 100" v-show="showSearch">                            '
    + '<div class="pf bgcffffff w100p h100p pf t0 l0" style="z-index: 777">     '
    + '    <header class="w100p h172 bgcffffff pf t0 l0">                                                                   '
    + '        <div class="w668 h70 bgcf5f5f5 br45 header_search pa t80 l40">                                               '
    + '            <input type="text" v-model="searchContent" @change="inputChange" id="iptSearch" class="w608 h54 lh54 mt8 fl pl30 pr30 fz26" placeholder="产品、场地、案例" />         '
    + '        </div>                                                                                                       '
    + '        <a class="fr mr30 mt20" href="javascript:void(0)" @click="showSearch=false">                                '
    + '                    <img src="images/close_icon_a.png" class="w24 h24" alt="">                                       '
    + '                </a>                                                                                                 '
    + '    </header>                                                                                                        '
    + '    <section class="mt180">                                                                                                        '
    + '            <div class="fz28 ml40 fw700">搜索历史</div>                                                              '
    + '            <div class="ml40 w670 pt30 fz26">                                                                        '
    + '                <a v-for="item in searchHistory" href="javascript:void(0)" @click="quickSelect(item)" class="dib h64 lh64 pl30 pr30 b1se5e5e5 c333333 br10 mr20">{{item}}</a>       '
    + '            </div>                                                                                                   '
    + '        </section>                                                                                                   '
    + '     <ul class="run_menu page"> '
    + '            <a href="javascript:void(0)" @click="changeTab(\'product\')" :class="showTab===\'product\'?styleClass.tabActiveClass:styleClass.tabClass">产品 <i class="w12 h12 br50p pa t16 r40 dn" style="background-color: #f6b03e;"></i></a> '
    + '            <a href="javascript:void(0)" @click="changeTab(\'case\')" :class="showTab===\'case\'?styleClass.tabActiveClass:styleClass.tabClass">案例 <i class="w12 h12 br50p pa t16 r40 dn" style="background-color: #f6b03e;"></i></a> '
    + '            <a href="javascript:void(0)" @click="changeTab(\'location\')" :class="showTab===\'location\'?styleClass.tabActiveClass:styleClass.tabClass">场地 <i class="w12 h12 br50p pa t16 r40 dn" style="background-color: #f6b03e;"></i></a> '
    + '        </ul> '
    + '      <div style="height: 10rem" class="ofs mt20">'
    + '         <section class="li_list w100p bgcffffff mt-4 pb40 " v-show="showTab===\'product\'" >'
    + '             <ul class="pt40">'
    + '                    <li class="pb200 pr" v-for="item in model.productions">'
    + '                        <div class="w48 h92 pa t-10 r80">'
    + '                            <img src="images/label_new.png" class="object-fit-cover" alt="">'
    + '                        </div>'
    + '                        <div class="w670 pb100 bgcffffff bs-0-0-6-0-dddddd br10 m0a">'
    + '                            <div title="item.name" class="fz32 fw700 pl30 pt30 w534 mult-line-ellipsis-1">{{item.name}}</div>'
    + '                            <div class="pl30 pr30 pt20 fz18">'
    + '                                <span v-for="str in item.tips" class="dib pl14 pr14 pt10 pb10 cffffff br6 mr20 mb10" style="background-color: #282b33;">{{str}}</span>'
    + '                            </div>'
    + '                            <div class="pl30 pr30 pt10 fz24">'
    + '                                <p>{{item.activityProvinceCode}}·{{item.activityCityCode}}</p>'
    + '                                <p>{{item.dur}} / {{item.groupNumber}}</p>'
    + '                            </div>'
    + '                        </div>'
    + '                        <div class="w570 h240 pa b40 l90">'
    + '                            <img @click="linktoDetail(item.id,\'product\')" :src="item.mainImageLarge" class="object-fit-cover" alt="">'
    + '                        </div>'
    + '                    </li>'
    + '                </ul>'
    + '          </section>'
    + '         <section v-show="showTab===\'location\'" class="li_list w100p bgcffffff mt-4 pb40 ">'
    + '               <ul class="pt40">'
    + '                   <li class="mb34" v-for="item in model.locations">'
    + '                       <div class="w670 h392 bgcffffff bs-0-0-6-0-dddddd br10 m0a ">'
    + '                           <div class="ofh">'
    + '                               <div class="w254 h254 br20 ml22 mt22 fl pr">'
    + '                                   <div class="w130 h104 pa t0 l0">'
    + '                                       <img src="images/label_tuijian.png" class="object-fit-cover" alt="">'
    + '                                   </div>'
    + '                                   <img @click="linktoDetail(item.id,\'location\')" :src="item.descirbeText2" class="object-fit-cover" alt="">'
    + '                               </div>'
    + '                               <div class="fl w382">'
    + '                                   <div class="fz32 fw700 pl30 pt30 w382 mult-line-ellipsis-1">{{item.name}}</div>'
    + '                                   <div class="pl30 pr30 pt14 fz22 w382 mult-line-ellipsis-1">'
    + '                                       <span v-for="(str,index) in item.tips" v-if="index<4" class="dib mr10">#{{str}}#</span>'
    + '                                   </div>'
    + '                                   <div class="pl30 pr30 pt14 fz24 c666666">'
    + '                                       <p>{{item.location}}</p>'
    //+ '                                       <p>{{item.designDuringCo}} / {{item.groupNumberLimit}}</p>'
    + '                                   </div>'
    //+ '                                   <div class="ml30 mr30 mt60 pr">'
    //+ '                                       <div class="fz34 pa b0 l0" style="color: #fd5509">'
    //+ '                                           ￥{{item.simulatePriceCode}}<span class="fz20">/人起</span>'
    //+ '                                       </div>'
    //+ '                                       <div v-show="item.isDiscount" class="fz20 pa b0 r0">支付立减 <span class="fz18 h30 lh30 dib" style="border:1px solid #fd5509;color: #fd5509;padding: 0 5px;border-radius: 3px;">红包</span></div>'
    //+ '                                   </div>'
    + '                               </div>'
    + '                           </div>'
    + '                           <div class="w612 h2 m0a bgcf5f5f5 mt22 mb22"></div>'
    + '                           <div class="fz24 h46 lh46 ml22 mr22">'
    + '                               <span v-for="str in item.tipNotes" class="dib h46 br25 pl20 pr20" style="background-color: #fef7eb;color: #f6b03e;">{{str}}</span>'
    + '                           </div>'
    + '                       </div>'
    + '                   </li>'
    + '               </ul>'
    + '          </section>'
    + '         <section v-show="showTab===\'case\'" class="li_list w100p bgcffffff mt-4 pb40">'
    + '                <ul class="pt40">'
    + '                    <li class="mb34" v-for="item in model.cases">'
    + '                        <div class="w670 h504 bgcffffff bs-0-0-6-0-dddddd br10 m0a ">'
    + '                            <div class="w100p h328">'
    + '                                <img @click="linktoDetail(item.id,\'case\')" :src="item.descirbeText2" class="object-fit-cover" alt="">'
    + '                            </div>'
    + '                            <div class="fz32 fw700 pl30 pt20 w534 mult-line-ellipsis-1">{{item.name}}</div>'
    + '                            <div class="pl30 pr30 pt20 fz24 c666666">'
    + '                                <p>{{item.location}}</p>'
    //+ '                                <p>{{item.designDuringCo}} / {{item.groupNumber}}</p>'
    + '                            </div>'
    + '                        </div>'
    + '                    </li>'
    + '                </ul>'
    + '                <div class="w670 h2 bgcf5f5f5 m0a"></div>'
    + '            </section>'
    + '      </div>'
    + '    </div>                                                                                                       '
    + '    </div>                                                                                                       '
    + '';

var search = new Vue({
    el: '#search',
    template: searchtemplate,
    data: {
        showSearch: false,
        searchHistory: [],
        callback: null,
        showTab: 'product',
        styleClass: {
            tabClass: 'dib w80 ml30 mr30 h80 lh80 fz28 c333333 tac pr',
            tabActiveClass: 'dib w80 ml30 mr30 h80 lh80 fz28 c333333 tac pr search_actives'
        },
        searchContent: '',
        model: {
            cases: [],
            productions: [],
            locations: []
        },
        pageSize: 10
    },
    methods: {
        Search: function () {
            var vm = this;
            var query = {
                product: vm.productionInfomobileQuery,
                case: vm.casesmobileQuery,
                location: vm.locationmobileQuery
            };
            if (typeof query[vm.showTab] === 'function') {
                query[vm.showTab](0);
            }
        },
        changeTab: function (tab) {
            var vm = this;
            vm.showTab = tab || 'product';
            vm.Search();
        },
        linktoDetail: function (id, type) {
            location.href = 'detail.html?id=' + id + '&type=' + type;
        },
        inputChange: function () {
            var vm = this;
            console.log(vm.searchContent);

            if (vm.searchContent && vm.searchHistory.indexOf(vm.searchContent) < 0) {
                vm.searchHistory.push(vm.searchContent);
                jQuery.cookie('searchHistory', vm.searchHistory.join(','));
            }
            vm.Search();
        },
        quickSelect: function (content) {
            var vm = this;
            vm.searchContent = content;
			vm.Search();
        },
        casesmobileQuery: function (pageIndex) {
            var vm = this, url = requestUrl.casesmobileQuery, param = {
                pageIndex: pageIndex,
                pageSize: vm.pageSize,
                obj:{
                    name: vm.searchContent
                }
            };
            axios.post(url, param).then(function (response) {
                //console.log(response.data);
                vm.model.cases = response.data.content;
            })
        },
        productionInfomobileQuery: function (pageIndex) {
            var vm = this, url = requestUrl.productionInfomobileQuery, param = {
                pageIndex: pageIndex,
                pageSize: vm.pageSize,
                obj: {
                    name: vm.searchContent
                }
            };
            axios.post(url, param).then(function (response) {
                //console.log(response.data);
                vm.model.productions = response.data.content;
            })
        },
        locationmobileQuery: function (pageIndex) {
            var vm = this, url = requestUrl.locationmobileQuery, param = {
                pageIndex: pageIndex,
                pageSize: vm.pageSize,
                obj: {
                    name: vm.searchContent
                }
            };
            axios.post(url, param).then(function (response) {
                //console.log(response.data.content);
                vm.model.locations = response.data.content;
            })
        }
    },
    created: function () {
        var vm = this, searchHistory = jQuery.cookie('searchHistory');
        if (searchHistory) {
            vm.searchHistory = searchHistory.split(',');
        }

        vm.productionInfomobileQuery(0);
    }
})
