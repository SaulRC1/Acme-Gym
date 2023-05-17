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

<form:form action="training/edit.do" modelAttribute="training">

	<!-- TODO -->

	<!-- Generic buttons -->
	<input type="submit" name="save"
		value="<spring:message code="form.save" />" />&nbsp; 
	
	<input type="submit" name="save"
		value="<spring:message code="form.save" />" />&nbsp; 
	
	<jstl:if test="${training.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="form.delete" />" onclick="return confirm('<spring:message code="form.confirm.delete" />')" />&nbsp;
	</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="form.cancel" />" onclick="javascript: relativeRedir(${cancelUrl});" />
	<br />

</form:form>
