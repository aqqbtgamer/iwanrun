/**
 * Created by WXP22 on 2018/4/14.
 */

$(document).ready(
    function(){
        initUE();
    }
);

function initUE() {
    ue.ready(function (obj) {
        var editorId = '#' + ue.container.id;
        $(editorId).css('max-width', 800);
    });
}
