<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<!-- 导入bootStrapcss样式 -->
<link href="/springMvc_4/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<!-- 引入jquery -->
<script type="text/javascript" src="/springMvc_4/js/jquery-1.11.3.min.js"></script>
<!--引入bootStrapjs文件  -->
<script type="text/javascript" src="/springMvc_4/js/bootstrap.min.js"></script>

<!-- 引入外部js文件 -->
<script type="text/javascript" src="/springMvc_4/js/loadCustomerByPage.js"></script>
<style type="text/css">
	th{
		text-align:center; 
		vertical-align: middle;
	}
</style>


</head>
<body>
<div class="container-fluid">
   <form method="post" action="" class="form-inline">
		<table  class="table table-bordered table-striped table-hover">
		<!--添加搜索框  -->
		<tr>
		<td><input type="text" name="custName" class="form-control"></td>
		<td><input type="text" name="custLevel" class="form-control"></td>
		<td><input type="button"  class='btn btn-info btn-sm active' value="搜索" onclick="loadCustByPage(1)"></td>
		</tr>
		<tr align="center">
			<th>编号</th>
			<th>客户名称</th>
			<th>来源</th>
			<th>等级</th>
			<th>联系人</th>
			<th>联系方式</th>
			<th>出生日期</th>
			<th>图片路径</th>
            <th>操作</th>
          
		</tr>
		<!--显示主体内容部分  -->
		<tbody id="myTbody">
		
		</tbody>
		
		
		
		</table>
		<!--分页代码显示位置  -->
		<div id="myPage" align="center"></div>
		
		
	</form>
	</div>
</body>
</html>