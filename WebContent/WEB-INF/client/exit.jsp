<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String path = request.getContextPath();
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<title>关闭窗口</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 引用Buttons -->
<link rel="stylesheet" href="Buttons/css/font-awesome.min.css">
<link rel="stylesheet" href="Buttons/css/buttons.css">

<!-- 引用bootstrap -->
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
<script src="js/bootstrap.min.js"></script>

</head>
<body>
<script type="text/javascript">
	function CloseWin()
	{     
	window.opener=null;     
	window.open("","_self");     
	window.close();     
	}     
</script>
	
	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span2">
			&nbsp;
		</div>
		<div class="span8">
			<div align="center" style="padding-top: 130px;">
			
			<label><strong>已经成功提交!</strong></label>
			<span class="button-wrap"><a href="javascript:;" class="button button-circle" onclick="CloseWin();">关闭窗口</a></span>
			</div>
		</div>
		<div class="span2">
			&nbsp;
		</div>
	</div>
</div>
</body>
</html>