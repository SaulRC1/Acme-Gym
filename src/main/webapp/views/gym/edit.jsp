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

<form:form action="gym/edit.do" modelAttribute="gym">

	<!-- Gym inputs -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="active" />
	<form:hidden path="activities" />
	<form:hidden path="gym" />
	<form:hidden path="logo" />

	<form:label path="address">
		<spring:message code="gym.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />

	<form:label path="name">
		<spring:message code="gym.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="fee">
		<spring:message code="gym.fee" />:
	</form:label>
	<form:input path="fee" />
	<form:errors cssClass="error" path="fee" />
	<br />

	<!-- Generic buttons -->
	<input type="submit" name="save"
		value="<spring:message code="form.save" />" />&nbsp; 
	
	<input type="submit" name="save"
		value="<spring:message code="form.save" />" ${gym.activate = false} />&nbsp; 
	
	<jstl:if test="${gym.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="form.delete" />"
			onclick="return confirm('<spring:message code="form.confirm.delete" />')">/>&nbsp;
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
