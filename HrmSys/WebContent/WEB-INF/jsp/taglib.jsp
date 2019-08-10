<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 需要配置到web.xml的 <jsp-config>中 --> 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!-- 设置项目路径的一个变量 -->
<c:set var="ctp" value="${pageContext.request.contextPath}"></c:set>

<!-- 使用自定义标签 -->
<%@ taglib uri="/pager" prefix="jkl"%>