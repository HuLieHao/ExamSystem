<%@page import="org.apache.struts2.ServletActionContext"%>
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
<title>考试页面</title>

<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>

<!-- 引用富文本编辑器的JS -->
<script type="text/javascript" src="ueditor2/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor2/ueditor.all.js"></script>

<!-- 引用Buttons -->
<link rel="stylesheet" href="Buttons/css/font-awesome.min.css">
<link rel="stylesheet" href="Buttons/css/buttons.css">

<!-- 引用bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/bootstrap.min.js"></script>

<!-- 引用倒计时插件 -->
<script src="js/kkcountdown.min.js"></script>
<script type="text/javascript">
            $(document).ready(function(){

                $(".kkcountdown-1").kkcountdown({
                	dayText		: 'day ',
                	daysText 	: 'days ',
                    hoursText	: 'h ',
                    minutesText	: 'm ',
                    secondsText	: 's',
                    displayDays	:	true,
                    displayZeroDays : false,
                    callback	: test,
                    rusNumbers  :   false
                });

              
            });

            function test(){
            	alert("时间已到！提交答案");
            	getContent();
            	var form = document.getElementById("form");
            	form.submit();
            }
        </script>
</head>

<!-- [if it's IE6]  -->
  <!-- bsie css 补丁文件 -->
  <link rel="stylesheet" type="text/css" href="css/bootstrap-ie6.min.css">
  <!-- bsie 额外的 css 补丁文件 -->
  <link rel="stylesheet" type="text/css" href="css/ie.css">
  <!-- bsie js 补丁只在IE6中才执行 -->
  <script type="text/javascript" src="js/bootstrap-ie.js"></script>
<!-- [endif] -->
<body>

<script type="text/javascript">

	var editor2 = new baidu.editor.ui.Editor();
	editor2.render('contentValue');
	
	
	
	var editor = new baidu.editor.ui.Editor();
	editor.render('editorValue');
	 
	editor2.setDisabled();
	function getContent()
	{
		
		var desc = $('#editorValue');
		desc.val(editor.getPlainTxt());

	}
	
</script>

<%

 

 String timestamp = (String)ServletActionContext.getRequest().getAttribute("timeStamp");

%>
<div class="container" style="padding-top: 20px;">
<form id="form" action="client/uploadAnswerAct.do" method="post" onsubmit="getContent();">
	
	<div class="control-group">
    	<div class="controls ">
    		<label>个人信息：${sessionScope.user.name}${sessionScope.user.student_ID}</label>
    		
			 <a id="modal-670664" href="#modal-container-670664" class="button button-primary button-pill offset3" data-toggle="modal" onclick="getTestContent();">查看你的考试试题</a>
			<span data-time="<%=timestamp %>" class="kkcountdown-1 offset4">剩余时间：</span>
			<div id="modal-container-670664" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">关闭</button>
					<h3 id="myModalLabel">
						试题题目：${requestScope.info.title}
					</h3>
				</div>
				<div class="modal-body">
					<textarea name="contentValue" id="contentValue">"${requestScope.info.content}"</textarea>
					<script type="text/plain">
		
					</script> 
				</div>
				<div class="modal-footer">
					
				</div>
			</div>
		 </div>
	</div>

	<div class="control-group">
        	
        		<textarea name="editorValue" id=editorValue></textarea>
				<script type="text/plain">
				</script> 
				<br>
				<br/>
				<button type="submit" class="btn btn-primary" onclick="return confirm('是否确定提交');">提交</button>
			
	</div>
	</form>


	<div align="center" class="control-group">
		<!-- 
		<span class="button-wrap"><a href="javascript:;" class="button button-circle button-primary">Run it</a></span>
		 -->
	</div>
	
</div>

</body>
</html>