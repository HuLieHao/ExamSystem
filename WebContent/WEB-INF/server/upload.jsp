<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath %>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>上传下载页面</title>

<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>

<!-- 引用bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen"/>
<script src="js/bootstrap.min.js"></script>

<!-- 验证表单插件 -->
<script src="js/jquery.validate.min.js"></script>
<script src="js/additional-methods.js"></script>

</head>
<body>
<!-- 

	<hr/>
	<div>
		导出学生信息:
		<a href="downloadAction">下载</a>
	</div>
	
	 -->  
  
  <div class="container-fluid">
	<div class="row-fluid">
		<div class="span2">
			&nbsp;
		</div>
		<div class="span8">
			<form id="uploadForm" class="form-horizontal" action="server/uploadAction.do" method="post" enctype="multipart/form-data" novalidate="novalidate">
			    <fieldset>
			      <div id="legend" class="">
			        <legend class="">导入学生信息</legend>
			      </div>
			    <div class="control-group">
			          <label class="control-label">选择文件</label>
			
			          <!-- File Upload -->
			          <div class="controls">
			            <input name="upload" class="input-file" type="file"/>
			            <p class="help-block"></p>
			          </div>
			    </div>
			
				<div class="control-group">
				
				          <!-- Button -->
				          <div class="controls">
				             <button class="btn btn-success" data-loading-text="Loading...">确定上传</button>
				          </div>
				</div>
				
				<div class="control-group">
					    	<div class="controls">
  								<strong>${requestScope.upSu}</strong>
							</div>
				</div>
				
				<div class="control-group">
					    	<div class="controls">
  								<strong>${requestScope.upEr}</strong>
							</div>
				</div>
				
			    </fieldset>
			  </form>
		</div>
		<div class="span2">
			&nbsp;
		</div>
	</div>
</div>
<script type="text/javascript">
	(function() {
		$("#uploadForm").validate(
				
				{
					rules:{
						upload:{
							required:true,
							//accept:"application/vnd.ms-excel|application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
							extension:'xls|xlsx'
						}
					},
					messages:{
						upload:{
							required:'请选择文件！',
							//accept:'上传的文件格式不正确！',
							extension:'上传的文件格式不正确！'
						}
					},
					submitHandler:function(form){
						form.submit();
					}
				}
		)
	})();
</script>

</body>
</html>