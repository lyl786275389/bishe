//ajax请求

var myAjax = function (url, method, param,type,async) {
    var result = null;
    // alert($api.getStorage('loginData'));
    var newParam = null;
    if(type){
        var paramObj = JSON.parse(param); 
        // paramObj.userToken = $.cookie('loginData');
        newParam = JSON.stringify(paramObj);
    }else {
        // param.userToken = $.cookie('loginData');
        newParam = param;
    }
    var asyncStatu = false;
    if(async){
        asyncStatu = true;
    }
    var ajaxParam = {
        url: url,
        type: method,
        data: newParam,
        cache: false,
        async:asyncStatu,
        success: function(data) {
            result = data;
        },
    };
     if(type){
        ajaxParam.contentType = "application/json; charset=UTF-8";
    }
    if (method.toLowerCase() == "get") {
        ajaxParam.datatype = "json";
        ajaxParam.contentType = "application/json; charset=UTF-8";
    } else {
        if (typeof(param) == "string") {
            ajaxParam.contentType = "application/json; charset=UTF-8";
        }
    }

    $.ajax(ajaxParam);
    return result;
};