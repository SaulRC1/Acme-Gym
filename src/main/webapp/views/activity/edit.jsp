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

<form:form action="activity/edit.do" modelAttribute="activity">

	<form:label path="title">
		<spring:message code="activity.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="photo">
		<spring:message code="activity.photo" />:
	</form:label>
	<form:input path="photo" />
	<form:errors cssClass="error" path="photo" />
	<br />
	
	<form:label path="description">
		<spring:message code="activity.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="daysOfWeek">
		<spring:message code="activity.daysOfWeek" />:
	</form:label>
	<form:input path="daysOfWeek" />
	<form:errors cssClass="error" path="daysOfWeek" />
	<br />
	
	<form:label path="startHour">
		<spring:message code="activity.startHour" />:
	</form:label>
	<form:input path="startHour" />
	<form:errors cssClass="error" path="startHour" />
	<br />
	
	<form:label path="endHour">
		<spring:message code="activity.endHour" />:
	</form:label>
	<form:input path="endHour" />
	<form:errors cssClass="error" path="endHour" />
	<br />
	
	<form:label path="availablePlaces">
		<spring:message code="activity.endHour" />:
	</form:label>
	<form:input path="availablePlaces" />
	<form:errors cssClass="error" path="availablePlaces" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="form.save" />" />&nbsp; 
	
	<jstl:if test="${activity.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="form.delete" />" <%-- onclick="return confirm('<spring:message code="form.confirm.delete" />')" --%>/>&nbsp;
	</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="form.cancel" />" onclick="javascript: relativeRedir(${cancelUrl});" />
	<br />

</form:form>
