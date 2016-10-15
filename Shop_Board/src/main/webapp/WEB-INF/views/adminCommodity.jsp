<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row-fluid">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="">
				<ul class="nav navbar-nav">
					<li><a href="/admin/city">City</a></li>
					<li><a href="/admin/category">Category</a></li>
					<li class="active"><a href="/admin/commodity">Commodity</a><span
						class="sr-only">(current)</span></li>
					<li><a href="/admin/user">Users</a></li>
					<li><a href="/admin/send">Send email</a></li>
					<li><a href="/">Home</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div>
	<div class="col-md-3 col-xs-12">
		<div class="col-md-12 col-xs-12">
			<form:form action="/admin/commodity" class="form-inline" method="get"
				modelAttribute="filter">
				<custom:hiddenInputs excludeParams="search" />
				<div>
					<form:input type="text" path="search" placeholder="search" class="form-control" />
					<label></label>
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
			</form:form>
		</div>
	</div>
	<form:form action="/admin/commodity" method="post" modelAttribute="form"
		class="form-inline" enctype="multipart/form-data">
		<form:errors path="*" />
		<form:hidden path="id" />
		<form:hidden path="path" />
		<form:hidden path="version" />
		<custom:hiddenInputs
			excludeParams="id, title, price, count, discription, path, version, category, user" />
		<div class="form-group">
			<form:select path="category" items="${categories}" itemLabel="name"
				itemValue="id" class="form-control">
				<option value="0">Category</option>
			</form:select>
			<form:select path="user" items="${users}" itemLabel="login"
				itemValue="id" class="form-control">
				<option value="0">User</option>
			</form:select>
			<label for="title"><form:errors path="title" /></label>
			<form:input path="title" id="title" class="form-control"
				placeholder="Title" />
			<form:input path="price" id="price" class="form-control"
				placeholder="Price" />
			<form:input path="count" id="count" class="form-control"
				placeholder="Count" /><br><br>
			<form:textarea path="discription" id="discription" class="form-control"
				rows="1" cols="116" placeholder="Discription" /><br><br>
			<label class="btn btn-default btn-file" style="background: #337ab7; color: snow;"> Browse <input
				type="file" name="file" style="display: none;">
			</label>
			<button type="submit" class="btn btn-primary">Create Commodity</button>
		</div>
	</form:form>
	<div class="row">
		<div class="col-sm-3">
			<h4>Image</h4>
		</div>
		<div class="col-sm-3">
			<h4>Commodity Title</h4>
		</div>
		<div class="col-sm-3">
			<h4>Delete</h4>
		</div>
		<div class="col-sm-3">
			<h4>Update</h4>
		</div>
	</div>
	<c:forEach items="${page.content}" var="commodity">
		<div class="row">
			<div class="col-sm-3">
				<img class="img-thumbnail" width="200"
					src="/images/commodity/${commodity.id}${commodity.path}?version=${commodity.version}" />
			</div>
			<div class="col-sm-3">
				<p>${commodity.title}</p>
			</div>
			<div class="col-sm-3">
				<h4>
					<a href="/admin/commodity/delete/${commodity.id}<custom:allParams/>">delete</a>
				</h4>
			</div>
			<div class="col-sm-3">
				<h4>
					<a href="/admin/commodity/update/${commodity.id}<custom:allParams/>">update</a>
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
				<custom:sort innerHtml="Title asc" paramValue="title" />
				<custom:sort innerHtml="Title desc" paramValue="title,desc" />
			</ul>
		</div>
	</div>
	<div class="col-md-6">
		<custom:size posibleSizes="1,2,5,10" size="${page.size}"
			title="Page size" />
	</div>
</div>
</div>
