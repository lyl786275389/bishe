<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link href="static/css/style.css" rel="stylesheet" />
</head>
<body id='applyFormBody'>
	<form action="${pageContext.request.contextPath}/login.zhtml"
		method="post" class='form-inline'>
		<div class='form-group'>
			<label for="">用户名</label>
			<div class='form-input'>
				<input type="text" id='name' placeholder='请输入您的姓名' name='username'>
			</div>
		</div>
		<div class='form-group'>
			<label for="">密码</label>
			<div class='form-input'>
				<input type="text" id='name' placeholder='请输入您的密码' name="password">
			</div>
		</div>
		<div class="form-group  col-lg-6">  
    <label for="id" class="col-sm-4 control-label">  
        验证码:  
    </label>  
	    <div class="form-group  col-lg-6">  
	        <input type="text" id="code" name="code" placeholder='请输入验证码' />  
	        <img id="imgObj" alt="验证码" src="${pageContext.request.contextPath}/validateCode" onclick="changeImg()"/>  
	        <a href="#" onclick="changeImg()">换一张</a>  
	    </div>  
	</div> 
		<!-- 用户名：<input type="text" name="username"></br> 
		密码： <input type="password" name="password"></br> -->
		<button type="submit" class='btn'>登录</button>
		<p>${error}</p>
	</form>
</body>

<script src="http://libs.baidu.com/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript">  
    // 刷新图片  
    function changeImg() {  
        var imgSrc = $("#imgObj");  
        var src = imgSrc.attr("src");  
        imgSrc.attr("src", changeUrl(src));  
    }  
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳  
    function changeUrl(url) {  
        var timestamp = (new Date()).valueOf();  
        var index = url.indexOf("?",url);  
        if (index > 0) {  
            url = url.substring(index, url.indexOf(url, "?"));  
        }  
        if ((url.indexOf("&") >= 0)) {  
            url = url + "×tamp=" + timestamp;  
        } else {  
            url = url + "?timestamp=" + timestamp;  
        }  
        return url;  
    }  
</script> 
</html>