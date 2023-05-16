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

<display:table name="gyms" id="gym" requestURI="${requestURI}"
	pagesize="5" class="displaytag">-->

	<!--  Edit column -->
	<security:authorize access="hasRole('MANAGER')">
		<display:column>
			<a href="gym/edit.do?gymId=${gym.id}"> <spring:message
					code="row.edit" /></a>
		</display:column>
	</security:authorize>
	
	<display:column titleKey="gym.logo">
		<img src="${gym.logo}" alt="Italian Trulli" width="100">
	</display:column>
	<display:column property="name" titleKey="gym.name" />
	<display:column property="address" titleKey="gym.address" />
	<display:column property="fee" titleKey="gym.fee" />
	
	<display:column>
			<a href="activity/listByGymId.do?gymId=${gym.id}"> <spring:message
					code="activities" /></a>
	</display:column>
	
	<display:column>
			<a href="gym/cancelGym.do?gymId=${gym.id}"> <spring:message
					code="cancel" /> </a>
	</display:column>
	
</display:table>
