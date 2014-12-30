<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/page/base/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户列表</title>
</head>
<body>
	<form id="mainForm" action="customer.action" method="post">
		<table width="100%">
			<tr>
				<td colspan="4" style="background: #5CACEE">
					<div class="top clearfix">
						<b>客户列表</b>
						<div>
							[<a href="readCustomer.action">增加客户</a>]
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="search clearfix">
						<div class="form">
							客户ID:<input name="filter_LIKES_customerId"
								value="${param['filter_LIKES_customerId']}"> 客户名称：<input
								name="filter_LIKES_customerName"
								value="${param['filter_LIKES_customerName']}"> 客户英文名称：<input
								name="filter_LIKES_customerShortName"
								value="${param['filter_LIKES_customerShortName']}"> <input
								type="button" value="搜索" onclick="search();" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>客户id</td>
				<td>客户名称</td>
				<td>客户英文名称</td>
				<td>操作</td>
			</tr>

			<c:forEach items="page.result">
				<tr>
					<td>${customerId}</td>
					<td>${customerName}</td>
					<td>${customerShortName}</td>
					<td><a href="readCustomer.action?id=${id}">编辑</a> <!-- <a
								href="admin/customer!delete.action?id=${id}">删除</a> --></td>
				</tr>
			</c:forEach>
			<tr>
		</table>
		<%@include file="/page/base/page.jsp"%>
	</form>
</body>
</html>