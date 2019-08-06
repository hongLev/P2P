<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>蓝源Eloan-P2P平台(系统管理平台)</title> <#include "../common/header.ftl"/>
<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
<script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	$(function(){
		
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
		//查询提交表单
		$("#query").click(function(){
			$("#currentPage").val(1);
			$("#searchForm").submit();
		})
		//弹窗
		$("#addVedioAuthBtn").click(function(){
			$("#editForm")[0].reset();
			$("#vedioAuthModal").modal("show");
		})
		//查询用户是否存在
		$("#editForm").validate({
			errorPlacement: function(error, element) {  
			    error.appendTo(element.parent());  
			},
			rules:{
				loginInfoDisplay:{
					required:true,	
					rangelength:[4,16],
					remote:{
						url:"checkUsernameVodie.do",
						type:"post"
					}
				}
			},
			messages:{
				loginInfoDisplay:{
					required:"用户名不能为空",
					rangelength:"用户名必须到{0}~{1}之间",
					remote:"用户不存在"
				}
			},
			errorClass:"text-danger"
		})
		$("#editForm").ajaxForm(function(data){
			if(data.flag){
				//说明注册成功	
				$.messager.confirm("提示","审核成功",function(){
					window.location.reload();
				});
			}else{
				$.messager.popup(data.message);
			}
			
		})
		//触发事件 
		$(".btn_audit").click(function(){
			var form=$("#editForm");
			form.find("[name=state]").val($(this).val());
			$("#myModal").modal("hide");
			form.submit();
		})
	})
	
</script>
</head>

<body>
	<div class="container">
		<#include "../common/top.ftl"/>
		<div class="row">
			<div class="col-sm-3"><#assign currentMenu="vedioAuth" />
				<#include "../common/menu.ftl" /></div>
			<div class="col-sm-9">
				<div class="page-header">
					<h3>视频认证管理</h3>
				</div>
				<div class="row">
					<!-- 提交分页的表单 -->
					<form id="searchForm" class="form-inline" method="post"
						action="/vedioAuth.do">
						<input id="currentPage" type="hidden" name="currentPage" value="" />
						<div class="form-group">
							<label>状态</label> <select class="form-control" name="state">
								<option value="-1" selected="selected">全部</option>
								<!-- <option value="0">申请中</option> -->
								<option value="1">审核通过</option>
								<option value="2">审核拒绝</option>
							</select>
							
						</div>
						<div class="form-group">
							<label>申请时间</label> <input class="form-control"
								style="width: 130px;" type="text" name="beginDate"
								id="beginDate" value="" />到
							<input class="form-control" style="width: 130px;" type="text"
								name="endDate" id="endDate"
								value="" />
						</div>
						<div class="form-group">
							<button id="query" class="btn btn-success">
								<i class="icon-search"></i> 查询
							</button>
							<a href="javascript:void(-1);" class="btn btn-success"
								id="addVedioAuthBtn">添加审核</a>
						</div>
					</form>
				</div>
				<div class="row">
					<table class="table">
						<thead>
							<tr>
								<th>用户名</th>
								<th>状态</th>
								<th>审核人</th>
								<th>审核说明</th>
								<th>审核时间</th>
							</tr>
						</thead>
						<tbody>
							<#list pageResult.listData as vo>
								<td>${vo.applier.userName}</td>
								<td>${vo.stateDisplay}</td>
								<td>${vo.auditor.userName}</td>
								<td>${(vo.remark)!""}</td>
								<td>${(vo.auditTime?string("yyyy-MM-dd"))!""}</td>
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
	</div>

	<div id="vedioAuthModal" class="modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">添加视频审核</h4>
				</div>
				<div class="modal-body">
					<form id="editForm" class="form-horizontal" method="post"
						action="/vedioAuth_audit.do">
						<input type="hidden" name="state" value="" />
						<div class="form-group">
							<label class="col-sm-2 control-label">用户</label>
							<div class="col-sm-6">
								<div class="dropdown" id="autocomplate">
									<input type="text" class="form-control" id="loginInfoDisplay" name="loginInfoDisplay"
										autocomplete="off" /> <input type="hidden" name="loginInfoValue" id="loginInfoValue"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="name">审核备注</label>
							<div class="col-sm-6">
								<textarea name="remark" rows="4" cols="40"></textarea>
							</div>
						</div>
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
</body>
</html>