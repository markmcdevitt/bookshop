<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="script.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
<style>
body {
	font-family: helvetica, arial, verdana, sans-serif;
}

label {
	display: block;
	font-size: .8em;
}

.rating {
	overflow: hidden;
	display: inline-block;
	font-size: 0;
	position: relative;
}

.rating-input {
	float: right;
	width: 16px;
	height: 16px;
	padding: 0;
	margin: 0 0 0 -16px;
	opacity: 0;
}

.rating:hover .rating-star:hover, .rating:hover .rating-star:hover ~
	.rating-star, .rating-input:checked ~ .rating-star {
	background-position: 0 0;
}

.rating-star, .rating:hover .rating-star {
	position: relative;
	float: right;
	display: block;
	width: 16px;
	height: 16px;
	background: url('http://kubyshkin.ru/samples/star-rating/star.png') 0
		-16px;
}

body {
	margin: 20px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<c:forEach var="book" items="${book}">

		<div align="right">
			<form
				action="${pageContext.request.contextPath}/addtoshoppingcart/${book.title}"
				method="post">
				Number Of books:
				<input type='button' value='-' class='qtyminus' field='quantity' />
				<input type='text' name='quantity' value='0' class='qty'
					style="width: 20px" /> <input type='button' value='+'
					class='qtyplus' field='quantity' />
				<button type="submit" class="btn btn-primary btn-lg">Add To Cart</button>
			</form>
		</div>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<form action="${pageContext.request.contextPath}/editStock/${book.title}"
			method="post">
			<input type='button' value='-' class='qtyminus' field='quantity' />
			<input type='text' name='quantity' value='0' class='qty'
				style="width: 20px" /> <input type='button' value='+'
				class='qtyplus' field='quantity' />
			<button type="submit" class="btn btn-primary btn-lg">Edit Stock</button>	
		</form>
	</sec:authorize>
	<tr>
		<td><h3 align="center">
				<c:out value="${book.title}"></c:out>
			</h3></td>
	</tr>
</c:forEach>
<body>
	<c:forEach var="book" items="${book}">
		<center>
			<img src="${book.image}">
		</center>
	</c:forEach>
	<c:forEach var="book" items="${book}">
		<h4>
			Stock:
			<c:out value="${book.stock}"></c:out>
		</h4>
	</c:forEach>


	<table class="table table-bordered table-striped">
		<tr>
			<td><b>Author</b></td>
			<td><b>Category</b></td>
			<td><b>Price</b></td>
			<td><b>Rating</b></td>
		</tr>

		<c:forEach var="book" items="${book}">
			<tr>
				<td><c:out value="${book.author}"></c:out></td>
				<td><c:out value="${book.category}"></c:out></td>
				<td><c:out value="${book.price}"></c:out></td>
				<td><span class="stars"><span><c:out
								value="${book.totalRating}"></c:out></span></span></td>
			</tr>
		</c:forEach>
	</table>

	<c:forEach var="book" items="${book}">
		<c:if test="${book.review.size()>0}" var='0'>
			<h4>Reviews</h4>
		</c:if>
	</c:forEach>

	<table class="table table-bordered table-striped">
		<c:forEach var="book" items="${book}">
			<c:if test="${empty book.review}" var='0'>
				<p>There are no reviews yet</p>
				<p>Be the first!</p>
			</c:if>
		</c:forEach>
		<c:forEach var="book" items="${book}">
			<c:if test="${book.review.size()>0}" var='0'>
				<tr>
					<td width="50"><b>Users Rating</b></td>
					<td><b>Message</b></td>
					<td><b>Reviewed By</b></td>
				</tr>
				<c:forEach var="review" items="${book.review}">
					<tr>
						<td><span class="stars"><span><c:out
										value="${review.rating}"></c:out></span></span></td>
						<td><c:out value="${review.message}"></c:out></td>

						<td><c:out value="${review.user.username}"></c:out></td>

					</tr>
				</c:forEach>
			</c:if>
		</c:forEach>
	</table>
	<c:forEach var="book" items="${book}">
		<form id="form2"
			action="${pageContext.request.contextPath}/createreview/${book.title}"
			method="post">
			<div class="form-group">
				<label for="comment">Your Review:</label>
				<textarea name="message" class="form-control" rows="5" id="comment"></textarea>

				<button type="button" class="btn btn-info btn-lg"
					data-toggle="modal" data-target="#myModal">Submit Review</button>

			</div>

			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Give The Book A Rating</h4>
						</div>
						<div class="modal-body">
							<span class="rating"> <input type="radio"
								class="rating-input" id="rating-input-1-5" name="rating-input-1"
								value="5"> <label for="rating-input-1-5"
								class="rating-star"></label> <input type="radio"
								class="rating-input" id="rating-input-1-4" name="rating-input-1"
								value="4"> <label for="rating-input-1-4"
								class="rating-star"></label> <input type="radio"
								class="rating-input" id="rating-input-1-3" name="rating-input-1"
								value="3"> <label for="rating-input-1-3"
								class="rating-star"></label> <input type="radio"
								class="rating-input" id="rating-input-1-2" name="rating-input-1"
								value="2"> <label for="rating-input-1-2"
								class="rating-star"></label> <input type="radio"
								class="rating-input" id="rating-input-1-1" name="rating-input-1"
								value="1"> <label for="rating-input-1-1"
								class="rating-star"></label>
							</span>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary btn-lg">Submit</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:forEach>

	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$.fn.stars = function() {
			return this.each(function(i, e) {
				$(e).html($('<span/>').width($(e).text() * 16));
			});
		};
		$('.stars').stars();
	</script>
	<script>
		jQuery(document)
				.ready(
						function() {
							// This button will increment the value
							$('.qtyplus')
									.click(
											function(e) {
												// Stop acting like a button
												e.preventDefault();
												// Get the field name
												fieldName = $(this).attr(
														'field');
												// Get its current value
												var currentVal = parseInt($(
														'input[name='
																+ fieldName
																+ ']').val());
												// If is not undefined
												if (!isNaN(currentVal)) {
													// Increment
													$(
															'input[name='
																	+ fieldName
																	+ ']').val(
															currentVal + 1);
												} else {
													// Otherwise put a 0 there
													$(
															'input[name='
																	+ fieldName
																	+ ']').val(
															0);
												}
											});
							// This button will decrement the value till 0
							$(".qtyminus")
									.click(
											function(e) {
												// Stop acting like a button
												e.preventDefault();
												// Get the field name
												fieldName = $(this).attr(
														'field');
												// Get its current value
												var currentVal = parseInt($(
														'input[name='
																+ fieldName
																+ ']').val());
												// If it isn't undefined or its greater than 0
												if (!isNaN(currentVal)
														&& currentVal > 0) {
													// Decrement one
													$(
															'input[name='
																	+ fieldName
																	+ ']').val(
															currentVal - 1);
												} else {
													// Otherwise put a 0 there
													$(
															'input[name='
																	+ fieldName
																	+ ']').val(
															0);
												}
											});
						});
	</script>
</body>
</html>