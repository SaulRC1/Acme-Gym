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

<display:table name="actors" id="row"
	requestURI="actor/admin,trainer,manager,client/list.do" pagesize="5"
	class="displaytag">
	
	<!-- Admin's Edit column -->
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="actor/admin/edit.do?actorId=${row.id}">
				<spring:message	code="actor.edit" />
			</a>
		</display:column>		
	</security:authorize>
	
	<!-- Actor's generic columns -->
	<display:column property="firstName" titleKey="actor.firstName" />
	<display:column property="lastName" titleKey="actor.lastName" />
	<display:column property="address" titleKey="actor.address" />
	<display:column property="email" titleKey="actor.email" />
	<display:column property="phoneNumber" titleKey="actor.phoneNumber" />
	<display:column property="postalCode" titleKey="actor.postalCode" />
	<display:column property="city" titleKey="actor.city" />
	<display:column property="country" titleKey="actor.country" />
	
	<!-- Client's Card Number column -->
	<security:authorize access="hasRole('CLIENT')">
		<display:column property="cardNumber" titleKey="actor.card.number" />		
	</security:authorize>
	
	<!-- Manager's Banned and Gyms columns -->
	<security:authorize access="hasRole('MANAGER')">
		<display:column property="banned" titleKey="actor.banned" />
		<display:column property="gyms" titleKey="actor.gyms" />		
	</security:authorize>
	 
	<!-- Trainer's Gym column -->
	<security:authorize access="hasRole('TRAINER')">
		<display:column property="gym" titleKey="actor.gym" />		
	</security:authorize>
</display:table>
