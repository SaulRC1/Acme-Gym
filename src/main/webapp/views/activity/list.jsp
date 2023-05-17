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

<div>
	<input id="keywordInput" value="${keyword}"><input
		onclick="javascript: filter();" id="filterButton" type="button"
		value="<spring:message code="form.filter" />" />
</div>

<display:table name="activities" id="activity"
	requestURI="${requestURI}" pagesize="5" class="displaytag">

	<!-- Activity columns -->
	<display:column property="title" titleKey="activity.title" />
	<display:column property="photo" titleKey="activity.photo" />
	<display:column property="description" titleKey="activity.description" />
	<display:column property="daysOfWeek" titleKey="activity.daysOfWeek" />
	<display:column property="startHour" titleKey="activity.startHour" />
	<display:column property="endHour" titleKey="activity.endHour" />
	<display:column property="availablePlaces"
		titleKey="activity.availablePlaces" />
	<display:column>
		<a href="trainer/listByActivityId.do?activityId=${activity.id}"><spring:message
				code="trainers" /></a>
	</display:column>
	<display:column>
		<a href="gym/listByActivityId.do?activityId=${activity.id}"><spring:message
				code="gyms" /></a>
	</display:column>

	<security:authorize access="hasRole('CLIENT')">
		<jstl:choose>
			<jstl:when test="${enrolledActivities.contains(activity)}">
				<display:column>
					<a
						href="activity/unsubscribe.do?activityId=${activity.id}&userAccountId=<security:authentication property="principal.id" />"><spring:message
							code="activity.unsubscribe" /></a>
				</display:column>
			</jstl:when>
			<jstl:otherwise>
				<display:column>
					<a
						href="activity/enroll.do?activityId=${activity.id}&userAccountId=<security:authentication property="principal.id" />"><spring:message
							code="activity.enroll" /></a>
				</display:column>
			</jstl:otherwise>
		</jstl:choose>
	</security:authorize>

</display:table>

<script>
	function filter() {
		var keyword = document.getElementById("keywordInput").value;
		var gymId = $
		{
			gymId != null ? gymId : -1
		}
		;
		if (gymId != -1) {
			window.location.href = "activity/listByGymAndKeyword.do?gymId="
					+ gymId + "&keyword=" + keyword;
		} else {
			window.location.href = "activity/listByKeyword.do?keyword="
					+ keyword;
		}
	}
</script>
