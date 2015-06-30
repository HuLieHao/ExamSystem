<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="page" uri="/WEB-INF/mytld/pagetag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息</title>

<!-- 引用bootstrap -->
<script language="javascript" type="text/javascript" src="<%=basePath %>js/jquery-1.11.0.min.js"></script>
<link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="<%=basePath %>js/bootstrap.min.js"></script>

<!-- 验证表单插件 -->
<script src="<%=basePath %>js/jquery.validate.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=basePath %>js/EventUtil.js"></script>
<!-- [if it's IE6]  -->
  <!-- bsie css 补丁文件 -->
  <link rel="stylesheet" type="text/css" href="css/bootstrap-ie6.min.css">
  <!-- bsie 额外的 css 补丁文件 -->
  <link rel="stylesheet" type="text/css" href="css/ie.css">
  <!-- bsie js 补丁只在IE6中才执行 -->
  <script type="text/javascript" src="js/bootstrap-ie.js"></script>
<!-- [endif] -->



<script>
var var_1, var_2, var_3, var_4, var_5;
var isSave=0;
(function(){
		$('.editClick').live('click',function(){
			
			var edit=$('.editClick');
			var index=edit.index(this);
			
			if (edit.eq(index).text() == "编辑")
			{
				//判断当前表单是否保存,不能同时编辑多行数据。
				if (isSave == 0) 	
				{
					isSave = 1;
				}else if (confirm("是否保存已修改的数据！"))
				{
					var form = document.getElementById("update_form");
					form.submit();
					return;
				}else{
					return;
				}
				
				var obj=$('.replace_text');
				
				val_1=obj.eq(index * 5).text();
				val_2=obj.eq(index * 5 + 1).text();
				val_3=obj.eq(index * 5 + 2).text();
				val_4=obj.eq(index * 5 + 3).text();
				val_5=obj.eq(index * 5 + 4).text();
				
				var array_id=$('.replace_id');
				var id = array_id.eq(index).text();
								
				obj.eq(index * 5).replaceWith('<input name="st.id" type="hidden" value="'+id+'" /><input name="st.password" type="hidden"/>'+'<input name="st.student_ID" class="replace_input input-medium" type="text"  value="'+val_1+'" />');
				obj.eq(index * 5 + 1).replaceWith('<input name="st.name"  class="replace_input input-small" type="text" value="'+val_2+'" />');
				obj.eq(index * 5 + 2).replaceWith('<input name="st.sex"  class="replace_input input-mini" type="text" value="'+val_3+'" />');
				obj.eq(index * 5 + 3).replaceWith('<input name="st.classes" class="replace_input input-mini" type="text" value="'+val_4+'" />');
				obj.eq(index * 5 + 4).replaceWith('<input name="st.professional" class="replace_input input-medium" type="text" value="'+val_5+'" />');
				
				edit.eq(index).replaceWith('<a href="#" class="editClick"><img src="<%=basePath %>images/037.gif"/>保存</a>');
			
				obj=$('.replace_input');
				index=obj.index(this);
				obj.eq(index * 5).focus();
			}
			else{	
				isSave=1;
				var obj = $('.replace_input');
				
				var form = document.getElementById("update_form");
				form.submit();
				
				val_1=obj.eq(0).val();
				val_2=obj.eq(1).val();
				val_3=obj.eq(2).val();
				val_4=obj.eq(3).val();
				val_5=obj.eq(4).val();
				
				obj.eq(index * 5).replaceWith('<div class="replace_text">'+val_1+'</div>');
				obj.eq(index * 5 + 1).replaceWith('<div class="replace_text">'+val_2+'</div>');
				obj.eq(index * 5 + 2).replaceWith('<div class="replace_text">'+val_3+'</div>');
				obj.eq(index * 5 + 3).replaceWith('<div class="replace_text">'+val_4+'</div>');
				obj.eq(index * 5 + 4).replaceWith('<div class="replace_text">'+val_5+'</div>');
			
				edit.eq(index).replaceWith('<a href="#" class="editClick"><img src="<%=basePath %>images/037.gif"/>编辑</a>');
	
			}

			});
		})();
    
</script>
</head>
<body topmargin="0">
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span1">
			&nbsp;
		</div>
		<div class="span10">
			
			
			<div class="accordion-group">
			   	<div class="accordion-heading">
			      <a class="accordion-toggle" data-toggle="collapse" href="#collapseOne">
			        	添加学生信息
			      </a>
			    </div>
			    <div id="collapseOne" class="accordion-body collapse">
			      <div class="accordion-inner">
			        <form id="addStu" action="<%=basePath %>server/AddStu.do" method="post">
				        <table border="0" cellspacing="5">
				        	<tr>
				        		<td><label class="control-label">学号</label></td>
				        		<td><label class="control-label">姓名</label></td>
				        		<td><label class="control-label">性别</label></td>
				        		<td><label class="control-label">班级</label></td>
				        		<td><label class="control-label">专业</label></td>
				        		<td rowspan="2" align="right" class="offset3" width="100px">
				        			<input type="submit" value="添加"/>
				        		</td>
				        	</tr>
				        	<tr>
				        		<td>
				        			<input name="st.student_ID" type="text" class="input-medium" placeholder="输入学号"/>
				        			<input name="st.password" type="hidden" value="888888" />
				        		</td>
				        		<td>
				        			<input name="st.name" type="text" placeholder="输入姓名" class="input-small">
				        		</td>
				        		<td>
				        			<select name="st.sex" class="input-mini">
										<option value="男" selected="selected">男</option>
										<option value="女" >女</option>
									</select>
				        		</td>
				        		<td>
									<input name="st.classes" type="text" placeholder="班级" class="input-mini">
				        		</td>
				        		<td>
				        			 <select name="st.professional" class="input-medium">
										<option value="软件开发Java" >软件开发Java</option>
										<option value=".Net" >.Net</option>
										<option value="嵌入式" >嵌入式</option>
									</select>
				        		</td>
				        	</tr>
				        </table>
					</form>
			      </div>
			    </div>
		  </div>
			
		</div>
		<div class="span1">
			&nbsp;
		</div>
	</div>
</div>

<script type="text/javascript">
	(function(){
		$("#addStu").validate(
		
			{
				rules:{
					'st.student_ID':{
						required:true,
						digits:true,
						rangelength:[11,11]
					},
					'st.name':{
						required:true,
						digits:false,
						maxlength:4
					},
					'st.classes':{
						required:true,
						digits:true,
						maxlength:1
					}
				},
				messages:{
					'st.student_ID':{
						required:'学号不能为空',
						digits:'输入合法的学号',
						rangelength:'输入学号长度为11位'
					},
					'st.name':{
						required:'姓名不能为空',
						digits:'输入合法的姓名',
						maxlength:'输入合法的姓名'
					},
					'st.classes':{
						required:'班级不能为空',
						digits:'输入合法的班级',
						maxlength:'输入合法的班级'
					}
				}
			}
		)
		
	})();
</script>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span1">
			&nbsp;
		</div>
		<div class="span10">
			<s:if test="%{#request.StuInfoList == null}">
				</br>
				<h4 class="offset3">没有查找到学生信息 请 &nbsp;<a href="server/upload.do" target="main">导入学生数据</a></h4>
			</s:if>
			<s:else>
				<form action="<%=basePath %>server/UpdateStu.do" method="post" id="update_form" class="blur_form">
				<table class="table table-hover table-striped" border="0">
					<thead>
						<tr>
							<th>学号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>班级</th>
							<th>专业</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.StuInfoList" id="s" status="st">
							<tr style="fong-size: 15px">
					
					<td>
						<div class="replace_text"><s:if test="%{#s != null && #s.student_ID != null}"><s:property value="#s.student_ID"/></s:if><s:else>&nbsp;</s:else></div>
					</td>
					
					<td>
						
						<div class="replace_text"><s:if test="%{#s != null && #s.name != null}"><s:property value="#s.name"/></s:if><s:else>&nbsp;</s:else></div>
					</td>
					
					<td>
						
						<div class="replace_text"><s:if test="%{#s != null && #s.sex != null}"><s:property value="#s.sex"/></s:if><s:else>&nbsp;</s:else></div>
					</td>
					
					<td>
						
						<div class="replace_text"><s:if test="%{#s != null && #s.classes != null}"><s:property value="#s.classes"/></s:if><s:else>&nbsp;</s:else></div>
					</td>
					
					<td width="200">
						<div class="replace_text"><s:if test="%{#s != null && #s.professional != null}"><s:property value="#s.professional"/></s:if><s:else>&nbsp;</s:else></div>
						<div class="replace_id" style="display: none;"><s:if test="%{#s != null}"><s:property value="#s.id"/></s:if><s:else>&nbsp;</s:else></div>
					</td>
						
					<td>
						<div class="editClick"><s:if test="%{#s != null}"><a href="#"><img src="<%=basePath %>images/037.gif"/>编辑</a></s:if><s:else></s:else></div>
					</td>
				
					<td>
						<s:if test="%{#s != null}">
							<a href="<%=basePath %>server/DeleStu.do?id=${s.id}" onclick="return confirm('确定删除该用户吗？');"><img src="<%=basePath %>images/010.gif"/>删除</a>
						</s:if>
						<s:else>
							&nbsp;
						</s:else>
					</td>	
				</tr>
						</s:iterator>
					</tbody>
				</table>
				</form>
				
				<div class="pagination pagination-centered pagination-large">
					<page:page pager="${pb}" />
				</div>
			</s:else>
		</div>
		<div class="span1">
			&nbsp;
		</div>
	</div>
</div>

</body>
</html>