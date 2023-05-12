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

<form:form action="client/edit.do" modelAttribute="client">

	<!-- Actors generic inputs -->
	<jsp:include page="../actor/edit.jsp" />


	<!-- Clients inputs -->
	<form:label path="cardNumber">
		<spring:message code="client.card.number" />:
		</form:label>
	<form:input path="cardNumber" />
	<form:errors cssClass="error" path="cardNumber" />
	<br />

	<form:label path="cardBrand">
		<spring:message code="client.card.brand" />:
		</form:label>
	<form:input path="cardBrand" />
	<form:errors cssClass="error" path="cardBrand" />
	<br />

	<form:label path="cardExpirationMonth">
		<spring:message code="client.card.expirationMonth" />:
		</form:label>
	<form:input path="cardExpirationMonth" />
	<form:errors cssClass="error" path="cardExpirationMonth" />
	<br />

	<form:label path="cardExpirationYear">
		<spring:message code="client.card.expirationYear" />:
		</form:label>
	<form:input path="cardExpirationYear" />
	<form:errors cssClass="error" path="cardExpirationYear" />
	<br />

	<form:label path="cardCVV">
		<spring:message code="client.card.CVV" />:
		</form:label>
	<form:input path="cardCVV" />
	<form:errors cssClass="error" path="cardCVV" />
	<br />

	<form:label path="cardHolder">
		<spring:message code="client.card.holder" />:
		</form:label>
	<form:input path="cardHolder" />
	<form:errors cssClass="error" path="cardHolder" />
	<br />

	<form:label path="gym">
		<spring:message code="client.gym" />:
		</form:label>
	<form:select id="gyms" path="gyms">
		<form:option value="0" label="----" />
		<form:options items="${gyms}" itemValue="id" itemLabel="name" />
	</form:select>


	<!-- Generic buttons -->
	<input type="submit" name="save"
		value="<spring:message code="actor.save" />" />&nbsp; 
	
	<jstl:if test="${actor.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="actor.delete" />"
			<%-- onclick="return confirm('<spring:message code="actor.confirm.delete" />')" --%> />&nbsp;
	</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="actor.cancel" />"
		<%-- onclick="javascript: relativeRedir('actor/administrator/list.do');" --%> />
	<br />

</form:form>
