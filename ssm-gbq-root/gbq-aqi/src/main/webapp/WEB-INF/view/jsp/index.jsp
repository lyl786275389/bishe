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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>信息录入</title>
	<link rel="stylesheet" href="static/css/style.css">
</head>
<body id='applyFormBody'>
	<form action="" class='form-inline'>
		<div class='form-group'>
			<label for="">您的姓名</label>
			<div class='form-input'>
				<input type="text" id='name' placeholder='请输入您的姓名'>
			</div>
		</div>
		<div class='form-group'>
			<label for="">身份证号</label>
			<div class='form-input'>
				<input type="text" id='idNum' placeholder='请输入您的身份证号码'>
			</div>
		</div>
		<div class='form-group'>
			<label for="">您的锁号</label>
			<div class='form-input'>
				<input type="text" id='lockNum' placeholder='请输入您的锁号'>
			</div>
		</div>
		<div class='form-group'>
			<label for="">密码</label>
			<div class='form-input'>
				<input type="password" id='password' placeholder='请输入您的密码' maxlength="12">
			</div>
		</div>
		<div class='form-group'>
			<label for="">相似度阈值</label>
			<div class='form-input'>
				<input type="text" id='threshold' placeholder='请输入相似度阈值(最多两位有效小数点)'>
			</div>
		</div>
		<button type='button' class='btn' onclick='submitF()'>提交申请</button>
	</form>
</body>
<script src  ='static/js/jquery-min.js'></script>
<script src='static/js/main.js'></script>
<script>
function submitF() {
	var url = '/checkFace/updateByLockId';
	var name = $('#name').val();
	var idNum = $('#idNum').val();
	/* var lockNum = $('#lockNum').val(); */
	var password = $('#password').val();
	var threshold = $('#threshold').val();
	
	var IdReg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	if(name.length<1){
		alert('请输入用户名');
		return;
	}
	if(IdReg.test(idNum) === false) {
		alert('请输入正确的身份证号码');
		return;
	}
	if(lockNum=='' || lockNum == null){
		alert('请输入锁号');
		return;
	} 
	if(password.length !=12){
		alert('请输入12位密码');
		return;
	}
	if(threshold.length<1){
		alert('阈值不能为空');
		return;
	}
	
	var param = {
		name:name,
		idNum:idNum,
		lockId:1,
		password:password,
		threshold:threshold
	};
	var data = myAjax(url,'post',param);
	console.log(JSON.stringify(data));
	if(data.success){
		window.location.href='./checkFace/result.zhtml?lockNum='+1;
	}else{
		alert(data.errorMsg);
	}
}
</script>
</html>