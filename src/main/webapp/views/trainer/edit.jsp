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

<p>
	<form:form action="trainer/edit.do"
		modelAttribute="trainer">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:label path="title">
			<spring:message code="trainer.title" />: </form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br />
		<input type="submit" name="save"
			value="<spring:message code="trainer.save" />" />
	</form:form>
</p>
