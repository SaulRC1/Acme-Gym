 <%--
 * singup.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="security/signup.do" modelAttribute="userAccount">

	<form:select id="actor" path="actor">
		<form:option value="client" label="CLIENT" />
		<form:option value="trainer" label="TRAINER" />
	</form:select>

	<form:label path="username">
		<spring:message code="security.username" />
	</form:label>
	<form:input path="username" />	
	<form:errors class="error" path="username" />
	<br />

	<form:label path="password">
		<spring:message code="security.password" />
	</form:label>
	<form:password path="password" />	
	<form:errors class="error" path="password" />
	<br />

	<!-- Generic buttons -->
	<input type="submit" name="signup"
		value="<spring:message code="form.register" />" />&nbsp; 

	<input type="button" name="cancel"
		value="<spring:message code="form.cancel" />" onclick="javascript: relativeRedir('welcome/index.do');" />
	<br />

</form:form>