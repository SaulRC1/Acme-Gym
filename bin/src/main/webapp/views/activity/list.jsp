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

<display:table name="activity" id="activity" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<!-- Activity columns -->
	<display:column property="title" titleKey="activity.title" />
	<display:column property="photo" titleKey="activity.photo" />
	<display:column property="description" titleKey="activity.description" />
	<display:column property="weekDays" titleKey="activity.weekDays" />
	<display:column property="startHour" titleKey="activity.startHour" />
	<display:column property="endHour" titleKey="activity.endHour" />
	<display:column property="availablePlaces" titleKey="activity.availablePlaces" />
	
	
	<!-- Edit column -->
	<security:authorize access="hasRole('MANAGER')">
		<display:column>
			<a href="activity/edit.do?activityId=${activity.id}"> <spring:message
					code="row.edit" /></a>
		</display:column>
	</security:authorize>

</display:table>
