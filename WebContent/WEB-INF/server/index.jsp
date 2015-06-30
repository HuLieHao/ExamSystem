<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
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
</head>

<FRAMESET border="0" frameSpacing="0" rows="53px,*">
		<FRAME name="header" src="server/index_header.do" frameBorder="0" noResize scrolling="no">
		<FRAMESET cols="222px,*" id="frame">
			<FRAME name="menu" src="server/index_menu.do" frameBorder="0" noResize scrolling="no">
			<FRAME name="main" src="server/index_main.do" frameBorder="0" noResize scrolling="yes">
		</FRAMESET>
</FRAMESET>
<NOFRAMES>
<body>
您的流畅器不支持框架
</body>
</NOFRAMES>
</html>