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
<title></title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- [if it's IE6]  -->
  <!-- bsie css 补丁文件 -->
  <link rel="stylesheet" type="text/css" href="css/bootstrap-ie6.min.css">
  <!-- bsie 额外的 css 补丁文件 -->
  <link rel="stylesheet" type="text/css" href="css/ie.css">
  <!-- bsie js 补丁只在IE6中才执行 -->
  <script type="text/javascript" src="js/bootstrap-ie.js"></script>
<!-- [endif] -->
</head>
<body>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<br />
			<h4>考试注意事项：</h4>
			<dl>
				<dt>
					<span class="badge">1 </span>：
				</dt>
				<dd>
					在考试过程中严禁携带相关资料。
				</dd>
				<dd><br />
				</dd>
				<dt>
					<span class="badge">2 </span>：
				</dt>
				<dd>
				</dd>
				<dd>
					考试过程中严禁交头接耳。
				</dd>
				<dd><br />
				</dd>
				<dt>
					<span class="badge">3 </span>：
				</dt>
				<dd>
					作业提交完成后，请迅速离开考场，并关掉电脑。
				</dd>
				<dd><br />
				</dd>
				<dt>
					<span class="badge">4 </span>：
				</dt>
				<dd>
					。。。。。。
				</dd>
				<dd><br />
				</dd>
				<dt>
					<span class="label badge-warning">若违反以上，按作弊处理</span>
				</dt>
				
			</dl>
		</div>
	</div>
	
</div>
</body>
</html>