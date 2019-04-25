<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>结果回显</title>
	<link rel="stylesheet" href="../static/css/style.css">
</head>
<body>
	<div id="loadLayerGray" ></div>
	<div id="loadLayer" >
		<img src="../static/img/loading.gif" alt="">
		<p>请您录入密码和人像后等待...</p>
	</div>
	<div id="result">
		<h3>相似度:<span id="similar"></span></h3>
		<!-- <p id='prompt'>提示</p> -->
		<img src='' id='upLoadImg' width='500px 'height='auto'>
	</div>
</body>
<script src='../static/js/jquery-min.js'></script>
<script src='../static/js/main.js'></script>
<script>
var myIndex = 0;
var lockNum =${lockNum};
var myInterval = setInterval(function () {
	var url = '/checkFace/selectSimilar';
	var param = {
		lockId:lockNum
	};
	var data = myAjax(url,'post',param);
	console.log(JSON.stringify(data));
	 if(data.result.similary){
		$('#upLoadImg').attr('src','data:image/png;base64,'+data.result.picture);
		$('#loadLayerGray,#loadLayer').hide();
		$('#similar').text(data.result.similary);
		clearInterval(myInterval);
	}
},2000);
</script>
</html>