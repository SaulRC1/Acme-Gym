<%--
 * list.jsp
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

<display:table name="admins" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<!-- Edit column -->
	<security:authorize access="hasRole('MANAGER')">
		<display:column>
			<a href="gym/edit.do?gymId=${row.id}"> <spring:message
					code="manager.edit" /></a>
		</display:column>
	</security:authorize>


	<!-- Actors generic columns -->
	<display:column property="firstName" titleKey="actor.firstName" />
	<display:column property="lastName" titleKey="actor.lastName" />
	<display:column property="address" titleKey="actor.address" />
	<display:column property="email" titleKey="actor.email" />
	<display:column property="phoneNumber" titleKey="actor.phoneNumber" />
	<display:column property="postalCode" titleKey="actor.postalCode" />
	<display:column property="city" titleKey="actor.city" />
	<display:column property="country" titleKey="actor.country" />

</display:table>
