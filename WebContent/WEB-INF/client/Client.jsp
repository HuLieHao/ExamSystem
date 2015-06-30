<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String path = request.getContextPath();
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生考试系统 Beta1.0</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/client.css" rel="stylesheet">

<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<style type="text/css">
body{margin:0;background:#f4f5eb;color:#000;}
</style>

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

<!-- header content -->
 <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="javascript:;">在线考试系统  Beta1.0</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="javascript:;">首页</a></li>
              <li><a href="javascript:;">关于</a></li>
              <li><a href="javascript:logout();">退出</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
     </div>
</div>  <!-- header content -->
<script type="text/javascript">
function logout(){
	  if( confirm("你确实要退出吗？？")){
	   window.parent.location.href="http://192.168.167.13:8080/ExamSystem_2.0/user/userlogin.do";
	  }
	  else{
	   return;
	  }
}
</script>
<div class="container-fluid container_top">
	<div class="row-fluid">
		<div class="span1">
			&nbsp;
		</div>
		<div class="span2">
			<img alt="140x140" src="img/head.jpg" class="img-polaroid" />
			 <div class="account-details">
				<span class="account-name">${sessionScope.user.name}</span>
				<span class="account-role">${sessionScope.user.student_ID}</span>
			</div> <!-- /account-details -->
		</div>
		<div class="span7" >
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container-fluid">
						<div class="nav-collapse collapse navbar-responsive-collapse">
							<ul class="nav nav-pills" >
								<li>
									<a href="client/main.do" target="content"  >首页</a>
								</li>
								<li class="disabled">
									<a href="javascript:;">考试成绩</a>
								</li>
								
								<li>
									<a href="client/allTestAct.do" target="content" class="dropdown-toggle">参加考试</a>
								</li>
								<li class="disabled">
									<a href="javascript:;" >学习资料</a>
								</li>
								<li class="dropdown">
									 <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:;">设置<strong class="caret"></strong></a>
									<ul class="dropdown-menu">
										<li>
											<a href="user/UpdateStuInfo.do" target="content">修改个人信息</a>
										</li>
										<li class="divider">
										</li>
										<li>
											<a href="javascript:;">发送消息</a>
										</li>
										<li class="divider">
										</li>
										<li>
											<a href="javascript:;">其他</a>
										</li>
										<li class="divider">
										</li>
									</ul>
								</li>
							</ul>
						</div>
						
					</div>
				</div>
				
			</div>
			<div class="row-fluid">
					<iframe id="iFrame1" frameborder="0" onload="this.height=iFrame1.document.body.scrollHeight" name="content" width="100%" height="100%" src="client/main.do" scrolling="no"></iframe>
				</div>
			</div>
		</div>
		<div class="span2">
			&nbsp;
		</div>
</div>
</body>
</html>