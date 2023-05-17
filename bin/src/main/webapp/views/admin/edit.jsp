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
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="firstName">
		<spring:message code="actor.firstName" />:
	</form:label>
	<form:input path="firstName" />
	<form:errors cssClass="error" path="firstName" />
	<br />

	<form:label path="lastName">
		<spring:message code="actor.lastName" />:
	</form:label>
	<form:input path="lastName" />
	<form:errors cssClass="error" path="lastName" />
	<br />

	<form:label path="address">
		<spring:message code="actor.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />

	<form:label path="email">
		<spring:message code="actor.email" />:
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />

	<form:label path="phoneNumber">
		<spring:message code="actor.phoneNumber" />:
	</form:label>
	<form:input path="phoneNumber" />
	<form:errors cssClass="error" path="phoneNumber" />
	<br />

	<form:label path="postalCode">
		<spring:message code="actor.postalCode" />:
	</form:label>
	<form:input path="postalCode" />
	<form:errors cssClass="error" path="postalCode" />
	<br />

	<form:label path="city">
		<spring:message code="actor.city" />:
	</form:label>
	<form:input path="city" />
	<form:errors cssClass="error" path="city" />
	<br />

	<form:label path="country">
		<spring:message code="actor.country" />:
	</form:label>
	<form:input path="country" />
	<form:errors cssClass="error" path="country" />
	<br />


	<!-- Generic buttons -->
	<input type="submit" name="save"
		value="<spring:message code="form.save" />" />&nbsp; 
	
	<jstl:if test="${admin.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="form.delete" />" onclick="return confirm('<spring:message code="form.confirm.delete" />')"> &nbsp;
	</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="form.cancel" />" onclick="javascript: relativeRedir(${cancelUrl});" />
	<br />

</form:form>
