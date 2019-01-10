//等宽修改字体大小
var CARD_DATA ={};
function changeFontSize() {
    var window_width = $(window).width();
    var font_size = window_width / 10;
    if (font_size < 32.5) {
        font_size = 32.5;
    }
    $("html").css("font-size", font_size);
    return font_size;
}
$(function(){
    

    CARD_DATA.font_size = changeFontSize();
    realRemChange();
    $(window).resize(function() {
        CARD_DATA.font_size = changeFontSize();
        realRemChange();
    });
});

function realRemChange() {
    var u = navigator.userAgent, app = navigator.appVersion;
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //g
    if (isAndroid) {
        setTimeout(function(){
            var d = document.createElement('div');
            d.style.cssText="width:1rem;height:0;overflow: hidden;position:absolute;z-index:-1;visibility: hidden;";
            document.body.appendChild(d);
            var dw=d.offsetWidth; // 1rem的实际展示px值
            document.body.removeChild(d);
            var html = document.querySelector('html');
            var fz = html.style.fontSize || 0; //正常计算出来的rem基准值 , 可自行修改为rem计算好的值
            var realRem = parseFloat(fz);
            if(dw != realRem){//不相等 则被缩放了
                realRem = Math.pow(realRem,2)/dw;
            }
            html.style.fontSize = realRem + 'px';

        } , 0)
    }

}


