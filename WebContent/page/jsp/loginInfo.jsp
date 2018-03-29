<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>区管信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="page/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="page/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="page/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="page/static/h-ui.admin/skin/blue/skin.css" />
<!-- Bootstrap -->
<script type="text/javascript" src="page/js/jquery-1.9.1.min.js"></script>
<link href="page/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="page/js/bootstrap.js"></script>
<script type="text/javascript">


function searchInfo(pageIndex,pageSize){	
   	var name=$("#nameID").val();
   	if(pageSize==null||pageSize==""){
   		var pageSize=$("#pageSize").val();
   	}
   	var html="";
   	$.ajax({
   		url:"loginCon/searchInfo.action",
   		async:false,
   		dateType:"json",
   		data:{
   			pageNum:pageIndex,
   			limit:pageSize,
   			name:name
   		},
   		success:function(data){
   			$("#tablediv").empty();
   			var dd=eval(data);
   			var d=dd.page.items;
   			
   			for(var i=0;i<d.length;i++){
   				html += "<tr class='text-c'>";
				html += "<td><input type='checkbox' value='"+d[i].id+"' name='checkboxes'/></td>";
				html += "<td>" + d[i].name + "</td>";
				html += "<td>" + d[i].pwd + "</td>";
				if(d[i].role==0){
					html += "<td>管理员</td>";	
				}else{
					html += "<td>工作时间 </td>";
				}
				if(d[i].state==0){
					html += "<td>已启用</td>";	
				}else{
					html += "<td>已停用</td>";		
				}
				html += "<td class='td-manage'> "
				if(d[i].state==0){
				html += "<a style='text-decoration: none' onClick='admin_stop(this,"+d[i].id+")' href='javascript:;' title='账户已启用  '<i class='Hui-iconfont' style='color: #009900;'>&#xe615;</i></a>&nbsp;&nbsp;<span class='pipe'>|</span>";
				}
				if(d[i].state==1){
				html += "<a style='text-decoration: none' onClick='admin_start(this,"+d[i].id+")' href='javascript:;'title='账户已停用 ' <i class='Hui-iconfont' style='color: #CC0033;'>&#xe631;</i></a>&nbsp;&nbsp;<span class='pipe'>|</span>";
				}
				html += "</td> "
				html += "<td>" + FormatDate(d[i].statTime) + "</td>";
				html += "<td>" + FormatDate(d[i].endTime)+"</td>";
				html += "</tr>";
   				}
   			
   				$("#tablediv").append(html);
   				$("#total").text(data.page.total);
   				$("#pageIndex").text(data.page.pageIndex);
   				$("#totalPage").text(data.page.totalPage);
   		}
   	});
}

</script>
<script type="text/javascript">
//时间格式化
function FormatDate(strTime) {
	var date = new Date(strTime);
	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
			+ date.getDate() + " " + date.getHours() + ":"
			+ date.getMinutes() + ":" + date.getSeconds();
}
//停用	
function admin_stop(obj,id){

	layer.confirm('确认要停用吗？',function(index){
		location.href = "loginCon/upState.action?id=" + id + "&states="+1;
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="账户已启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已停用</span>'); 
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:4000});
	});
}
//启用
function admin_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		location.href = "loginCon/upState.action?id=" + id + "&states="+0; 
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id})" href="javascript:;" title="账户已停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:4000});
	});
}	


//下一页
function nextPage (){
	var pageIndex=parseInt($("#pageIndex").text());
	var totalPage=parseInt($("#totalPage").text());
	var pageSize=parseInt($("#pageSize").val());
	if(pageIndex==totalPage){
		return false;
	}
	if((pageIndex+1)>totalPage){
		pageIndex=1;
	}else{
		pageIndex+=1;
	}
	searchInfo(pageIndex,pageSize);
}
//上一页
function prevPage(){
	var pageIndex=parseInt($("#pageIndex").text());
	var totalPage=parseInt($("#totalPage").text());
	var pageSize=parseInt($("#pageSize").val());
	if(pageIndex==1){
		return false;
	}
	if((pageIndex-1)==0){
		pageIndex=1;
	}else{
		pageIndex-=1;
	}
	searchInfo(pageIndex,pageSize);

}
/* 删除 */
function del(title, url, w, h){
	debugger;
	var ids="";
	var r = document.getElementsByName("checkboxes");
	for(var i=0;i<r.length;i++){
		if (r[i].checked) {
			ids += r[i].value + ",";
		}	
	}
	if(ids==""){
	layer.alert('请选择您要删除的项!')	
	}
	var id = ids.substring(0, ids.length - 1);
	layer.confirm('您确定要删除选定项？',function(index){
	location.href = "loginCon/delete.action?id=" + id; 
	$(obj).parents("tr").remove();
	layer.msg('已删除!',{icon:1,time:4000});
	});
}
/*添加*/
function add(title, url, w, h){
	layer_show(title, url, w, h);
}
/* 更新 */
function update(title,url,w,h){
	var ckbs=$("#tablediv input[type=checkbox]:checked");
	if(ckbs.length==0){
		alert("请选择要修改的信息");
		return false;
	}
	
	var id=ckbs.val();
	var url2=url+id;
	layer_show(title,url2,w,h);
}


</script>
<body>
<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 信息管理 <span class="c-gray en">&gt;</span>用户信息
	</nav>
		<div class="panel-body">
			<label for="nameId">用户名称</label> <input id="nameID" name="name"
				class="input-text" style="width: 150px; margin-right: 15px;" /> <input
				type="button" onclick="searchInfo()" value="搜索"
				class="btn btn-success" />

		</div>
		<button type="button" class="btn btn-success"
			onclick="add('添加','loginCon/toInsert.action','800','500')">添加</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success"
			onClick="update('修改','loginCon/toUpdate.action?id=','800','500')">修改</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" onClick="del()">删除</button>
		&nbsp;&nbsp;
			
		<hr />

		<div>
	<table id="tableSort"
				class="table table-border table-bordered table-bg table-hover table-striped table-sort">
				<thead>
					<tr class="text-c">
						<th></th>
						<th>姓名</th>
						<th>密码</th>
						<th>状态</th>
						<th>角色</th>
						<th>操作</th>
						<th>开始时间</th>
						<th>结束时间</th>
					</tr>
				</thead>
				<tbody id="tablediv">
					<c:forEach var="loginInfo" items="${page.items}">
						<tr class="text-c">
							<td><input type="checkbox" value="${loginInfo.id }" name="checkboxes"/></td>
							<td>${loginInfo.name}</td>
							<td>${loginInfo.pwd}</td>
							<c:if test="${loginInfo.role==0}">
							<td>管理员</td>
							</c:if>
							<c:if test="${loginInfo.role==1}">
							<td>工作人员</td>
							</c:if>
							<c:if test="${loginInfo.state==0}">
							<td>已启用</td>
							</c:if>
							<c:if test="${loginInfo.state==1}">
							<td>已停用</td>
							</c:if>
							<td class="td-manage">
							<c:if test="${loginInfo.state==0}">
									<a style="text-decoration: none"
										onClick="admin_stop(this,${loginInfo.id})" href="javascript:;"
										title="账户已启用"><i class="Hui-iconfont"
										style="color: #009900;">&#xe615;</i> </a>&nbsp;&nbsp;<span
										class="pipe">|</span>
								</c:if> 
								<c:if test="${loginInfo.state==1}">
									<a style="text-decoration: none"
										onClick="admin_start(this,${loginInfo.id})" href="javascript:;"
										title="账户已停用"><i class="Hui-iconfont"
										style="color: #CC0033;">&#xe631;</i> </a>&nbsp;&nbsp;<span
										class="pipe">|</span>
								</c:if></td>
							<td><fmt:formatDate
									value="${loginInfo.statTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${loginInfo.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
</body>

<div class="row">
			<div class="col-sm-4 col-sm-offset-8">
				共<span id="total">${page.total }</span>条数据 &nbsp;&nbsp;&nbsp; <select
					id="pageSize">
					<option value="5">5</option>
					<option value="10">10</option>
				</select> <span id="pageIndex"> ${page.pageIndex }</span>/<span
					id="totalPage">${page.totalPage }</span>页
				<button type="button" onClick="prevPage()"
					class="btn btn-primary btn-sm">上一页</button>
				<button type="button" onClick="nextPage()"
					class="btn btn-primary btn-sm">下一页</button>

			</div>
		</div>
<script type="text/javascript"
		src="page/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="page/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="page/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="page/static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript"
		src="page/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="page/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript">
$('.table-sort').dataTable({
		//"aaSorting": [[ 0, "asc" ]],//默认第几个排序
		"bLengthChange" : false, //改变每页显示数据数
		"bStateSave" : false,//状态保存
		"bPaginate" : false, //翻页功能
		"aoColumnDefs" : [
		//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		{
			"orderable" : false,
			"aTargets" : [ 0 ]
		} // 制定列不参与排序
		]
	});

</script>
</html>