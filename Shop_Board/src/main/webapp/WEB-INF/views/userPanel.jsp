<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<div class="row-fluid">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="">
				<ul class="nav navbar-nav">
					<security:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
						<li><a href="/user/commodity">Add commodity</a></li>
					</security:authorize>
					<li><a href="/">Home</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>