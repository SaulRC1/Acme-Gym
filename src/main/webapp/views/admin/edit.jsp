<%--
 * edit.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="admin/edit.do" modelAttribute="admin">

	<!-- Actors generic inputs -->
	<jsp:include page="../actor/edit.jsp" />


	<!-- Generic buttons -->
	<input type="submit" name="save"
		value="<spring:message code="actor.save" />" />&nbsp; 
	
	<jstl:if test="${admin.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="actor.delete" />"
			<%-- onclick="return confirm('<spring:message code="actor.confirm.delete" />')" --%> />&nbsp;
	</jstl:if>
	
	<input type="button" name="cancel"
		value="<spring:message code="actor.cancel" />"
		<%-- onclick="javascript: relativeRedir('actor/administrator/list.do');"  --%>/>
	<br />

</form:form>
