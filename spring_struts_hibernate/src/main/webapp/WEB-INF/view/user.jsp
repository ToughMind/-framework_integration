<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<body>
	---------------------------------------------------------
    <s:if test="page != null">
        <table border="1">
            <tr>
                <th>id</th>
                <th>name</th>
                <th>status</th>
                <th>createTime</th>
                <th>updateTime</th>
                <th>price</th>
                <th>money</th>
            </tr>
            <s:iterator value="page" status="status">
                <tr>
                    <th><s:property value="id"/></th>
                    <th><s:property value="name"/></th>
                    <th><s:property value="status"/></th>
                    <th><s:property value="createTime"/></th>
                    <th><s:property value="updateTime"/></th>
                    <th><s:property value="price"/></th>
                    <th><s:property value="money"/></th>
                </tr>
            </s:iterator>
        </table>
    </s:if>
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

