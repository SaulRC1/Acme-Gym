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

<display:table name="unbannedManagers" id="manager" requestURI="manager/list.do"
	pagesize="5" class="displaytag">

	<!-- Actors generic columns -->
	<display:column property="firstName" titleKey="actor.firstName" />
	<display:column property="lastName" titleKey="actor.lastName" />
	<display:column property="address" titleKey="actor.address" />
	<display:column property="email" titleKey="actor.email" />
	<display:column property="phoneNumber" titleKey="actor.phoneNumber" />
	<display:column property="postalCode" titleKey="actor.postalCode" />
	<display:column property="city" titleKey="actor.city" />
	<display:column property="country" titleKey="actor.country" />


	<!-- Managers Gyms columns -->
	<display:column property="gyms" titleKey="gyms" />
	
	<!-- Edit column -->
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="manager/banned.do?managerId=${manager.id}"> <spring:message
					code="row.banned" /></a>
		</display:column>
	</security:authorize>

</display:table>

<display:table name="bannedManagers" id="manager" requestURI="manager/list.do"
	pagesize="5" class="displaytag">

	<!-- Actors generic columns -->
	<display:column property="firstName" titleKey="actor.firstName" />
	<display:column property="lastName" titleKey="actor.lastName" />
	<display:column property="address" titleKey="actor.address" />
	<display:column property="email" titleKey="actor.email" />
	<display:column property="phoneNumber" titleKey="actor.phoneNumber" />
	<display:column property="postalCode" titleKey="actor.postalCode" />
	<display:column property="city" titleKey="actor.city" />
	<display:column property="country" titleKey="actor.country" />
	
	<!-- Managers Gyms columns -->
	<display:column property="gyms" titleKey="gyms" />
	
	<!-- Edit column -->
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="manager/unbanned.do?managerId=${manager.id}"> <spring:message
					code="row.unbanned" /></a>
		</display:column>
	</security:authorize>

</display:table>
