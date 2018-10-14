/**
 * Created by WXP22 on 2018/10/14.
 */
console.log("add relation app js 加载");
const locationQueryUrl = '/iwant_admin/location/queryAll';
const caseQueryUrl = '/iwant_admin/cases/queryAll';
const productionQueryUrl = '/iwant_admin/productionInfo/find';
var type = getUrlParam('type');
var oprationId = getUrlParam('id');
var target = getUrlParam('add');
const locationTableHead = new Array(
		'',

);

