<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="img/admin.ico" type="image/x-icon">
<link rel="shortcut icon" href="img/admin.ico"  type="image/x-icon">
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员登录</title>

<!-- 引用jquery -->
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

<!-- 引用bootstrap v2-->
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
	<div class="container-fluid" style="margin-top: 100px;">
		<div class="row-fluid">
			<div class="span3">
				&nbsp;
			</div>
			<div class="span6">
				<form class="form-horizontal" id="loginForm" action="admin/adminloginAct.do" method="post">
				    <fieldset>
				      <div id="legend" class="">
				        <legend class="">后台管理员登录</legend>
				      </div>
				    <div class="control-group">
				
				          <!-- name input-->
				          <label class="control-label" for="name">用户名</label>
				          <div class="controls">
				            <input type="text" name="admin.name" placeholder="用户名" class="input-xlarge ">
				          </div>
				    </div>
				
				    <div class="control-group">
				
				          <!-- password input-->
				          <label class="control-label" for="password">密码</label>
				          <div class="controls">
				            <input type="password" name="admin.password" placeholder="密码" class="input-xlarge">
				          </div>
				     </div>
				     <!-- 
				       验证码 在通过域名访问考试系统时出现 null pointer异常。通过127.0.0.1访问正常
				     <div class="control-group">
				
				          <label class="control-label" for="captcha">验证码</label>
				          <div class="controls">
				            <input type="text" name="captcha" placeholder="请输入图片中的单词" class="input-small">
				            <p class="help-block">${requestScope.informationuser}</p>
				          </div>
				     </div>
				     
				      <div class="control-group">
				
				          <div class="controls">
				            <img src="simpleCaptcha.png">&nbsp;<a href="#" onclick="window.location.reload(); "><i class="icon-refresh"></i></a>
				          </div>
				     </div>
				 -->
				    <div class="control-group">
				
				          <!-- Button -->
				          <div class="controls">
				            <input type="submit" class="btn btn-success" value="登录"/>
				          </div>
				    </div>
					<div class="control-group">
						<div class="controls">
							<h5><p class="error" style="color:red;">${requestScope.AdminLoginInfo}</p></h5>
						</div>
					</div>
				    </fieldset>
 				 </form>
				
			</div>
			<div class="span3">
				&nbsp;
			</div>
		</div>
	</div>
	
<script type="text/javascript">
	(function(){
		$("#loginForm").validate(
		
				{
					rules:{
						'admin.name':{
							required:true,
							maxlength:8
						},
						'admin.password':{
							required:true,
							maxlength:16
						}/*,
						captcha:{
							required:true,
							remote:'admin/captchaSubmit.do'
						}*/
					},
					messages:{
						'admin.name':{
							required:'用户名不能为空',
							maxlength:'用户名长度不能大于8'
						},
						'admin.password':{
							required:'密码不能为空',
							maxlength:'密码长度不能大于16'
						}/*,
						captcha:{
							required:'验证码不能为空',
							remote:'验证码输入错误'
						}*/
					},
					submitHandler:function(form){
						form.submit();
					}
				}
		);
	})();
</script>
</body>
</html>