<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="resources/css/Style.css">
<div class="row-fluid">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="">
				<ul class="nav navbar-nav">
					<li><a href="/admin/city">City</a></li>
					<li><a href="/admin/category">Category</a></li>
					<li><a href="/admin/commodity">Commodity</a></li>
					<li class="active"><a href="/admin/user">Users</a><span
						class="sr-only">(current)</span></li>
					<li><a href="/admin/send">Send email</a></li>
					<li><a href="/">Home</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div>
	<form:form action="/admin/user" class="form-inline" method="get"
				modelAttribute="filter">
				<custom:hiddenInputs excludeParams="search" />
				<div class="form-group">
					<form:input path="search" placeholder="search" class="form-control" />
					<label></label>
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
	</form:form>
	<div><br></div>
	<form:form action="/admin/user" method="post" modelAttribute="form" class="form-inline" enctype="multipart/form-data">
		<form:errors path="*"/>
		<form:hidden path="id" />
		<custom:hiddenInputs excludeParams="id, firstName, lastName, login, password, mail, telephoneNumber, city"/>
			<div class="form-group">
			<form:select path="city" items="${cities}" itemLabel="name" itemValue="id" class="form-control">
				<option value="0">City</option>
			</form:select><br><br>
			<form:input path="firstName" id="firstName" class="form-control"  placeholder="FirstName" />
			<form:input path="lastName" id="lastName" class="form-control" placeholder="LastName" />
			<form:input path="login" id="login" class="form-control" placeholder="Login" />
			<form:input type="password" path="password" id="password" class="form-control" placeholder="Password" />
			<form:input path="mail" id="mail" class="form-control" placeholder="Mail" />
			<form:input path="telephoneNumber" id="telephoneNumber" class="form-control" placeholder="TelephoneNumber" />
			<button type="submit" class="btn btn-primary">Create User</button>
			</div>
	</form:form>
	<div class="row">
		<div class="col-sm-2">
			<h4>User first name</h4>
		</div>
		<div class="col-sm-2">
			<h4>User last name</h4>
		</div>
		<div class="col-sm-2">
			<h4>Login</h4>
		</div>
		<div class="col-sm-2">
			<h4>Phone number</h4>
		</div>
		<div class="col-sm-2">
			<h4>Delete</h4>
		</div>
		<div class="col-sm-2">
			<h4>Update</h4>
		</div>
	</div>
	<c:forEach items="${page.content}" var="user">
		<div class="row">
			<div class="col-sm-2">
				<h4>${user.firstName}</h4>
			</div>
			<div class="col-sm-2">
				<h4>${user.lastName}</h4>
			</div>
			<div class="col-sm-2">
				<h4>${user.login}</h4>
			</div>
			<div class="col-sm-2">
				<h4>${user.telephoneNumber}</h4>
			</div>
			<div class="col-sm-2">
				<h4>
					<a href="/admin/user/delete/${user.id}<custom:allParams/>">delete</a>
				</h4>
			</div>
			<div class="col-sm-2">
				<h4>
					<a href="/admin/user/update/${user.id}<custom:allParams/>">update</a>
				</h4>
			</div>
		</div>
	</c:forEach>
	<div class="col-md-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>"
			container="<ul class='pagination'></ul>" />
	</div>
</div>
<div class="col-md-2">
	<div class="col-md-6">
		<div class="dropdown">
			<button class="btn btn-primary dropdown-toggle" type="button"
				data-toggle="dropdown">
				Sort <span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<custom:sort innerHtml="First Name asc" paramValue="firstName" />
				<custom:sort innerHtml="First Name desc" paramValue="firstName,desc" />
				<custom:sort innerHtml="Last Name asc" paramValue="lastName" />
				<custom:sort innerHtml="Last Name desc" paramValue="lastName,desc" />
				<custom:sort innerHtml="Login name asc" paramValue="login" />
				<custom:sort innerHtml="Login desc"
					paramValue="login,desc" />
			</ul>
		</div>
	</div>
	<div class="col-md-6">
		<custom:size posibleSizes="1,2,5,10" size="${page.size}"
			title="Page size" />
	</div>
</div>