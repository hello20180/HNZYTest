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

//搜索
function searchInfo(pageIndex,pageSize) {
	var name = $('#nameID').val();
	if(pageSize==null||pageSize==""){
		var pageSize=$("#pageSize").val();
	}
	var html = "";
	$.ajax({
		url :"myInfoCon/searchInfo.action",
		async : false,
		dataType : "json",
		data:{
			pageNum:pageIndex,
			limit:pageSize,
			name:name
			},
		success : function(data) {
			$("#tablediv").empty();
			$("#tablediv").html("");
			var dd=eval(data);
			var d = dd.page.items;
			for (var i = 0; i < d.length; i++) {
				html += "<tr class='text-c'>";
				html += "<td><input type='checkbox' value='"+d[i].id+"' name='checkboxes'/></td>";
				html += "<td>" + d[i].name + "</td>";
				html += "<td>" + d[i].age + "</td>";
				html += "<td>" + d[i].school + "</td>";
				html += "<td>" + d[i].address + "</td>";
				html += "</tr>";
			}

			html += "</tbody>"
			html += "</table>";
			$("#tablediv").append(html);
			$("#total").text(data.page.total);
			$("#pageIndex").text(data.page.pageIndex);
			$("#totalPage").text(data.page.totalPage);
		}

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
</script>

<script type="text/javascript">

/*添加*/
function add(title, url, w, h){
	layer_show(title, url, w, h);
}

/*添加*/
function update(title, url, w, h){
	var ckbs=$("#tablediv input[type=checkbox]:checked");
	if(ckbs.length==0){
		alert("请选择要修改的信息");
		return false;
	}
	
	var id=ckbs.val();
	var url2=url+id;
	layer_show(title, url2, w, h);
}
function del(){
	var ids = "";
	var r = document.getElementsByName("checkboxes");
	for (var i = 0; i < r.length; i++) {
		if (r[i].checked) {
			ids += r[i].value + ",";
		}
	}
	if (ids == "") { 
           layer.alert('请选择您要删除的项!');    
		return false;
	}
	var id = ids.substring(0, ids.length - 1);
		layer.confirm('您确定要删除选定项？',function(index){
		location.href = "myInfoCon/delete.action?id=" + id; 
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:4000});
});	
}
function doExportExcel(){
	debugger;
	var name = $('#nameID').val();
	window.open("myInfoCon/MyInfodoExportExcel.action?name=" + name);

}

</script>
</head>
<body>

			<label for="nameId">用户名称</label> 
			<input class="input-text" style="width: 150px; margin-right: 15px;" placeholder="用户名称" id="nameID" name="name">
			<input type="button" onclick="searchInfo()" value="搜索" class="btn btn-success" />
 		<button type="button" class="btn btn-success" onclick="add('添加','myInfoCon/toInsert.action','800','500')">添加</button> 
			&nbsp;&nbsp;
		<button type="button" class="btn btn-success" onClick="update('修改','myInfoCon/toUpdate.action?id=','800','500')">修改</button>
	&nbsp;&nbsp;
 	 <button type="button" class="btn btn-success" onClick="del()">删除</button> 
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" onClick="doExportExcel()">导出</button>
		&nbsp;&nbsp;
		<form action="inportExcel" method="post" enctype="multipart/form-data">
		<input type="file" name="importExcel" id="importExcel"/>
		<input type="submit" value="导入"/>
		</form>
			<hr />
  			<div>
			<table id="tableSort"
				class="table table-border table-bordered table-bg table-hover table-striped table-sort">
				<thead>
					<tr class="text-c">
						<th></th>
						<th>姓名</th>
						<th>年龄</th>
						<th>学校</th>
						<th>地址</th>
					</tr>
				</thead>
				<tbody id="tablediv">
					<c:forEach var="myinfo" items="${page.items}">
						<tr class="text-c">
							<td><input type="checkbox" value="${myinfo.id }" name="checkboxes"/></td>
							<td>${myinfo.name}</td>
							<td>${myinfo.age}</td>
							<td>${myinfo.school}</td>
							<td>${myinfo.address}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
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
</body>
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