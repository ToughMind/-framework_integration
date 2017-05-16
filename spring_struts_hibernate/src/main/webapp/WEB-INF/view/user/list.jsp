<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%-- <%@include file="../common/inc/tld.jsp"%> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../common/inc/header.jsp">
	<jsp:param name="title" value="用户列表" />
</jsp:include>

hello
<tr>
	<td><s:property value="page.id" /></td>
	<td><s:property value="page.name" /></td>
	<td><s:property value="page.status" /></td>
	<td><s:property value="page.createTime" /></td>
	<td><s:property value="page.price" /></td>
	<td><s:property value="page.updateTime" /></td>
	<td><s:property value="page.money" /></td>
</tr>

<%-- <a href="${ctx}/add.action">新增</a>
<br />
<table border="1">
	<tr>
		<th>ID</th>
		<th>商品名称</th>
		<th>商品描述</th>
		<th>原需积分</th>
		<th>现需积分</th>
		<th>是否已发布</th>
		<th></th>
		<th></th>
		<th></th>
	</tr>
	<s:iterator value="page.items">
		<tr>
			<td><a
				href="${ctx}/admin/goods/toUpdate.action?id=<s:property value='id'/>"><s:property
						value="id" /></a></td>
			<td><s:property value="name" /></td>
			<td><s:property value="description" /></td>
			<td><s:property value="originalPoint" /></td>
			<td><s:property value="nowPoint" /></td>
			<td><s:property value="published" /></td>
			<td>更新</td>
			<td>删除</td>
			<td><a
				href="${ctx}/admin/goodsCode/list.action?goodsId=<s:property value='id'/>">查看兑换码</a></td>
		</tr>
	</s:iterator>
</table> --%>





<!--<my:page url="${ctx}/admin/goods/list.action" version="v1"/>-->
<jsp:include page="../common/inc/footer.jsp" />