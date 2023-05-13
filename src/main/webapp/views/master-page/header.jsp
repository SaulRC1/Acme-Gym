<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/logo.png" alt="Acme Gym Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Admin dropdown -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message code="master.page.admin" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="admin/list.do"><spring:message
								code="master.page.admin.list" /></a></li>
					<li><a href="admin/create.do"><spring:message
								code="master.page.admin.create" /></a></li>
				</ul></li>
		</security:authorize>

		<!-- Client dropdown -->
		<security:authorize access="">
			<li><a class="fNiv"><spring:message
						code="master.page.client" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="client/list.do"><spring:message
								code="master.page.client.list" /></a></li>
					<li><a href="client/create.do"><spring:message
								code="master.page.client.create" /></a></li>
				</ul></li>
		</security:authorize>

		<!-- Manager dropdown -->
		<security:authorize access="">
			<li><a class="fNiv"><spring:message
						code="master.page.manager" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="manager/list.do"><spring:message
								code="master.page.manager.list" /></a></li>
					<li><a href="manager/create.do"><spring:message
								code="master.page.manager.create" /></a></li>
				</ul></li>
		</security:authorize>

		<!-- Manager dropdown -->
		<security:authorize access="">
			<li><a class="fNiv"><spring:message
						code="master.page.manager" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="manager/list.do"><spring:message
								code="master.page.manager.list" /></a></li>
					<li><a href="manager/create.do"><spring:message
								code="master.page.manager.create" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<security:authorize access="hasRole('ADMIN')">
						<li><a
							href="admin/editProfile.do?userAccountId=<security:authentication property="principal.id" />">
								<spring:message code="master.page.profile.edit" />
						</a></li>
					</security:authorize>
					<security:authorize access="hasRole('CLIENT')">
						<li><a
							href="client/editProfile.do?<security:authentication property="principal.id" />">
								<spring:message code="master.page.profile.edit" />
						</a></li>
					</security:authorize>
					<security:authorize access="hasRole('MANAGER')">
						<li><a
							href="manager/editProfile.do?<security:authentication property="principal.id" />">
								<spring:message code="master.page.profile.edit" />
						</a></li>
					</security:authorize>
					<security:authorize access="hasRole('TRAINER')">
						<li><a
							href="trainer/editProfile.do?<security:authentication property="principal.id" />">
								<spring:message code="master.page.profile.edit" />
						</a></li>
					</security:authorize>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

