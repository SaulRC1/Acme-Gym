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

<display:table name="clients" id="row"
	requestURI="client/list.do" pagesize="5"
	class="displaytag">

	<!-- Edit column -->
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="admin/edit.do?adminId=${row.id}"> <spring:message
					code="admin.edit" /></a>
		</display:column>
	</security:authorize>


	<!-- Actors generic columns -->
	<jsp:include page="../actor/list.jsp" />


	<!-- Clients Card Number column -->
	<display:column property="cardNumber" titleKey="client.card.number" />

</display:table>
