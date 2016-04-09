<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty books}" var='0'>
	<p>Cannot find anything with this name</p>
</c:if>
<c:if test="${books.size()>0}" var='0'>
<h3>Results For: <c:out value="${search}" /></h3>
	<table class="table table-bordered table-striped">
		<tr>
			<td><b>Title</b></td>
			<td><b>Author</b></td>
			<td><b>Category</b></td>
		</tr>
		<c:forEach var="books" items="${books}">
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/book/${books.title}"><c:out
							value="${books.title}"></c:out></a></td>
				<td><c:out value="${books.author}"></c:out></td>
				<td><c:out value="${books.category}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</c:if>