<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/bootstrap-3.3.6-dist/css/bootstrap.min.css">
	
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="<c:url value='/'/>">PaddyBookShop</a>
		</div>

		<ul class="nav navbar-nav">
			<li><a href="${pageContext.request.contextPath}/allbooks">Show
					All Books</a></li>

			<sec:authorize access="!isAuthenticated()">
				<li><a href="${pageContext.request.contextPath}/newaccount">Register</a></li>
			</sec:authorize>

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="<c:url value='/admin'/>">Admin User Page</a></li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="<c:url value='/allbooks'/>">Admin Book Page</a></li>
			</sec:authorize>
		</ul>

		<sec:authorize access="!isAuthenticated()">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value='/login'/>"><span
						class="glyphicon glyphicon-log-in"></span>Log in</a></li>
			</ul>
		</sec:authorize>

		<div class="col-sm-3 col-md-3 pull-right">
			<form class="navbar-form"
				action="${pageContext.request.contextPath}/search" role="search">
				<select name="type">
					<option value="author">Author</option>
					<option value="title">Title</option>
					<option value="category">Category</option>
				</select>
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search"
						name="search">
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form>
		</div>

		<sec:authorize access="isAuthenticated()">
			<ul class="nav navbar-nav navbar-right ">
				<li><a href="<c:url value='/j_spring_security_logout'/>"><span
						class="glyphicon glyphicon-log-in"></span>Log out</a></li>
			</ul>
		</sec:authorize>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="${pageContext.request.contextPath}/shoppingcart"
				role="button"> <span class="glyphicon glyphicon-shopping-cart">
				</span>Shopping Cart
			</a></li>
		</ul>
	</div>
</nav>