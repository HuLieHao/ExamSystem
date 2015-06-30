<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String path = request.getContextPath();
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/WEB-INF/mytld/pagetag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<title>在线考试系统Beta</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

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
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="row-fluid">
				<div class="span12">
				<s:if test="%{#request.testInfoError != null}">
						<div class="alert">
  						<button type="button" class="close" data-dismiss="alert">&times;</button>
  						<strong>警告!</strong> ${requestScope.testInfoError }</div>
				</s:if>
				<s:elseif test="%{#request.testIDlist != null}">
					<table class="table table-hover table-striped">
						<thead>
							<tr>
								<th>
									编号
								</th>
								<th>
									标题
								</th>
								<th>
									描述
								</th>
								<th>
									考试时间 (时间限制：${requestScope.lessTime})
								</th>
								<th>
									<a href="client/takeTestAct.do" target="_blank" class="btn btn-primary" type="button">参加考试</a>
								</th>
							</tr>
						</thead>
						<tbody>
						<s:set name="var" value="0"/>
						<s:iterator value="#request.testIDlist" id="s" status="st">
							<s:set name="var" value="%{#var+1}"/>
							<tr>
								<td>
									<s:property value="#var"/>
								</td>
								<td>
									<s:property value="#s.get('title')"/>
								</td>
								<td>
									<s:property value="#s.get('desc')"/>
								</td>
								<td>
									${requestScope.startTime}--${requestScope.endTime}
								</td>
								<td>
									&nbsp;
								</td>
							</tr>
							</s:iterator>
						</tbody>
					</table>
					</s:elseif>
					
					
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>