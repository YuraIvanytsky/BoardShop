<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
	<form action="/user/commodity/${commodity.id}" method="get">
		<div style="width: 70%;
					color: black;
					float: left;
					margin-left: 25%;
					margin-top: 2%;">
			<div style="float:left">
				<img class="img-thumbnail" width="740"
					src="/images/commodity/${commodity.id}${commodity.path}?version=${commodity.version}" />
			</div>
			<div style="font-family: Arial;
						font-weight: 800;
						width: 80%;
						height:100%;
						float: left;
						text-align: center;">
				<div style="width: 100%;
							height:20%;
							float: left;">
					<div style="width: 60%;
								height:100%;
								float: left;
								border: 1px solid black;">
								${commodity.title}</div>
					<div style="width: 20%;
								height:20%%;
								float: left;
								border: 1px solid black;">
								${commodity.price} uan</div>
					<div style="width: 20%;
								height:20%%;
								float: left;
								border: 1px solid black;">
								${commodity.count}</div>
				</div>
				<div class="discr">
					<p>${commodity.discription}</p>
				</div>
			</div>
		</div>
	</form>
	
</div>