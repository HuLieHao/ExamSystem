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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ExamSystem Rules</title>

<!-- Date -->

<LINK href="css/bootstrap.min.css" rel="stylesheet"  media="screen"> 
<LINK href="css/bootstrap-responsive.css" rel="stylesheet">
<LINK href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" media="screen">  
   
<SCRIPT src="js/jquery-1.11.0.min.js" type="text/javascript"></SCRIPT>
   
<SCRIPT src="js/bootstrap.min.js" type="text/javascript"></SCRIPT>
   
<SCRIPT src="js/bootstrap-datetimepicker.min.js" type="text/javascript"></SCRIPT>
 
<SCRIPT src="js/bootstrap-datetimepicker.pt-BR.js" type="text/javascript"></SCRIPT>
 
<SCRIPT src="js/respond.min.js"></SCRIPT>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="#1" data-toggle="tab">考试时间规则</a>
		</li>
		<li>
			<a href="#2" data-toggle="tab">考题选择规则</a>
		</li>
		<li>
			<a href="#3" data-toggle="tab">监控规则</a>
		</li>
		<li>
			<a href="#4" data-toggle="tab">上传目录</a>
		</li>
		<li>
			<a href="#5" data-toggle="tab">IP限制</a>
		</li>
		<li>
			<a href="#6" data-toggle="tab">作业批改</a>
		</li>
		<li>
			<a href="#7" data-toggle="tab">考试学生限制</a>
		</li>
	</ul>
	
	<br />
	
	<div class="tab-content">
		<div class="tab-pane active" id="1">
			<form id="edit-profile1" class="form-horizontal" action="server/RulesAct.do" method="post">
				
				<div class="control-group">
					    	<div class="controls">
					    		<strong>你已经设置了第 ${requestScope.testID-1} 场考试时间。</strong>
  								<strong>请设置第 ${requestScope.testID} 场考试时间</strong>
							</div>
				</div>
				
				<div class="control-group">	
																	
					<label class="control-label" for="startTime">考试开始时间</label>
					<div class="controls">
						<DIV class="input-append date" id="datetimepicker1">
							<INPUT type="text" name="startTime" data-format="yyyy/MM/dd hh:mm:ss" placeholder="yyy/MM/dd hh:mm:ss"/>     
							<SPAN class="add-on">
								<I data-time-icon="icon-time" data-date-icon="icon-calendar"></I>
							</SPAN> 
						</DIV>
						<SCRIPT type="text/javascript">
 							 $(function() {
							   $('#datetimepicker1').datetimepicker({
							     language: 'pt-BR'
							   });
							 });
						</SCRIPT>
					</div> <!-- /controls -->	
				
				</div> <!-- /control-group -->
											
											
				<div class="control-group">		
														
					<label class="control-label" for="endTime">考试结束时间</label>
					<div class="controls">
						<DIV class="input-append date" id="datetimepicker2">
							<INPUT type="text" name="endTime" data-format="yyyy/MM/dd hh:mm:ss" placeholder="yyy/MM/dd hh:mm:ss"/>     
							<SPAN class="add-on">
								<I data-time-icon="icon-time" data-date-icon="icon-calendar"></I>
							</SPAN> 
						</DIV>
						<SCRIPT type="text/javascript">
								$(function() {
								   $('#datetimepicker2').datetimepicker({
								     language: 'pt-BR'
								   });
								 });
						</SCRIPT>
					</div> <!-- /controls -->	
									
				</div> <!-- /control-group -->
			
				<hr />
			
				<div class="control-group">		
														
					<label class="control-label" for="lessTime">考试时间限制</label>
					<div class="controls">
						<DIV class="input-append" id="datetimepicker3">
							<INPUT type="text" name="lessTime" data-format="hh:mm:ss" placeholder="hh:mm:ss"/>     
							<SPAN class="add-on">
								<I data-time-icon="icon-time" data-date-icon="icon-calendar"></I>
								
							</SPAN> 
						</DIV>
						<p class="help-block">学生的考试时间限制(如30分钟内不准重考).</p>
						<SCRIPT type="text/javascript">
								$(function() {
								   $('#datetimepicker3').datetimepicker({
									   pickDate: false
								   });
								 });
						</SCRIPT>
					</div>			
				</div>
				
				<div class="control-group  offset3">
					<div class="controls">
						<strong><a href="server/clearTimeAct.do">清除所有的考试时间</a></strong>
					</div>
				</div>
				
				<div class="control-group">
					    	<div class="controls">
  								<strong>${requestScope.timeInfo}</strong>
							</div>
				</div>
				<br />
				
				<div class="form-actions" style="background-color: inherit;">
					<INPUT type="hidden" name="timeId" value="${requestScope.testID}"/>
					<button type="submit" class="btn btn-primary">Save</button> <button type="reset" class="btn">Cancel</button>
				</div>
			</form>
		</div>
								
		<div class="tab-pane" id="2">
			<form id="edit-profile2" class="form-horizontal" action="server/RulesQuestionAct.do" method="post">
				<div class="control-group">
					<label class="control-label" for="accounttype">考题选择运算</label>
					<br />
					<br />
					<div class="controls">
					<!--  	<label class="radio">
							<input type="radio"   name="question1" value="1" id="accounttype" />
							根据学号的最后一位设置考生考题 (学号除以题数取余设置考题)
						</label>
						
						<label class="radio">
							<input type="radio" name="question1" value="2" />
							根据IP地址的最后一位设置考生考题 (IP地址的最后一位除以题数取余设置考题)
						</label>
						
						<label class="radio">
							<input type="radio" name="question1" value="3" />
							根据座位分布设置考生考题  (保证每一位同学的与前后左右的同学考题都不一样)
						</label>
				   -->
				   
				   学号最后一位&nbsp;
				   <select class="input-mini" name="OperID">
					  <option>+</option>
					  <option>-</option>
					  <option>*</option>
					  <option>/</option>
					</select>
				&nbsp;IP最后一位&nbsp;
					<select class="input-mini" name="OperIP">
					  <option>+</option>
					  <option>-</option>
					  <option>*</option>
					  <option>/</option>
					</select>
				&nbsp;题数&nbsp;
					+
				&nbsp;&nbsp;
					<select class="input-mini" name="OperNum">
					  <option>1</option>
					  <option>2</option>
					  <option>3</option>
					  <option>4</option>
					  <option>5</option>
					  <option>6</option>
					  <option>7</option>
					  <option>8</option>
					  <option>9</option>
					</select>
					</div>
				</div>
				
				
				<div class="control-group">
					    	<div class="controls">
  								<strong>${requestScope.rulesInfo}</strong>
							</div>
				</div>
				
				<br />
											
				<div class="form-actions" style="background-color: inherit;">
					<button type="submit" class="btn btn-primary">Save</button> <button class="btn">Cancel</button>
				</div>
							
			</form>
		</div>
		
		<div class="tab-pane" id="3">
			Pane 3
		</div>
		
		<div class="tab-pane" id="4">
			<form id="edit-profile4" class="form-horizontal" method="post" action="server/dirAct.do">
				<div class="control-group">
					<label class="control-label" for="accountadvanced">上传目录</label>
					<div class="controls">
						<input type="text" name="directory" id="backDir" class="input-xlarge accordion-toggle" data-toggle="collapse" href="#collapseOne" value="C:\" />
						<button type="submit" class="btn">确定</button>
					</div>
				</div>
				<div class="control-group accordion-body collapse" id="collapseOne">
						<label class="control-label" for="accountadvanced">目录</label>
						<div class="controls">
			     				 <div class="accordion-inner">
			      				 	<select class="input-medium" name="tables_drive" id="tables_drives_1" onchange="get_drives_1()"">
									</select>
									
			     				 </div>
			     				 <select  size="10"  name="table_folder" id="table_folder_1" ondblclick="get_file_1()" onclick="get_fileName_1()">
								</select>
			 			  </div>
	 			</div>
			</form>
			
		</div>
		
		<div class="tab-pane" id="5">
			<form id="edit-profile5" class="form-horizontal" method="post" action="server/IPAct.do">
				<div class="control-group">
					<label class="control-label">设置考试IP段地址</label>
					<br />
					<div class="controls"><div><br/>
							<INPUT type="text" name="startIPS" placeholder="开始IP字段 " > 
							---
							<INPUT type="text" name="endIPS" placeholder="结束IP字段" >
							<a href="javascript:;"><i class="icon-plus-sign" onclick="addOnes(this);"></i></a>
						</div>
						
					</div>
				
					<div class="controls" id="DelIPS"><div><br/>
						<INPUT type="text" name="startIPS" placeholder="开始IP字段 " >
						---
						<INPUT type="text" name="endIPS" placeholder="结束IP字段" >
						<a href="javascript:;" onclick="DelNode(this)"><i class="icon-trash"></i></a>
						</div>
						
					</div>
					
				</div>
				
				<div class="control-group">
					<label class="control-label">设置考试单个IP地址</label>
					<br />
					
					<div class="controls"><div><br/>
							<INPUT type="text" name="oneIP" placeholder="IP字段 " > 
							<a href="javascript:;"><i class="icon-plus-sign" onclick="addOne(this);"></i></a>
						</div>
					</div>
					
					<div class="controls" id="DelIP"><div><br/>
						<INPUT type="text" name="oneIP" placeholder="IP字段" >
						<a href="javascript:;" onclick="DelNode(this)"><i class="icon-trash"></i></a>
						</div>
					</div>
				</div>
				
				<br />
												
				<div class="form-actions" style="background-color: inherit;">
					<button type="submit" class="btn btn-primary">Save</button> <button class="btn">Cancel</button>
				</div>
				
				
			</form>
		</div>		
	
		<div class="tab-pane" id="6">
			<form id="edit-profile6" class="form-horizontal" method="post" action="server/CorrectWorkAct.do">
			
				<!-- 作业上传目录 -->
				<div class="control-group">
					<label class="control-label" for="accountadvanced">作业上传目录</label>
					<div class="controls">
						<input type="text" name="directory" id="workDir" class="input-xlarge accordion-toggle" data-toggle="collapse" href="#collapseTwo" value="C:\"/>
						<strong>${requestScope.error_1}</strong>
					</div>
				</div>
				<div class="control-group accordion-body collapse" id="collapseTwo">
						<label class="control-label" for="accountadvanced">目录</label>
						<div class="controls">
			     				 <div class="accordion-inner">
			      				 	<select class="input-medium" name="tables_drive" id="tables_drives_2" onchange="get_drives_2()">
									</select>
									
			     				 </div>
			     				 <select size="10" multiple="multiple" name="table_folder" id="table_folder_2" ondblclick="get_file_2()" onclick="get_fileName_2()">
								</select>
			 			  </div>
	 			</div>
	 			
	 			<!-- 作业答案路径 -->
	 			<div class="control-group">
					<label class="control-label" for="accountadvanced">作业答案路径</label>
					<div class="controls">
						<input type="text" name="results" id="ResultDir" class="input-xlarge accordion-toggle" data-toggle="collapse" href="#collapseThres" value="C:\" />
						<strong>${requestScope.error_2}</strong>
					</div>
				</div>
				<div class="control-group accordion-body collapse" id="collapseThres">
						<label class="control-label" for="accountadvanced">目录</label>
						<div class="controls">
			     				 <div class="accordion-inner">
			      				 	<select class="input-medium" name="tables_drive" id="tables_drives_3" onchange="get_drives_3()">
									</select>
									
			     				 </div>
			     				 <select size="10" multiple="multiple" name="table_folder" id="table_folder_3" ondblclick="get_file_3()" onclick="get_fileName_3()">
								</select>
			 			  </div>
	 			</div>
	 			
	 			
	 			<div class="control-group">
					    	<div class="controls">
  								<strong>${requestScope.succ}</strong>
  								<strong>${requestScope.error_3}</strong>
							</div>
				</div>
				</br>
	 			
	 			<div class="form-actions" style="background-color: inherit;">
					<button type="submit" class="btn btn-primary">开始批改作业</button>
				</div>
			</form>
			
			<form class="form-horizontal" method="post" action="server/getResultGradeAct.do">
				<div class="form-actions" style="background-color: inherit;">
					<button type="submit" class="btn btn-primary">开始获取成绩</button>
				</div>
				
	 			<div class="control-group">
				   <div class="controls">
  						<strong>${requestScope.succ}</strong>
  						<strong>${requestScope.error}</strong>
					</div>
				</div>
			</form>
		</div>
		
		<div class="tab-pane" id="7">
			<s:if test="%{#request.testID-1 != 0}">
			
			<form id="edit-profile7" action="server/stuAllowID.do" method="post" class="form-horizontal">
			<div class="control-group">
					<div class="controls">
						<strong>总共有 ${requestScope.testID-1} 场考试</strong>
						<strong>
							<s:if test="%{#request.allowID != -1}">
								请设置第 ${requestScope.allowID} 场 学生限制
							</s:if>
							<s:else>
								所有场次学生限制已经设置完毕
							</s:else>
						</strong>
						<strong class="offset1"><a href="server/clearAllowID.do">清除考试学生限制</a></strong>
						<input type="hidden" name="allowID" value="${requestScope.allowID}"/>
					</div>
			</div>
			<s:if test="%{#request.allowID != -1}">
			<div class="control-group">
					
					<label class="control-label" for="accountadvanced">学生专业</label>
					<div class="controls">
						<select name="professional">
							<s:iterator value="#request.professionalList" id="s" status="st">
								<option value="<s:property value='#s'/>"><s:property value="#s"/></option>
							</s:iterator>
						</select>
						班级
						<select name="classes">
							<s:iterator value="#request.classesList" id="s" status="st">
								<option value="<s:property value='#s'/>"><s:property value="#s"/></option>
							</s:iterator>
						</select>	
						
					</div>
					
					
			</div>
			<div class="form-actions" style="background-color: inherit;">
					<button type="submit" class="btn btn-primary">Save</button> <button class="btn">Cancel</button>
					
			</div>
			</s:if>
			</form>
			</s:if>
			<s:else>
				<div class="control-group offset2">
					<div class="controls">
						<strong>当前没有设置考试时间，请先设置考试时间</strong>
					</div>
			</div>
			</s:else>
		</div>
	
	</div>
<SCRIPT type="text/javascript">

	var delIPS = document.getElementById("DelIPS").firstChild;
	var delIP = document.getElementById("DelIP").firstChild;
	
	function addOnes(obj)
	{
		var newNode = delIPS.cloneNode(true);
		obj.parentNode.parentNode.appendChild(newNode);
	
	}
	
	function addOne(obj)
	{
		var newNode = delIP.cloneNode(true);
		obj.parentNode.parentNode.appendChild(newNode);
	
	}
	
	function DelNode(obj) {
		  obj.parentNode.parentNode.removeChild(obj.parentNode);
	}
	
	function codeSearch(code,id1,id2,id3,id4) 
	{
	    var url = code+"?id1="+id1+ "&id2="+ id2+ "&id3="+ id3+ "&id4="+ id4;
	    var name = "EpcCode";

	    var awidth=640; //窗口宽度,需要设置

	    var aheight=480; //窗口高度,需要设置 
	    var atop=(screen.availHeight - aheight)/2; //窗口顶部位置,一般不需要改
	    var aleft=(screen.availWidth - awidth)/2; //窗口放中央,一般不需要改

	    var param =  "directories=no,status=no,location=no,toolbar=no,scrollbars=yes,resizable=no,hotkeys=no";
	    //新窗口的左部位置，顶部位置，宽度，高度

	    var params="top=" + atop + ",left=" + aleft + ",width=" + awidth + ",height=" + aheight + "," + param;

	   window.open(url,name,params); 
	}
</SCRIPT>


<!-- 设置上传作业目录 -->
<script language="javascript">

/**
*初始化，将系统所有的驱动器放入table_drives列表
*/
window.onload = new function init()
{
    var fso, s, n, e, x;
    fso = new ActiveXObject("Scripting.FileSystemObject");
    e = new Enumerator(fso.Drives);
    s = "";
    for (; !e.atEnd(); e.moveNext())
    {
      x = e.item();
      s = s + x.DriveLetter;
      s += ":";
      if (x.DriveType == 3)
         n = x.ShareName;
      else if (x.IsReady)
         n = x.VolumeName;
      else
         n = "[驱动器未就绪]";
      s +=   n + ",";
    }
    var drives = s.split(",");    
    var tableDrives_1 = document.getElementById("tables_drives_1");
    var tableDrives_2 = document.getElementById("tables_drives_2");
    var tableDrives_3 = document.getElementById("tables_drives_3");
    
    for ( var i = 0; i < drives.length-1; i++ )
    {
        var option = document.createElement("OPTION");
        drives[i].split(":");
        option.value = "["+drives[i].split(":")[0]+":]"+drives[i].split(":")[1];
        option.text = "["+drives[i].split(":")[0]+":]"+drives[i].split(":")[1];
        tableDrives_1.add(option);
    }
    
    for ( var i = 0; i < drives.length-1; i++ )
    {
        var option = document.createElement("OPTION");
        drives[i].split(":");
        option.value = "["+drives[i].split(":")[0]+":]"+drives[i].split(":")[1];
        option.text = "["+drives[i].split(":")[0]+":]"+drives[i].split(":")[1];
        tableDrives_2.add(option);
    }
    
    for ( var i = 0; i < drives.length-1; i++ )
    {
        var option = document.createElement("OPTION");
        drives[i].split(":");
        option.value = "["+drives[i].split(":")[0]+":]"+drives[i].split(":")[1];
        option.text = "["+drives[i].split(":")[0]+":]"+drives[i].split(":")[1];
        tableDrives_3.add(option);
    }
    
    get_drives_1();
    get_drives_2();
    get_drives_3();
}

/**//*
*tables_drives列表中选中的驱动器上所有文件夹放入table_folder列表中
*/
function get_drives_1()
{
    var tableDrives = document.getElementById("tables_drives_1");
    var tableFolders = document.getElementById("table_folder_1");    
    for ( var i = 0; i < tableDrives.options.length; i++ )
    {        
        if ( tableDrives.options[i].selected == true )
        {
            var fso, f, fc, s;            
            var drive = tableDrives.options[i].value.split(":")[0].substring(1,tableDrives.options[i].value.split(":")[0].length);
            document.getElementById("backDir").value = drive + ":\\";
            fso = new ActiveXObject("Scripting.FileSystemObject");            
             if (fso.DriveExists(drive))
            {
                d = fso.GetDrive(drive);
                if ( d.IsReady )
                {
                    f = fso.GetFolder(d.RootFolder); 
                    fc = new Enumerator(f.SubFolders);
                    s = "";
                    for (;!fc.atEnd(); fc.moveNext())
                    {
                     s += fc.item();
                     s += ",";
                    }
                    
                    
                    var len = tableFolders.options.length;
                    while(len >= 0)
                    {
                        tableFolders.options.remove(len);
                        len--;
                    }
                    var option = document.createElement("OPTION");
                    option.value = drive + ":\\";
                    option.text = drive + ":\\";
                    tableFolders.add(option);
                    var folders = s.split(",");                                      
                    for ( j = 0; j < folders.length -1; j++)
                    {
                        option = document.createElement("OPTION");
                        option.value =  folders[j];
                        option.text = folders[j];
                        tableFolders.add(option);
                    }    
                }
                else
                {
                    alert("无法改变当前内容！")
                }                
            }
            else
            return false;  
        }        
    }
}
function get_drives_2()
{
    var tableDrives = document.getElementById("tables_drives_2");
    var tableFolders = document.getElementById("table_folder_2");    
    for ( var i = 0; i < tableDrives.options.length; i++ )
    {        
        if ( tableDrives.options[i].selected == true )
        {
            var fso, f, fc, s;            
            var drive = tableDrives.options[i].value.split(":")[0].substring(1,tableDrives.options[i].value.split(":")[0].length);
            document.getElementById("workDir").value = drive + ":\\";
            fso = new ActiveXObject("Scripting.FileSystemObject");            
             if (fso.DriveExists(drive))
            {
                d = fso.GetDrive(drive);
                if ( d.IsReady )
                {
                    f = fso.GetFolder(d.RootFolder); 
                    fc = new Enumerator(f.SubFolders);
                    s = "";
                    for (;!fc.atEnd(); fc.moveNext())
                    {
                     s += fc.item();
                     s += ",";
                    }
                    
                    fc = new Enumerator(f.Files);
                   // s = "";
                    for (;!fc.atEnd(); fc.moveNext())
                    {
                     s += fc.item();
                     s += ",";
                    }
                    
                    var len = tableFolders.options.length;
                    while(len >= 0)
                    {
                        tableFolders.options.remove(len);
                        len--;
                    }
                    var option = document.createElement("OPTION");
                    option.value = drive + ":\\";
                    option.text = drive + ":\\";
                    tableFolders.add(option);
                    var folders = s.split(",");                                      
                    for ( j = 0; j < folders.length -1; j++)
                    {
                        option = document.createElement("OPTION");
                        option.value =  folders[j];
                        option.text = folders[j];
                        tableFolders.add(option);
                    }    
                }
                else
                {
                    alert("无法改变当前内容！")
                }                
            }
            else
            return false;  
        }        
    }
}
function get_drives_3()
{
    var tableDrives = document.getElementById("tables_drives_3");
    var tableFolders = document.getElementById("table_folder_3");    
    for ( var i = 0; i < tableDrives.options.length; i++ )
    {        
        if ( tableDrives.options[i].selected == true )
        {
            var fso, f, fc, s;            
            var drive = tableDrives.options[i].value.split(":")[0].substring(1,tableDrives.options[i].value.split(":")[0].length);
            document.getElementById("ResultDir").value = drive + ":\\";
            fso = new ActiveXObject("Scripting.FileSystemObject");            
             if (fso.DriveExists(drive))
            {
                d = fso.GetDrive(drive);
                if ( d.IsReady )
                {
                    f = fso.GetFolder(d.RootFolder); 
                    fc = new Enumerator(f.SubFolders);
                    s = "";
                    for (;!fc.atEnd(); fc.moveNext())
                    {
                     s += fc.item();
                     s += ",";
                    }
                    
                    fc = new Enumerator(f.Files);
                   // s = "";
                    for (;!fc.atEnd(); fc.moveNext())
                    {
                     s += fc.item();
                     s += ",";
                    }
                    
                    var len = tableFolders.options.length;
                    while(len >= 0)
                    {
                        tableFolders.options.remove(len);
                        len--;
                    }
                    var option = document.createElement("OPTION");
                    option.value = drive + ":\\";
                    option.text = drive + ":\\";
                    tableFolders.add(option);
                    var folders = s.split(",");                                      
                    for ( j = 0; j < folders.length -1; j++)
                    {
                        option = document.createElement("OPTION");
                        option.value =  folders[j];
                        option.text = folders[j];
                        tableFolders.add(option);
                    }    
                }
                else
                {
                    alert("无法改变当前内容！")
                }                
            }
            else
            return false;  
        }        
    }
}
function get_fileName_1()
{
	
    var tableFolders = document.getElementById("table_folder_1");      
    for ( var i = 0; i < tableFolders.options.length; i++ )
    {
        if ( tableFolders.options[i].selected == true )
        {
            var folderpath = tableFolders.options[i].value.substring(0,tableFolders.options[i].value.length);
            if ( folderpath.charAt(folderpath.length-1) == "\\" )
            {
                document.getElementById("backDir").value = folderpath;
            }
            else
            {
                document.getElementById("backDir").value = folderpath + "\\";
            }
	}
    }
}

function get_fileName_2()
{
	
    var tableFolders = document.getElementById("table_folder_2");      
    for ( var i = 0; i < tableFolders.options.length; i++ )
    {
        if ( tableFolders.options[i].selected == true )
        {
            var folderpath = tableFolders.options[i].value.substring(0,tableFolders.options[i].value.length);
            if ( folderpath.charAt(folderpath.length-1) == "\\" )
            {
                document.getElementById("workDir").value = folderpath;
            }
            else
            {
                document.getElementById("workDir").value = folderpath + "\\";
            }
	}
    }
}
function get_fileName_3()
{
	
    var tableFolders = document.getElementById("table_folder_3");      
    for ( var i = 0; i < tableFolders.options.length; i++ )
    {
        if ( tableFolders.options[i].selected == true )
        {
            var folderpath = tableFolders.options[i].value.substring(0,tableFolders.options[i].value.length);
            if ( folderpath.charAt(folderpath.length-1) == "\\" )
            {
                document.getElementById("ResultDir").value = folderpath;
            }
            else
            {
                document.getElementById("ResultDir").value = folderpath + "\\";
            }
	}
    }
}
/**//*
*table_folder双击选项中的一个选项，就将该文件夹下面的文件夹显示在table_folder列表中。
*/
function get_file_1()
{
    var tableFolders = document.getElementById("table_folder_1");    
    var tableDrives = document.getElementById("tables_drives_1");
    for ( var i = 0; i < tableFolders.options.length; i++ )
    {
        if ( tableFolders.options[i].selected == true )
        {
            var fso, f, fc, s;            
            var folderpath = tableFolders.options[i].value.substring(0,tableFolders.options[i].value.length);
            if ( folderpath.charAt(folderpath.length-1) == "\\" )
            {
                document.getElementById("backDir").value = folderpath;
            }
            else
            {
                document.getElementById("backDir").value = folderpath + "\\";
            }
            
            
            fso = new ActiveXObject("Scripting.FileSystemObject");    
            f = fso.GetFolder(folderpath); 
            fc = new Enumerator(f.SubFolders);
            s = "";
            for (;!fc.atEnd(); fc.moveNext())
            {
             s += fc.item();
             s += ",";
            }    
            
           
            var len = tableFolders.options.length;
            while(len >= 0)
            {
                tableFolders.options.remove(len);
                len--;
            }        
            var opt = "";            
            var opt1 = "";
            for ( j = 0; j < folderpath.split("\\").length; j++ )
            {
                var option = document.createElement("OPTION");
                opt = opt + folderpath.split("\\")[j]+"\\";
                if ( j > 0)
                {
                    opt1 = opt;
                    option.value = opt1.substring(0,opt1.length-1);
                    option.text = opt1.substring(0,opt1.length-1);
                    tableFolders.add(option);    
                }
                else
                {
                    option.value = opt;
                    option.text = opt;
                    tableFolders.add(option);                            
                }
                            
            }
            if ( tableFolders.options[0].value == tableFolders.options[1].value )
            {
                tableFolders.options.remove(1);
            } 
            if ( s != "" )        
            {                
                var folders = s.split(",");                                      
                for ( j = 0; j < folders.length -1; j++)
                {
                    option = document.createElement("OPTION");
                    option.value = folders[j];
                    option.text = folders[j];
                    tableFolders.add(option);
                }    
            }                    
        }
    }
}

function get_file_2()
{
    var tableFolders = document.getElementById("table_folder_2");    
    var tableDrives = document.getElementById("tables_drives_2");
    for ( var i = 0; i < tableFolders.options.length; i++ )
    {
        if ( tableFolders.options[i].selected == true )
        {
            var fso, f, fc, s;            
            var folderpath = tableFolders.options[i].value.substring(0,tableFolders.options[i].value.length);
            if ( folderpath.charAt(folderpath.length-1) == "\\" )
            {
                document.getElementById("workDir").value = folderpath;
            }
            else
            {
                document.getElementById("workDir").value = folderpath + "\\";
            }
            
            
            fso = new ActiveXObject("Scripting.FileSystemObject");    
            f = fso.GetFolder(folderpath); 
            fc = new Enumerator(f.SubFolders);
            s = "";
            for (;!fc.atEnd(); fc.moveNext())
            {
             s += fc.item();
             s += ",";
            }    
            
            fc = new Enumerator(f.Files);
            for (;!fc.atEnd(); fc.moveNext())
            {
             s += fc.item();
             s += ",";
            }    
            var len = tableFolders.options.length;
            while(len >= 0)
            {
                tableFolders.options.remove(len);
                len--;
            }        
            var opt = "";            
            var opt1 = "";
            for ( j = 0; j < folderpath.split("\\").length; j++ )
            {
                var option = document.createElement("OPTION");
                opt = opt + folderpath.split("\\")[j]+"\\";
                if ( j > 0)
                {
                    opt1 = opt;
                    option.value = opt1.substring(0,opt1.length-1);
                    option.text = opt1.substring(0,opt1.length-1);
                    tableFolders.add(option);    
                }
                else
                {
                    option.value = opt;
                    option.text = opt;
                    tableFolders.add(option);                            
                }
                            
            }
            if ( tableFolders.options[0].value == tableFolders.options[1].value )
            {
                tableFolders.options.remove(1);
            } 
            if ( s != "" )        
            {                
                var folders = s.split(",");                                      
                for ( j = 0; j < folders.length -1; j++)
                {
                    option = document.createElement("OPTION");
                    option.value = folders[j];
                    option.text = folders[j];
                    tableFolders.add(option);
                }    
            }                    
        }
    }
}

function get_file_3()
{
    var tableFolders = document.getElementById("table_folder_3");    
    var tableDrives = document.getElementById("tables_drives_3");
    for ( var i = 0; i < tableFolders.options.length; i++ )
    {
        if ( tableFolders.options[i].selected == true )
        {
            var fso, f, fc, s;            
            var folderpath = tableFolders.options[i].value.substring(0,tableFolders.options[i].value.length);
            if ( folderpath.charAt(folderpath.length-1) == "\\" )
            {
                document.getElementById("ResultDir").value = folderpath;
            }
            else
            {
                document.getElementById("ResultDir").value = folderpath + "\\";
            }
            
            
            fso = new ActiveXObject("Scripting.FileSystemObject");    
            f = fso.GetFolder(folderpath); 
            fc = new Enumerator(f.SubFolders);
            s = "";
            for (;!fc.atEnd(); fc.moveNext())
            {
             s += fc.item();
             s += ",";
            }    
            
            fc = new Enumerator(f.Files);
            for (;!fc.atEnd(); fc.moveNext())
            {
             s += fc.item();
             s += ",";
            }    
            var len = tableFolders.options.length;
            while(len >= 0)
            {
                tableFolders.options.remove(len);
                len--;
            }        
            var opt = "";            
            var opt1 = "";
            for ( j = 0; j < folderpath.split("\\").length; j++ )
            {
                var option = document.createElement("OPTION");
                opt = opt + folderpath.split("\\")[j]+"\\";
                if ( j > 0)
                {
                    opt1 = opt;
                    option.value = opt1.substring(0,opt1.length-1);
                    option.text = opt1.substring(0,opt1.length-1);
                    tableFolders.add(option);    
                }
                else
                {
                    option.value = opt;
                    option.text = opt;
                    tableFolders.add(option);                            
                }
                            
            }
            if ( tableFolders.options[0].value == tableFolders.options[1].value )
            {
                tableFolders.options.remove(1);
            } 
            if ( s != "" )        
            {                
                var folders = s.split(",");                                      
                for ( j = 0; j < folders.length -1; j++)
                {
                    option = document.createElement("OPTION");
                    option.value = folders[j];
                    option.text = folders[j];
                    tableFolders.add(option);
                }    
            }                    
        }
    }
}
</script>
</body>
</html>