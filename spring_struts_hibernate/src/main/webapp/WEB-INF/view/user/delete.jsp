<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/inc/tld.jsp"%>
<jsp:include page="../../common/inc/header.jsp">
    <jsp:param name="title" value="用户管理-删除"/>
</jsp:include>
确认删除吗?
<s:form action="/admin/goods/delete.action" method="POST" acceptcharset="UTF-8">
    <s:token/>
    <s:hidden name="id" value="%{model.id}"/>
    <input name="submit" type="submit" value="确定"/>
    <input name="cancel" type="button" onclick="javascript:window.location.href='${ctx}/admin/goods/list.action'" value="取消"/>
</s:form>
<jsp:include page="../../common/inc/footer.jsp"/>