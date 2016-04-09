<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form
	action="${pageContext.request.contextPath}/editStock/${book.title}"
	method="post">
	<table class="table table-bordered table-striped">
		<tr>
			<td><b>Title</b></td>
			<td><b>Author</b></td>
			<td><b>Category</b></td>
			<td><b>Stock</b></td>
		</tr>
		<c:forEach var="books" items="${books}">

			<tr>
				<td><a
					href="${pageContext.request.contextPath}/book/${books.title}">
						<c:out value="${books.title}"></c:out>
				</a></td>
				<td><c:out value="${books.author}"></c:out></td>
				<td><c:out value="${books.category}"></c:out></td>
				<td><input type='button' value='-' class='qtyminus'
					field='quantity' /> <input type='text' name='quantity' value='0'
					class='qty' style="width: 20px" /> <input type='button' value='+'
					class='qtyplus' field='quantity' /></td>
			</tr>
		</c:forEach>
	</table>
	<button type="submit" class="btn btn-primary btn-lg">Submit</button>
</form>
<script>
	<script>
	jQuery(document).ready(
			function() {
				$('.qtyplus').click(
						function(e) {
							e.preventDefault();
							fieldName = $(this).attr('field');
							var currentVal = parseInt($(
									'input[name=' + fieldName + ']').val());
							if (!isNaN(currentVal)) {

								$('input[name=' + fieldName + ']').val(
										currentVal + 1);
							} else {

								$('input[name=' + fieldName + ']').val(0);
							}
						});
				$(".qtyminus").click(
						function(e) {
							e.preventDefault();
							fieldName = $(this).attr('field');
							var currentVal = parseInt($(
									'input[name=' + fieldName + ']').val());
							if (!isNaN(currentVal) && currentVal > 0) {
								$('input[name=' + fieldName + ']').val(
										currentVal - 1);
							} else {
								$('input[name=' + fieldName + ']').val(0);
							}
						});
			});
</script>