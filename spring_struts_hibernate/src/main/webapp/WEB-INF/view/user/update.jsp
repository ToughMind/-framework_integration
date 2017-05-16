<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../common/inc/tld.jsp"%>
<jsp:include page="../../common/inc/header.jsp">
    <jsp:param name="title" value="商品管理-更新"/>
</jsp:include>
<s:fielderror cssStyle="color:red"/>
<s:form action="/admin/goods/update.action" method="POST" acceptcharset="UTF-8" >
<s:token/>
<table border="1">
    <s:hidden name="goods.id" value="%{model.id}"/>
    <s:hidden name="goods.version" value="%{model.version}"/>
    <tr>
        <s:textfield label="商品名称" name="goods.name" value="%{model.name}" required="true"/>
    </tr>
    <tr>
        <s:textarea label="商品简介" name="goods.description" value="%{model.description}" required="true" cols="20" rows="3"/>
    </tr>
    <tr>
        <s:textfield label="原需积分" name="goods.originalPoint" value="%{model.originalPoint}" required="true"/>
    </tr>
    <tr>
        <s:textfield label="现需积分" name="goods.nowPoint" value="%{model.nowPoint}" required="true"/>
    </tr>
    <tr>
        <s:radio label="是否发布" name="goods.published" list="#{true:'发布',false:'不发布'}" value="%{model.published}" />
    </tr>
    <tr>
       <td><input name="submit" type="submit" value="更新"/></td>
       <td><input name="cancel" type="button" onclick="javascript:window.location.href='${ctx}/admin/goods/list.action'" value="取消"/></td>
    </tr>    
</table>
</s:form>
<jsp:include page="../../common/inc/footer.jsp"/>