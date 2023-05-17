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

<h1>ENTRENADORES DEL GIMNASIO</h1>

<display:table name="linkTrainers" id="linkTrainer" pagesize="5"
	class="displaytag">

	<!-- Trainer columns -->
	<display:column property="firstName" titleKey="actor.firstName" />
	<display:column property="lastName" titleKey="actor.lastName" />
	<display:column property="address" titleKey="actor.address" />
	<display:column property="email" titleKey="actor.email" />
	<display:column property="phoneNumber" titleKey="actor.phoneNumber" />
	<display:column property="postalCode" titleKey="actor.postalCode" />
	<display:column property="city" titleKey="actor.city" />
	<display:column property="country" titleKey="actor.country" />


	<!-- Remove column --> 
	<display:column>
		<a href="gym/unlink.do?gymId=${gymId}&trainerId=${linkTrainer.id}"> <spring:message
				code="row.unlink" /></a>
	</display:column>

</display:table>


<display:table name="unlinkTrainers" id="unlinkTrainer" pagesize="5"
	class="displaytag">

	<!-- Trainer columns -->
	<display:column property="firstName" titleKey="actor.firstName" />
	<display:column property="lastName" titleKey="actor.lastName" />
	<display:column property="address" titleKey="actor.address" />
	<display:column property="email" titleKey="actor.email" />
	<display:column property="phoneNumber" titleKey="actor.phoneNumber" />
	<display:column property="postalCode" titleKey="actor.postalCode" />
	<display:column property="city" titleKey="actor.city" />
	<display:column property="country" titleKey="actor.country" />


	<!-- Remove column --> 
	<display:column>
		<a href="gym/link.do?gymId=${gymId}&trainerId=${unlinkTrainer.id}"> <spring:message
				code="row.link" /></a>
	</display:column>

</display:table>
