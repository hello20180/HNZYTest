<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加用户</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="page/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="page/static/h-ui.admin/css/H-ui.admin.css" />
	<script type="text/javascript"
		src="page/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="page/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="page/lib/validform/2.2.0/validate.min.js"></script>
	<script type="text/javascript" src="page/lib/validform/2.2.0/validate-methods.js"></script>
	<script type="text/javascript" src="page/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript" src="page/static/h-ui.admin/js/H-ui.admin.js"></script>
		<script type="text/javascript">
	   function findName(){
		   var name=$("#nameId").val();
		   	$.ajax({
		   		url:"loginCon/findName.action",
		   		async:false,
		   		dateType:"json",
		   		data:{
		   			name:name
		   		},
		   		success:function(data){	
		   			var d=data.json;
		   			if(d==1){
		   			 document.getElementById('na').innerHTML = "";
		   			 $("#na").append("<div><font color='#FF4500'>用户名重复</font></div>");
		   			 $("#nameId").val("").focus(); // 清空并获得焦点
		   				return false;
		   			}else{
		   			document.getElementById('na').innerHTML = "";
		   				return true;
		   			}
		   		}
		   	});
		}
		
		</script>
</head>

<body>
	<div class="page-container">
		<form class="form form-horizontal" id="form-user-pwd" action="loginCon/Insert.action" >
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"> <span class="c-red">*</span>用户名:
				</label>
				<div style="float: left;" class="formControls col-5">
					<input required autofocus type="text" class="input-text" name="name" id="nameId" onblur="findName()">
				</div>
				<div id="na"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"> <span
					class="c-red">*</span>密码:
				</label>
				<div style="float: left;" class="formControls col-5">
					<input type="password" class="input-text" id="pwdId" name="pwd" required>
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"> <span
					class="c-red">*</span>状态:
				</label>
				<div style="float: left;" class="formControls col-5">
					<select name="state" id="stateId">
					<option value=0>账户启用</option>
					<option value=1>账户停用</option>
					</select>
				</div>
			</div>
			
			
			
			
				<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"> <span
					class="c-red">*</span>角色:
				</label>
				<div style="float: left;" class="formControls col-5">
					<select name="role" id="RoleId">
					<option value=0>管理员</option>
					<option value=1>工作人员</option>
					</select>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit"
						value="&nbsp;&nbsp;添&nbsp;加&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</div>
</body>
</html>
