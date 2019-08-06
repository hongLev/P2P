<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>蓝源Eloan-P2P平台(系统管理平台)</title>
<#include "../common/header.ftl"/>
<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
<script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript">
	$(function(){
		//分页
		$("#pagination").twbsPagination({
			   //总页数 
			 totalPages: ${pageResult.totalPage},
			   //没页显示多少条
		    visiblePages: ${qo.pageSize},
		    startPage:${pageResult.currentPage},
		    //点击事件
		    	first: "首页",
				last: "未页",
				prev: '上一页',	
				next: '下一页',
		    onPageClick: function (event, page) {
		 	   $("#currentPage").val(page);
		 	   $("#searchForm").submit();
		    }
		});
		$(".auditClass").click(function(){
			$("#myModal").modal("show");
		})
		
		//show
		$(".auditClass").click(function(){
			$("#editform")[0].reset();
			$("#myModal").modal("show");
			var json=$(this).data("json");
		 	$("#editForm_id").val(json.id);
			/*$("#editForm_state").val(json.state); */
			$("#applier").text(json.applier.userName);
			$("#fileType").text(json.fileType.title);
			$("#image").attr("src",json.image);
			
		})
		$(".btn_audit").click(function(){
			var form=$("#editform");
			form.find("[name=state]").val($(this).val());
			$("#myModal").modal("hide");
			form.ajaxSubmit(function(data){
				if(data.flag){
					$.messager.confirm("提示","审核成功!",function(){
						window.location.reload();
					});
				}else{
					$.messager.alert("提示",data.message);
				}
			});
			return false;
		});
		$("#query").click(function(){
			$("#currentPage").val(1);
			$("#searchForm").submit();
		})
		
	})
</script>


</head>
<body>
	<div class="container">
		<#include "../common/top.ftl"/>
		<div class="row">
			<div class="col-sm-3">
				<#assign currentMenu="userFileAuth" />
				<#include "../common/menu.ftl" />
			</div>
			<div class="col-sm-9">
				<div class="page-header">
					<h3>用户征信文件审核管理</h3>
				</div>
				<form id="searchForm" class="form-inline" method="post" action="/userFileAuth.do">
					<input type="hidden" id="currentPage" name="currentPage" value=""/>
					<div class="form-group">
					    <label>状态</label>
					    <select class="form-control" name="state" id="state">
					    	<option value="-1">全部</option>
					    	<option value="0" selected="selected">申请中</option>
					    	<option value="1">审核通过</option>
					    	<option value="2">审核拒绝</option>
					    </select>
					  <script type="text/javascript">
					 	 $("#state option[value=${qo.state}]").attr("selected","true");
					  </script>
					</div>
					<div class="form-group">
					    <label>申请时间</label>
					    <input class="form-control" type="text" name="beginDate" id="beginDate" value='${(qo.beginDate?string("yyyy-MM-dd"))!""}' />到
					    <input class="form-control" type="text" name="endDate" id="endDate" value='${(qo.endDate?string("yyyy-MM-dd"))!""}' />
					</div>
					<div class="form-group">
						<button id="query" class="btn btn-success"><i class="icon-search"></i> 查询</button>
					</div>
				</form>
				<div class="panel panel-default">
					<table class="table">
						<thead>
							<tr>
								<th>用户名</th>
								<th>状态</th>
								<th>分数</th>
								<th>资料类型</th>
								<th>审核人</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<#list pageResult.listData as vo>
							<tr>
								<td>${vo.applier.userName}</td>
								<td>${vo.stateDisplay}</td>
								<td>${vo.score}</td>
								<td>${vo.fileType.title}</td>
								<td>${(vo.auditor.userName)!""}</td>
								<td>
									<#if vo.state==0>
									<a href="javascript:void(-1);" class="auditClass" data-json='${vo.jonsStr}'>审核</a>
									<#else>
									已审核
									</#if>
								</td>
							</tr>
						</#list> 
						</tbody>
					</table>
					<div style="text-align: center;">
						<ul id="pagination" class="pagination"></ul>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-body">
		      	<form class="form-horizontal" id="editform" method="post" action="/userFile_audit.do">
					<fieldset>
						<div id="legend" class="">
							<legend>实名认证审核</legend>
						</div>
						<input type="hidden" name="id" id="editForm_id" value="" />
						<input type="hidden" name="state" id="editForm_state" value="" /> 
						<div class="form-group">
				        	<label class="col-sm-2 control-label" for="name">用户名</label>
				        	<div class="col-sm-6">
				        		<label class="form-control" id="applier"></label>
				        	</div>
			        	</div>
			        	<div class="form-group">
			        	<label class="col-sm-2 control-label" for="name">资料类型</label>
			        	<div class="col-sm-6">
			        		<label class="form-control" id="fileType"></label>
			        	</div>
			        	</div>
			        	<div class="form-group">
						<label class="col-sm-2 control-label" for="name">资料图片</label>
			        	<div class="col-sm-6">
			        		<img src="" id="image" name="image" style="width::200px;"/>
			        	</div>
			        	</div>
			        	<div class="form-group">
			        	<label class="col-sm-2 control-label" for="name">征信分数</label>
			        	<div class="col-sm-6">
			        		<select name="score" class="form-control">
			        			<option value="1">1分</option>
			        			<option value="2">2分</option>
			        			<option value="3">3分</option>
			        			<option value="4">4分</option>
			        			<option value="5">5分</option>
			        		</select>
			        	</div>
			        	</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="name">审核备注</label>
				        	<div class="col-sm-6">
				        		<textarea name="remark" rows="4" cols="60"></textarea>
				        	</div>
						</div>
					</fieldset>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        <button type="button" class="btn btn-success btn_audit" value="1">审核通过</button>
		        <button type="button" class="btn btn-warning btn_audit" value="2">审核拒绝</button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
</body>
</html>