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
<title>Student Search Info Web</title>
</head>

<frameset rows="70px, *" >

	<frame name="search" src="server/SearchInfo.do" frameBorder="0" noresize="noresize" scrolling="no">
	<frame name="info" src="server/AllStuInfo.do" frameBorder="0" noresize="noresize" scrolling="yes">
</frameset>

<body>
</body>
</html>