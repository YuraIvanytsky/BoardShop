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
					<li><a href="/admin/city">City</a></li>
					<li><a href="/admin/category">Category</a></li>
					<li><a href="/admin/commodity">Commodity</a></li>
					<li><a href="/admin/user">Users</a></li>
					<li class="active"><a href="/admin/send">Send email</a><span
						class="sr-only">(current)</span></li>
					<li><a href="/">Home</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div style="width: 40%; margin: 0 auto; float: left;">
		<form action="/admin/send" method="post">
			<div class="form-group">
				<input type="text" name="content" placeholder="content"
					class="form-control">
			</div>
			<div class="form-group">
				<select name="mail" class="form-control">
					<option>user</option>
					<c:forEach items="${users}" var="user">
						<option value="${user.mail}">${user.login}:____${user.mail}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<textarea rows="10" cols="45" name="mailBody" placeholder="message"
					class="form-control"></textarea>
			</div>
			<div>
				<input type="submit" class="btn btn-primary" value="Send">
			</div>
		</form>
	</div>
</div>