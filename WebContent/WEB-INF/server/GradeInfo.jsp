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
<title>学生成绩信息</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">

<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid" style="padding-top: 20px;">
		<div class="row-fluid">
			<div class="span8">
				&nbsp;
			</div>
			<div class="span4">
				<form class="form-search">
					<input class="input-medium search-query" type="text" /> <button type="submit" class="btn">查找</button>
				</form>
			</div>
		 </div>
			
	
	<div class="row-fluid">
		<div class="span1">
			&nbsp;
		</div>
		<div class="span10">
			<form action="server/updateGrade.do" method="post" id="update_Grade" class="blur_form">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>
							学号
						</th>
						<th>
							姓名
						</th>
						<th>
							题号
						</th>
						<th>
							成绩
						</th>
						<th>
							日期
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.GradeInfoList" id="s" status="st">
					<tr>
						<td>
							<div class=""><s:if test="%{#s != null && #s.student_ID != null}"><s:property value="#s.student_ID"/></s:if><s:else>&nbsp;</s:else></div>
						</td>
						<td>
							<div class=""><s:if test="%{#s != null && #s.name != null}"><s:property value="#s.name"/></s:if><s:else>&nbsp;</s:else></div>
						</td>
						<td>
							<div class=""><s:if test="%{#s != null && #s.question_ID != null}"><s:property value="#s.question_ID"/></s:if><s:else>&nbsp;</s:else></div>
						</td>
						<td>
							<div class=""><s:if test="%{#s != null && #s.grade != null}"><s:property value="#s.grade"/></s:if><s:else>&nbsp;</s:else></div>
						</td>
						<td>
							<div class=""><s:if test="%{#s != null && #s.date != null}"><s:property value="#s.date"/></s:if><s:else>&nbsp;</s:else></div>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
			</form>
			
			<div class="pagination pagination-centered pagination-large">
				<ul>
					<li>
						<a href="#">上一页</a>
					</li>
					<li>
						<a href="#">1</a>
					</li>
					<li>
						<a href="#">2</a>
					</li>
					<li>
						<a href="#">3</a>
					</li>
					<li>
						<a href="#">4</a>
					</li>
					<li>
						<a href="#">5</a>
					</li>
					<li>
						<a href="#">下一页</a>
					</li>
				</ul>
			</div>
			
		</div>
		<div class="span1">
			&nbsp;
		</div>
	</div>
</body>
</html>