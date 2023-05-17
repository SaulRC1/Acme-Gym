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

<h1>ACTIVIDADES DEL GIMNASIO</h1>

<display:table name="activedActivities" id="activedActivity" pagesize="5"
	class="displaytag">

	<!-- Activity columns -->
	<display:column property="title" titleKey="activity.title" />
	<display:column property="photo" titleKey="activity.photo" />
	<display:column property="description" titleKey="activity.description" />
	<display:column property="daysOfWeek" titleKey="activity.daysOfWeek" />
	<display:column property="startHour" titleKey="activity.startHour" />
	<display:column property="endHour" titleKey="activity.endHour" />
	<display:column property="availablePlaces"
		titleKey="activity.availablePlaces" />


	<!-- Remove column -->
	<display:column>
		<a href="gym/unactivateActivity.do?gymId=${gymId}&activityId=${activedActivity.id}"> <spring:message
				code="row.unactivate" /></a>
	</display:column>

</display:table>

<p>ACTIVIDADES DISPONIBLES</p>

<display:table name="unactivedActivities" id="unactivedActivity" pagesize="5"
	class="displaytag">

	<!-- Activity columns -->
	<display:column property="title" titleKey="activity.title" />
	<display:column property="photo" titleKey="activity.photo" />
	<display:column property="description" titleKey="activity.description" />
	<display:column property="daysOfWeek" titleKey="activity.daysOfWeek" />
	<display:column property="startHour" titleKey="activity.startHour" />
	<display:column property="endHour" titleKey="activity.endHour" />
	<display:column property="availablePlaces"
		titleKey="activity.availablePlaces" />

	<!-- Activity activity -->
	<display:column>
		<a href="gym/activateActivity.do?gymId=${gymId}&activityId=${unactivedActivity.id}"> <spring:message
				code="row.activate" /></a>
	</display:column>

</display:table>
