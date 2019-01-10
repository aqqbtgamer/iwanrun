var companyfootertemplate =
    '<footer class="w100p h368 bgcffffff">'
    + '<div class="w100p h368 pt40 home_footer">'
    + '<div class="w218 h64 m0a ofh">'
    + '  <img src="images/logo_icon.png" class="object-fit-cover fl" alt="logo">'
    + '            </div>'
    + '<div class="tac fz30 mt30 mb30" style="color: #f08200;">'
    + '  这里想放一句,类似平台的slogn'
    + '        </div>'
    + '  <div class="w670 h2 bgcf5f5f5 m0a op70"></div>'
    + '    <div class="tac mt30">'
    + '      <a href="tel:0000000" class="fz30 cffffff op60 lh30 mr30"><img src="images/icon_tel.png" class="w30 h30 mr10" alt="">联系我们</a>'
    + '        <a href="###" class="fz30 cffffff op60 lh30"><img src="images/icon_aboutus.png" class="w30 h30 mr10" alt="">关于我们</a>'
    + '          </div>'
    + '    <div class="tac fz20 cffffff op50 mt10">'
    + '      上海沐跑信息科技有限公司 <br />'
    + '    北京ICP备0000000号'
    + '  </div>'
    + '</div>'
    + '</footer>';



var companyfooter = new Vue({
    el: '#companyfooter',
    template: companyfootertemplate
})