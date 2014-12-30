<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/page/base/base.jsp"%>
<%@include file="/page/base/validate.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#inputForm").validate(
						{
							rules : {
								"model.userName" : {
									required : true,
									digits : true,
									maxlength : (12),
									minlength : (1),
									remote : "checkUniqueCustomer.action?id="
											+ encodeURIComponent('${model.id}')
								},
								"model.psd" : {
									required : true
								}
							},
							messages : {
								"model.userName" : {
									remote : "the same customer id is exist!"
								}
							}
						});
			});
</script>
</head>
<body>
	<c:choose>
		<c:when test="${empty id}">
			<c:set var="url_action" value="create.do"></c:set>
			<c:set var="tbl_title" value="创建客户"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="url_action" value="update.do"></c:set>
			<c:set var="tbl_title" value="修改客户信息"></c:set>
		</c:otherwise>
	</c:choose>

	<f:form action="${url_action}" method="post" id="inputForm">
		<input type="hidden" name="id" />
		<table width="100%">
			<tr>
				<td colspan="2" style="background: #5CACEE">
					<div class="top clearfix ">
						<b><a href="customer.action"> task列表 </a></b><span class="add">>>${tbl_title}
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tdRight">名称：</td>
				<td class="tdLeft"><input name="name"></td>
			</tr>
			<tr>
				<td class="tdRight"></td>
				<td class="tdLeft"><input name="" type="submit" value="提交"
					style="margin-right: 20px;" /> <input name="" type="reset"
					value="重置" /></td>
			</tr>
		</table>
	</f:form>
</body>
</html>