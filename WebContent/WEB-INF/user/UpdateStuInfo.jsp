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

</head>
<body>
<div class="container-fluid" >
	<div class="row-fluid">
		<div class="span2">
			&nbsp;
		</div>
		<div class="span8">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#1" data-toggle="tab">修改密码</a>
				</li>
			</ul>
			
			<div class="tab-content">
				<div class="tab-pane active" id="1">
					<form id="updatePass" class="form-horizontal" action="user/updateUserPass.do" method="post">
					    <fieldset>
					      <div id="legend" class="">
					        <legend class=""></legend>
					      </div>
					    <div class="control-group">
					
					          <!-- Text input-->
					          <label class="control-label" for="input01">输入新密码</label>
					          <div class="controls">
					            <input id="input01" name="password" type="password" placeholder="输入密码" class="input-xlarge">
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
					            <button type="submit" class="btn btn-primary">Save</button>
					          </div>
					    </div>
					    
					    <div class="control-group">
					    	<div class="controls">
  								<strong>${requestScope.updatePassInfo}</strong>
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
						password:{
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
						password:{
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