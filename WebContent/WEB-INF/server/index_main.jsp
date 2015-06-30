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
<link href="css/style.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>

<script type="text/JavaScript"> 
var temp=0;
function switchBar(){
	if (temp==0)
	{
		parent.frame.cols="0,*";
		document.getElementById('Mobile').style.background='url(images/center.gif)';
		
		temp=1;	
	}
	else{
		parent.frame.cols="222px,*";
		document.getElementById('Mobile').style.background='url(images/center0.gif)';
		
		temp=0;	
	}
}
</script>

</head>
<body>
	
<div class="rrcc"  id="RightBox">
	<div class="center" id="Mobile" onclick="switchBar()"></div>
	<div class="right" id="li010">
		<div class="right01"><img src="images/04.gif" /> <span>欢迎使用上机考试管理</span></div>
	</div>
</div>

</body>
</html>