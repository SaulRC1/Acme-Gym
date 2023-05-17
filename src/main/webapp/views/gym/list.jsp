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

<display:table name="activedGyms" id="activedGym"
	requestURI="${requestURI}" pagesize="5" class="displaytag">
	<display:column titleKey="gym.logo">
		<img src="${activedGym.logo}" alt="Italian Trulli" width="100">
	</display:column>

	<display:column property="name" titleKey="gym.name" />

	<display:column property="address" titleKey="gym.address" />
	<display:column property="fee" titleKey="gym.fee" />

	<display:column property="active" titleKey="gym.active" />


	<security:authorize access="hasRole('CLIENT')">
		<display:column titleKey="activities">
			<a href="activity/listByGymId.do?gymId=${activedGym.id}"> <spring:message
					code="activities" /></a>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('CLIENT')">
		<display:column titleKey="gym.enroll">
			<jstl:choose>
				<jstl:when test="${isEnrolled}">
					<jstl:if test="${enrolledGym.id == activedGym.id}">
						<a
							href="gym/unsubscribe.do?userAccountId=<security:authentication property="principal.id" />">
							<spring:message code="gym.unsubscribe" />
						</a>
					</jstl:if>
				</jstl:when>
				<jstl:otherwise>
					<a
						href="gym/enroll.do?userAccountId=<security:authentication property="principal.id" />&gymId=${activedGym.id}">
						<spring:message code="gym.enroll" />
					</a>
				</jstl:otherwise>
			</jstl:choose>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('MANAGER')">
		<!--  Edit column -->
		<display:column titleKey="gym.manageAcitivities">
			<a href="gym/manageActivities.do?gymId=${activedGym.id}">${name}<spring:message
					code="gym.manageAcitivities" />
			</a>
		</display:column>

		<display:column titleKey="gym.manageTrainings">
			<a href="gym/manageTrainings.do?gymId=${activedGym.id}">${name}<spring:message
					code="gym.manageTrainings" />
			</a>
		</display:column>

		<display:column titleKey="gym.manageTrainers">
			<a href="gym/manageTrainers.do?gymId=${activedGym.id}">${name}<spring:message
					code="gym.manageTrainers" /></a>
		</display:column>


		<display:column titleKey="gym.cancel">
			<a href="gym/cancelGym.do?gymId=${activedGym.id}&managerId=${activedGym.managers}"> <spring:message
					code="gym.cancel" />
			</a>
		</display:column>
	</security:authorize>
</display:table>

<!-- ROL MANAGER -->

<security:authorize access="hasRole('MANAGER')">
	<display:table name="unactivedGyms" id="unactivedGym"
		requestURI="${requestURI}" pagesize="5" class="displaytag">
		<display:column titleKey="gym.logo">
			<img src="${unactivedGym.logo}" alt="Italian Trulli" width="100">
		</display:column>

		<display:column property="name" titleKey="gym.name" />

		<display:column property="address" titleKey="gym.address" />
		<display:column property="fee" titleKey="gym.fee" />

		<display:column property="active" titleKey="gym.active" />

		<display:column titleKey="activities">
			<a href="activity/listByGymId.do?gymId=${unactivedGym.id}"> <spring:message
					code="activities" /></a>
		</display:column>

		<!--  Manage activities column -->
		<display:column titleKey="gym.manageAcitivities">
			<a href="gym/manageActivities.do?gymId=${unactivedGym.id}">${name}<spring:message
					code="gym.manageAcitivities" />
			</a>
		</display:column>

		<display:column titleKey="gym.manageTrainers">
			<a
				href="gym/manageTrainers.do?userAccountId=<security:authentication property="principal.id" />"><spring:message
					code="gym.manageTrainers" /></a>
		</display:column>

		<!--  Activate activities column -->
		<display:column titleKey="gym.active">
			<a href="gym/activateGym.do?gymId=${unactivedGym.id}"> <spring:message
					code="gym.activate" />
			</a>
		</display:column>

	</display:table>
</security:authorize>
