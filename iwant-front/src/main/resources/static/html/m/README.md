TODO
4.微信登陆
5.微信打开首页 图片切换有问题

测试
1.首页 
	城市定位
	图片切换 微信浏览器有问题
	三个list展示 OK

2.关于我们 OK

3.登陆 注册 忘记密码
	密码登录 OK
	短信登录 OK
	注册 OK
	忘记密码 OK
	※※※※ 微信登录 ※※※※※※

4.个人中心 OK

5.个人资料编辑 OK

6.认证信息 OK

7.提交订单 OK

8.方案列表

9.案例列表 OK

10.场地列表 OK

11.详情页 OK

12.筛选功能 OK

13.我的订单 OK

14.我的订单列表 OK

15.我的收藏

16.

git pull
mvn install-Dmaven.test.skip=true
mvn spring-boot:run


11.微信分享
	图片

12.城市选择


优化：
1.返回首页 可拖拽





TODO
1.collection OK
2.about us  翻译拿到 文字图片
3.心愿清单 TODO
4.我的订单列表  OK
5.我的订单 OK
6.微信登录 TODO
7.订单提交成功 OK
8.搜索  搜索接口 TODO  搜索历史 OK  VUE搜索自动焦点toconfirm


BUG
1.搜索历史


BLOCK
1.详情页 心愿单
2.微信登录 


TOAsk
1.忘记密码 接口有么  如何实现
2.认证信息 无法判断取得"已认证XX天"
3.location接口没有数据


TOMakeSure
2、微信登陆和PC端保持一致，目前PC端微信登陆正在申请新的公众号
3、忘记密码沿用立即注册的页面设计
4、热门推荐/猜您喜欢 暂时不做
5、“心愿单”就是PC端提交需求时“感兴趣的产品、场地、案例”，之后PC端也会完善该功能； 
7、“我的订单”需要做，我会联系设计师设计，但是要等一段时间
8、不需要做待付款


问题:
2.定制页面 联系咨询师
3.侧边栏 联系我们 设置
4.登录 微信登录 找回密码
5.详情页的电话 咨询 和加入心愿单
6.个人中心/个人资料编辑  邮箱 性别  地址 待付款 消息数  我的订单要不要做 我的收藏要不要做 
7.location接口没有数据
8.认证信息 无法判断取得"已认证XX天"


※※※※※※※※使用说明※※※※※※※※※※※※
目前使用的是用json文件模拟请求的,
	实际调动接口时请将scripts文件夹中common.js中requestUrl 改为实际请求地址
	注释掉axios.post = axios.get;
	修改文件上传请求方式 get->post

3.移动和web的自动跳转

添加
function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}

var baseUrl = "";
if (IsPC) {
    window.location.href = baseUrl;
} else {
    window.location.href = baseUrl + 'm/index.html'
}