<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>无线测温管理系统 v1.0</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link href="page/css/login.css" rel="stylesheet" type="text/css">

	</head>
<script type="text/javascript">
//解决子框架嵌套的问题  
if(window != window.parent){  
    window.parent.location.reload(true);  
}  
</script>
	<body>
		<div class="login">
			<form action="loginCon/loginInfo.action" method="post">
				<div class="logo"></div>

				<div class="login_form">
					<div class="user">
						<input class="text_value" name="name" type="text"
							id="name" required autofocus>
						<input class="text_value" name="pwd" type="password"
							id="pwd" required autofocus>
					</div>
					<button class="button" type="submit">
					登录	
					</button>
				</div>
                <div id="tip">${msg}</div>
				<div class="foot">
					Copyright &copy; 2017.Company name All rights reserved.
				</div>
			</form>
		</div>

	</body>
</html>
