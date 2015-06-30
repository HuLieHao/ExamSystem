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
<title>考试试题信息</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap -->
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
<script language="javascript" type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/EventUtil.js"></script>

<!-- 引用富文本编辑器的JS -->
<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor/ueditor.all.js"></script>
<script type="text/javascript">

	var editor = new baidu.editor.ui.Editor();
	 editor.render('editorValue'); 
	 
(function(){
	$('.editClick').live('click',function(){
		var click = $('.editClick');
		var obj=$('.questionID');
		var index=click.index(this);
		var questionID = obj.eq(index).text();
		var nameHttp;
		try{
			nameHttp = new XMLHttpRequest();
		}catch(e) {
			nameHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		nameHttp.open('POST', 'json/seeExamAct.do', true);
		nameHttp.onreadystatechange = function() {
			if (nameHttp.readyState == 4 && nameHttp.status == 200)
			{
				var userObj = eval('(' + nameHttp.responseText + ')'); 
				var title = $('#title');
				var desc = $('#desc');
				var id = $('#clearId');
				var flag = $('#flag');
				id.val(userObj.id);
				flag.val(userObj.flag);
				title.val(userObj.title);
				desc.val(userObj.desc);
				editor.setContent(userObj.content,false);
			}
		};
		nameHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		nameHttp.send("questionID="+questionID);
		
	});
	$('#insertClick').live('click',function(){
		var id = $('#clearId');
		id.val('-1');
		var title = $('#title');
		var desc = $('#desc');
		title.val(null);
		desc.val(null);
		editor.setContent("");
	});
	
})();
</script>
<div class="container-fluid" style="padding-top: 20px;">
	<div class="row-fluid">
		<div class="span12">
			<div class="row-fluid">
				<div class="span8">
				&nbsp;
				</div>
				<div class="span4">
					<form class="form-search form-inline">
						<input class="input-medium search-query" type="text" /> <button type="submit" class="btn">查找</button>
					</form>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span1">
					&nbsp;
				</div>
				<div class="span10">
					<table class="table table-hover table-striped">
						<thead>
							<tr>
								<th>
									题号
								</th>
								<th>
									标题
								</th>
								<th>
									描述
								</th>
								<th>
									状态
								</th>
								<th>
								<div class="btn-group">
									<a href="#collapseOne" role="button" class="btn accordion-toggle" id="insertClick"  data-toggle="collapse" data-parent="#accordion2">插入试题</a>
								</div>
								</th>
							</tr>
						</thead>
						<tbody>
						<s:iterator value="#request.examInfoList" id="s" status="st">
							<tr>
								<td>
									<div class="questionID"><s:if test="%{#s.id != null}"><s:property value="#s.id"/></s:if><s:else>&nbsp;</s:else></div>
								</td>
								<td>
									<s:if test="%{#s.title != null}"><s:property value="#s.title"/></s:if><s:else>&nbsp;</s:else>
								</td>
								<td>
									<s:if test="%{#s.description != null}"><s:property value="#s.description"/></s:if><s:else>&nbsp;</s:else>
								</td>
								<td>
									<s:if test="%{#s.flag == 0}">未参加考试</s:if><s:elseif test="%{#s.flag == 1}">已参加考试</s:elseif>
								</td>
								<td>
									<s:if test="%{#s != null}">
										<div class="btn-group">
											  <s:if test="%{#s.flag == 0}"><a href="server/addExamAct.do?id=${s.id}" class="btn">参加考试</a></s:if><s:else><a href="server/delExamAct.do?id=${s.id}" class="btn btn-success">取消考试</a></s:else>
											  <div class="editClick"><button class="btn">查看试题</button></div>
											 <a href="server/delQuestionAct.do?id=${s.id}" class="btn " onclick="return confirm('确定删除该用户吗？');">删除试题</a>
										</div>
									</s:if><s:else></s:else>
								</td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
					<div class="pagination pagination-centered pagination-large">
					<page:page pager="${pb}" />
					</div>
				</div>
				<div class="span1">
					&nbsp;
				</div>
			</div>
		</div>
	</div>
</div>
<div class="accordion" id="accordion2">
  <div class="accordion-group">
    <div id="collapseOne" class="accordion-body collapse">
      <div class="accordion-inner">
        <div id="newscontent"></div> 
        	<form action="server/ExamQuestionsAct.do" method="post">
        		<textarea name="exam.content" id=editorValue></textarea>
				<script type="text/javascript" name="editorValue" id=editor> 
	   				
				</script> 
				<br>
				<label class="control-label">标题</label>
				<div class="controls">
						<INPUT type="text" name="exam.title" id="title" placeholder="标题....." >
						<input type="hidden" name="id" id="clearId" value="0">
						<input type="hidden" name="exam.flag" id="flag" value="0">
				</div>
				<label class="control-label">描述</label>
				<div class="controls">
						<textarea rows="3" name="exam.description" id="desc"></textarea>
				</div>
				<br/>
				<button type="submit" class="btn btn-primary">Save</button>
			</form>
      </div>
    </div>
  </div>
</div>


</body>
</html>