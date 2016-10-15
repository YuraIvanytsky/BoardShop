<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="resources/css/Style.css">
<nav class="navbar navbar-default navbar-fixed-top"
	style="background: -webkit-linear-gradient(top, rgba(183, 222, 237, 1) 0%, rgba(113, 206, 239, 1) 50%, rgba(33, 180, 226, 1) 51%, rgba(183, 222, 237, 1) 100%);">
	<div class="container">
		<ul class="nav navbar-nav navbar-left">
			<li><a class="btn btn-default" href="/">Home</a></li>
			<security:authorize
				access="isAuthenticated() and hasRole('ROLE_ADMIN')">
				<li><a class="btn btn-default" href="/admin">Admin panel</a></li>
			</security:authorize>
			<security:authorize
				access="isAuthenticated() and hasRole('ROLE_USER')">
				<li><a class="btn btn-default" href="/user">User panel</a></li>
			</security:authorize>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<%--     <security:authentication property="principal.password"/> --%>
			<li><a><b><b>${authUser.login}</b></b></a></li>
			<security:authorize access="isAuthenticated()">
				<li><form:form action="logout" method="post"
						class="navbar-form navbar-right">
						<button type="submit" class="btn btn-default">Logout</button>
					</form:form></li>
			</security:authorize>
			<security:authorize access="!isAuthenticated()">
				<li><a class="btn btn-default" href="/login">Login</a></li>
			</security:authorize>
			<security:authorize access="!isAuthenticated()">
				<li><a class="btn btn-default" href="/registration">Register</a></li>
			</security:authorize>
		</ul>
	</div>
</nav>
<div><br></div>
<div class="col-md-3 col-xs-12" style="margin-left: 0.5%;">
	<div class="col-md-12 col-xs-12">
		<form:form action="/" class="form-inline" method="get" modelAttribute="filter">
			<custom:hiddenInputs excludeParams="search"/>
			<div class="form-group">
				<form:input path="search" placeholder="search" class="form-control" />
				<label></label>
				<button type="submit" class="btn btn-primary">Ok</button>
			</div>
		</form:form>
	</div>
</div>
<div class="own">
	<div class="chbox">
		<form:form action="/" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="catId, _catId"/>
					<div class="check-box2">
						<form:checkboxes items="${categories}" path="catId" itemLabel="name" itemValue="id" element="div"/>
					</div>
					<div class="check-box">
						<button type="submit" class="btn btn-primary">Ok</button>
					</div>
		</form:form>
	</div>
	<div>
		<c:forEach items="${page.content}" var="commodity">
			<div class="primary">
				<div class="imgblock">
					<a href="/user/commodity/${commodity.id}"><img src="/images/commodity/${commodity.id}${commodity.path}?version=${commodity.version}" /></a>
				</div>
				<div class="primary-right">
					<div class="textblock">
						<div class="ftitle">${commodity.title}</div>
						<div class="fsize">${commodity.price} uan</div>
						<div class="fsize">${commodity.count}</div>
					</div>
					<div class="discr">
						<p>${commodity.discription}</p>
					</div>
				</div>
			</div>
		</c:forEach>
			<div class="col-md-12 text-center">
				<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
			</div>
	</div>
	<div class="col-md-2 col-xs-12">
			<div class="col-md-6">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Title asc" paramValue="title"/>
						<custom:sort innerHtml="Title desc" paramValue="title,desc"/>
						<custom:sort innerHtml="Price asc" paramValue="price"/>
						<custom:sort innerHtml="Price desc" paramValue="price,desc"/>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="Page size"/>
			</div>
		</div>
</div>