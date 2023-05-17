<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="security/signup_manager.do" modelAttribute="manager">

	<label>
		<spring:message code="form.firstName" />
	</label>
	<form:input name="firstName" path="firstName"/>	
	<br />
	
	<label>
		<spring:message code="form.lastName" />
	</label>
	<form:input name="lastName" path="lastName"/>	
	<br />
	
	<label>
		<spring:message code="form.address" />
	</label>
	<form:input name="address" path="address"/>	
	<br />
	
	<label>
		<spring:message code="form.email" />
	</label>
	<form:input name="email" path="email"/>	
	<br />
	
	<label>
		<spring:message code="form.phoneNumber" />
	</label>
	<form:input name="phoneNumber" path="phoneNumber"/>	
	<br />
	
	<label>
		<spring:message code="form.postalCode" />
	</label>
	<form:input name="postalCode" path="postalCode"/>	
	<br />
	
	<label>
		<spring:message code="form.city" />
	</label>
	<form:input name="city" path="city"/>	
	<br />
	
	<label>
		<spring:message code="form.country" />
	</label>
	<form:input name="country" path="country"/>	
	<br />
	
	<jstl:forEach items="${gyms}" var="gym">
		<form:checkbox value="${gym.id}" name="gyms" path="gyms" label="${gym.name}"/>
	</jstl:forEach>

	<jstl:if test="${not empty gymError}">
		<p style="color: red;">${gymError}</p>
	</jstl:if>
	
	<!-- Generic buttons -->
	<input type="submit" name="signup"
		value="<spring:message code="form.signUp" />" />&nbsp; 

	<input type="button" name="cancel"
		value="<spring:message code="form.cancel" />" onclick="javascript: relativeRedir('welcome/index.do');" />
	<br />

</form:form>

<script>
	
</script>