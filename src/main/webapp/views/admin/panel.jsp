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

 <table>
  <tr>
    <th><spring:message code="information.panel.minimumNumberOfGymsPerManager" /></th>
    <th><spring:message code="information.panel.maximumNumberOfGymsPerManager" /></th>
    <th><spring:message code="information.panel.averageNumberOfGymsPerManager" /></th>
    <th><spring:message code="information.panel.standardDeviationNumberOfGymsPerManager" /></th>
  </tr>
  <tr>
    <td>${minimumNumberOfGymsPerManager}</td>
    <td>${maximumNumberOfGymsPerManager}</td>
    <td>${averageNumberOfGymsPerManager}</td>
    <td>${standardDeviationNumberOfGymsPerManager}</td>
  </tr>
</table>

 <table>
  <tr>
    <th><spring:message code="information.panel.minimumNumberOfGymsPerClient" /></th>
    <th><spring:message code="information.panel.maximumNumberOfGymsPerClient" /></th>
    <th><spring:message code="information.panel.averageNumberOfGymsPerClient" /></th>
    <th><spring:message code="information.panel.standardDeviationNumberOfGymsPerClient" /></th>
  </tr>
  <tr>
    <td>${minimumNumberOfGymsPerClient}</td>
    <td>${maximumNumberOfGymsPerClient}</td>
    <td>${averageNumberOfGymsPerClient}</td>
    <td>${standardDeviationNumberOfGymsPerClient}</td>
  </tr>
</table> 
<table>
  <tr>
    <th><spring:message code="information.panel.minimumNumberOfClientsPerGym" /></th>
    <th><spring:message code="information.panel.maximumNumberOfClientsPerGym" /></th>
    <th><spring:message code="information.panel.averageNumberOfClientsPerGym" /></th>
    <th><spring:message code="information.panel.standardDeviationNumberOfClientsPerGym" /></th>
  </tr>
  <tr>
    <td>${minimumNumberOfClientsPerGym}</td>
    <td>${maximumNumberOfClientsPerGym}</td>
    <td>${averageNumberOfClientsPerGym}</td>
    <td>${standardDeviationNumberOfClientsPerGym}</td>
  </tr>
</table>

<table>
  <tr>
    <th><spring:message code="information.panel.gymWithMostNumberOfActivities" /></th>
  </tr>
  <tr>
    <td>${gymWithMostNumberOfActivities.name}</td>
  </tr>
</table>

<table>
  <tr>
    <th><spring:message code="information.panel.clientsWithMostActivities" /></th>
  </tr>
	<jstl:forEach items="${clientsWithMostActivities}" var="clients">
		<tr>
			<td>${clients.firstName}</td>
		</tr>
	</jstl:forEach>
</table>  

<table>
  <tr>
    <th><spring:message code="information.panel.minimumNumberOfTrainingsPerGym" /></th>
    <th><spring:message code="information.panel.maximumNumberOfTrainingsPerGym" /></th>
    <th><spring:message code="information.panel.averageNumberOfTrainingsPerGym" /></th>
  </tr>
  <tr>
    <td>${minimumNumberOfTrainingsPerGym}</td>
    <td>${maximumNumberOfTrainingsPerGym}</td>
    <td>${averageNumberOfTrainingsPerGym}</td>
  </tr>
</table> 

<table>
  <tr>
    <th><spring:message code="information.panel.minimumNumberOfStepsPerTraining" /></th>
    <th><spring:message code="information.panel.maximumNumberOfStepsPerTraining" /></th>
    <th><spring:message code="information.panel.averageNumberOfStepsPerTraining" /></th>
  </tr>
  <tr>
    <td>${minimumNumberOfStepsPerTraining}</td>
    <td>${maximumNumberOfStepsPerTraining}</td>
    <td>${averageNumberOfStepsPerTraining}</td>
  </tr>
</table>

<table>
  <tr>
    <th><spring:message code="information.panel.listWithBestValuedTrainings" /></th>
  </tr>
  <jstl:forEach items="${listWithBestValuedTrainings}" var="training">
	<tr>
    	<td>${training.title}</td>
  	</tr>
  </jstl:forEach>
</table>