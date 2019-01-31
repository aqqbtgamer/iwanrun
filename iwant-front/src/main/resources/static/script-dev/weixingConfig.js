/**
 * 
 */

 $.ajax({
            url: '../../weixing/getWeixingConfig',
            type: 'POST',
            dataType: 'json',
            //url需要编码传入而且要是完整的url除#之后的
            data: {"url":encodeURIComponent(window.location.href.split("#")[0])}
        })
        .done(function(res) {
            wx.config({
                debug:false, //调试阶段建议开启
                appId:res.appId,//APPID
                timestamp:res.timestamp,//上面main方法中拿到的时间戳timestamp
                nonceStr:res.nonceStr,//上面main方法中拿到的随机数nonceStr
                signature:res.signature,//上面main方法中拿到的签名signature               
                jsApiList: [ 'onMenuShareTimeline','onMenuShareAppMessage','onMenuShareWeibo','onMenuShareQQ','onMenuShareQZone']
            });
        });