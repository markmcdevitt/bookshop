<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<table class="table table-bordered table-striped">
	<tr>
		<td><b>Quantity</b></td>
		<td><b>Book</b></td>
		<td><b>Price</b></td>
	</tr>
	<c:forEach var="user" items="${user}">
		<c:forEach var="lineItem" items="${user.shoppingCart.lineItem}">
			<tr>
				<td><c:out value='${lineItem.quantity}'></c:out></td>
				<td><c:out value="${lineItem.book.title}"></c:out></td>
				<td><c:out value="${lineItem.book.price}"></c:out></td>
			</tr>
		</c:forEach>
	</c:forEach>
</table>
<table class="table table-bordered table-striped">
	<tr>
		<td><b>Card Holders Name</b></td>
		<td><b>Card Number</b></td>
		<td><b>Expiry date</b></td>
		<td><b>Total Price After Tax</b></td>

	</tr>
	<c:forEach var="user" items="${user}">
		<tr>
			<td><c:out value='${user.card.cardHolder}'></c:out></td>
			<td><c:out value='${user.card.cardNumber}'></c:out></td>
			<td><c:out
					value='${user.card.expiryMonth}/${user.card.expiryYear}'></c:out></td>
			<td><c:out value='${user.shoppingCart.totalCost}'></c:out></td>
		</tr>
	</c:forEach>
</table>
<div>
	<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
		data-target="#myModal">Open Modal</button>
</div>


<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">

				<c:forEach var="savedAddress" items="${savedAddress}" begin="1" >
					<fieldset>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<legend>Confirm Address</legend>
						<div class="form-group">
							<label class="col-sm-3 control-label"><c:out
									value="${savedAddress}"></c:out></label>		
						</div>
						<div class="form-group">
							<form class="form-horizontal" action="${pageContext.request.contextPath}/newaddress"
								method="post">
								<div class="modal-footer">
									<button type="submit" class="btn btn-primary btn-lg">Save New Shipping Address</button>
								</div>
							</form>
							<form class="form-horizontal" action="${pageContext.request.contextPath}/restoreaddress"
								method="post">
								<div class="modal-footer">
									<button type="submit" class="btn btn-primary btn-lg">Restore Shipping Address</button>
								</div>
							</form>
						</div>
					</fieldset>
				</c:forEach>
			</div>
		</div>
	</div>
</div>


