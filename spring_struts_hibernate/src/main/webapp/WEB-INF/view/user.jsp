<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<body>
	---------------------------------------------------------
	<br />
	<s:property value="page.id" />
	<br />
	<s:property value="page.name" />
	<br />
	<s:property value="page.status" />
	<br />
	<s:property value="page.createTime" />
	<br />
	<s:property value="page.price" />
	<br />
	<s:property value="page.updateTime" />
	<br />
	<s:property value="page.money" />
	<br /> 
	---------------------------------------------------------
	<br />
	<s:property value="%{model.id}" />
	<br />
	<s:property value="%{model.name}" />
	<br />
	<s:property value="%{model.status}" />
	<br />
	<s:property value="%{model.createTime}" />
	<br />
	<s:property value="%{model.price}" />
	<br />
	<s:property value="%{model.updateTime}" />
	<br />
	<s:property value="%{model.money}" />
	<br />
</body>
</html>

