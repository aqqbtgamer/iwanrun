/**
 * Created by WXP22 on 2018/4/14.
 */

var uploadServer = '/iwant_admin/remote/fileupload';
var submitUrl = '/iwant_admin/location/add'

$(document).ready(
    function(){
        initUE();
        bindUploadFile('mainImageUpload',uploadServer,'mainImage',singleDisplay);
        bindUploadFile('uploadedLocationImages',uploadServer,'imgManage',mutipleDisplay);
        bindDataSubmit('submitForm',
            new Array('name',
                'location_type_code',
                'special_tags',
                'group_number_limit_code',
                'activity_province_code',
                'activity_city_code',
                'activity_dist_code',
                'location',
                'mainImage',
                'imgManage'
            ),
            submitUrl);
    }
);




