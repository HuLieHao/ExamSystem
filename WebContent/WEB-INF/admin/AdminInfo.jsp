<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员信息界面</title>

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

<!-- 引用bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/bootstrap.min.js"></script>

<!-- 验证表单插件 -->
<script src="js/jquery.validate.min.js"></script>

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
<div class="container-fluid" style="margin-top: 40px;">
	<div class="row-fluid">
		<div class="span2">
			&nbsp;
		</div>
		<div class="span8">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#1" data-toggle="tab">修改密码</a>
				</li>
				<li class="">
					<a href="#2" data-toggle="tab">待添加模块</a>
				</li>
			</ul>
			
			<div class="tab-content">
				<div class="tab-pane active" id="1">
					<form id="updatePass" class="form-horizontal" action="admin/updateAdminPass.do" method="post">
					    <fieldset>
					      <div id="legend" class="">
					        <legend class="">修改密码</legend>
					      </div>
					    <div class="control-group">
					
					          <!-- Text input-->
					          <label class="control-label" for="input01">输入新密码</label>
					          <div class="controls">
					            <input id="input01" name="admin.password" type="password" placeholder="输入密码" class="input-xlarge">
					            <p class="help-block"></p>
					          </div>
					        </div>
					
					    <div class="control-group">
					
					          <!-- Text input-->
					          <label class="control-label" for="input02">确认新密码</label>
					          <div class="controls">
					            <input name="pass" type="password" placeholder="再输一次" class="input-xlarge">
					            <p class="help-block"></p>
					          </div>
					        </div>
					        
					    <div class="control-group">
					         
					          <!-- Button -->
					          <div class="controls">
					            <input type="submit" class="btn btn-primary" value="确定"/>
					          </div>
					    </div>
					    
					    <div class="control-group">
					    	<div class="controls">
  								<h5><p class="error" style="color:red;">${requestScope.updateInfo}</p></h5>
							</div>
					    </div>
					    </fieldset>
					  </form>
				</div>
			</div>
		</div>
		<div class="span2">
			&nbsp;
		</div>
	</div>
</div>

<script type="text/javascript">

	(function(){
		$("#updatePass").validate(
				
				{
					rules:{
						'admin.password':{
							required:true,
							maxlength:8,
							minlength:4
						},
						pass:{
							required:true,
							equalTo:'#input01'
						}
					},
					messages:{
						'admin.password':{
							required:'密码不能为空',
							maxlength:'密码最大长度为8',
							minlength:'密码最小长度为4'
						},
						pass:{
							required:'密码不能为空',
							equalTo:'两次输入密码不一致!'
						}
					}
				}
		)
		
	})();
</script>
</body>
</html>