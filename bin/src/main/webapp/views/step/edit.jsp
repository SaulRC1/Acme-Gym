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

<form:form action="step/edit.do" modelAttribute="step">

	<!-- Gym inputs -->
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="number" />

	<form:label path="description">
		<spring:message code="step.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />

	<form:label path="tutorial">
		<spring:message code="step.tutorial" />:
	</form:label>
	<form:input path="tutorial" />
	<form:errors cssClass="error" path="tutorial" />
	<br />

	<!-- Generic buttons -->
	<input type="submit" name="save"
		value="<spring:message code="form.save" />" />&nbsp; 
	
	<input type="submit" name="save"
		value="<spring:message code="form.save" />" ${step.activate = false} />&nbsp; 
	
	<jstl:if test="${step.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="form.delete" />"
			onclick="return confirm('<spring:message code="form.confirm.delete" />')">&nbsp;
	</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="form.cancel" />"
		onclick="javascript: relativeRedir(${cancelUrl});" />
	<br />

</form:form>

<script type="text/javascript">
		function addActivity(activity) {
			gym.activities.add(activity);
			activities.remove(activity);
		}
		function removeActivity(activity) {
			activities.add(activity);
			gym.activities.remove(activity);
		}
</script>
