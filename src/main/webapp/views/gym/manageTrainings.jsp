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

<h1>ENTRENAMIENTOS DEL GIMNASIO</h1>

<display:table name="trainings" id="manageTraining" pagesize="5"
	class="displaytag">

	<!-- Training columns -->
	<display:column property="title" titleKey="training.title" />
	<display:column property="description" titleKey="training.description" />

	<display:column>
		<a href="step/list.do?trainingId=${manageTraining.id}"> <spring:message
				code="training.steps" /></a>
	</display:column>


	<!-- Remove column --> 
	<display:column>
		<a href="training/delete.do?gymId=${gymId}&trainingId=${manageTraining.id}"> <spring:message
				code="row.delete" /></a>
	</display:column>

</display:table>

