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


	<!-- Clients inputs -->
	<form:label path="creditCard.number">
		<spring:message code="client.creditCard.number" />:
		</form:label>
	<form:input path="creditCard.number" />
	<form:errors cssClass="error" path="creditCard.number" />
	<br />

	<form:label path="creditCard.brand">
		<spring:message code="client.creditCard.brand" />:
		</form:label>
	<form:input path="creditCard.brand" />
	<form:errors cssClass="error" path="creditCard.brand" />
	<br />

	<form:label path="creditCard.ExpirationMonth">
		<spring:message code="client.creditCard.expirationMonth" />:
		</form:label>
	<form:input path="creditCard.ExpirationMonth" />
	<form:errors cssClass="error" path="creditCard.ExpirationMonth" />
	<br />

	<form:label path="creditCard.ExpirationYear">
		<spring:message code="client.creditCard.expirationYear" />:
		</form:label>
	<form:input path="creditCard.ExpirationYear" />
	<form:errors cssClass="error" path="creditCard.ExpirationYear" />
	<br />

	<form:label path="creditCard.CVV">
		<spring:message code="client.creditCard.CVV" />:
		</form:label>
	<form:input path="creditCard.CVV" />
	<form:errors cssClass="error" path="creditCard.CVV" />
	<br />

	<form:label path="creditCard.Holder">
		<spring:message code="client.creditCard.holder" />:
		</form:label>
	<form:input path="creditCard.Holder" />
	<form:errors cssClass="error" path="creditCard.Holder" />
	<br />
	
	<form:hidden path="inscriptions" />

	<form:label path="activities">
		<spring:message code="client.activities" />:
		</form:label>
	<form:select id="activities" path="activities">
		<form:option value="0" label="----" />
		<form:options items="${activities}" itemValue="id" itemLabel="name" />
	</form:select>


	<!-- Generic buttons -->
	<input type="submit" name="save"
		value="<spring:message code="form.save" />" />&nbsp; 
	
	<jstl:if test="${actor.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="form.delete" />" onclick="return confirm('<spring:message code="form.confirm.delete" />')" > />&nbsp;
	</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="form.cancel" />" onclick="javascript: relativeRedir(${cancelUrl});" />
	<br />

</form:form>
