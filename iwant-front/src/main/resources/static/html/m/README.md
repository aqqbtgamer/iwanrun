TODO
1.忘记密码 ->直接短信登录
4.我的账号  实际收藏数 OK 
6.城市自动定位 OK
2.首页 关键字超过两行  处理
3.提交个人资料保存修改 

BUG
1.订单提交页 条件有问题 OK
2.详情页显示问题 OK
3.第一次登录 侧滑栏公司状态没有更新 OK

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