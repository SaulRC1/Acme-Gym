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

<display:table name="trainings" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<!-- Edit column -->
	<security:authorize access="hasRole('MANAGER')">
		<display:column>
			<a href="training/edit.do?trainingId=${row.id}"> <spring:message
					code="manager.edit" /></a>
		</display:column>
	</security:authorize>
	
	<!-- TODO -->

</display:table>
