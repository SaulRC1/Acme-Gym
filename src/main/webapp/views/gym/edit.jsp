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

<form:form action="gym/edit.do" modelAttribute="gym">

	<!-- Actors generic inputs -->
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="logo" />

	<form:label path="address">
		<spring:message code="gym.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />

	<form:label path="name">
		<spring:message code="gym.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="fee">
		<spring:message code="gym.fee" />:
	</form:label>
	<form:input path="fee" />
	<form:errors cssClass="error" path="fee" />
	<br />

	<form:hidden path="active" />


	<!-- Activities -->
	<div style="display: flex;">
		<display:table name="addedActivities" id="row"
			pagesize="5" class="displaytag">
			<thead>
				<tr>
					<th>TITLE</th>
					<th>REMOVE</th>
				</tr>
			</thead>
			<jstl:forEach var="addedActivity" items="${gym.activities}">
				<tr>
					<td>${activity.title}</td>
					<td><button onclick="removeActivity(activity);">Remove</button></td>
				</tr>
			</jstl:forEach>
		</display:table>
		<display:table name="allActivities" id="allActivities" pagesize="5"
			class="displaytag">
			<thead>
				<tr>
					<th>TITLE</th>
					<th>ADD</th>
				</tr>
			</thead>
			<jstl:forEach var="activity" items="${activities}">
				<tr>
					<td>${activity.title}</td>
					<td><button onclick="addActivity(activity);">Add</button></td>
				</tr>
			</jstl:forEach>
		</display:table>

	</div>


	<!-- Trainings -->



	<!-- Generic buttons -->
	<input type="submit" name="save"
		value="<spring:message code="actor.save" />" />&nbsp; 
	
	<input type="submit" name="save"
		value="<spring:message code="actor.save" />" ${gym.activate = false} />&nbsp; 
	
	<jstl:if test="${gym.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="actor.delete" />" <%-- onclick="return confirm('<spring:message code="actor.confirm.delete" />')" --%>/>&nbsp;
	</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="actor.cancel" />" onclick="javascript: relativeRedir(${cancelUrl});" />
	<br />

</form:form>

<script type="text/javascript">
		function addActivity(activity) {
			gym.activities.add(activity);
			activities.remove(activity);
		}
		function removeActivity(activity) {
			activities.add(activity);
			gym.activities.remove(activity);
		}
</script>
