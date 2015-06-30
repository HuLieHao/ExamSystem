<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ taglib prefix="s" uri="/struts-tags" %>
   
   <%
   		String path = request.getContextPath();
   		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="img/user.ico" type="image/x-icon">
<link rel="shortcut icon" href="img/user.ico"  type="image/x-icon">
<base href="<%=basePath %>">
<title>学生登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/login.css" rel="stylesheet" media="screen">

<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/additional-methods.js"></script>

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


<div class="container">
	<div class="row">
		<div class="span6">
			<img alt="" src="img/login.jpg">
		</div>
		<div class="span5">
			<div class="login">
			 
				  <form class="form-horizontal" id="contact-form" method="POST" action="client/useloginAct.do">
					<div id="legend" class="offset1">
				        <legend class="">学生考试系统客户端登录  Beta</legend>
				      </div>
				    <div class="control-group">
				
				          <!-- StudentID input-->
				          <label class="control-label" for="name">用户名</label>
				          <div class="controls">
				            <input name="name" id="name" type="text" placeholder="输入学号" class="input-xlarge">
				          </div>
				     </div>
				
				    <div class="control-group">
				
				          <!-- Password input-->
				          <label class="control-label" for="password">密码</label>
				          <div class="controls">
				            <input type="password" name="password" id="password" placeholder="输入密码" class="input-xlarge">
				          </div>
				    </div>
			  <!-- 
			  验证码 在通过域名访问考试系统时出现 null pointer异常。通过127.0.0.1访问正常
				    <div class="control-group">
				
				          <label class="control-label" for="captcha">验证码</label>
				          <div class="controls">
				            <input type="text" name="captcha" placeholder="请输入图片中的单词" class="input-small"/>
				          </div>
				     </div>
				     
				      <div class="control-group">
				
				          <div class="controls">
				            <img src="simpleCaptcha.png">&nbsp;<a href="javascript:;" onclick="window.location.reload(); "><i class="icon-refresh"></i></a>
				          </div>
				     </div>
				 -->
				    <div class="control-group">
				
				          <!-- Button -->
				          <div class="controls">
				            <button type="submit" class="btn btn-success" data-toggle="button">登录</button>
				            <p class="help-block" style="color:red;">${requestScope.loginInfo}</p>
				          </div>
				        
				    </div>
				  </form>
 			 </div>
		</div>
		
		<div class="span1">
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function () {
	
    $('#contact-form').validate({
        rules: {
            name: {
                required: true,
                digits: true
            },
            password: {
                required: true,
            }/*,
            captcha:{
				required:true,
				remote:'user/captchaSubmit.do'
			}*/
        },
        messages:{
        	name:{
        		required:'学号不能为空',
        		digits:'输入合法的学号'
        	},
        	password:{
        		required:'密码不能为空'
        	}/*,
        	captcha:{
        		required:'请输入验证码',
        		remote:'验证码错误'
        	}*/
        },
        highlight: function (element) {
            $(element).closest('.control-group').removeClass('success').addClass('error');
        },
    });

});
</script>

</body>
</html>