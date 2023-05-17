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

	<spring:message var="clientRadioButtonLabel" code="form.clientRadioButton"/>
	<spring:message var="managerRadioButtonLabel" code="form.managerRadioButton"/>
	
	<form:radiobutton id="client-radio-button" path="authorities" value="CLIENT" label="${clientRadioButtonLabel}"/>
  	<form:radiobutton id="manager-radio-button" path="authorities" value="MANAGER" label="${managerRadioButtonLabel}"/>
  	<jstl:if test="${not empty authorityError}">
		<p style="color: red;">${authorityError}</p>
	</jstl:if>
  	<br/>

	<form:label path="username">
		<spring:message code="security.username" />
	</form:label>
	<form:input name="username" path="username" />	
	
	<jstl:if test="${not empty usernameError}">
		<p style="color: red;">${usernameError}</p>
	</jstl:if>
	
	<br />

	<form:label  path="password">
		<spring:message code="security.password" />
	</form:label>
	<form:password name="password" path="password"/>	
	<form:errors class="error" path="password" />
	
	<jstl:if test="${not empty passwordError}">
		<p style="color: red;">${passwordError}</p>
	</jstl:if>
	
	<br />

	<!-- Generic buttons -->
	<input type="submit" name="signup"
		value="<spring:message code="form.signUp" />" />&nbsp; 

	<input type="button" name="cancel"
		value="<spring:message code="form.cancel" />" onclick="javascript: relativeRedir('welcome/index.do');" />
	<br />

</form:form>

<script>
	
</script>