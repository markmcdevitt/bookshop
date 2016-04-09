<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="script.js"></script>
<table class="table table-bordered table-striped">
	<tr>
		<td><b><a
				href="${pageContext.request.contextPath}/books/alphabetical">Title</a></b></td>
		<td><b><a
				href="${pageContext.request.contextPath}/author/alphabetical">Author</a></b></td>
		<td><b>Category</b></td>
		<td><b><a
				href="${pageContext.request.contextPath}/rating/alphabetical">Rating</a></b></td>
	</tr>

	<c:forEach var="books" items="${books}">
		<tr>
			<td><a
				href="${pageContext.request.contextPath}/book/${books.title}"><c:out
						value="${books.title}"></c:out></a></td>
			<td><c:out value="${books.author}"></c:out></td>
			<td><c:out value="${books.category}"></c:out></td>
			<td><span class="stars"><span><c:out
							value="${books.totalRating}"></c:out></span></span></td>
		</tr>
	</c:forEach>
</table>

<script type="text/javascript">
	$.fn.stars = function() {
		return this.each(function(i, e) {
			$(e).html($('<span/>').width($(e).text() * 16));
		});
	};
	$('.stars').stars();
</script>