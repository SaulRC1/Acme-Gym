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

<form:form action="actor/edit.do" modelAttribute="actor">

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
	
	<security:authorize access="hasRole('CLIENT')">
		<form:label path="cardNumber">
			<spring:message code="actor.card.number" />:
		</form:label>
		<form:input path="cardNumber" />
		<form:errors cssClass="error" path="cardNumber" />
		<br />
		
		<form:label path="cardBrand">
			<spring:message code="actor.card.brand" />:
		</form:label>
		<form:input path="cardBrand" />
		<form:errors cssClass="error" path="cardBrand" />
		<br />
		
		<form:label path="cardExpirationMonth">
			<spring:message code="actor.card.expirationMonth" />:
		</form:label>
		<form:input path="cardExpirationMonth" />
		<form:errors cssClass="error" path="cardExpirationMonth" />
		<br />
	
		<form:label path="cardExpirationYear">
			<spring:message code="actor.card.expirationYear" />:
		</form:label>
		<form:input path="cardExpirationYear" />
		<form:errors cssClass="error" path="cardExpirationYear" />
		<br />
		
		<form:label path="cardCVV">
			<spring:message code="actor.card.CVV" />:
		</form:label>
		<form:input path="cardCVV" />
		<form:errors cssClass="error" path="cardCVV" />
		<br />
		
		<form:label path="cardHolder">
			<spring:message code="actor.card.holder" />:
		</form:label>
		<form:input path="cardHolder" />
		<form:errors cssClass="error" path="cardHolder" />
		<br />		
	</security:authorize>
	
	<security:authorize access="hasRole('TRAINER','MANAGER')">
		<form:label path="gyms">
			<spring:message code="actor.gyms" />:
		</form:label>
		<form:select id="gyms" path="gyms">
			<form:option value="0" label="----" />		
			<form:options items="${gyms}" itemValue="id" itemLabel="name" />
		</form:select>
		<%-- <form:errors cssClass="error" path="reviewer" /> --%>
		<br />
		<security:authorize access="hasRole('MANAGER')">
			<%-- Boton de a�adir, lista para ver, boton para borrar --%>
		</security:authorize>
	</security:authorize>
	
	
	<input type="submit" name="save"
		value="<spring:message code="actor.save" />" />&nbsp; 
	
	<jstl:if test="${actor.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="actor.delete" />"
			onclick="return confirm('<spring:message code="actor.confirm.delete" />')" />&nbsp;
	</jstl:if>
	
	<input type="button" name="cancel"
		value="<spring:message code="actor.cancel" />"
		onclick="javascript: relativeRedir('announcement/administrator/list.do');" />
	<br />

</form:form>