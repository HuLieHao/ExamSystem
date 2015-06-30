
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<title>Index_Menu Web</title>

</head>
<body>
<div class="left" id="LeftBox">
	<div class="left01">
		<div class="left01_right"></div>
		<div class="left01_left"></div>
		<div class="left01_c">管理员：<a href="admin/AdminInfo.do" target="main">${sessionScope.adminInfo.name }</a></div>
	</div>
	<div class="left02">
		<div class="left02top">
			<div class="left02top_right"></div>
			<div class="left02top_left"></div>
			<div class="left02top_c">学生信息管理</div>
		</div>
	  <div class="left02down">
			<div class="left02down01"><a href="server/StuInfo.do" target="main">学生个人信息</a></div>	
		    <div class="left02down01"><a href="server/GradeInfo.do" target="main">学生成绩信息</a></div>
			<div class="left02down01"><a href="server/upload.do" target="main">导入导出数据</a></div>
		</div>
	</div>
	
	<div class="left02">
		<div class="left02top">
			<div class="left02top_right"></div>
			<div class="left02top_left"></div>
			<div class="left02top_c">考试试题管理</div>
		</div>
		<div class="left02down">
			<div class="left02down01"><a  href="server/allQuestionsAct.do" target="main">试题信息</a></div>
			
		</div>
	</div>
	
	<div class="left02">
		<div class="left02top">
			<div class="left02top_right"></div>
			<div class="left02top_left"></div>
			<div class="left02top_c">考试记录信息管理</div>
		</div>
		<div class="left02down">
			<div class="left02down01"><a  onclick="show_menu(83)" href="javascript:;">考试记录信息</a></div>	
		</div>
	</div>
	<div class="left02">
		<div class="left02top">
			<div class="left02top_right"></div>
			<div class="left02top_left"></div>
			<div class="left02top_c">考试规则管理</div>
		</div>
		<div class="left02down">
			<div class="left02down01"><a href="server/PreRulesAct.do" target="main">考试规则设置</a></div>
			
		</div>
	</div>

	<div class="left01">
		<div class="left03_right"></div>
		<div class="left01_left"></div>
		<div class="left03_c"><a href="javascript:logout();">安全退出</a></div>
	</div>
	
	
</div>

<script type="text/javascript">
		
		 function logout(){
			  if( confirm("你确实要退出吗？？")){
			   window.parent.location.href="http://127.0.0.1:8080/ExamSystem/admin/adminlogin.do";
			  }
			  else{
			   return;
			  }
		}
		
</script>
</body>
</html>