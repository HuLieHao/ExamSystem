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
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="javascript" type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<title>Search Info Web</title>

<style type="text/css">
	.doubleselect br{
			display: none;
			}
</style>
<script type="text/javascript">

	
	(function(){
		$('.ChoiceClasses').live('change',function(){
		
							var edit=$('.ChoiceClasses');
							var index=edit.index(this);
							
							var form = document.getElementById("choiceForm");
							form.submit();
		});
		})();
</script>
</head>
<body leftmargin="10"  topmargin="10" >

	
	<s:set name="bs" value="#{'请选择专业':{'请选择班级'},
							  '软件开发Java':{'请选择班级','全部','1','2','3','4'},
							  '.Net':{'请选择班级','全部','1','2','3'},
							  '嵌入式':{'请选择班级','全部','1','2'} }"
	/>


<table border="0"  cellpadding="0" cellspacing="0">

	<tr style="">
		<td rowspan="2" width="500px">
			<div class="doubleselect">
				<s:form action="server/ChoiceStu.do" method="post" target="info" id="choiceForm">
					<s:doubleselect label="选择要查看的数据" doubleList="#bs[top]" list="#bs.keySet()"    name="choiceValue_1" doubleName="choiceValue_2" doubleCssClass="ChoiceClasses"/>
				</s:form>
			</div>
		</td>
		<td width="240">&nbsp;</td>
		<td></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td> 	<s:fielderror/>
			<form action="server/SearchStu.do" method="post" target="info">
	            <select name="searchType">
		    			<option value="0" selected="selected">学号</option>
		    			<option value="1" >姓名</option>
		    	</select>
		    	
		    	<input name="searchValue" size="22" title="搜索学号和姓名..." maxlength="11" value="">
	            <input type="submit" value="搜索">
	        </form>
		</td>
	</tr>
</table>
</body>
</html>