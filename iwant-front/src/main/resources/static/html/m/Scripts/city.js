
$(function () {


    let city_date = myCities();
    // console.log(city_date)
    let a = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    let htmlT = '<li data-group="A" class="mui-table-view-divider mui-indexed-list-group " style="background-color: #f3f3f3">A</li>';
    $(".city-item").append(htmlT);
    $.each(city_date,function (index,cityArr) {
        // console.log(cityArr);
        let city = cityArr.split("|");
        let htmlC = '<li data-value="'+city[0]+'" data-tags="'+city[0]+'" class="mui-table-view-cell mui-indexed-list-item" onclick="cityClickFn(this);">'+city[1]+'</li>'
        $(".city-item").append(htmlC);
        let t = city_date[index];
        let x = city_date[index+1];
        // console.log(t);
        if (x) {
            if (t.charAt(0) != x.charAt(0)) {
                // console.log(x.charAt(0));
                let htmlX = '<li data-group="'+x.charAt(0)+'" class="mui-table-view-divider mui-indexed-list-group " style="background-color: #f3f3f3">'+x.charAt(0)+'</li>';
                $(".city-item").append(htmlX);
            }
        }
    })


    $(".positioning-btn, .hot-city-btn").on("click",function() {
        cityClickFn(this);
    });



})


function myCities() {
    let c = new CitySelect();
    let tmp = [];
    $.each(c.cities,function (name,city) {
        name = name.substring(0,1).toUpperCase()+name.substring(1);
        tmp.push(name + "|" + city);

    })
    let s = JSON.stringify(tmp).substring(1);
    let m = s.substring(0,s.length-1);
    let r = JSON.parse("[" + stringSort(m) + "]");
    // console.log(r);
    return r;
}


function stringSort(str) {
    if(typeof str === 'string')
        str = str.split(',');
    if (!Array.isArray(str)) {
        console.error('参数类型错误, 必须为数组或以(,)分割的字符串.')
        return str;
    }
    str.sort();
    return str.join();
}




mui.init();
mui.ready(function() {
    var header = document.querySelector('header.mui-bar');
    var list = document.getElementById('list');
    //calc hieght
    list.style.height = (document.body.offsetHeight - header.offsetHeight) + 'px';
    //create
    window.indexedList = new mui.IndexedList(list);

    var currentcity = jQuery.cookie('currentcity');
    if (currentcity) {
        $('#currentcity').text(currentcity);
        sethotcity(currentcity);
    }
});

function cityClickFn(el) {
    var currentcity = $(el).text();
    $('#currentcity').text(currentcity);
    jQuery.cookie('currentcity', currentcity);
    sethotcity(currentcity);
    window.history.back();
}

function sethotcity(currentcity) {
    $('.location-active').removeClass('location-active');
    $('.hot-city-btn').each(function (index, item) {
        if ($(item).text().trim() === currentcity) {
            $(item).addClass('location-active');
        } 
    });
}